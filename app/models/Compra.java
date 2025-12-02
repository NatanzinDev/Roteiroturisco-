package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Compra extends Model {
    
    @ManyToOne
    public Usuario usuario;
    
    @Enumerated(EnumType.STRING)
    public StatusCompra status;

    
    // Lista de itens dentro desta compra
    // 'mappedBy' diz que quem manda na relação é o atributo 'compra' lá em ItemCompra
    @OneToMany(mappedBy = "compra")
    public List<ItemCompra> itens;
    
    // Construtor
    public Compra(Usuario usuario) {
        this.usuario = usuario;
        this.status = StatusCompra.CARRINHO; // Nasce como carrinho
       
    }
    
 // Adicionar dentro da classe Compra.java
    public Double getValorTotal() {
        double total = 0;
        if (itens != null) {
            for (ItemCompra item : itens) {
                // Multiplica preço x quantidade de cada item
                total += (item.precoUnitario * item.quantidade);
            }
        }
        return total;
    }
}
