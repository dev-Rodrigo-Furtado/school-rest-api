package com.example.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByDocument(String document);
}
