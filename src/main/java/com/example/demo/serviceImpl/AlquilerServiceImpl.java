package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alquiler;
import com.example.demo.repository.AlquilerRepository;
import com.example.demo.service.Operaciones;

@Service
public class AlquilerServiceImpl implements Operaciones<Alquiler>{
	
	@Autowired
	private AlquilerRepository alquilerRepository;

	@Override
	public Alquiler create(Alquiler t) {
		// TODO Auto-generated method stub
		return alquilerRepository.save(t);
	}

	@Override
	public Alquiler update(Alquiler t) {
		// TODO Auto-generated method stub
		return alquilerRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		alquilerRepository.deleteById(id);
	}

	@Override
	public Optional<Alquiler> read(Long id) {
		// TODO Auto-generated method stub
		return alquilerRepository.findById(id);
	}

	@Override
	public List<Alquiler> readAll() {
		// TODO Auto-generated method stub
		return alquilerRepository.findAll();
	}

}