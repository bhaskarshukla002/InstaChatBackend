package com.example.InstaChat.repositories;

import com.example.InstaChat.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDataRepository extends JpaRepository<FileData,Long> {
}
