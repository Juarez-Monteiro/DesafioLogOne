package com.teste.pratico.business.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.entity.AgendamentoEntity;
import com.teste.pratico.domain.entity.SolicitanteEntity;
import com.teste.pratico.helpers.LogOneUtil;

@Mapper(componentModel = "spring" , uses = { SolicitanteMapper.class })
public interface AgendamentoMapper extends MapperI<AgendamentoEntity, AgendamentoDTO>{

	@Mapping(target = "solicitante", source = "solicitante",qualifiedByName = "createSoliciFromID")
	public AgendamentoEntity toEntity(AgendamentoDTO dto);
	
	@Mapping(target = "solicitante", source = "solicitante.id")
	@Mapping(target = "solicitanteNome", source = "solicitante.nome")
	public AgendamentoDTO toDto(AgendamentoEntity ent);
	
	List<AgendamentoDTO> toDTOList(List<AgendamentoEntity> entities);
	
	@Named("createSoliciFromID")
	default SolicitanteEntity createSoliciFromID(Long id) {
		if(LogOneUtil.nuloOuVazio(id)) {
		return null;
		}
		SolicitanteEntity solicitante = new SolicitanteEntity();
		solicitante.setId(id);
		return solicitante;
		
	}
}
