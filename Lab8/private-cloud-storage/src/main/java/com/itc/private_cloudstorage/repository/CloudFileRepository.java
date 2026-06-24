package com.itc.private_cloudstorage.repository;

import com.itc.private_cloudstorage.model.CloudFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CloudFileRepository extends JpaRepository<CloudFile, Long> {
    List<CloudFile> findByOwnerId(Long ownerId);
}