package com.challenge.agileEngine.model.response;

import lombok.Data;

@Data
public class BaseResponse {

	private Long page;
	
	private Integer pageCount;
	
	private Boolean hasMore;
	
}
