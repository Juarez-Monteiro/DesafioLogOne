package com.teste.pratico.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.web.context.annotation.RequestScope;

import com.teste.pratico.business.service.SolicitanteService;
import com.teste.pratico.domain.dto.SolicitanteDTO;

import lombok.Data;

@ManagedBean
@RequestScope
@Data
public class SolicitanteBean extends AbstractBean {

	private final SolicitanteService service;

	private SolicitanteDTO dto;

	private String filtroNome;
	
	private List<SolicitanteDTO> listaSolicitantes;
	

	public SolicitanteBean(SolicitanteService serv) {
		this.service = serv;
		this.dto = new SolicitanteDTO();
		this.listaSolicitantes = new ArrayList<>();
	}

	public void buscarPorNomeOuTodos() {
		if (filtroNome != null && !filtroNome.trim().isEmpty()) {
			listaSolicitantes = service.buscarTodosContemNome(filtroNome);
		} else {
			listaSolicitantes = service.findAll();
		}
	}

	public Optional<SolicitanteDTO> bucarPorId(Long solicitanteID) {
		return service.findById(solicitanteID);
	}

	public void salvarSolicitante() throws IOException {
		try {
			String mensagem = service.create(dto);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.getExternalContext().redirect("/index.xhtml");

		} catch (Exception e) {
			erroMensagem(e.getMessage());
		}
	}

	public void atualizarSolicitante(SolicitanteDTO dto) {
		try {
			String mensagem = service.update(dto);
			infoMensagem(mensagem);

		} catch (Exception e) {
			erroMensagem(e.getMessage());
		}
	}

	public void apagarSolicitante(Long id) {
		try {
			String mensagem = service.delete(id);
			infoMensagem(mensagem);

		} catch (Exception e) {
			erroMensagem(e.getMessage());
		}
	}
}
