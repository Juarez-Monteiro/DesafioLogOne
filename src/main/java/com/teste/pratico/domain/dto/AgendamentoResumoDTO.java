package com.teste.pratico.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoResumoDTO {

	private String solicitante;
    private Long vagaId;
    private Long totalAgendamentos;
    private Integer quantidadeVagas;
    private Double percentual;
    
    public AgendamentoResumoDTO(String solicitante, Long totalAgendamentos, Long vagaId, Integer quantidadeVagas) {
        this.solicitante = solicitante;
        this.totalAgendamentos = totalAgendamentos;
        this.vagaId = vagaId;
        this.quantidadeVagas = quantidadeVagas;
    }
    
    public void calcularPercentual() {
        if (quantidadeVagas != null && quantidadeVagas > 0) {
            this.percentual = (totalAgendamentos * 100.0) / quantidadeVagas;
        } else {
            this.percentual = 0.0;
        }
    }
}
