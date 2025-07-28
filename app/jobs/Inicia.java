package jobs;

import models.Atividade;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicia extends Job{
	 @Override
	public void doJob() throws Exception {
		if(Atividade.count() == 0) {
			Atividade a = new Atividade("Passeio de Buggy");
			Atividade b = new Atividade("Visita aos Parrachos");
			Atividade c = new Atividade("Pôr do Sol em Tourinhos");
			Atividade d = new Atividade("Aula de Kitesurf");
			Atividade e = new Atividade("Aventura de Quadriciclo");
			Atividade f = new Atividade("Cavalgada na Praia");
			
			a.save();
			b.save();
			c.save();
			d.save();
			e.save();
			f.save(); 
		}
	}
}
