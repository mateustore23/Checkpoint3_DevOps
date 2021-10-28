package br.com.fiap.meowater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.meowater.model.Profile;
import br.com.fiap.meowater.repository.ProfileRepository;

@Controller
public class ProfileController {
	
	@Autowired
	private ProfileRepository repository;
	
	@GetMapping("/profile")
	public String index() {
		return "profiles";
	}
	
	@PostMapping("/profile")
	public String save(Profile profile) {
		repository.save(profile);
		System.out.println("salvando gato..." + profile);
		return "profiles";
	}
	
	@RequestMapping("/profile/new")
	public String create() {
		return "profile-form";
	}
	
}
