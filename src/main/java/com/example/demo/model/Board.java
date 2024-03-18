package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Board {
	@Id
	private Long id;
	
	@Column(nullable = false, length = 255)
	private String title;
	
	@Column(nullable = false, length = 4000)
	private String content;
	
    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
