package controllers;

import java.util.List;

import models.Perfil;
import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;

public class Usuarios extends Controller{
	
	public static void form() {
		Usuario u = (Usuario) Cache.get("usuario");
		render(u);
	}
	
	public static void salvar(Usuario u, String senha) {
	
		
		if(senha.equals("") == false) {
			u.senha = senha;
			
			if(senha.length() < 6) {
				validation.addError("u.senha", "A senha de conter pelo menos 6 digitos");
			}
		}
		
	
		
		validation.valid(u);

		if (validation.hasErrors()) {
			validation.keep();
			Cache.set("usuario", u);
			params.flash();
			flash.error("Falha ao tentar criar o usuário");
			form();
		}
		
		Usuario usu = Usuario.find("email = ?1", u.email).first();
		
		if(usu == null) {
			u.save();
			flash.success("Usuário cadastrado com sucesso!");
		
			session.put("usuario", u.email);
			session.put("usuarioperfil", u.perfil.name());
			if(session.get("usuarioperfil").equals(Perfil.ADMINISTRADOR.name())) {
				session.put("adm", u.perfil.name());
			}
			if(session.get("usuarioperfil").equals(Perfil.CLIENTE.name())) {
				session.put("cliente", u.perfil.name());
			}
			Pacotes.inicio();      
		}else {
			flash.error("Email já cadastrado, tente outro!");
			form();
			
		} 
	
		
	}
}
