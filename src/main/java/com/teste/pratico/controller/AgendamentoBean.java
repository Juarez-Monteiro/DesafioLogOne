package com.teste.pratico.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.web.context.annotation.RequestScope;

import com.teste.pratico.business.exception.EntidadeNaoEncontradaException;
import com.teste.pratico.business.exception.LimiteExcedidoException;
import com.teste.pratico.business.exception.ViolacaoRegraNegocioException;
import com.teste.pratico.business.service.AgendamentoService;
import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.dto.AgendamentoResumoDTO;
import com.teste.pratico.domain.entity.AgendamentoEntity;
import com.teste.pratico.helpers.Mensagens;

import lombok.Data;

@ManagedBean
@RequestScope
@Data
public class AgendamentoBean {
	
	private final AgendamentoService service;

	private AgendamentoDTO dto;

	public AgendamentoBean(AgendamentoService service) {
		this.service = service;
		this.dto = new AgendamentoDTO();
	}
	
	public List<AgendamentoResumoDTO> listarTodosAgendamentos(LocalDate inicio, LocalDate fim, Long solicitanteId) {
		return service.resumoAgendamentos(inicio,fim, solicitanteId);
	}
	
	public Optional<AgendamentoDTO> buscarPorId(Long id) {
        return service.findById(id);
    }

    public List<AgendamentoEntity> listarAgendamentosPorPeriodo(LocalDate inicio, LocalDate fim, Long solicitanteId) {
        return service.buscarAgendamentosPorPeriodo(inicio, fim, solicitanteId);
    }

    public void salvarAgendamento() throws IOException {
        try {
            String mensagem = service.create(dto);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext().redirect("/index.xhtml");

        } catch (LimiteExcedidoException | ViolacaoRegraNegocioException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, Mensagens.ERRO_PROCESSAR, ""));
        }
    }

    public void atualizarAgendamento(AgendamentoDTO dto) {
        try {
            String mensagem = service.update(dto);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
        } catch (EntidadeNaoEncontradaException e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
    }

    public void apagarAgendamento(Long id) {
        try {
            String mensagem = service.delete(id);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
        } catch (EntidadeNaoEncontradaException e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
    }
	
}
