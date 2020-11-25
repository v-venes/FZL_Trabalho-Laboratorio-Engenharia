package com.fatec.evento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fatec.evento.entities.Artista;
import com.fatec.evento.repositories.ArtistaRepository;
import com.fatec.evento.services.exceptions.DatabaseException;
import com.fatec.evento.services.exceptions.ResourceNotFoundException;

@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository repository;
	
	public List<Artista> findAll() {
		return repository.findAll();
	}
	
	public Artista findById(Integer id) {
		Optional<Artista> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Artista insert(Artista obj) {
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Artista updte(Integer id, Artista obj) {
		Artista entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Artista entity, Artista obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setSenha(obj.getSenha());
		entity.setTelefone(obj.getTelefone());
		entity.setCep(obj.getCep());
		entity.setDescricao(obj.getDescricao());
		entity.setTipo(obj.isTipo());
		
	}
	
}
