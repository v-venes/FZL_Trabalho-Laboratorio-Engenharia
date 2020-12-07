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
import com.fatec.evento.util.Md5;

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
		Comum c1 = new Comum(null, "Caique", "caique@gmail.com", Md5.criptogrfar("comum1!@#"), "143218761", "08111100");
		Comum c2 = new Comum(null, "Gustavo", "gustavo@gmail.com", Md5.criptogrfar("comum2!@#"), "243218762", "08222200");
		
		String img1 = "https://avatars2.githubusercontent.com/u/43397591?s=460&u=d7464a667a9eb129bd752d4e466b59b91a88d08b&v=4";
		String img2 = "https://avatars3.githubusercontent.com/u/47512206?s=460&u=a4e5fcc7e5ed0b4fab9f574116a7a8233926c854&v=4";
		
		Artista a1 = new Artista(null, "Victor Neves", "victor@gmail.com", Md5.criptogrfar("artista1!@#"), img1, "91119911", "09111100", "artista individual 1 com sua descrição", false);
		Artista a2 = new Artista(null, "Raizer Varela", "raizer@gmail.com", Md5.criptogrfar("artista2!@#"), img2, "92229922", "09222200", "artistas - grupo 2 com sua descrição", true);
		
		String img3 = "https://theatromunicipal.org.br/wp-content/uploads/2015/12/photosphe%CC%80re_lobby-1024x512-1-e1575584495396.jpg";
		String img4 = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/MASP_Brazil.jpg/300px-MASP_Brazil.jpg";
		
		Espaco e1 = new Espaco(null, "Teatro blala", "espaco1@gmail.com", Md5.criptogrfar("espaco1!@#"), img3, "991191119", "Rua A, 01 - São Paulo - SP", "espaco 1 acessível público com sua desrição", true, true);
		Espaco e2 = new Espaco(null, "Museu uma boa", "espaco2@gmail.com", Md5.criptogrfar("espaco2!@#"), img4, "992292229", "Rua B, 02 - São Paulo - SP", "espaco 2 sem acessibilidade privado com sua desrição", false, false);
		
		Historico h1 = new Historico(null, Instant.parse("2020-11-22T19:53:07Z"), c1, e1, null);
		Historico h2 = new Historico(null, Instant.parse("2020-11-22T20:20:20Z"), c1, null, a1);
		Historico h3 = new Historico(null, Instant.parse("2020-11-23T05:17:44Z"), c2, e2, null);
		
		Area ar1 = new Area(null, "musica");
		Area ar2 = new Area(null, "danca");
		
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
