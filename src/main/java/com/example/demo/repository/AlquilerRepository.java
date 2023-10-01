package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Alquiler;
@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long>{

}