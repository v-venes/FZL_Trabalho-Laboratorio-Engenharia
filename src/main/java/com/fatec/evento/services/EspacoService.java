package com.fatec.evento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fatec.evento.entities.Espaco;
import com.fatec.evento.repositories.EspacoRepository;
import com.fatec.evento.services.exceptions.DatabaseException;
import com.fatec.evento.services.exceptions.ResourceNotFoundException;

@Service
public class EspacoService {

	@Autowired
	private EspacoRepository repository;
	
	public List<Espaco> findAll() {
		return repository.findAll();
	}
	
	public Espaco findById(Integer id) {
		Optional<Espaco> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Espaco insert(Espaco obj) {
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
	
	public Espaco updte(Integer id, Espaco obj) {
		Espaco entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Espaco entity, Espaco obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setSenha(obj.getSenha());
		entity.setTelefone(obj.getTelefone());
		entity.setEndereco(obj.getEndereco());
		entity.setDescricao(obj.getDescricao());
		entity.setAcessibilidade(obj.isAcessibilidade());
		entity.setSetor(obj.isSetor());
	}
}
