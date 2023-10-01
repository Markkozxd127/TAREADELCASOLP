package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="LIBROS")
public class Libro {
	@Id
	@Column(name = "ID_LIBRO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLibros")
    @SequenceGenerator(name = "seqLibros", allocationSize = 1, sequenceName = "SEQ_LIBRO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "TITULO")   
    private String titulo;
	
	@Column(name = "FECHA_LANZAMIENTO")	
    private String fechalan;
	
	@Column(name = "IDIOMA")    
    private String idioma;
	
	@Column(name = "PAGINAS")    
    private Integer paginas;
	
	@Column(name = "DESCRIPCION")   
    private String descripcion;
	
	@Column(name = "PORTADA")    
    private String portada;
	
	
	@ManyToOne
    @JoinColumn(name="AUTOR_ID", nullable = false)
    private Autor autor;
	
	@ManyToOne
    @JoinColumn(name="CATEGORIA_ID", nullable = false)
    private Categoria categoria;
	
	@ManyToOne
    @JoinColumn(name="EDITORIAL_ID", nullable = false)
    private Editorial editorial;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libro")
	@JsonIgnore
	private Set<Alquiler> alquileres;
}

