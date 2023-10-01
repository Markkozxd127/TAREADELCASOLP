package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Lector;
import com.example.demo.serviceImpl.LectorServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_LECTORES;;

@RestController
@RequestMapping(API_LECTORES)
public class LectorController {
	@Autowired
	private LectorServiceImpl lectorServiceImpl;
	
	
	@GetMapping("/ListLect")
	public ResponseEntity<List<Lector>> listar() {
		try {
		      List<Lector> lec = lectorServiceImpl.readAll();
		      if (lec.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(lec, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	
	
	@GetMapping("BuscarLect/{id}")
	public ResponseEntity<Lector> getLectorById(@PathVariable("id") Long id){
		Optional<Lector> carData = lectorServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Lector>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/InsertLect")
    public ResponseEntity<Lector> crear(@Valid @RequestBody Lector lector){
        try {
        	Lector _lec = lectorServiceImpl.create(lector);
            return new ResponseEntity<Lector>(_lec, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	@DeleteMapping("DeleteLect/{id}")
	public ResponseEntity<Lector> delete(@PathVariable("id") Long id){
		try {
			lectorServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("EdidLect/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Lector lector){
		Optional<Lector> carData = lectorServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Lector dblector = carData.get();
	        dblector.setNombre(lector.getNombre());
	        dblector.setTelefono(lector.getTelefono());
	        dblector.setDireccion(lector.getDireccion());
	        dblector.setCodigopos(lector.getCodigopos());
	        dblector.setObserv(lector.getObserv());
	        return new ResponseEntity<Lector>(lectorServiceImpl.update(dblector), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}