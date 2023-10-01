package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Libro;
import com.example.demo.serviceImpl.LibroServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_LIBROS;;

@RestController
@RequestMapping(API_LIBROS)
public class LibroController {
	
	@Autowired
	private LibroServiceImpl libroServiceImpl;
	
	@GetMapping("/ListBook")
	public ResponseEntity<List<Libro>> listar() {
		try {
		      List<Libro> lib = libroServiceImpl.readAll();
		      if (lib.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(lib, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	

	
	@GetMapping("/BuscarBook/{id}")
	public ResponseEntity<Libro> getLibroById(@PathVariable("id") Long id){
		Optional<Libro> carData = libroServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Libro>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@PostMapping("/InsertBock")
    public ResponseEntity<Libro> crear(@Valid @RequestBody Libro libro){
        try {
        	Libro _alq = libroServiceImpl.create(libro);
            return new ResponseEntity<Libro>(_alq, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	@DeleteMapping("/DeleteBook/{id}")
	public ResponseEntity<Libro> delete(@PathVariable("id") Long id){
		try {
			libroServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("/EdidBook/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Libro libro){
		Optional<Libro> carData = libroServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Libro dblibro = carData.get();
	        dblibro.setTitulo(libro.getTitulo());
	        dblibro.setFechalan(libro.getFechalan());
	        dblibro.setAutor(libro.getAutor());
	        dblibro.setCategoria(libro.getCategoria());
	        dblibro.setEditorial(libro.getEditorial());
	        dblibro.setIdioma(libro.getIdioma());
	        dblibro.setPaginas(libro.getPaginas());
	        dblibro.setDescripcion(libro.getDescripcion());
	        dblibro.setDescripcion(libro.getDescripcion());	        
	        
	        return new ResponseEntity<Libro>(libroServiceImpl.update(dblibro), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}