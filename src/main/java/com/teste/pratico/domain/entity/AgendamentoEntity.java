package com.teste.pratico.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "agendamento")
public class AgendamentoEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private LocalDate data;
	
	@Column(nullable = false)
	private String numero;
	
	@Column(nullable = false)
	private String motivo;
	
	@ManyToOne
	@JoinColumn(name = "solicitante_id", nullable = false)
	private SolicitanteEntity solicitante;
}
