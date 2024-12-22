package com.teste.pratico.business.converter;

public interface MapperI<E, D> {
	
	public E toEntity(D dto);

	public D toDto(E ent);
}
