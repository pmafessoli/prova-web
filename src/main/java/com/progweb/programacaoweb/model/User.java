package com.progweb.programacaoweb.model;

import com.progweb.programacaoweb.ENUM.Perfil;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "user", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "email_uk", columnNames = "email") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user", nullable = false)
	private int id;

	@Column(name = "nome", length = 200, nullable = false)
	private String nome;

	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@Column(name = "senha", length = 128, nullable = false)
	private String senha;
	
	@Column(name = "phone", length = 128, nullable = false)
	private int phone;

	@JsonIgnore
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfis")
	private Set<Integer> perfis = new HashSet<Integer>();

	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil){
		perfis.add(perfil.getCod());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Vendedor{" +
				"id='" + id + '\'' +
				", nome='" + nome + '\'' +
				", email='" + email + '\'' +
				", senha='" + senha + '\'' +
				", senha='" + phone + '\'' +
				'}';
	}
}
