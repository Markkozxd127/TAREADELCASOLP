package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Autor;
import com.example.demo.serviceImpl.AutorServiceImpl;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.demo.commons.GlobalConstans.API_AUTORES;

@RestController
@RequestMapping(API_AUTORES)
public class AutorController {
	@Autowired
	private AutorServiceImpl autorServiceImpl;
	
	
	@GetMapping("/ListAutores")
	public ResponseEntity<List<Autor>> listar() {
		try {
		      List<Autor> aut = autorServiceImpl.readAll();
		      if (aut.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(aut, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	@GetMapping("/BuscarAutores/{id}")
	public ResponseEntity<Autor> getAutorById(@PathVariable("id") Long id){
		Optional<Autor> carData = autorServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Autor>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/InsertAutores")
    public ResponseEntity<Autor> crear(@Valid @RequestBody Autor autor){
        try {
    
            Autor _aut = autorServiceImpl.create(autor);
            return new ResponseEntity<Autor>(_aut, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	
	@DeleteMapping("DeleteAutores/{id}")
	public ResponseEntity<Autor> delete(@PathVariable("id") Long id){
		try {
			autorServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	
	
	@PutMapping("EdidAutores/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Autor autor){
		Optional<Autor> carData = autorServiceImpl.read(id);
	      if (carData.isPresent()) {
	        Autor dbautor = carData.get();
	        dbautor.setAutor(autor.getAutor());
	        return new ResponseEntity<Autor>(autorServiceImpl.update(dbautor), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}