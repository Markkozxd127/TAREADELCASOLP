package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Alquiler;
import com.example.demo.serviceImpl.AlquilerServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ALQUILERES;

@RestController
@RequestMapping(API_ALQUILERES)
public class AlquilerController {
	@Autowired
	private AlquilerServiceImpl alquilerServiceImpl;
	
	
	@GetMapping("/ListAlqui")
	public ResponseEntity<List<Alquiler>> listar() {
		try {
		      List<Alquiler> alq = alquilerServiceImpl.readAll();
		      if (alq.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(alq, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	
	@GetMapping("BuscarAlqui/{id}")
	public ResponseEntity<Alquiler> getAlquilerById(@PathVariable("id") Long id){
		Optional<Alquiler> carData = alquilerServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Alquiler>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@PostMapping("/InsertAlqui")
    public ResponseEntity<Alquiler> crear(@Valid @RequestBody Alquiler alquiler){
        try {
        	Alquiler _alq = alquilerServiceImpl.create(alquiler);
            return new ResponseEntity<Alquiler>(_alq, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	

	@DeleteMapping("DeleteAlqui/{id}")
	public ResponseEntity<Alquiler> delete(@PathVariable("id") Long id){
		try {
			alquilerServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	
	
	@PutMapping("EdidAlqui/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Alquiler alquiler){
		Optional<Alquiler> carData = alquilerServiceImpl.read(id);
	      if (carData.isPresent()) {
	        Alquiler dbalquiler = carData.get();
	        dbalquiler.setLector(alquiler.getLector());
	        dbalquiler.setLibro(alquiler.getLibro());
	        dbalquiler.setFechasa(alquiler.getFechasa());
	        dbalquiler.setFechaen(alquiler.getFechaen());
	        
	        return new ResponseEntity<Alquiler>(alquilerServiceImpl.update(dbalquiler), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}