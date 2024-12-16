package com.teste.pratico.business.converter;

import org.mapstruct.Mapper;

import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.domain.entity.SolicitanteEntity;

@Mapper(componentModel = "spring")
public interface SolicitanteMapper extends MapperI<SolicitanteEntity, SolicitanteDTO>{

}
