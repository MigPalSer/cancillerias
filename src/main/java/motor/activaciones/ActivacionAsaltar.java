package motor.activaciones;

import java.util.HashSet;

import modelo.escenario.Escenario;
import modelo.jugador.*;
import modelo.territorio.Territorio;
import modelo.tropas.Bandera;
import motor.activaciones.combatir.ServicioCombate;
import motor.activaciones.combatir.ServicioEnemigos;
import motor.activaciones.mover.ServicioConexiones;
import motor.activaciones.mover.ServicioMovimientos;
import motor.partida.ServicioTerritorios;
import motor.vista.*;

public class ActivacionAsaltar extends Activacion {

	//No tiene responsabilidad de comprobar que el territorio a activar sea en disputa 	
	//Elige tropas y las mueve con distancia 1, simplemente, luego resuelve un combate.
	
	public void activar(Escenario e, Jugador j, int idterritorio) {
		
			/////////////////MOVIMIENTO////////////////
		
				//Traemos el territorio activado
				Territorio t=e.getTerritorios().get(idterritorio);
				
				//Mensaje que anuncie la activacion
				ServicioMensajes.println(j.getNombre()+" asalta "+t.getNombre());
				
				//Crea la bandera si es necesario y la trae
				ServicioMovimientos.crearBanderaSiEsNecesario(j, t);
				Bandera banderadestino=t.getTropas().get(j);

				//Obtiene el hashset de territorios de origen posibles
				HashSet<Territorio> ts=ServicioConexiones.territoriosOrigen(e, j, t, 1, false);
				
				//Realiza las decisiones de reforzar
				ServicioMovimientos.ReforzarDesdeTerritoriosDeOrigen(ts, j, banderadestino);

			//////////////////COMBATE//////////////////////	
				//Definir enemigos, solo se instanciará si hay combate
				//Combatir
				ServicioTerritorios.actualizaDisputa(t);
				if(t.isDisputado()) {
					Bandera banderaenemiga=ServicioEnemigos.tropasEnemigas(t, j);
					ServicioCombate.combateTerrestre1a1(banderadestino, banderaenemiga);
				}
				
				///////////ACTUALIZACIÓN Y MENSAJES////////////////////
				
				//Actualizar territorio
				ServicioTerritorios.actualizaTerritorio(t, j);

				//Pasa por consola el estado final
				ServicioMensajes.tropasContendientes(t);
		
	}

}
