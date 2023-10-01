package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface Operaciones <T>{
	
	T create(T t);
	T update(T t);
	void delete(Long id);
	Optional<T> read(Long id);
	List<T> readAll();

}