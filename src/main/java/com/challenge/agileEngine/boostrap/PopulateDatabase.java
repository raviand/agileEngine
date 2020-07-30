package com.challenge.agileEngine.boostrap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.challenge.agileEngine.controller.constant.Constants;
import com.challenge.agileEngine.model.Picture;
import com.challenge.agileEngine.model.Updates;
import com.challenge.agileEngine.model.response.PictureResponse;
import com.challenge.agileEngine.repository.PictureRepository;
import com.challenge.agileEngine.repository.UpdateRepository;

@Component
public class PopulateDatabase {

	@Autowired
	private PictureRepository pictureRepository;

	@Autowired
	private UpdateRepository updateRepository;
	
	private RestTemplate restTemplate;
	
	@PostConstruct
	public void populateDatabase() {
		this.restTemplate = new RestTemplate();
		
		
		
		this.loadImageFromApi();
		
	}
	
	private void loadImageFromApi() {
		List<Picture> pictures = new ArrayList<Picture>();
		
		boolean hasNext = false;
		int pageNumber = 1;
		Optional<Updates> update = updateRepository.findTopByOrderByIdDesc();
		
		if(update.isPresent() && update.get().getLastUpdate().plusDays(update.get().getDaysToNextRefresh()).isBefore(LocalDateTime.now())) {
			pictureRepository.deleteAll();
			String token = this.authenticate();
			do {
				// create headers
				HttpHeaders headers = new HttpHeaders();
				// set `content-type` header
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setBearerAuth(token);
				HttpEntity request = new HttpEntity(headers);
				ResponseEntity<PictureResponse> response = this.restTemplate.exchange(Constants.URL + "images?page=" + pageNumber++,HttpMethod.GET, request, PictureResponse.class);
				pictures.addAll(response.getBody().getPictures());
				hasNext = response.getBody().getHasMore().booleanValue();
			}while(hasNext);
			token = this.authenticate();
			for(Picture p : pictures) {
								
				// create headers
				HttpHeaders headers = new HttpHeaders();
				// set `content-type` header
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setBearerAuth(token);
				HttpEntity request = new HttpEntity(headers);
				ResponseEntity<Picture> response = this.restTemplate.exchange(Constants.URL + "images/" + p.getId(),HttpMethod.GET, request, Picture.class);
				//picturesToSave.add(response.getBody());
				System.out.println(response.getBody().getAuthor());
				pictureRepository.save(response.getBody());
				
			}
			Updates newUpdate = new Updates();
			newUpdate.setLastUpdate(LocalDateTime.now());
			newUpdate.setDaysToNextRefresh(10);
			updateRepository.save(newUpdate);
		}
		
	}
	
	public String authenticate() {

	    // create headers
	    HttpHeaders headers = new HttpHeaders();
	    // set `content-type` header
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    // set `accept` header
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    
	    
	    // create a map for post parameters
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("apiKey", Constants.apiKey);
	    
	    // build the request
	    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

	    // send POST request
	    ResponseEntity<Map> response = this.restTemplate.postForEntity(Constants.URL+"auth", entity, Map.class);

	    // check response status code
	    if (response.getStatusCode() == HttpStatus.OK) {
	        return (String) response.getBody().get("token");
	    } else {
	        return null;
	    }
	}
	
}
