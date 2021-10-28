package br.com.fiap.meowater.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.meowater.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
		
	Page<Profile> findByNameLike(String name, Pageable pageable);
	
}
