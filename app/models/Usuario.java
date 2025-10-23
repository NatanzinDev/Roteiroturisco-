package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{
	@Required
	public String nome;
	@Required
	@Email
	public String email;
	@Required
	@Equals("confirmarsenha")
	public String senha;
	
	@Transient
	public String confirmarsenha;
	
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
	
	public void setSenha(String s) {
		this.senha = Crypto.passwordHash(s);
	}
	
	public void setConfirmarsenha(String s) {
		this.confirmarsenha = Crypto.passwordHash(s);
	}
}
