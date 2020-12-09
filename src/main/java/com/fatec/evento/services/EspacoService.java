package com.fatec.evento.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fatec.evento.entities.Area;
import com.fatec.evento.entities.Espaco;
import com.fatec.evento.repositories.EspacoRepository;
import com.fatec.evento.services.exceptions.DatabaseException;
import com.fatec.evento.services.exceptions.InvalidLoginException;
import com.fatec.evento.services.exceptions.ResourceNotFoundException;
import com.fatec.evento.util.Md5;

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
	
	public List<Espaco> findByArea(Integer area) {
		List<Espaco> list = repository.findAll();
		List<Espaco> list2 = new ArrayList<>();
		for(Espaco obj : list) {
			for(Area a : obj.getAreas()) {
				if(a.getId() == area) {
					list2.add(obj);
				}
			}	
		}
		return list2;
	}
	
	public Espaco findByEmailAndSenha(String email, String senha) {
		Optional<Espaco> obj = Optional.ofNullable(repository.findByEmailAndSenha(email, Md5.criptografar(senha)));
		return obj.orElseThrow(() -> new InvalidLoginException(email, senha));
	}
	
	public Espaco insert(Espaco obj) {
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
	
	public Espaco update(Integer id, Espaco obj) {
		Espaco entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Espaco entity, Espaco obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setSenha(Md5.criptografar(obj.getSenha()));
		entity.setImg(obj.getImg());
		entity.setTelefone(obj.getTelefone());
		entity.setEndereco(obj.getEndereco());
		entity.setDescricao(obj.getDescricao());
		entity.setAcessibilidade(obj.isAcessibilidade());
		entity.setSetor(obj.isSetor());
	}
}
