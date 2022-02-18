package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
public class RuleName {
	// TODO: Map columns in data table RULENAME with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(length = 125)
	@NotBlank(message = "name is mandatory")
	private String name;
	@Column(length = 125)
	@NotBlank(message = "Description is mandatory")
	private String description;
	@Column(length = 125)
	@NotBlank(message = "Json is mandatory")
	private String json;
	@Column(length = 125)
	@NotBlank(message = "Template is mandatory")
	private String template;
	@Column(length = 125)
	@NotBlank(message = "sqlStr is mandatory")
	private String sqlStr;
	@Column(length = 125)
	@NotBlank(message = "sqlPart is mandatory")
	
	private String sqlPart;

	
	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		super();
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
		
	}

	public RuleName() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}
	
	
	
}
