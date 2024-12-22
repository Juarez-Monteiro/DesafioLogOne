package com.teste.pratico.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagaDTO extends AbstractDTO{

	private Date inicio;
	
	private Date fim;

	private Integer quantidade;
}
