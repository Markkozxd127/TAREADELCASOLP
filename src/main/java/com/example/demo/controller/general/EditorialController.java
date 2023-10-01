package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Editorial;
import com.example.demo.serviceImpl.EditorialServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_EDITORIALES;;

@RestController
@RequestMapping(API_EDITORIALES)
public class EditorialController {
	@Autowired
	private EditorialServiceImpl editorialServiceImpl;
	
	
	@GetMapping("/ListEdi")
	public ResponseEntity<List<Editorial>> listar() {
		try {
		      List<Editorial> edi = editorialServiceImpl.readAll();
		      if (edi.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(edi, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	

	
	@GetMapping("/BuscarEdi/{id}")
	public ResponseEntity<Editorial> getEditorialById(@PathVariable("id") Long id){
		Optional<Editorial> carData = editorialServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Editorial>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/InsertEdi")
    public ResponseEntity<Editorial> crear(@Valid @RequestBody Editorial editorial){
        try {
        	Editorial _aut = editorialServiceImpl.create(editorial);
            return new ResponseEntity<Editorial>(_aut, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	@DeleteMapping("DeleteEdi/{id}")
	public ResponseEntity<Editorial> delete(@PathVariable("id") Long id){
		try {
			editorialServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("EdidEdi/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Editorial editorial){
		Optional<Editorial> carData = editorialServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Editorial dbeditorial = carData.get();
	    	dbeditorial.setEditorial(editorial.getEditorial());
	        return new ResponseEntity<Editorial>(editorialServiceImpl.update(dbeditorial), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}