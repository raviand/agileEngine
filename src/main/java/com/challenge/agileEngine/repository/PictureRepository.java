package com.challenge.agileEngine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challenge.agileEngine.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, String>{

	@Query(value = "select p from Picture p where "
			+ " p.author like %?1% or p.camera like %?1% or p.tags like %?1%")
	Optional<List<Picture>> searchByValue(String value);

	
}
