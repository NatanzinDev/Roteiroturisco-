package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class ItemCompra extends Model {
    
    @ManyToOne
    public PacoteTuristico pacote;
    
    @ManyToOne
    public Compra compra;
    
    public Integer quantidade;
    public Double precoUnitario; // Importante para congelar o preço no dia da compra
    
    // Construtor
    public ItemCompra(PacoteTuristico pacote, Compra compra) {
        this.pacote = pacote;
        this.compra = compra;
        this.quantidade = 1; // Começa sempre com 1
        this.precoUnitario = pacote.preco;
    }
}
