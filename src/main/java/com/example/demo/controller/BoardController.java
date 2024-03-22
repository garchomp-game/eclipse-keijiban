package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.BoardDTO;
import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;

import jakarta.validation.Valid;

@Controller
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
    
    @GetMapping("/boards")
    public String getBoards(Model model) {
        Iterable<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "boards/index"; // boards.htmlに対応するビュー名
    }
    
    @PostMapping("/boards")
    public String createBoard(@Valid BoardDTO boardDTO, BindingResult result) {
    	if (result.hasErrors()) {
    		return "boardForm";
    	}
    	return "redirect:/boards";
    }
}
