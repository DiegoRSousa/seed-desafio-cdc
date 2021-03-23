package br.com.diego.seeddesafiocdc;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	Optional<Autor> findByEmail(String email);
}
