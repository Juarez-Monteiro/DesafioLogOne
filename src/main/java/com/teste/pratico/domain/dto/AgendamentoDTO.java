package com.teste.pratico.domain.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoDTO extends AbstractDTO{

	private LocalDate data;
	private String numero;
	private String motivo;
	private Long solicitante;
}
