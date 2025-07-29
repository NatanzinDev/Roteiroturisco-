package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class PacoteTuristico extends Model{
	public String nome;
	public Double preco;
	public int duracao;
	public String descricao;
	
	@Enumerated(EnumType.STRING)
	public Status status;
}
