package com.teste.pratico.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "vagas")
public class VagaEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false )
	private LocalDate inicio;
	
	@Column(nullable = false )
	private LocalDate fim;
	
	@Column(nullable = false )
	private Integer quantidade;

}