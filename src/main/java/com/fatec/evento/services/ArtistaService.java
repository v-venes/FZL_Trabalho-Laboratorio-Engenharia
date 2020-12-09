package com.fatec.evento.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fatec.evento.entities.Area;
import com.fatec.evento.entities.Artista;
import com.fatec.evento.repositories.ArtistaRepository;
import com.fatec.evento.services.exceptions.DatabaseException;
import com.fatec.evento.services.exceptions.InvalidLoginException;
import com.fatec.evento.services.exceptions.ResourceNotFoundException;
import com.fatec.evento.util.Md5;

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
	
	public List<Artista> findByArea(Integer area) {
		List<Artista> list = repository.findAll();
		List<Artista> list2 = new ArrayList<>();
		for(Artista obj : list) {
			for(Area a : obj.getAreas()) {
				if(a.getId() == area) {
					list2.add(obj);
				}
			}	
		}
		return list2;
	}
	
	public Artista findByEmailAndSenha(String email, String senha) {
		Optional<Artista> obj = Optional.ofNullable(repository.findByEmailAndSenha(email, Md5.criptografar(senha)));
		return obj.orElseThrow(() -> new InvalidLoginException(email, senha));
	}
	
	public Artista insert(Artista obj) {
		obj.setSenha(Md5.criptografar(obj.getSenha()));
		obj.setImg("https://journeypurebowlinggreen.com/wp-content/uploads/2018/05/placeholder-person.jpg");
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
	
	public Artista update(Integer id, Artista obj) {
		Artista entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Artista entity, Artista obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setSenha(Md5.criptografar(obj.getSenha()));
		entity.setImg(obj.getImg());
		entity.setTelefone(obj.getTelefone());
		entity.setCep(obj.getCep());
		entity.setDescricao(obj.getDescricao());
		entity.setTipo(obj.isTipo());
		
	}
	
}
