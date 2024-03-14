package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Test;
import com.example.demo.repository.TestRepository;

@RestController
@RequestMapping("/tests")
public class TestController {
	@Autowired
	private TestRepository testRepository;
	
	@GetMapping
	public Iterable<Test> getTests() {
		return testRepository.findAll();
	}
}
