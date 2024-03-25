package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.BoardDTO;
import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;

import jakarta.validation.Valid;

@Controller
public class BoardController {
	@Autowired
	private BoardRepository boardRepository;

	@GetMapping("/board")
	public String getBoards(Model model) {
		Iterable<Board> boards = boardRepository.findAll();
		model.addAttribute("boards", boards);
		return "boards/index"; // boards.htmlに対応するビュー名
	}

	@GetMapping("/board/create")
	public String createBoard() {
		return "boards/create";
	}

	@PostMapping("/board")
	public String storeBoard(@Valid BoardDTO boardDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "boardForm";
		}
		return "redirect:/boards";
	}

	@GetMapping("/board/{id}")
	public String showBoard(@PathVariable("id") Long id, Model model) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("not found board id is: " + id));
		model.addAttribute("board", board);
		return "boards/show";
	}
}
