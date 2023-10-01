package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Editorial;
@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long>{

}