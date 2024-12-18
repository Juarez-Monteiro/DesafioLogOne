package com.teste.pratico.domain.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagaComAgendamentoDTO {

	private Long id;
    private LocalDate inicio;
    private LocalDate fim;
    private Integer quantidade;
    private Long totalAgendamentos;

    public VagaComAgendamentoDTO(Long id, LocalDate inicio, LocalDate fim, Integer quantidade, Long totalAgendamentos) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.quantidade = quantidade;
        this.totalAgendamentos = totalAgendamentos;
    }
}
