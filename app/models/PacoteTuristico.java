package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

@Entity
public class PacoteTuristico extends Model{
	public String nome;
	public Double preco;
	public int duracao;
	public String descricao;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	@ManyToMany
	@JoinTable(name="pacote_atividade")
	public List<Atividade> atividades = null;

	public PacoteTuristico(String nome, Double preco, int duracao, String descricao, List<Atividade> atividades) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.duracao = duracao;
		this.descricao = descricao;
		this.atividades = atividades;
		this.status = Status.ATIVO;
	}
	
	
} 
