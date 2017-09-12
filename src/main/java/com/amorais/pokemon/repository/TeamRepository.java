package com.amorais.pokemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.amorais.pokemon.domain.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Integer>, JpaSpecificationExecutor<Team> {

	List<Team> findByOrderByName();
	
}
