package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public 	class BoardDTO {

    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @NotNull
    @Size(min = 1, max = 4000)
    private String content;

    @NotNull
    @Size(min = 1, max = 50)
    private String author;

    // getters and setters

    // LocalDateTimeのフィールドはバリデーション不要かもしれません
    // 作成日時と更新日時は通常、サーバー側で自動的にセットされます
}
