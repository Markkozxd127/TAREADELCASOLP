package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Libro;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.Operaciones;

@Service
public class LibroServiceImpl implements Operaciones<Libro>{
	
	@Autowired
	private LibroRepository libroRepository;

	@Override
	public Libro create(Libro t) {
		// TODO Auto-generated method stub
		return libroRepository.save(t);
	}

	@Override
	public Libro update(Libro t) {
		// TODO Auto-generated method stub
		return libroRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		libroRepository.deleteById(id);
	}

	@Override
	public Optional<Libro> read(Long id) {
		// TODO Auto-generated method stub
		return libroRepository.findById(id);
	}

	@Override
	public List<Libro> readAll() {
		// TODO Auto-generated method stub
		return libroRepository.findAll();
	}

}
