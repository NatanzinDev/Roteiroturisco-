package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class Usuario extends Model{
	public String nome;
	public String email;
	public String senha;
	
	@Enumerated(EnumType.STRING)
	public Perfil perfil;
	
	public Usuario() {
		this.perfil = Perfil.CLIENTE;
	}
	
	public Usuario(String nome, String email, String senha) {
		this.perfil = Perfil.CLIENTE;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
}
