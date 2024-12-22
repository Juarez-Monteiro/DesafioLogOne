package com.teste.pratico.persistense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.entity.SolicitanteEntity;

@Repository
public interface SolicitanteRepository extends JpaRepository<SolicitanteEntity, Long> {

	List<SolicitanteEntity> findByNomeContainingIgnoreCase(String nome);
	Optional<SolicitanteEntity>  findByNome(String nome);
	
	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
		       "FROM AgendamentoEntity a WHERE a.id = :id")
	boolean existeAgendamentoSolicitante(@Param("id") Long id);
}
