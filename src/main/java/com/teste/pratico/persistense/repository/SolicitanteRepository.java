package com.teste.pratico.persistense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.entity.SolicitanteEntity;

@Repository
public interface SolicitanteRepository extends JpaRepository<SolicitanteEntity, Long> {

	Optional<SolicitanteEntity> findByNome(String nome);
}
