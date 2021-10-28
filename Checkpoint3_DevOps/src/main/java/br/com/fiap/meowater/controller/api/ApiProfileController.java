package br.com.fiap.meowater.controller.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.meowater.model.Profile;
import br.com.fiap.meowater.repository.ProfileRepository;

@RestController
@RequestMapping("/api/profile")
public class ApiProfileController {
	
	@Autowired
	private ProfileRepository repository;
	
	@GetMapping
	public Page<Profile> index(
			@RequestParam(required = false) String name,
			@PageableDefault Pageable pageable
			) {
		
		if(name == null)
			return repository.findAll(pageable);
		
		return repository.findByNameLike("%" + name + "%", pageable);
	}
	
	@PostMapping
	public ResponseEntity<Profile> create(@RequestBody Profile profile, UriComponentsBuilder uriBuilder) {
		repository.save(profile);
		URI uri = uriBuilder.path("/api/profile/{id}")
				.buildAndExpand(profile.getId())
				.toUri();
		return ResponseEntity.created(uri).body(profile);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Profile> get(@PathVariable Long id) {
		Optional<Profile> profile = repository.findById(id);
		
		if(profile.isPresent()) 
			return ResponseEntity.ok(profile.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Profile> delete(@PathVariable Long id){
		Optional<Profile> profile = repository.findById(id);
		
		if(profile.isEmpty())
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Profile> update(@PathVariable Long id, @RequestBody Profile newProfile) {
		Optional<Profile> optional = repository.findById(id);
		
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		Profile profile = optional.get();
		profile.setName(newProfile.getName());
		profile.setPeso(newProfile.getPeso());
		profile.setRaca(newProfile.getRaca());
		profile.setSexo(newProfile.getSexo());
		profile.setDtnasc(newProfile.getDtnasc());
		
		repository.save(profile);
		return ResponseEntity.ok(profile);
	}
			
	
}
