package com.teste.pratico.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.web.context.annotation.RequestScope;

import com.teste.pratico.business.exception.EntidadeExisteException;
import com.teste.pratico.business.service.VagaService;
import com.teste.pratico.domain.dto.VagaDTO;
import com.teste.pratico.helpers.Mensagens;

import lombok.Data;

@ManagedBean
@RequestScope
@Data
public class VagaBaen {

	private final VagaService service;
	
	private VagaDTO dto;

	public VagaBaen(VagaService service) {
		this.service = service;
		this.dto = new VagaDTO();
	}
	
	public List<VagaDTO> listarTodasVagas() {
		return service.findAll();
	}

	public Optional<VagaDTO> bucarPorId(Long vagaID) {
		return service.findById(vagaID);
	}

	public void salvarVaga() throws IOException {
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

	public void atualizarVaga(VagaDTO dto) {
		String mensagem = service.update(dto);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
	}

	public void apagarVaga(Long id) {
		String mensagem = service.delete(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
	}
	
}
