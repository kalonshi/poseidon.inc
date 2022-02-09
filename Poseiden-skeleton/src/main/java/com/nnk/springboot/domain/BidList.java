package com.nnk.springboot.domain;


import javax.persistence.*;

import javax.validation.constraints.NotBlank;



import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {
	// TODO: Map columns in data table BIDLIST with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="bidListId") 
	
	private Integer bidListId;

	@Column(length = 30, nullable = false)
	@NotBlank(message = "account is mandatory")
	private String account;

	@Column(length = 30, nullable = false)
	/* @NotBlank(message = "Type is mandatory") */
	private String type;
	
	@Column(name="bidQuantity")
	private Double bidQuantity;

	@Column(name="askQuantity")
	private Double askQuantity;
	
	private Double bid;
	
	private Double ask;
	@Column(length = 125)
	private String benchmark;
	@Column(name="bidListDate")
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
	@Column(name="creationName",length = 125)
	private String creationName;
	@Column(name="creationDate")
	private Timestamp creationDate;
	
	@Column(name="revisionName",length = 125)
	private String revisionName;
	@Column(name="revisionDate")
	private Timestamp revisionDate;

	@Column(name="dealName",length = 125)
	private String dealName;
	@Column(name="dealType",length = 125)
	private String dealType;
	@Column(name="sourceL",length = 125)
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



	public Timestamp getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}



	

}
