package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Test;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {
}
