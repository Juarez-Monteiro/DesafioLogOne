package com.teste.pratico.persistense.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.dto.VagaComAgendamentoDTO;
import com.teste.pratico.domain.entity.VagaEntity;

@Repository
public interface VagaRepository extends JpaRepository<VagaEntity, Long> {

	@Query("SELECT i FROM VagaEntity i WHERE (:inicio >= i.inicio AND :inicio <= i.fim ) OR (:fim >= i.inicio AND :fim <= i.fim )")
	Optional<VagaEntity> findVagasInPeriodo(@Param("inicio") LocalDate inicio,@Param("fim") LocalDate fim);

	@Query("SELECT new com.teste.pratico.domain.dto.VagaComAgendamentoDTO(v.id, v.inicio, v.fim, v.quantidade, COUNT(a)) " +
		       "FROM VagaEntity v LEFT JOIN AgendamentoEntity a ON a.data BETWEEN v.inicio AND v.fim " +
		       "WHERE v.inicio <= :data AND v.fim >= :data")
	Optional<VagaComAgendamentoDTO> buscarVagaComTotalAgendamentos(@Param("data") LocalDate data);
	
	@Query(value = "SELECT EXISTS (SELECT 1 FROM agendamentos a WHERE a.data BETWEEN :inicio AND :fim)", nativeQuery = true)
	boolean existeAgendamentoEntreDatas(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
