package br.com.diego.seeddesafiocdc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.seeddesafiocdc.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	Optional<Autor> findByEmail(String email);
}
