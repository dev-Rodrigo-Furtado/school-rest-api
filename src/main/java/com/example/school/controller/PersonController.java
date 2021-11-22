package com.example.school.controller;

import com.example.school.dto.PersonDto;
import com.example.school.model.Person;
import com.example.school.response.Response;
import com.example.school.response.ResponseError;
import com.example.school.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class PersonController {

	@Autowired
	private PersonService personService;

	@ApiOperation(value = "Método para inserir uma nova pessoa.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Pessoa inserida com sucesso."),
			@ApiResponse(code = 400, message = "A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.", response = ResponseError.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("person")
	public ResponseEntity<Response<Person>> insertPerson(@Valid @RequestBody PersonDto personDto) {
		Response<Person> response = new Response<>();
		response.setData(personService.insertPerson(personDto));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation(value = "Método para obter pessoa utilizando seu identificador único.")
	@GetMapping("person/{id}")
	public ResponseEntity<Response<Person>> findPerson(
			@ApiParam(required = true, value = "Identificador único da pessoa.")
			@PathVariable Long id) {
		
		Optional<Person> personOptional = personService.findPersonById(id);
		
		if(personOptional.isEmpty()) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		Response<Person> response = new Response<>();
		response.setData(personOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation(value = "Método para atualizar pessoa.")
	@PutMapping("person/{id}")
	public ResponseEntity<Response<Person>> updatePerson(
			@ApiParam(required = true, value = "Identificador único da pessoa.")
			@PathVariable Long id,
			@Valid @RequestBody PersonDto personDto) {
		Optional<Person> personOptional = personService.updatePerson(id, personDto);
		
		if(personOptional.isEmpty()) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		Response<Person> response = new Response<>();
		response.setData(personOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation(value = "Método para remover pessoa.")
	@DeleteMapping("person/{id}")
	public ResponseEntity<Response<Person>> deletePerson(
			@ApiParam(required = true, value = "Identificador único da pessoa.")
			@PathVariable Long id) {
		if(personService.deletePerson(id).isEmpty()) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@ApiOperation(value = "Método para obter lista de pessoas cadastradas.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Lista de pessoas obtida com sucesso."),
			@ApiResponse(code = 400, message = "A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.", response = ResponseError.class)
	})
	@GetMapping("person")
	public ResponseEntity<Response<List<Person>>> findPersonList() {
		Response<List<Person>> response = new Response<>();
		response.setData(personService.findAllPerson());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
