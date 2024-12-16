package com.teste.pratico.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "solicitante")
public class SolicitanteEntity extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String nome;
}
