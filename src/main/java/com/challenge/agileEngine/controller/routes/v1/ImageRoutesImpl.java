package com.challenge.agileEngine.controller.routes.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.challenge.agileEngine.controller.routes.ImageRoutes;
import com.challenge.agileEngine.model.response.PictureResponse;
import com.challenge.agileEngine.service.PictureService;
@Controller
public class ImageRoutesImpl implements ImageRoutes{

	@Autowired
	private PictureService pictureService;
	
	
	@Override
	public ResponseEntity<PictureResponse> getPictures(HttpServletRequest request, Long pageNumber) {
		
		PictureResponse pictureResponse = new PictureResponse();

		HttpStatus status = pictureService.getPctureList(pictureResponse , pageNumber );
		
		return ResponseEntity.status(status).body(pictureResponse);
	}

	@Override
	public ResponseEntity<PictureResponse> getPicture(HttpServletRequest request, String id) {
		
		PictureResponse pictureResponse = new PictureResponse();

		HttpStatus status = pictureService.getPctureData(pictureResponse , id);
		
		return ResponseEntity.status(status).body(pictureResponse);
	}

	@Override
	public ResponseEntity<PictureResponse> findPictures(HttpServletRequest request, String searchTerm) {
		
		PictureResponse pictureResponse = new PictureResponse();

		HttpStatus status = pictureService.searchByValue(pictureResponse , searchTerm);
		
		return ResponseEntity.status(status).body(pictureResponse);
	}

}
