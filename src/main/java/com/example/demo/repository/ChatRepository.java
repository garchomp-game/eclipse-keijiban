package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Chat;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    // ボードIDに基づいてチャットのリストを取得するメソッド
    List<Chat> findByBoardId(Long boardId);
}
