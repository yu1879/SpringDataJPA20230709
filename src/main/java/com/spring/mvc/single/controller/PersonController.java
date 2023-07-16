package com.spring.mvc.single.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/page")
	public String page(@RequestParam(name = "no", required = false, defaultValue = "0") Integer no, Model model) {
		int pageNo = no;
		int pageSize = 5;
		Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
		Sort sort = new Sort(order);
		PageRequest pageRequest = new PageRequest(pageNo, pageSize, sort);
		Page<Person> page = personRepository.findAll(pageRequest);
		List<Person> persons = page.getContent();
		model.addAttribute("persons", persons);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		return "person/page";
	}

}
