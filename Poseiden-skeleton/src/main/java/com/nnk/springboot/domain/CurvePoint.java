package com.nnk.springboot.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
	 @Id
	    @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	 
	 @NotNull(message = "CurveId is mandatory")
	private Integer curveId;
	
	 private Timestamp asOfDate;
	/* @Size(min = 1,max=5) */
	 private Double term;
	/* @Size(min = 1,max=5) */
	 private Double value;
	private Timestamp creationDate;
	public CurvePoint(@NotBlank(message = "CurveId is mandatory") Integer curveId, Double term, Double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}
	
	public CurvePoint() {
		// TODO Auto-generated constructor stub
	}

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
	
}
