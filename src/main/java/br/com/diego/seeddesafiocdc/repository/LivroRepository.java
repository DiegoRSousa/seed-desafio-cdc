package br.com.diego.seeddesafiocdc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.seeddesafiocdc.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
