package com.example.school.service;

import java.util.List;
import java.util.Optional;

import com.example.school.dto.PersonDto;
import com.example.school.model.Person;

public interface PersonService {
	
	Person insertPerson(PersonDto personDto);
	Optional<Person> findPersonById(Long id);
	Optional<Person> updatePerson(Long id, PersonDto personDto);
	Optional<Person> deletePerson(Long id);
	List<Person> findAllPerson();
}
