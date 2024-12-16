package com.teste.pratico.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.teste.pratico.business.converter.SolicitanteMapper;
import com.teste.pratico.business.exception.EntidadeExisteException;
import com.teste.pratico.business.exception.EntidadeNaoEncontradaException;
import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.domain.entity.SolicitanteEntity;
import com.teste.pratico.helpers.LogOneUtil;
import com.teste.pratico.helpers.Mensagens;
import com.teste.pratico.persistense.repository.SolicitanteRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SolicitanteService {

	private final SolicitanteRepository repository;
	private final SolicitanteMapper mapper;
	
	public SolicitanteService(SolicitanteRepository repo, SolicitanteMapper map) {
		this.repository = repo;
		this.mapper = map;
	}
	
	public List<SolicitanteDTO> findAll() {
		Iterable<SolicitanteEntity> listEntity = repository.findAll();
		List<SolicitanteDTO> retorno = new ArrayList<>();
		if (!LogOneUtil.nuloOuVazio(listEntity)) {
			listEntity.forEach(entity -> retorno.add(mapper.toDto(entity)));
		}
		return retorno;
	}

	public Optional<SolicitanteDTO> findById(Long id) {
		Optional<SolicitanteEntity> optEntity = repository.findById(id);
		if (optEntity.isPresent()) {
			return Optional.of(mapper.toDto(optEntity.get()));
		}
		return Optional.empty();
	}


	public String create(SolicitanteDTO dto) {
		jaExiste(dto);
		validaRegrasNegocio(dto);
		SolicitanteEntity entity = mapper.toEntity(dto);
		entity = repository.save(entity);
		return String.format(Mensagens.SOLICITANTE_SALVO, entity.getNome());
	}

	public String update(SolicitanteDTO dto) {
		Optional<SolicitanteEntity> optEntityBase = repository.findById(dto.getId());
		if (optEntityBase.isPresent()) {
			validaRegrasNegocio(dto);

			SolicitanteEntity entityFromBase = optEntityBase.get();
			SolicitanteEntity entityFromDto = mapper.toEntity(dto);

			trataUpdate(entityFromBase, entityFromDto);
			entityFromBase = repository.save(entityFromBase);

			return String.format(Mensagens.SOLICITANTE_ATUALIZADO, entityFromBase.getNome());
		}
	throw new EntidadeNaoEncontradaException(String.format(Mensagens.SOLICITANTE_NAO_LOCALIZADO,dto.getId()));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String delete(Long id) {
		Optional<SolicitanteDTO> optDtoBase = this.findById(id);
		if (optDtoBase.isPresent()) {
			SolicitanteDTO dtoBase = optDtoBase.get();
			repository.deleteById(dtoBase.getId());
			return String.format(Mensagens.SOLICITANTE_REMOVIDO, dtoBase.getNome());
		}
		throw new EntidadeNaoEncontradaException(String.format(Mensagens.SOLICITANTE_NAO_LOCALIZADO));
	}
		
	private void jaExiste(SolicitanteDTO dto) {
		Optional<SolicitanteEntity> opt = repository.findByNome(dto.getNome());
		if (opt.isPresent()) {
			 throw new EntidadeExisteException(String.format(Mensagens.SOLICITANTE_EXISTENTE, dto.getNome()));
		   }
	}
		
	private void validaRegrasNegocio(SolicitanteDTO dto) {

	}

	private void trataUpdate(SolicitanteEntity entBD, SolicitanteEntity ent) {
	}
	
}
