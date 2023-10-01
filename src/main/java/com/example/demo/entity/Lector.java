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
@Table(name="LECTORES")
public class Lector {
	@Id
	@Column(name = "ID_LECTOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLectores")
    @SequenceGenerator(name = "seqLectores", allocationSize = 1, sequenceName = "SEQ_LECTORES")
    @Builder.Default
    private Long id=0L;
	@Column(name = "NOMBRE")
	@NotNull @NotBlank    
    private String nombre;
	
	@Column(name = "TELEFONO")
	@NotNull @NotBlank    
    private String telefono;
	
	@Column(name = "DIRECCION")
	@NotNull @NotBlank    
    private String direccion;
	
	@Column(name = "CODIGO_POSTAL")
	@NotNull @NotBlank    
    private String codigopos;
	
	@Column(name = "OBSERVACIONES")
	@NotNull @NotBlank    
    private String observ;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lector")
	@JsonIgnore
	private Set<Alquiler> alquileres;

}
