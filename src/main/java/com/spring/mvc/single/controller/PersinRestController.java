package com.spring.mvc.single.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.single.entity.Person;
import com.spring.mvc.single.repository.PersonRepository;

@RestController
@RequestMapping("/rest/person")
public class PersinRestController {
	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/{id}")
	public List<Person> queryAll() {
		return personRepository.findAll();
	}

	@PostMapping("/")
	public String create(@RequestBody Person person) {
		personRepository.save(person);
		return "Add OK";
	}

	@PutMapping("/")
	public String update(@RequestBody Person person) {
		personRepository.save(person);
		return "Update OK";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id) {
		personRepository.delete(id);
		return "Delete" + id + "OK";
	}
}
