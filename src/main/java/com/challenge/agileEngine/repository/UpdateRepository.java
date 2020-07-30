package com.challenge.agileEngine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.agileEngine.model.Updates;

public interface UpdateRepository extends JpaRepository<Updates, Long>{

	public Optional<Updates> findTopByOrderByIdDesc();
	
}
