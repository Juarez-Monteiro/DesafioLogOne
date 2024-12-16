package com.teste.pratico.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.web.context.annotation.SessionScope;

import com.teste.pratico.business.exception.EntidadeExisteException;
import com.teste.pratico.business.service.SolicitanteService;
import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.helpers.Mensagens;

import lombok.Data;

@ManagedBean
@SessionScope
@Data
public class SolicitanteBean {

	private final SolicitanteService service;

	private SolicitanteDTO dto;

	public SolicitanteBean(SolicitanteService serv) {
		this.service = serv;
		this.dto = new SolicitanteDTO();
	}

	public List<SolicitanteDTO> listarTodosSolicitantes() {
		return service.findAll();
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
			
		} catch (EntidadeExisteException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Mensagens.ERRO_PROCESSAR, ""));
		}

	}

	public void atualizarSolicitante(SolicitanteDTO dto) {
		String mensagem = service.update(dto);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
	}

	public void apagarSolicitante(Long id) {
		String mensagem = service.delete(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
	}

}
