package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {
	// TODO: Map columns in data table BIDLIST with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bidListId;

	@Column(length = 30, nullable = false)
	@NotBlank(message = "account is mandatory")
	private String account;

	@Column(length = 30, nullable = false)
	/* @NotBlank(message = "Type is mandatory") */
	private String type;
	/* @Size(min = 1,max=3) */
	private Double bidQuantity;
	/* @Size(min = 1,max=2) */
	private Double askQuantity;
	/* @Size(min = 1,max=5) */
	private Double bid;
	/* @Size(min = 1,max=2) */
	private Double ask;
	@Column(length = 125)
	private String benchmark;
	
	private Timestamp bidListDate;
	
	@Column(length = 125)
	private String commentary;
	@Column(length = 125)
	private String security;
	@Column(length = 10)
	private String status;
	@Column(length = 125)
	private String trader;
	@Column(length = 125)
	private String book;
	@Column(length = 125)
	private String creationName;

	private Timestamp creationDate;
	
	@Column(length = 125)
	private String revisionName;

	private Timestamp revisionDate;

	@Column(length = 125)
	private String dealName;
	@Column(length = 125)
	private String dealType;
	@Column(length = 125)
	private String sourceL;
	@Column(length = 125)
	private String side;

	public BidList(@NotBlank(message = "account is mandatory") String account,
			@NotBlank(message = "Type is mandatory") String type, Double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}



	public BidList() {
		// TODO Auto-generated constructor stub
	}



	public String getAccount() {
		return account;
	}



	public void setAccount(String account) {
		this.account = account;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Double getAskQuantity() {
		return askQuantity;
	}



	public void setAskQuantity(Double askQuantity) {
		this.askQuantity = askQuantity;
	}



	public Integer getBidListId() {
		return bidListId;
	}

	public void setBidListId(Integer bidListId) {
		this.bidListId = bidListId;
	}

	public Double getBidQuantity() {
		return bidQuantity;
	}

	public void setBidQuantity(Double bidQuantity) {
		this.bidQuantity = bidQuantity;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

}
