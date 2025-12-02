package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Compra;
import models.ItemCompra;
import models.PacoteTuristico;
import models.StatusCompra;
import models.Usuario;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Carrinho extends Controller {

	public static void detalhes() {

		String email = session.get("usuario");

		Usuario usuario = Usuario.find("email = ?1", email).first();

		// Busca o carrinho ABERTO deste usuário
		Compra carrinho = Compra.find("usuario = ?1 AND status = ?2", usuario, StatusCompra.CARRINHO).first();

		render(carrinho);

	}

	public static void adicionarAjax(Long idPacote) {

		String emailUsuario = session.get("usuario");

		Usuario usuario = Usuario.find("email = ?1", emailUsuario).first();

		Compra carrinho = Compra.find("usuario = ?1 AND status = ?2", usuario, StatusCompra.CARRINHO).first();

		if (carrinho == null) {
			carrinho = new Compra(usuario);
			carrinho.save();
		}

		PacoteTuristico pacote = PacoteTuristico.findById(idPacote);

		ItemCompra itemExistente = ItemCompra.find("compra = ?1 AND pacote = ?2", carrinho, pacote).first();

		if (itemExistente != null) {
			itemExistente.quantidade++;
			itemExistente.save();
		} else {
			ItemCompra novoItem = new ItemCompra(pacote, carrinho);
			novoItem.save();
		}

		Long totalItens = ItemCompra.count("compra = ?1", carrinho);

		Map<String, Object> json = new HashMap<String, Object>();
		json.put("total", totalItens);
		json.put("mensagem", "Sucesso");

		renderJSON(json);
	}
	
	public static void remover(Long idItem) {

	    ItemCompra item = ItemCompra.findById(idItem);
	    
	    if (item != null) {
	        
	        String email = session.get("usuario");
	        if (item.compra.usuario.email.equals(email)) {
	            item.delete();
	            flash.success("Item removido com sucesso.");
	        }
	    }
	    
	    detalhes();
	}
	
	public static void finalizarCompra() {
	    String email = session.get("usuario");
	    Usuario usuario = Usuario.find("email = ?1", email).first();
	    Compra carrinho = Compra.find("usuario = ?1 AND status = ?2", usuario, StatusCompra.CARRINHO).first();

	   
	    if (carrinho == null || carrinho.itens.size() == 0) {
	        flash.error("Carrinho vazio!");
	        detalhes();
	    }

	 
	    String texto = "Olá! Gostaria de finalizar meu pedido no Explora Gostoso.\n\n";
	    
	    texto += "*Cliente:* " + usuario.nome + "\n";
	    texto += "*Email:* " + usuario.email + "\n";
	    texto += "------------------\n";

	    
	    for (ItemCompra item : carrinho.itens) {
	        texto += "+ " + item.pacote.nome + " (Qtd: " + item.quantidade + ")\n";
	    }
	    
	    texto += "\n*Total a Pagar:* R$ " + carrinho.getValorTotal();

	    carrinho.status = StatusCompra.FINALIZADA;
	    carrinho.save();

	    try {
	        // Transforma espaços e quebras de linha em códigos de URL (%20, %0A)
	        String textoPronto = java.net.URLEncoder.encode(texto, "UTF-8");
	        
	        String telefone = "558491928201"; 
	        
	        String urlWhatsapp = "https://api.whatsapp.com/send?phone=" + telefone + "&text=" + textoPronto;
	        
	        redirect(urlWhatsapp);

	    } catch (Exception e) {
	        e.printStackTrace(); 
	        flash.error("Erro ao gerar link do WhatsApp, mas seu pedido foi salvo!");
	        detalhes();
	    }
	}
}
