package com.teste.pratico.business.converter;

import org.mapstruct.Mapper;

import com.teste.pratico.domain.dto.VagaDTO;
import com.teste.pratico.domain.entity.VagaEntity;

@Mapper(componentModel = "spring")
public interface VagaMapper extends MapperI<VagaEntity, VagaDTO>{

}
