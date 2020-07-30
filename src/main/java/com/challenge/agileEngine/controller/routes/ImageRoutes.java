package com.challenge.agileEngine.controller.routes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.challenge.agileEngine.model.response.PictureResponse;

@Controller
public interface ImageRoutes {

	@GetMapping(path = "/images",  produces = (MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<PictureResponse> getPictures(HttpServletRequest request,  @RequestParam(required = false) Long pageNumber);
	
	@GetMapping(path = "/images/{imageId}",  produces = (MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<PictureResponse> getPicture(HttpServletRequest request,  @PathVariable(value="imageId") String id);
	
	@GetMapping(path = "/images/search/{searchTerm}",  produces = (MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<PictureResponse> findPictures(HttpServletRequest request,  @PathVariable(value="searchTerm") String searchTerm);
}
