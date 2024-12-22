package com.teste.pratico.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.teste.pratico.business.service.AgendamentoService;
import com.teste.pratico.business.service.SolicitanteService;
import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.dto.AgendamentoResumoDTO;
import com.teste.pratico.domain.dto.SolicitanteDTO;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class AgendamentoBean extends AbstractBean {

	private final AgendamentoService service;
	
	private final SolicitanteService solicitanteService;
	
	private AgendamentoDTO dto;
	
	private Date inicio;

    private Date fim;
	
	private List<SolicitanteDTO> solicitantes;
	
	private List<AgendamentoDTO> listaAgendamentos;
	
	private List<AgendamentoResumoDTO> listaAgendamentoResumo;
	
	private Long solicitanteSelecionado;

	public AgendamentoBean(AgendamentoService service,SolicitanteService solicServ) {
		this.service = service;
		this.solicitanteService = solicServ;
		this.dto = new AgendamentoDTO();
		this.solicitantes = new ArrayList<>();
		this.listaAgendamentos = new ArrayList<>();
		this.listaAgendamentoResumo = new ArrayList<>();
	}
	
	@PostConstruct
	public void carregarSolicitantes() {
	        solicitantes = solicitanteService.findAll();
    }

	public Date getMinDate() {
		return new Date();
	}

	public void listarTodosAgendamentos() {
		listaAgendamentoResumo = service.resumoAgendamentos(inicio, fim, solicitanteSelecionado);
	
	}

	public Optional<AgendamentoDTO> buscarPorId(Long id) {
		return service.findById(id);
	}

	public void listarAgendamentosPorPeriodo() {
		listaAgendamentos = service.buscarAgendamentosPorPeriodo(inicio, fim, solicitanteSelecionado);
		
	}

	public void salvarAgendamento() throws IOException {
		try {
			String mensagem = service.create(dto);
			infoMensagem(mensagem);

		} catch (Exception e) {
			erroMensagem(e.getMessage());

		}
	}

	public void atualizarAgendamento(RowEditEvent<AgendamentoDTO> event) {
		AgendamentoDTO dto = event.getObject();

		try {
			String mensagem = service.update(dto);
			infoMensagem(mensagem);
		} catch (Exception e) {
			erroMensagem(e.getMessage());
		}
	}

	public void apagarAgendamento(Long id) {
		try {
			String mensagem = service.delete(id);
			infoMensagem(mensagem);
		} catch (Exception e) {
			erroMensagem(e.getMessage());
		}
	}
}
