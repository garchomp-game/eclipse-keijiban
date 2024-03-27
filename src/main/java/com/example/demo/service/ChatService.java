package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ChatDTO;
import com.example.demo.model.Chat;
import com.example.demo.model.User;
import com.example.demo.repository.ChatRepository;

@Service
public class ChatService {
    
    private final ChatRepository chatRepository;
    private final ModelMapper modelMapper;
    
    // コンストラクタでChatRepositoryとModelMapperを注入
    @Autowired
    public ChatService(ChatRepository chatRepository, ModelMapper modelMapper) {
        this.chatRepository = chatRepository;
        this.modelMapper = modelMapper;
    }
    
    // ボードIDに基づいてチャットのリストを取得
    public List<Chat> getChatsByBoardId(Long boardId) {
        List<Chat> chats = chatRepository.findByBoardId(boardId);
        return chats.stream()
                .map(chat -> modelMapper.map(chat, Chat.class))
                .collect(Collectors.toList());
    }
    
    // 新しいチャットを作成
    public void createChat(ChatDTO chatDTO, Long boardId, User user) {
        Chat chat = modelMapper.map(chatDTO, Chat.class);
        // ここでboardIdとuserIdをセットする必要がある
        chat.setUser(user);
        chatRepository.save(chat);
    }
    
    // チャットを更新
    public void updateChat(ChatDTO chatDTO, Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found"));
        modelMapper.map(chatDTO, chat);
        chatRepository.save(chat);
    }
    
    // チャットを削除
    public void deleteChat(Long chatId) {
        chatRepository.deleteById(chatId);
    }
    
    // IDに基づいてチャットを取得
    public ChatDTO getChatById(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found"));
        return modelMapper.map(chat, ChatDTO.class);
    }
    
    // 他のメソッド...
}
