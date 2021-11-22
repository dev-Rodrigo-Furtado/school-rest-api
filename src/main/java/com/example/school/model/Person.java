package com.example.school.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Person implements Serializable {

	private static final long serialVersionUID = -3301243575035249392L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String document;
	private Date dop;

	public Person() {
	}

	public Person(String name, String document, Date dop) {
		this.name = name;
		this.document = document;
		this.dop = dop;
	}

	public Person(Long id, String name, String document, Date dop) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.dop = dop;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}

	public Date getDop() {
		return dop;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public void setDop(Date dop) {
		this.dop = dop;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", document=" + document + ", dop=" + dop + "]";
	}

}
