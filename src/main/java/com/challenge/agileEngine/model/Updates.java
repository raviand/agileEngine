package com.challenge.agileEngine.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Updates {

	@Id 
	@GeneratedValue(
	        strategy= GenerationType.AUTO,
	        generator="native"
	)
	@GenericGenerator(
	        name = "native",
	        strategy = "native")
	private Long id;
	private LocalDateTime lastUpdate;
	private Integer daysToNextRefresh;
	
}
