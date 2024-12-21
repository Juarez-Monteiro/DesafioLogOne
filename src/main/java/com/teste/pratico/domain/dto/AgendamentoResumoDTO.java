package com.teste.pratico.domain.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.teste.pratico.helpers.LogOneUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoResumoDTO {

	private String solicitante;
    private Long vagaId;
    private Long totalAgendamentos;
    private Integer quantidadeVagas;
    private BigDecimal percentual;
    private Date dataInicio;
    private Date dataFim;
    
    public AgendamentoResumoDTO(String solicitante, Long totalAgendamentos, Long vagaId, Integer quantidadeVagas, Date dataInicio, Date dataFim) {
        this.solicitante = solicitante;
        this.totalAgendamentos = totalAgendamentos;
        this.vagaId = vagaId;
        this.quantidadeVagas = quantidadeVagas;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    
    private void calcularPercentual() {
        if (quantidadeVagas != null && quantidadeVagas > 0) {
            this.percentual = new BigDecimal((totalAgendamentos * 100.0) / quantidadeVagas);
        } else {
            this.percentual = BigDecimal.ZERO;
        }
    }
    public String getPercentualFormatado() {
    	calcularPercentual();
    	DecimalFormat df = new DecimalFormat("#");
        return df.format(percentual) + "%";
    }
    
    public String getPeriodo() {
    	String periodo = LogOneUtil.convertDataToString(dataInicio) +" á "+ LogOneUtil.convertDataToString(dataFim);
    	return periodo;
    }
}
