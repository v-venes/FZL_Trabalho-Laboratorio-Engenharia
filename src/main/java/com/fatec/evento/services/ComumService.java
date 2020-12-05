package com.fatec.evento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fatec.evento.entities.Comum;
import com.fatec.evento.repositories.ComumRepository;
import com.fatec.evento.services.exceptions.DatabaseException;
import com.fatec.evento.services.exceptions.ResourceNotFoundException;

@Service
public class ComumService {

	@Autowired
	private ComumRepository repository;
	
	public List<Comum> findAll() {
		return repository.findAll();
	}
	
	public Comum findById(Integer id) {
		Optional<Comum> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Comum insert(Comum obj) {
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
	
	public Comum update(Integer id, Comum obj) {
		Comum entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Comum entity, Comum obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setSenha(obj.getSenha());
		entity.setTelefone(obj.getTelefone());
		entity.setCep(obj.getCep());
	}
}
