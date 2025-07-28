package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class PacoteTuristico extends Model{
	public String nome;
	public Double preco;
	public int duracao;
	public String descricao;
	
	
}
