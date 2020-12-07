package com.fatec.evento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fatec.evento.entities.Area;
import com.fatec.evento.repositories.AreaRepository;
import com.fatec.evento.services.exceptions.DatabaseException;
import com.fatec.evento.services.exceptions.ResourceNotFoundException;

@Service
public class AreaService {

	@Autowired
	private AreaRepository repository;
	
	public List<Area> findAll() {
		return repository.findAll();
	}
	
	public Area findById(Integer id) {
		Optional<Area> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Area insert(Area obj) {
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
	
	public Area update(Integer id, Area obj) {
		Area entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Area entity, Area obj) {
		entity.setNome(obj.getNome());
	}
}
