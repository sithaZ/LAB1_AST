package com.itc.private_cloudstorage.controller;

import com.itc.private_cloudstorage.dto.FolderRequest;
import com.itc.private_cloudstorage.model.Folder;
import com.itc.private_cloudstorage.model.User;
import com.itc.private_cloudstorage.repository.FolderRepository;
import com.itc.private_cloudstorage.repository.UserRepository;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    private final FolderRepository folderRepository;
    private final UserRepository userRepository;

    public FolderController(FolderRepository folderRepository, UserRepository userRepository) {
        this.folderRepository = folderRepository;
        this.userRepository = userRepository;
    }

    private User currentUser() {
        return userRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No user found"));
    }

    @PostMapping
    public Folder createFolder(@RequestBody FolderRequest request) {
        User user = currentUser();

        Folder folder = Folder.builder()
                .name(request.getName())
                .ownerId(user.getId())
                .build();

        return folderRepository.save(folder);
    }

    @PatchMapping("/{id}")
    public Folder renameFolder(
        @PathVariable Long id,
        @RequestBody FolderRequest request) {

    Folder folder = folderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Folder not found"));

    folder.setName(request.getName());

    return folderRepository.save(folder);
    }


    @DeleteMapping("/{id}")
    public String deleteFolder(@PathVariable Long id) {

    Folder folder = folderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Folder not found"));

    folderRepository.delete(folder);

    return "Folder deleted successfully";
    }
    
    @GetMapping
    public List<Folder> getFolders() {
        User user = currentUser();
        return folderRepository.findByOwnerId(user.getId());
    }
}