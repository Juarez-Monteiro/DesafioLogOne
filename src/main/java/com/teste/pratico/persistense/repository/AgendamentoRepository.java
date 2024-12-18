package com.teste.pratico.persistense.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.dto.AgendamentoResumoDTO;
import com.teste.pratico.domain.entity.AgendamentoEntity;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

	@Query("SELECT a FROM AgendamentoEntity a WHERE a.data >= :inicio AND a.data <= :fim AND a.solicitante.id = :solicitanteId")
	List<AgendamentoEntity> listarSolicitantePorPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim,
			@Param("solicitanteId") Long solicitanteId);

	@Query("SELECT COUNT(a) " +
		       "FROM AgendamentoEntity a " +
		       "WHERE a.data BETWEEN (SELECT v.inicio FROM VagaEntity v WHERE v.id = :vagaId) " +
		       "AND (SELECT v.fim FROM VagaEntity v WHERE v.id = :vagaId) " +
		       "AND a.solicitante.id = :solicitanteId")
	Integer contarAgendamentosPorSolicitanteEVaga(@Param("vagaId") Long vagaId,@Param("solicitanteId") Long solicitanteId);

	@Query("SELECT new com.teste.pratico.domain.dto.AgendamentoResumoDTO( " 
			+ "    a.solicitante.nome, " 
			+ "    COUNT(a.id), "
			+ "    v.id, " 
			+ "    v.quantidade) " 
			+ "FROM AgendamentoEntity a " 
			+ "JOIN VagaEntity v "
			+ "ON a.data BETWEEN v.inicio AND v.fim " 
			+ "WHERE a.data BETWEEN :inicio AND :fim "
			+ "AND a.solicitante.id = :solicitanteId " 
			+ "GROUP BY a.solicitante.nome, v.id, v.quantidade")
	List<AgendamentoResumoDTO> calcularResumoAgendamentos(@Param("inicio") LocalDate inicio,
			@Param("fim") LocalDate fim, @Param("solicitanteId") Long solicitanteId);

}
