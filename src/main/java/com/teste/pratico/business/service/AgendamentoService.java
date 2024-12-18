package com.teste.pratico.business.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.teste.pratico.business.converter.AgendamentoMapper;
import com.teste.pratico.business.exception.EntidadeNaoEncontradaException;
import com.teste.pratico.business.exception.LimiteExcedidoException;
import com.teste.pratico.business.exception.ViolacaoRegraNegocioException;
import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.dto.AgendamentoResumoDTO;
import com.teste.pratico.domain.dto.VagaComAgendamentoDTO;
import com.teste.pratico.domain.entity.AgendamentoEntity;
import com.teste.pratico.helpers.Mensagens;
import com.teste.pratico.persistense.repository.AgendamentoRepository;
import com.teste.pratico.persistense.repository.VagaRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AgendamentoService {

	private final AgendamentoRepository repository;
	private final AgendamentoMapper mapper;
	private final VagaRepository vagaRepository;

	public AgendamentoService(AgendamentoRepository repo, AgendamentoMapper map, VagaRepository vgRepo) {
		this.repository = repo;
		this.mapper = map;
		this.vagaRepository = vgRepo;
	}

	public String create(AgendamentoDTO dto) {
		jaExiste(dto);
		validaRegrasNegocio(dto);
		trataCreate(dto);
		AgendamentoEntity entity = mapper.toEntity(dto);
		entity = repository.save(entity);
		return String.format(Mensagens.AGENDAMENTO_SALVO, entity.getId(), entity.getData());
	}

	public String update(AgendamentoDTO dto) {
		Optional<AgendamentoEntity> optEntityBase = repository.findById(dto.getId());
		if (optEntityBase.isPresent()) {
//			jaExiste(dto); verificar regras
			validaRegrasNegocio(dto);

			AgendamentoEntity entityFromBase = optEntityBase.get();
			AgendamentoEntity entityFromDto = mapper.toEntity(dto);

			trataUpdate(entityFromBase, entityFromDto);
			entityFromBase = repository.save(entityFromBase);

			return String.format(Mensagens.AGENDAMENTO_ATUALIZADO, entityFromBase.getData());
		}
		throw new EntidadeNaoEncontradaException(String.format(Mensagens.AGENDAMENTO_NAO_LOCALIZADO, dto.getId()));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String delete(Long id) {
		Optional<AgendamentoDTO> optDtoBase = this.findById(id);
		if (optDtoBase.isPresent()) {
			AgendamentoDTO dtoBase = optDtoBase.get();
			repository.deleteById(dtoBase.getId());
			return String.format(Mensagens.AGENDAMENTO_REMOVIDO, dtoBase.getData());
		}
		throw new EntidadeNaoEncontradaException(String.format(Mensagens.AGENDAMENTO_NAO_LOCALIZADO));
	}

	public Optional<AgendamentoDTO> findById(Long id) {
		Optional<AgendamentoEntity> optEntity = repository.findById(id);
		if (optEntity.isPresent()) {
			return Optional.of(mapper.toDto(optEntity.get()));
		}
		return Optional.empty();
	}

	public List<AgendamentoResumoDTO> resumoAgendamentos(LocalDate inicio, LocalDate fim, Long solicitanteId) {

		List<AgendamentoResumoDTO> resultados = repository.calcularResumoAgendamentos(inicio, fim, solicitanteId);
		for (AgendamentoResumoDTO dto : resultados) {
			dto.calcularPercentual();
		}
		return resultados;
	}

	public List<AgendamentoEntity> buscarAgendamentosPorPeriodo(LocalDate inicio, LocalDate fim, Long solicitanteId) {
		return repository.listarSolicitantePorPeriodo(inicio, fim, solicitanteId);
	}

	private void jaExiste(AgendamentoDTO dto) {

	}

	private void validaRegrasNegocio(AgendamentoDTO dto) {

		VagaComAgendamentoDTO vagaDTO = montarVaga(dto);

		if (vagaDTO.getTotalAgendamentos() >= vagaDTO.getQuantidade()) {
			throw new LimiteExcedidoException(String.format(Mensagens.NAO_HA_VAGAS_PARA_DATA, dto.getData()));
		}

		int limitePermitido = (int) Math.ceil(vagaDTO.getQuantidade() * 0.25);
		int agendamentos = repository.contarAgendamentosPorSolicitanteEVaga(vagaDTO.getId(), dto.getSolicitante());

		if (agendamentos >= limitePermitido) {
			throw new LimiteExcedidoException(String.format(Mensagens.LIMITE_EXCEDIDO, limitePermitido));
		}
	}

	private void trataUpdate(AgendamentoEntity entBD, AgendamentoEntity ent) {
		entBD.setData(ent.getData());
		entBD.setMotivo(ent.getMotivo());
	}

	private void trataCreate(AgendamentoDTO dto) {
		dto.setNumero(criarNumeroAgendamento(dto));
	}

	private VagaComAgendamentoDTO montarVaga(AgendamentoDTO dto) {

		Optional<VagaComAgendamentoDTO> opt = vagaRepository.buscarVagaComTotalAgendamentos(dto.getData());
		if (opt.isEmpty()) {
			throw new ViolacaoRegraNegocioException(String.format(Mensagens.VAGA_NAO_ENCONTRADA_DATA, dto.getData()));
		}
		VagaComAgendamentoDTO vDTO = opt.get();
		return vDTO;
	}

	private String criarNumeroAgendamento(AgendamentoDTO dto) {
		VagaComAgendamentoDTO vagaDTO = montarVaga(dto);
		Long numeroAgendamento = vagaDTO.getTotalAgendamentos() + 1;
		return String.valueOf(numeroAgendamento);
	}

}
