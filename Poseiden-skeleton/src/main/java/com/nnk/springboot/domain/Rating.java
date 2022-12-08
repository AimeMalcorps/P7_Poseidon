package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	private String moodysRating;
	private String sandPRating;
	private String fitchRating;
	private Integer orderNumber;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMoodysRating() {
		return moodysRating;
	}
	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}
	public String getSandPRating() {
		return sandPRating;
	}
	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}
	public String getFitchRating() {
		return fitchRating;
	}
	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public Rating() {
		
	}
	
	public Rating(String string, String string2, String string3, int i) {
		Rating rating = new Rating();
		rating.setOrderNumber(i);
		rating.setMoodysRating(string);
		rating.setSandPRating(string2);
		rating.setFitchRating(string3);
	}
	
}
