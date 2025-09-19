package controllers;

import models.Perfil;
import play.mvc.Before;
import play.mvc.Controller;
import seguranca.Administrador;

public class Seguranca extends Controller{
	
	
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
