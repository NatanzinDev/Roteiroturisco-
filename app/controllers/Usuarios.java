package controllers;

import java.util.List;

import models.Usuario;
import play.mvc.Controller;

public class Usuarios extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void salvar(String nome, String email, String senha,String senha2) {
		
		
		
		Usuario u = Usuario.find("email = ?1", email).first();
		
		if(u == null) {
			if(senha.equals(senha2)) {
				u.email = email;
				u.nome = nome;
				u.senha = senha;
				u.save();
				flash.success("Usuário cadastrado com sucesso!");
				session.put("usuario", u.email);
				Pacotes.lista(null);
			}else {
				flash.error("Senha inválidas, tente novamente");
				form();
			
		}
	}else {
		flash.error("Email já cadastrado, tente outro!");
		form();
	}
		
	}
}
