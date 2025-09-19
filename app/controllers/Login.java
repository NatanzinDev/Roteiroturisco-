package controllers;

import models.Perfil;
import models.Usuario;
import play.mvc.Controller;

public class Login extends Controller {
	public static void logar(String email, String senha) {
		Usuario u = Usuario.find("email = ?1 and senha = ?2",
              	email, senha).first();
		
		if(u == null) {
			flash.error("Erro ao tentar logar");
			form();
		}else {
			session.put("usuario", u.email);
			session.put("usuarioperfil", u.perfil.name());
			if(session.get("usuarioperfil").equals(Perfil.ADMINISTRADOR.name())) {
				session.put("adm", u.perfil.name());
			}
			flash.success("Login bem sucessido.");
			Pacotes.lista(null);
		}
		
	}
	
	public static void logout() {
		session.clear(); //limpar a sessão
		flash.success("Você saiu do sistema!");
		form();
	}
	
	public static void form() {
		render();
	}
}
