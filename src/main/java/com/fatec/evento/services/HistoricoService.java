package com.fatec.evento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.evento.entities.Historico;
import com.fatec.evento.repositories.HistoricoRepository;
import com.fatec.evento.services.exceptions.ResourceNotFoundException;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository repository;
	
	public List<Historico> findAll() {
		return repository.findAll();
	}
	
	public Historico findById(Integer id) {
		Optional<Historico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
