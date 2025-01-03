package com.teste.pratico.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.teste.pratico.business.service.VagaService;
import com.teste.pratico.domain.dto.VagaDTO;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class VagaBean extends AbstractBean {

	private final VagaService service;

	private VagaDTO dto;

	private List<VagaDTO> listaVagas;

	public VagaBean(VagaService service) {
		this.service = service;
		this.dto = new VagaDTO();
		this.listaVagas = new ArrayList<>();
	}

	public Date getMinDate() {
		return new Date();
	}

	public void buscarPorPeriodoOuTodos() {
		listaVagas = service.buscaPorPeriodo(dto.getInicio(), dto.getFim());
	}

	public Optional<VagaDTO> bucarPorId(Long vagaID) {
		return service.findById(vagaID);
	}

	public void salvarVaga() throws IOException {
		try {
			String mensagem = service.create(dto);
			infoMensagem(mensagem);
		} catch (Exception e) {
			erroMensagem(e.getMessage());
		}

	}

	public void atualizarVaga(RowEditEvent<VagaDTO> event) {
	    VagaDTO dto = event.getObject();
		try {
			String mensagem = service.update(dto);
			infoMensagem(mensagem);

		} catch (Exception e) {
			erroMensagem(e.getMessage());
		}
	}

	public void apagarVaga(Long id) {
		try {
			String mensagem = service.delete(id);
			infoMensagem(mensagem);

		} catch (Exception e) {
			erroMensagem(e.getMessage());
		}
	}

}
