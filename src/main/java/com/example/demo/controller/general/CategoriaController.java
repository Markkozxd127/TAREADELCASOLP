package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Categoria;
import com.example.demo.serviceImpl.CategoriaServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_CATEGORIAS;;

@RestController
@RequestMapping(API_CATEGORIAS)
public class CategoriaController {
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	
	@GetMapping ("/ListCate")
	public ResponseEntity<List<Categoria>> listar() {
		try {
		      List<Categoria> cat = categoriaServiceImpl.readAll();
		      if (cat.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cat, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	

	@GetMapping("/BuscarCate/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") Long id){
		Optional<Categoria> carData = categoriaServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Categoria>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/InsertCate")
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria categoria){
        try {
        	Categoria _cat = categoriaServiceImpl.create(categoria);
            return new ResponseEntity<Categoria>(_cat, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
        
    }
	
	@DeleteMapping("/DeleteCate/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable("id") Long id){
		try {
			categoriaServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("EditarCate/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Categoria categoria){
		Optional<Categoria> carData = categoriaServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Categoria dbcategoria = carData.get();
	        dbcategoria.setCategoria(categoria.getCategoria());
	        return new ResponseEntity<Categoria>(categoriaServiceImpl.update(dbcategoria), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
