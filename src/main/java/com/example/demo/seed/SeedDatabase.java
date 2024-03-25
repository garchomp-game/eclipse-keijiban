package com.example.demo.seed;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;

@Configuration
public class SeedDatabase {

    @Bean
    CommandLineRunner initDatabase(BoardRepository repository) {
        return args -> {
            if (repository.count() == 0) { // データがまだない場合のみシードを実行
            	Board board = new Board();
            	board.setAuthor("Author1");
            	board.setContent("Content1");
            	board.setTitle("Title1");
            	board.setCreatedAt(LocalDateTime.now());
            	board.setUpdatedAt(LocalDateTime.now());
            	repository.save(board);
            	
            	Board board2 = new Board();
            	board2.setAuthor("Author2");
            	board2.setContent("Content2");
            	board2.setTitle("Title2");
            	board2.setCreatedAt(LocalDateTime.now());
            	board2.setUpdatedAt(LocalDateTime.now());
            	repository.save(board2);
                // 他のシードデータもここに追加
            }
        };
    }
}
