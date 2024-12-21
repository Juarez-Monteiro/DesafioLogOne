package com.teste.pratico.persistense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.entity.SolicitanteEntity;

@Repository
public interface SolicitanteRepository extends JpaRepository<SolicitanteEntity, Long> {

	List<SolicitanteEntity> findByNomeContainingIgnoreCase(String nome);
	Optional<SolicitanteEntity>  findByNome(String nome);
}
