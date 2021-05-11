package motor.activaciones.mover;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import modelo.jugador.Jugador;
import modelo.territorio.Territorio;
import modelo.tropas.Bandera;
import modelo.tropas.ServicioColeccionTropas;
import motor.vista.ServicioMensajes;

//Resuelve las decisiones y ejecucion de los movimientos
public class ServicioMovimientos {

	public static void ReforzarDesdeTerritoriosDeOrigen(HashSet<Territorio> ts, Jugador j, Bandera banderadestino) {
		
		//HashMap<String, Integer> tropasdestino=banderadestino.getTropas();
		
		//Para cada territorio desde el que se puede mover hace las preguntas de qué quiere mover y después las transfiere
		for (Territorio territorio : ts) {
			if(territorio.getTropas().containsKey(j)) {
			ServicioMensajes.tropasQuePuedenReforzar(territorio, j);
			Bandera banderaorigen=territorio.getTropas().get(j);
			HashMap<String, Integer> tropasorigen=banderaorigen.getTropas();
			Set<String> tipos=tropasorigen.keySet();
			
			for (String tipo : tipos) {
				int numero=j.getControlador().decidir("Cuántos "+tipo);
				if(numero>0)ServicioColeccionTropas.transferirTipo(banderaorigen, banderadestino, tipo, numero);
			}
			
						}
		}
	}

	//Crea una bandera del jugador en el territorio si no había ninguna
	public static void crearBanderaSiEsNecesario(Jugador j, Territorio t) {
		if(!t.getTropas().containsKey(j)) {
			t.getTropas().put(j, new Bandera(j));
		}	
	}

}
