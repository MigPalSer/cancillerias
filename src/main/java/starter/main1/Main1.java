package starter.main1;

import motor.partida.FactoriaPartidas;
import motor.partida.Partida;
import motor.partida.FactoriaPartidas.Partidas;
import motor.vista.ServicioMensajes;
import vista.consola.LogConsola;

public class Main1 {

	public static void main(String[] args) {

		System.out.println("Nueva partida");
		
		Partida p=FactoriaPartidas.crear(Partidas.ALFA1);
		
		ServicioMensajes.addVista(new LogConsola());
		
		p.run();
	
		
		
	}

}
