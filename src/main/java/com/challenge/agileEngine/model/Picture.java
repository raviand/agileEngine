package com.challenge.agileEngine.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@JsonInclude(Include.NON_NULL)
public class Picture {

	@Id
	private String id;
	
	@JsonProperty("cropped_picture")	
	private String croppedPicture;
	
	@JsonProperty("full_picture")	
	private String fullPicture;
	
	private String author;
	
	private String camera;
	
	private String tags;
	
}
