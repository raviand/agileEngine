package com.challenge.agileEngine.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "pagination")
@Data
public class PaginationConfig {

	private Integer size;

}
