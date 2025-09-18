package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Atividade;
import models.PacoteTuristico;
import models.Status;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Pacotes extends Controller {

	public static void form() {
		List<Atividade> a = Atividade.findAll();
		render(a);
	}

	public static void salvar(PacoteTuristico pacote, Long[] atividadesIds) {

		 
        pacote.atividades = new ArrayList<Atividade>();

        // Verifica se algum checkbox foi marcado
        if (atividadesIds != null) {
            // Itera sobre o array de IDs que veio do formulário
            for (Long id : atividadesIds) {
                //  Para cada ID, busca a Atividade correspondente no banco
                Atividade atividade = Atividade.findById(id);
                if (atividade != null) {
                    
                    pacote.atividades.add(atividade);
                }
            }
        }

        
        pacote.save();

        flash.success("Pacote '%s' salvo com sucesso!", pacote.nome);
    
        detalhar(pacote.id); 
		

	}

	public static void detalhar(Long id) {
		PacoteTuristico pacote = PacoteTuristico.findById(id);
		render(pacote);
		
	}

	public static void inicio() {
		render();
	}

	public static void lista(String termo) {
		List<PacoteTuristico> pacotes = null;
		
		if(termo == null) {
			pacotes = PacoteTuristico.find("status <> ?1", Status.INATIVO).fetch();
		}else {
			
            String termoBusca = "%" + termo.toLowerCase() + "%";
            
            pacotes = PacoteTuristico.find(
                
                "SELECT DISTINCT p FROM PacoteTuristico p JOIN p.atividades a " +
                "WHERE p.status <> ?1 AND (LOWER(p.nome) LIKE ?2 OR LOWER(a.nome) LIKE ?2)"
                , Status.INATIVO, termoBusca
            ).fetch();
		}
		
		
		render(pacotes,termo);
	}
	
	public static void editar(Long id) {
		
		PacoteTuristico pacote = PacoteTuristico.findById(id);
		List<Atividade> a = Atividade.findAll();
		renderTemplate("Pacotes/form.html", a, pacote);
		
	}
	
public static void excluir(Long id) {
		
	PacoteTuristico pacote = PacoteTuristico.findById(id);

    if (pacote != null) {
        pacote.status = Status.INATIVO;
        pacote.save();
        flash.success("Pacote '%s' foi removido com sucesso.", pacote.nome);
    } else {
        flash.error("Pacote não encontrado.");
    }
    	listaadm(null);
		
	}
	
public static void listaadm(String termo) {
	List<PacoteTuristico> pacotes = null;
	
	if(termo == null) {
		pacotes = PacoteTuristico.findAll();
	}else {
		
        String termoBusca = "%" + termo.toLowerCase() + "%";
        
        pacotes = PacoteTuristico.find(
                "SELECT DISTINCT p FROM PacoteTuristico p LEFT JOIN p.atividades a " +
                        "WHERE LOWER(p.nome) LIKE ?1 OR LOWER(a.nome) LIKE ?1", termoBusca
                    ).fetch();
	}
	
	
	render(pacotes,termo);
}

}
