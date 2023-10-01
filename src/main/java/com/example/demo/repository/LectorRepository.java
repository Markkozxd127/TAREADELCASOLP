package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Lector;
@Repository
public interface LectorRepository extends JpaRepository<Lector, Long>{

}
