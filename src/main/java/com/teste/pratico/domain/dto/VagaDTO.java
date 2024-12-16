package com.teste.pratico.domain.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagaDTO extends AbstractDTO{

	private LocalDate inicio;
	
	private LocalDate fim;

	private Integer quantidade;
}
