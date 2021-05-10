package motor.activaciones.mover;

import java.util.HashSet;

import modelo.bandera.Bandera;
import modelo.bandera.ServicioBanderas;
import modelo.jugador.Jugador;
import modelo.territorio.Territorio;
import motor.vista.ServicioMensajes;

//Resuelve las decisiones y ejecucion de los movimientos
public class ServicioMovimientos {

	public static void ReforzarDesdeTerritoriosDeOrigen(HashSet<Territorio> ts, Jugador j, Bandera banderadestino) {
		
		//Para cada territorio desde el que se puede mover hace las preguntas de qu� quiere mover y despu�s las transfiere
		for (Territorio territorio : ts) {
			if(territorio.getTropas().containsKey(j)) {
			ServicioMensajes.tropasQuePuedenReforzar(territorio, j);
			int inf=j.getControlador().decidir("Cu�nta infanteria");
			int art=j.getControlador().decidir("Cu�nta artilleria");
			Bandera banderaorigen=territorio.getTropas().get(j);
			ServicioBanderas.transferir(banderaorigen, banderadestino, inf, art);
			}
		}
	}

	//Crea una bandera del jugador en el territorio si no hab�a ninguna
	public static void crearBanderaSiEsNecesario(Jugador j, Territorio t) {
		if(!t.getTropas().containsKey(j)) {
			t.getTropas().put(j, new Bandera(j));
		}	
	}

}
