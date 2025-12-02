package controllers;

import java.util.ArrayList;
import java.util.List;

import play.cache.Cache;
import play.mvc.Controller;

public class Carrinho extends Controller{
	
	public static void detalhes() {
		render();
	}
	
	public static void adicionarAjax(Long idPacote) {
	    
	    List<Long> itens = Cache.get(session.getId() + "-carrinho", List.class);
	    if(itens == null) {
	    	itens = new ArrayList<Long>();
	    }
	    
	    itens.add(idPacote);
	    Cache.set(session.getId() + "-carrinho", itens);
	    
	    
	    renderJSON(itens.size());
	}
}
