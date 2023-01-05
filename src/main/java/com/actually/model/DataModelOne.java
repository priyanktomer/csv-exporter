/*
@JsonProperty is used to keep key name in JSON File
@JsonPropertyOrder is used to maintain order of keys in JSON File.
@Column is used to map field with Database column
@Id is primary key for JPA
@CsvBindByName is used for keeping header column name in CSV file for OpenCsv approach. Whereas in CommonCsv approach it will be handled i ServiceImpl.
*/

package com.actually.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@JsonPropertyOrder({"ID","NAME"})
@Entity
@Table(name = "basic")
public class DataModelOne {
	@Id
	@Column(name="ID")
	@JsonProperty("ID")
	@CsvBindByName(column = "ID")
	private Integer id;
	
	@Column(name="NAME")
	@JsonProperty("NAME")
	@CsvBindByName(column = "NAME")
	private String name;

	
	
	public DataModelOne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataModelOne(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
