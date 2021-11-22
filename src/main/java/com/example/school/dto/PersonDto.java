package com.example.school.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.example.school.validator.Date;

public class PersonDto {

	@NotBlank
	private String name;
	
	@CPF
	@Size(min = 11, max = 11)
	@NotNull
	private String document;
	
	@Date
	@NotNull
	private String dop;

	public PersonDto(String name, String document, String dop) {
		super();
		this.name = name;
		this.document = document;
		this.dop = dop;
	}

	public PersonDto() {
	
	}

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}

	public String getDop() {
		return dop;
	}

	@Override
	public String toString() {
		return "PersonDto [name=" + name + ", document=" + document + ", dop=" + dop + "]";
	}

}
