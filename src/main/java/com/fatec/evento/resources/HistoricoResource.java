package com.fatec.evento.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.evento.entities.Historico;
import com.fatec.evento.services.HistoricoService;

@RestController
@RequestMapping(value = "/historico")
public class HistoricoResource {
	
	@Autowired
	private HistoricoService service;
	
	@GetMapping
	public ResponseEntity<List<Historico>> findAll() {
		List<Historico> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Historico> findById(@PathVariable Integer id) {
		Historico obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}

}
