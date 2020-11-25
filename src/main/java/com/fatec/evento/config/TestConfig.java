package com.fatec.evento.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fatec.evento.entities.Area;
import com.fatec.evento.entities.Artista;
import com.fatec.evento.entities.Comum;
import com.fatec.evento.entities.Espaco;
import com.fatec.evento.entities.Historico;
import com.fatec.evento.repositories.AreaRepository;
import com.fatec.evento.repositories.ArtistaRepository;
import com.fatec.evento.repositories.ComumRepository;
import com.fatec.evento.repositories.EspacoRepository;
import com.fatec.evento.repositories.HistoricoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ComumRepository comumRepository;
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Autowired
	private EspacoRepository espacoRepository;
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	

	@Override
	public void run(String... args) throws Exception {
		Comum c1 = new Comum(null, "comum 1", "comum1@gmail.com", "comum1!@#", "143218761", "08111100");
		Comum c2 = new Comum(null, "comum 2", "comum2@gmail.com", "comum2!@#", "243218762", "08222200");
		
		Artista a1 = new Artista(null, "artista 1", "artista1@gmail.com", "artista1!@#", "91119911", "09111100", "artista individual 1 com sua descrição", false);
		Artista a2 = new Artista(null, "artista 2", "artista2@gmail.com", "artista2!@#", "92229922", "09222200", "artistas - grupo 2 com sua descrição", true);
		
		Espaco e1 = new Espaco(null, "espaco 1", "espaco1@gmail.com", "espaco1!@#", "991191119", "Rua A, 01 - São Paulo - SP", "espaco 1 acessível público com sua desrição", true, true);
		Espaco e2 = new Espaco(null, "espaco 2", "espaco2@gmail.com", "espaco2!@#", "992292229", "Rua B, 02 - São Paulo - SP", "espaco 2 sem acessibilidade privado com sua desrição", false, false);
		
		Historico h1 = new Historico(null, Instant.parse("2020-11-22T19:53:07Z"), c1, e1, null);
		Historico h2 = new Historico(null, Instant.parse("2020-11-22T20:20:20Z"), c1, null, a1);
		Historico h3 = new Historico(null, Instant.parse("2020-11-23T05:17:44Z"), c2, e2, null);
		
		Area ar1 = new Area(null, "area 1");
		Area ar2 = new Area(null, "area 2");
		
		comumRepository.saveAll(Arrays.asList(c1, c2));
		artistaRepository.saveAll(Arrays.asList(a1, a2));
		espacoRepository.saveAll(Arrays.asList(e1, e2));
		historicoRepository.saveAll(Arrays.asList(h1, h2, h3));
		areaRepository.saveAll(Arrays.asList(ar1, ar2));
		
		a1.getAreas().add(ar1);
		a2.getAreas().add(ar1);
		a2.getAreas().add(ar2);
		
		e1.getAreas().add(ar1);
		e1.getAreas().add(ar2);
		e2.getAreas().add(ar2);
		
		c1.getAreas().add(ar1);
		c1.getAreas().add(ar2);
		c2.getAreas().add(ar1);
		c2.getAreas().add(ar2);
		
		artistaRepository.saveAll(Arrays.asList(a1, a2));
		espacoRepository.saveAll(Arrays.asList(e1, e2));
		comumRepository.saveAll(Arrays.asList(c1,c2));
		
	}
}
