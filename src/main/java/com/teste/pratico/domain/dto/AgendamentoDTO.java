package com.teste.pratico.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoDTO extends AbstractDTO{

	private Date data;
	private String numero;
	private String motivo;
	private Long solicitante;
	private String solicitanteNome;
}
