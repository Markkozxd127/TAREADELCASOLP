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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name="AUTORES")
public class Autor {
	@Id
	@Column(name = "ID_AUTOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAutores")
    @SequenceGenerator(name = "seqAutores", allocationSize = 1, sequenceName = "SEQ_AUTOR")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "AUTOR")
	@NotNull @NotBlank    
    private String autor;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "autor")
	@JsonIgnore
	private Set<Libro> libros;

}
