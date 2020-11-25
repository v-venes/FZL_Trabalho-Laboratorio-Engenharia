package com.fatec.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.evento.entities.Comum;

public interface ComumRepository extends JpaRepository<Comum, Integer> {

}
