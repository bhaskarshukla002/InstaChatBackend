package com.example.InstaChat.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name="image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    
    private String type;
    
    @Lob
    @Column(name = "data",length = 1000)
    private byte[] data;

    // Getters and Setters
}
