package com.itc.private_cloudstorage.controller;

import com.itc.private_cloudstorage.model.CloudFile;
import com.itc.private_cloudstorage.model.User;
import com.itc.private_cloudstorage.repository.CloudFileRepository;
import com.itc.private_cloudstorage.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final CloudFileRepository fileRepository;
    private final UserRepository userRepository;

    public FileController(CloudFileRepository fileRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    private User currentUser() {
        return userRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No user found"));
    }

    @PostMapping
    public CloudFile uploadFile(@RequestParam("file") MultipartFile file) {
        User user = currentUser();

        long newUsedBytes = user.getUsedBytes() + file.getSize();

        if (newUsedBytes > user.getQuotaBytes()) {
            throw new RuntimeException("Quota exceeded");
        }

        CloudFile cloudFile = CloudFile.builder()
                .name(file.getOriginalFilename())
                .sizeBytes(file.getSize())
                .ownerId(user.getId())
                .build();

        CloudFile savedFile = fileRepository.save(cloudFile);

        user.setUsedBytes(newUsedBytes);
        userRepository.save(user);

        return savedFile;
    }

    @GetMapping
    public List<CloudFile> getFiles() {
        User user = currentUser();
        return fileRepository.findByOwnerId(user.getId());
    }

    @DeleteMapping("/{id}")
    public String deleteFile(@PathVariable Long id) {
        User user = currentUser();

        CloudFile file = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        if (!file.getOwnerId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        user.setUsedBytes(user.getUsedBytes() - file.getSizeBytes());
        userRepository.save(user);

        fileRepository.delete(file);

        return "File deleted successfully";
    }
}