package jobs;

import java.util.ArrayList;

import models.Atividade;
import models.PacoteTuristico;
import models.Perfil;
import models.Status;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicia extends Job{
	 @Override
	public void doJob() throws Exception {
		if(Atividade.count() == 0) {
			Atividade a = new Atividade("Passeio para Galinhos");
			Atividade b = new Atividade("Trilha nas dunas");
			Atividade c = new Atividade("Pôr do Sol em Tourinhos");
			Atividade d = new Atividade("Passeio para perobas");
			Atividade e = new Atividade("Aventura de Quadriciclo");
			Atividade f = new Atividade("Exploração no litoral de gostoso");
			
			Usuario root = new Usuario("root","root@gmail.com","123456");
			root.perfil = Perfil.ADMINISTRADOR;
			root.save();
			
			Usuario teste = new Usuario("teste","teste@gmail.com","123456");
			teste.save();
			
			a.save();
			b.save();
			c.save();
			d.save();
			e.save();
			f.save(); 
			
			 // Pacote 1
            PacoteTuristico p1 = new PacoteTuristico(
                "Aventura Radical em Gostoso", 
                1250.00, 
                3, 
                "Para os amantes de adrenalina! Um pacote completo com as atividades mais emocionantes que a região pode oferecer."
            );
            p1.atividades = new ArrayList<Atividade>();
            p1.atividades.add(a); // Passeio de Buggy
            p1.atividades.add(d); // Aula de Kitesurf
            p1.atividades.add(e); // Aventura de Quadriciclo
            p1.save();

            // Pacote 2
            PacoteTuristico p2 = new PacoteTuristico(
                "Roteiro do Pôr do Sol",
                780.50,
                2,
                "Um final de semana perfeito para relaxar e apreciar as belezas naturais, culminando no pôr do sol de Tourinhos."
            );
            p2.atividades = new ArrayList<Atividade>();
            p2.atividades.add(c); // Pôr do Sol em Tourinhos
            p2.atividades.add(f); // Cavalgada na Praia
            p2.save();

            // Pacote 3
            PacoteTuristico p3 = new PacoteTuristico(
                "Imersão Marítima",
                990.00,
                2,
                "Explore as riquezas do mar de São Miguel do Gostoso. Ideal para quem ama a vida marinha."
            );
            p3.atividades = new ArrayList<Atividade>();
            p3.atividades.add(b); // Visita aos Parrachos
            p3.atividades.add(d); // Aula de Kitesurf
            p3.save();

            // Pacote 4
            PacoteTuristico p4 = new PacoteTuristico(
                "Pacote Família Completo",
                2100.00,
                4,
                "Diversão garantida para toda a família, com atividades seguras e emocionantes para todas as idades."
            );
            p4.atividades = new ArrayList<Atividade>();
            p4.atividades.add(a); // Passeio de Buggy
            p4.atividades.add(b); // Visita aos Parrachos
            p4.atividades.add(f); // Cavalgada na Praia
            p4.save();

            // Pacote 5
            PacoteTuristico p5 = new PacoteTuristico(
                "Especial de Férias (Promocional)",
                650.00,
                2,
                "Um pacote com preço especial para curtir o melhor de Gostoso em um final de semana. Vagas limitadas!"
            );
            p5.status = Status.ATIVO; // Exemplo de como sobrescrever o status padrão
            p5.atividades = new ArrayList<Atividade>();
            p5.atividades.add(a); // Passeio de Buggy
            p5.atividades.add(c); // Pôr do Sol em Tourinhos
            p5.save();

            // Pacote 6
            PacoteTuristico p6 = new PacoteTuristico(
                "Roteiro de Inverno (Encerrado)",
                550.00,
                2,
                "Pacote de junho."
            );
            p6.status = Status.ATIVO;
            p6.atividades = new ArrayList<Atividade>();
            p6.atividades.add(e); // Aventura de Quadriciclo
            p6.atividades.add(f); // Cavalgada na Praia
            p6.save();
		}
		
		
	}
}
