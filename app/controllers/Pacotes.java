package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Atividade;
import models.PacoteTuristico;
import play.mvc.Controller;

public class Pacotes extends Controller {

	public static void form() {
		List<Atividade> a = Atividade.findAll();
		render(a);
	}

	public static void salvar(PacoteTuristico pacote, Long[] atividadesIds) {

		 // 1. Limpa a lista atual para evitar duplicatas em edições futuras
        pacote.atividades = new ArrayList<Atividade>();

        // 2. Verifica se algum checkbox foi marcado
        if (atividadesIds != null) {
            // 3. Itera sobre o array de IDs que veio do formulário
            for (Long id : atividadesIds) {
                // 4. Para cada ID, busca a Atividade correspondente no banco
                Atividade atividade = Atividade.findById(id);
                if (atividade != null) {
                    // 5. Adiciona a atividade encontrada à lista do pacote
                    pacote.atividades.add(atividade);
                }
            }
        }

        // 6. Salva o pacote. O JPA cuidará de preencher a tabela de junção (ManyToMany)
        pacote.save();

        flash.success("Pacote '%s' salvo com sucesso!", pacote.nome);
        // Redireciona para a página de listagem
        lista(); 
		

	}

	public static void inicio() {
		render();
	}

	public static void lista() {
		render();
	}

}
