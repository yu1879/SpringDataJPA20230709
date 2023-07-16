package com.spring.mvc.single.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mvc.single.entity.Person;
import com.spring.mvc.single.repository.PersonRepository;

@Controller
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/")
	public String index(@ModelAttribute Person person, Model model) {
		List<Person> persons = personRepository.findAll();
		model.addAttribute("_method", "POST");
		model.addAttribute("persons", persons);
		return "person/index";

	}

	@GetMapping("/{id}")
	public String getPersonById(@PathVariable Long id, Model model) {
		Person person = personRepository.findOne(id);
		List<Person> persons = personRepository.findAll();
		model.addAttribute("person", person);
		model.addAttribute("_method", "PUT");
		model.addAttribute("persons", persons);
		return "person/index";

	}

	@PostMapping("/")
	public String create(@ModelAttribute Person person) {
		// List<Person> persons = personRepository.findAll();
//		model.addAttribute("_method", "POST");
		personRepository.save(person);
		// model.addAttribute("persons", persons);
		return "redirect:./mvc/person";

	}

	@PutMapping("/")
	public String update(@ModelAttribute Person person) {
		if (personRepository.exists(person.getId())) {
			personRepository.save(person);
		}
		return "redirect:./mvc/person";

	}

	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable Long id) {
		personRepository.delete(id);
//		List<Person> persons = personRepository.findAll();
//		model.addAttribute("person", person);
//		model.addAttribute("_method", "PUT");
//		model.addAttribute("persons", persons);
		return "redirect:./mvc/person";

	}

}
