package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller{
	
	
	@Before
	static void verificarAutenticacao() {
		if (!session.contains("usuario")) {
			flash.error("Você deve logar no sistema.");
			Login.form();
		}
	}
}
