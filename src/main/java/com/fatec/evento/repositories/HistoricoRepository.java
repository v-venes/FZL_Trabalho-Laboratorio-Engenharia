package com.fatec.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.evento.entities.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {

}
