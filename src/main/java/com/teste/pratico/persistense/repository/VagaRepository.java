package com.teste.pratico.persistense.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.dto.VagaComAgendamentoDTO;
import com.teste.pratico.domain.entity.VagaEntity;

@Repository
public interface VagaRepository extends JpaRepository<VagaEntity, Long> {

	@Query("SELECT i FROM VagaEntity i WHERE ((:inicio >= i.inicio AND :inicio <= i.fim ) OR (:fim >= i.inicio AND :fim <= i.fim ) OR (:inicio < i.inicio AND :fim > i.fim))")
	List<VagaEntity> findVagasInPeriodo(@Param("inicio") Date inicio, @Param("fim") Date fim);
	
	@Query("SELECT i FROM VagaEntity i WHERE i.inicio >= :inicio OR i.fim >= :inicio")
	List<VagaEntity> findVagasInInicio(@Param("inicio") Date inicio);
	
	@Query("SELECT i FROM VagaEntity i WHERE i.inicio <= :fim OR i.fim <= :fim")
	List<VagaEntity> findVagasInFim(@Param("fim") Date fim);
	
	@Query("SELECT new com.teste.pratico.domain.dto.VagaComAgendamentoDTO(v.id, v.inicio, v.fim, v.quantidade, COUNT(a)) "
			+ "FROM VagaEntity v LEFT JOIN AgendamentoEntity a ON a.data BETWEEN v.inicio AND v.fim "
			+ "WHERE v.inicio <= :data AND v.fim >= :data " + "GROUP BY v.id, v.inicio, v.fim, v.quantidade")
	Optional<VagaComAgendamentoDTO> buscarVagaComTotalAgendamentos(@Param("data") Date data);

	@Query(value = "SELECT EXISTS (SELECT 1 FROM agendamentos a WHERE a.data BETWEEN :inicio AND :fim)", nativeQuery = true)
	boolean existeAgendamentoEntreDatas(@Param("inicio") Date inicio, @Param("fim") Date fim);
}
