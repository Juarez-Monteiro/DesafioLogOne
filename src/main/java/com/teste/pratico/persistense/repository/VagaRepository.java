package com.teste.pratico.persistense.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.entity.VagaEntity;

@Repository
public interface VagaRepository extends JpaRepository<VagaEntity, Long>{
	
	@Query("SELECT i FROM VagaEntity i WHERE :inicio <= i.fim ")
		Optional<VagaEntity> findVagasInPeriodo(@Param("inicio") LocalDate inicio);
}
