package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Board;

public interface BoardRepository extends CrudRepository<Board, Long>{

}
