package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	@NotNull
	private Integer curveId;
	private Date asOfDate;
	private Double term;
	private Double value;
	@CreatedDate
	private Date creationDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCurveId() {
		return curveId;
	}
	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}
	public Date getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}
	public Double getTerm() {
		return term;
	}
	public void setTerm(Double term) {
		this.term = term;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public CurvePoint() {
		
	}
	
	public CurvePoint(int i, double d, double d2) {
		CurvePoint curve = new CurvePoint();
		curve.setCurveId(i);
		curve.setTerm(d);
		curve.setValue(d2);
	}
}
