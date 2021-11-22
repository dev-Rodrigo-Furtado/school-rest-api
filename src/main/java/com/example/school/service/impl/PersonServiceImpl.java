package com.example.school.service.impl;

import com.example.school.dto.PersonDto;
import com.example.school.exception.DocumentFoundException;
import com.example.school.model.Person;
import com.example.school.repository.PersonRepository;
import com.example.school.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person insertPerson(PersonDto personDto) {
		if(personRepository.findByDocument(personDto.getDocument()).isEmpty()) {
			return personRepository.save(personAdapter(personDto));
		} else {
			throw new DocumentFoundException();
		}
	}

	@Override
	public Optional<Person> findPersonById(Long id) {
		return personRepository.findById(id);
	}

	@Override
	public Optional<Person> updatePerson(Long id, PersonDto personDto) {
		Optional<Person> personOptional = personRepository.findById(id);
		if(personOptional.isEmpty()) 
				return personOptional;
		
		Person personUpdated = personAdapter(id, personDto);
		personRepository.save(personUpdated);
		return Optional.of(personUpdated);
	}

	@Override
	public Optional<Person> deletePerson(Long id) {
		Optional<Person> personOptional = personRepository.findById(id);
		if(personOptional.isEmpty()) 
			return personOptional;
		
		personRepository.delete(personOptional.get());
		
		return personOptional;
	}

	@Override
	public List<Person> findAllPerson() {
		return personRepository.findAll();
	}
	
	private Person personAdapter(Long id, PersonDto personDto) {
		return new Person(id, personDto.getName(), personDto.getDocument(), Date.valueOf(personDto.getDop()));
	}

	private Person personAdapter(PersonDto personDto) {
		return new Person(personDto.getName(), personDto.getDocument(), Date.valueOf(personDto.getDop()));
	}
}
