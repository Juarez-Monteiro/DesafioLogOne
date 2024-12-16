package com.teste.pratico.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.teste.pratico.business.converter.VagaMapper;
import com.teste.pratico.business.exception.EntidadeExisteException;
import com.teste.pratico.business.exception.EntidadeNaoEncontradaException;
import com.teste.pratico.domain.dto.VagaDTO;
import com.teste.pratico.domain.entity.VagaEntity;
import com.teste.pratico.helpers.LogOneUtil;
import com.teste.pratico.helpers.Mensagens;
import com.teste.pratico.persistense.repository.VagaRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class VagaService {

	private final VagaRepository repository;
	private final VagaMapper mapper;
	
	public VagaService(VagaRepository repository, VagaMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public List<VagaDTO> findAll() {
		Iterable<VagaEntity> listEntity = repository.findAll();
		List<VagaDTO> retorno = new ArrayList<>();
		if (!LogOneUtil.nuloOuVazio(listEntity)) {
			listEntity.forEach(entity -> retorno.add(mapper.toDto(entity)));
		}
		return retorno;
	}

	public Optional<VagaDTO> findById(Long id) {
		Optional<VagaEntity> optEntity = repository.findById(id);
		if (optEntity.isPresent()) {
			return Optional.of(mapper.toDto(optEntity.get()));
		}
		return Optional.empty();
	}

	public String create(VagaDTO dto) {
		jaExiste(dto);
		validaRegrasNegocio(dto);
		VagaEntity entity = mapper.toEntity(dto);
		entity = repository.save(entity);
		return String.format(Mensagens.VAGA_SALVA, entity.getInicio(),entity.getFim());
	}

	public String update(VagaDTO dto) {
		Optional<VagaEntity> optEntityBase = repository.findById(dto.getId());
		if (optEntityBase.isPresent()) {
//			jaExiste(dto); fazer depois
			validaRegrasNegocio(dto);

			VagaEntity entityFromBase = optEntityBase.get();
			VagaEntity entityFromDto = mapper.toEntity(dto);

			trataUpdate(entityFromBase, entityFromDto);
			entityFromBase = repository.save(entityFromBase);

			return String.format(Mensagens.VAGA_ATUALIZADA, entityFromBase.getInicio(),entityFromBase.getFim());
		}
	throw new EntidadeNaoEncontradaException(String.format(Mensagens.VAGA_NAO_LOCALIZADA,dto.getId()));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String delete(Long id) {
		Optional<VagaDTO> optDtoBase = this.findById(id);
		if (optDtoBase.isPresent()) {
			VagaDTO dtoBase = optDtoBase.get();
			repository.deleteById(dtoBase.getId());
			return String.format(Mensagens.VAGA_REMOVIDA, dtoBase.getInicio(), dtoBase.getFim());
		}
		throw new EntidadeNaoEncontradaException(String.format(Mensagens.VAGA_NAO_LOCALIZADA, id));
	}
		
	private void jaExiste(VagaDTO dto) {
		Optional<VagaEntity> opt = repository.findVagasInPeriodo(dto.getInicio());
		if (opt.isPresent()) {
			 throw new EntidadeExisteException(String.format(Mensagens.VAGA_EXISTENTE, dto.getInicio(), dto.getFim()));
		   }
	}
		
	private  void validaRegrasNegocio(VagaDTO dto) {
		if (dto.getInicio().isAfter(dto.getFim())) {
		    throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim.");
		}
		if (dto.getInicio().isBefore(LocalDate.now())) {
			 throw new IllegalArgumentException("A data de início não pode ser anterior à hoje.");
		}
	}

	private void trataUpdate(VagaEntity entBD, VagaEntity ent) {
	}
	
	
}
