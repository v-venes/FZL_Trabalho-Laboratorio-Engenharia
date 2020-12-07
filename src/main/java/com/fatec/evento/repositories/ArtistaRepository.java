package com.fatec.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.evento.entities.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

	public Artista findByEmailAndSenha(String email, String senha);
	
}
