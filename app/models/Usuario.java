package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Usuario extends Model{
	public String nome;
	public String email;
	public String senha;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email, String senha) {
		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
}
