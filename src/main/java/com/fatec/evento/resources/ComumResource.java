package com.fatec.evento.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.evento.entities.Comum;
import com.fatec.evento.services.ComumService;

@RestController
@RequestMapping(value = "/comuns")
public class ComumResource {
	
	@Autowired
	private ComumService service;
	
	@GetMapping
	public ResponseEntity<List<Comum>> findAll() {
		List<Comum> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Comum> findById(@PathVariable Integer id) {
		Comum obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Comum> insert(@RequestBody Comum obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Comum> update(@PathVariable Integer id, @RequestBody Comum obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Comum> login(@RequestBody Comum obj) {
		String email = obj.getEmail();
		String senha = obj.getSenha();
		obj = service.findByEmailAndSenha(email, senha);
		return ResponseEntity.ok().body(obj);
	}

}
