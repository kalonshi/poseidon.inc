package com.nnk.springboot.domain;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
	 @Id
	    @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	 @Min(value = 1,message = "CurveId is mandatory")
	/* @NotNull(message = "CurveId is mandatory") */
	 @Column(name="curveId")
	 private Integer curveId;
	 @Column(name="asOfDate")
	 private Timestamp asOfDate;
	
	 private Double term;
	
	 private Double value;
	 @Column(name="creationDate")
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

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
}
