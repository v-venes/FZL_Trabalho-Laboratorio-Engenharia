package com.fatec.evento.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Espaco implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Column(unique = true)
	private String email;
	private String senha;
	private String img;
	private String telefone;
	private String endereco;
	private String descricao;
	private boolean acessibilidade;
	private boolean setor;
	
	@JsonIgnore
	@OneToMany(mappedBy = "espaco")
	private List<Historico> historico = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "Espaco_Area", joinColumns = @JoinColumn(name = "espaco_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
	private Set<Area> areas = new HashSet<>();
	
	public Espaco() {
	}

	public Espaco(Integer id, String nome, String email, String senha, String img, String telefone, String endereco,
			String descricao, boolean acessibilidade, boolean setor) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.img = img;
		this.telefone = telefone;
		this.endereco = endereco;
		this.descricao = descricao;
		this.acessibilidade = acessibilidade;
		this.setor = setor;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAcessibilidade() {
		return acessibilidade;
	}

	public void setAcessibilidade(boolean acessibilidade) {
		this.acessibilidade = acessibilidade;
	}

	public boolean isSetor() {
		return setor;
	}

	public void setSetor(boolean setor) {
		this.setor = setor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Historico> getHistorico() {
		return historico;
	}
	
	public Set<Area> getAreas() {
		return areas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Espaco other = (Espaco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
