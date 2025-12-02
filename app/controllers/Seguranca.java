package controllers;

import models.Compra;
import models.ItemCompra;
import models.Perfil;
import models.StatusCompra;
import models.Usuario;
import play.mvc.Before;
import play.mvc.Controller;
import seguranca.Administrador;

public class Seguranca extends Controller{
	
	@Before
	static void carregarQuantidadeCarrinho() {
	    String email = session.get("usuario");
	    
	    if (email != null) {
	        Usuario u = Usuario.find("email = ?1", email).first();
	        
	        
	        Compra carrinho = Compra.find("usuario = ?1 AND status = ?2", u, StatusCompra.CARRINHO).first();
	        
	        if (carrinho != null) {
	            Long qtd = ItemCompra.count("compra = ?1", carrinho);
	            renderArgs.put("qtdItensCarrinho", qtd);
	        } else {
	            renderArgs.put("qtdItensCarrinho", 0);
	        }
	    }
	}
	
	@Before
	static void verificarAutenticacao() {
		if (!session.contains("usuario")) {
			flash.error("Você deve logar no sistema.");
			Login.form();
		}
	}
	
	
	@Before
	static void verificarAdministracao() {
		String perfil = session.get("usuarioperfil");
		Administrador adminAnnotation = getActionAnnotation(Administrador.class);
		if(adminAnnotation != null && !Perfil.ADMINISTRADOR.name().equals(perfil)) {
			forbidden("Acesso restrito aos administradores do sistema");
		}
	}
}
