package motor.partida;

import modelo.escenario.Escenario;
import modelo.jugador.*;
import modelo.territorio.Territorio;

public class ServicioIngresos {

	public static void producir(Escenario e, Jugador j) {
	
		int dinero=0;
		
		for (int i = 1; i <= e.getTerritorios().size() ; i++) {
			Territorio t=e.getTerritorios().get(i);
			if(t.getPropietario().equals(j)&&!t.isDisputado()) {
				dinero+=t.getProduccion();
			}
		}
		
		ServicioJugador.ingresar(j, dinero);;
	}
	
}
