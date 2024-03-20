package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(Model model, Principal principal) {
	    if (principal != null) {
	        model.addAttribute("username", principal.getName());
	    } else {
	        model.addAttribute("username", "Guest");
	    }
	    return "index";
	}

}
