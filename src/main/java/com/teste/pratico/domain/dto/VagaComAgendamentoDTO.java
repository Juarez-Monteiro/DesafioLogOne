package com.teste.pratico.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagaComAgendamentoDTO {

	private Long id;
    private Date inicio;
    private Date fim;
    private Integer quantidade;
    private Long totalAgendamentos;

    public VagaComAgendamentoDTO(Long id, Date inicio, Date fim, Integer quantidade, Long totalAgendamentos) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.quantidade = quantidade;
        this.totalAgendamentos = totalAgendamentos;
    }
}
