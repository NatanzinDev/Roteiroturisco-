package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.data.validation.Max;
import play.data.validation.Min;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class PacoteTuristico extends Model{
	@Required
	public String nome;
	@Required
	@Min(0)
	public Double preco;
	@Required
	@Min(0)
	@Max(10)
	public Integer duracao;
	@Required
	@MinSize(20)
	public String descricao;
	
	public String nomeFoto;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	@Required
	@ManyToMany
	@JoinTable(name="pacote_atividade")
	public List<Atividade> atividades ;

	public PacoteTuristico(String nome, Double preco, int duracao, String descricao) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.duracao = duracao;
		this.descricao = descricao;
	
		this.status = Status.ATIVO;
	}
	
	public PacoteTuristico() {
		this.status = Status.ATIVO;
	}
	
	
} 
