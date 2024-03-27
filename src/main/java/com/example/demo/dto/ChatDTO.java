package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChatDTO {
    @NotNull
    @Size(min = 1, max = 1000)
    private String message;

    @NotNull
    private Long boardId;

    // userIDは認証情報から取得するため、ここには不要

    // Getters and Setters...
}
