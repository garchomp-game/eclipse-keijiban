package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.dto.BoardDTO;
import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;

import jakarta.validation.Valid;

@Controller
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/board/create")
	public String createBoard(Model model) {
		model.addAttribute("board", new BoardDTO());
		return "boards/create";
	}

	@PostMapping("/board")
	public String storeBoard(@Valid BoardDTO boardDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "boards/create";
		}
		Board board = new Board();
		modelMapper.map(boardDTO, board);
		boardRepository.save(board);
		return "redirect:/";
	}

	@GetMapping("/board/{id}")
	public String showBoard(@PathVariable("id") Long id, Model model) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("not found board id is: " + id));
		model.addAttribute("board", board);
		return "boards/show";
	}

	@PutMapping("/board/{id}")
	public String updateBoard(@PathVariable("id") Long id, @Validated @ModelAttribute("board") BoardDTO boardDTO,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			// バリデーションエラーがあれば編集画面に戻す
			model.addAttribute("board", boardDTO);
			return "boards/edit";
		}
		// 既存のBoardエンティティを取得
		Board board = boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Board not found"));

		// DTOの値をBoardエンティティにマッピング
		modelMapper.map(boardDTO, board);

		// リポジトリを通じて更新
		boardRepository.save(board);

		// 更新後は詳細画面にリダイレクトするなど、適切な画面へ遷移させる
		return "redirect:/board/" + id;
	}

	@GetMapping("/board/{id}/edit")
	public String editBoard(@PathVariable("id") Long id, Model model) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("not found board id is: " + id));
		model.addAttribute(board);
		return "boards/edit";
	}
	
	@DeleteMapping("/board/{id}/delete")
	public String deleteBoard(@PathVariable("id") Long id, Model model) {
		// 既存のBoardエンティティを取得
		boardRepository.deleteById(id);
		return "redirect:/";
	}
}
