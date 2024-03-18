package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;

@Controller
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
    
    @GetMapping("/my-board")
    public String getBoards(Model model) {
        Iterable<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board_list"; // board_list.htmlに対応するビュー名
    }
}
