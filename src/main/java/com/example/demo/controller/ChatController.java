package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.ChatDTO;
import com.example.demo.model.Chat;
import com.example.demo.model.User;
import com.example.demo.service.ChatService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/boards/{boardId}/chats")
public class ChatController {
    
    private final ChatService chatService;

    // コンストラクタでChatServiceを注入
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // チャット一覧表示
    @GetMapping
    public String listChats(@PathVariable("boardId") Long boardId, Model model) {
        List<Chat> chats = chatService.getChatsByBoardId(boardId);
        model.addAttribute("boardId", boardId);
        model.addAttribute("chats", chats);
        return "chat/index";
    }

    // チャット新規作成フォーム表示
    @GetMapping("/create")
    public String showCreateForm(@PathVariable("boardId") Long boardId, Model model) {
    	model.addAttribute("boardId", boardId);
        model.addAttribute("chatDTO", new ChatDTO());
        return "chat/create";
    }

    // チャット新規作成処理
    @PostMapping
    public String createChat(@PathVariable("boardId") Long boardId,
                             @ModelAttribute @Valid ChatDTO chatDTO,
                             BindingResult result, @AuthenticationPrincipal User user) {
        if (result.hasErrors()) {
            return "chat/create";
        }
        chatService.createChat(chatDTO, boardId, user);
        return "redirect:/boards/" + boardId + "/chats";
    }

    // チャット編集フォーム表示
    @GetMapping("/{chatId}/edit")
    public String showEditForm(@PathVariable("boardId") Long boardId,
                               @PathVariable("chatId") Long chatId, Model model) {
        ChatDTO chatDTO = chatService.getChatById(chatId);
        model.addAttribute("boardId", boardId);
        model.addAttribute("chatDTO", chatDTO);
        return "chat/edit";
    }

    // チャット更新処理
    @PostMapping("/{chatId}/edit")
    public String updateChat(@PathVariable("boardId") Long boardId,
                             @PathVariable("chatId") Long chatId,
                             @ModelAttribute @Valid ChatDTO chatDTO,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "chat/edit";
        }
        chatService.updateChat(chatDTO, chatId);
        return "redirect:/boards/" + boardId + "/chats";
    }

    // チャット削除処理
    @PostMapping("/{chatId}/delete")
    public String deleteChat(@PathVariable("boardId") Long boardId,
                             @PathVariable("chatId") Long chatId) {
        chatService.deleteChat(chatId);
        return "redirect:/boards/" + boardId + "/chats";
    }
    
    // 他のメソッド...
}
