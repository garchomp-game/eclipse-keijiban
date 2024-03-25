package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;

@Controller
public class HomeController {
	@Autowired
	BoardRepository boardRepository;

	@GetMapping("/")
	public String home(Model model, Principal principal) {
	    if (principal != null) {
	        model.addAttribute("username", principal.getName());
	    } else {
	        model.addAttribute("username", "Guest");
	    }
	    Iterable<Board> boards = boardRepository.findAll();
	    model.addAttribute("boards", boards);
	    return "index";
	}

}
