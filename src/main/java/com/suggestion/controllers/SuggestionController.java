package com.suggestion.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suggestion.models.Contact;
import com.suggestion.models.Phone;
import com.suggestion.models.Suggestion;
import com.suggestion.repositories.ContactRepository;
import com.suggestion.repositories.SuggestionRepository;

@Controller
public class SuggestionController {

	@Autowired
	private SuggestionRepository suggestionRepository;
	
	@Autowired
	private ContactRepository contactRepository;


	@GetMapping("/")
	public ModelAndView listSuggestion(@PageableDefault(size = 5, sort = {"created"}, direction = Direction.DESC ) Pageable pg) {

		ModelAndView view = new ModelAndView("suggestion/list");
		Page<Suggestion> suggestions = suggestionRepository.findAll(pg);
		view.addObject("suggestions", suggestions);

		return view;

	}

	@GetMapping("suggestion")
	public ModelAndView formSuggestion(Suggestion suggestion) {
		ModelAndView view = new ModelAndView("suggestion/new");
		Contact contact = new Contact();
		contact.getPhones().add(new Phone());
		suggestion.setContact(contact);
		view.addObject("suggestion", suggestion);

		return view;
	}

	@PostMapping("suggestion")
	public String newSuggestion(@Valid @ModelAttribute Suggestion suggestion, BindingResult result) {

		if (result.hasErrors()) {
			return "/suggestion/new";
		}

		Contact contact = suggestion.getContact();
		contact.getPhones().forEach(phone -> phone.setContact(contact));

		contactRepository.save(contact);
		suggestionRepository.save(suggestion);

		return "redirect:/";
	}

}
