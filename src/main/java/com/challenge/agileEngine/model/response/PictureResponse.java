package com.challenge.agileEngine.model.response;

import java.util.List;

import com.challenge.agileEngine.model.Picture;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
public class PictureResponse extends BaseResponse{

	private List<Picture> pictures; 
	
	private Picture picture;
	 
}
