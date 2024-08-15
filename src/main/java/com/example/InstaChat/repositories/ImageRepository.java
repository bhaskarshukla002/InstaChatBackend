package com.example.InstaChat.repositories;

import com.example.InstaChat.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
