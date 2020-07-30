package com.challenge.agileEngine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.challenge.agileEngine.configuration.PaginationConfig;
import com.challenge.agileEngine.model.Picture;
import com.challenge.agileEngine.model.response.PictureResponse;
import com.challenge.agileEngine.repository.PictureRepository;

@Service
public class PictureService {

	@Autowired @Lazy
	private PictureRepository pictureRepository;
	
	PaginationConfig config;
	
	public HttpStatus getPctureList(PictureResponse pictureResponse, Long pageNumber) {
		
		Page<Picture> page = null;
		
		Pageable pageable  = PageRequest.of(	pageNumber == null ? 0 : pageNumber.intValue(), 20, Sort.by(Sort.Direction.DESC, "id"));
		
		page = pictureRepository.findAll(pageable);
				
		pictureResponse.setPage(pageNumber);
		pictureResponse.setHasMore(page.hasNext());
		pictureResponse.setPictures(page.getContent());
		pictureResponse.setPageCount(page.getTotalPages());
		
		return HttpStatus.OK;
	}
	
	public HttpStatus getPctureData(PictureResponse pictureResponse, String id) {
		
		Optional<Picture> result = pictureRepository.findById(id);
		if(result.isPresent()) {
			pictureResponse.setPicture(result.get());
			return HttpStatus.OK;
		}else {
			return HttpStatus.BAD_REQUEST;
		}
		
	}
	
	public HttpStatus searchByValue(PictureResponse pictureResponse, String value) {
		Optional<List<Picture>> result = pictureRepository.searchByValue(value);
		if(!result.isPresent()) {
			return HttpStatus.BAD_REQUEST;
		}
		pictureResponse.setPictures(result.get());
		return HttpStatus.OK;
	}
	
	
	
}
