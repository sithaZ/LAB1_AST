package com.itc.private_cloudstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itc.private_cloudstorage.model.Folder;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findByOwnerId(Long ownerId);
}