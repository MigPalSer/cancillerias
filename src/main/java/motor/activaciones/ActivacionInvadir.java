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

public class ActivacionInvadir extends Activacion {

	//No tiene responsabilidad de comprobar que el territorio a activar sea enemigo sin disputar 	
	//Elige tropas y las mueve con distancia 1, simplemente, luego resuelve un combate.
	
	public void activar(Escenario e, Jugador j, int idterritorio) {
		
		//Traemos el territorio activado
		Territorio t=e.getTerritorios().get(idterritorio);
		
		//Mensaje que anuncie la activacion
		ServicioMensajes.println(j.getNombre()+" invade "+t.getNombre());
		
		//Crea la bandera si es necesario y la trae
		ServicioMovimientos.crearBanderaSiEsNecesario(j, t);
		Bandera banderadestino=t.getTropas().get(j);

		//Obtiene el hashset de territorios de origen posibles
		HashSet<Territorio> ts=ServicioConexiones.territoriosOrigen(e, j, t, 1, true);
		
		//Realiza las decisiones de reforzar
		ServicioMovimientos.ReforzarDesdeTerritoriosDeOrigen(ts, j, banderadestino);
		
		//Definir enemigos, solo se instanciará si hay combate
		//Combatir
		ServicioTerritorios.actualizaDisputa(t);
		if(t.isDisputado()) {
			Bandera banderaenemiga=ServicioEnemigos.tropasEnemigas(t, j);
			ServicioCombate.combateTerrestre1a1(banderadestino, banderaenemiga);
		}

		
		//Actualizar territorio
		ServicioTerritorios.actualizaTerritorio(t, j);

		//Pasa por consola el estado final
		ServicioMensajes.tropasContendientes(t);
		
		/*
		
		if(campodebatalla.getTropas().containsKey(campodebatalla.getPropietario())){
		Bandera banderaenemiga=campodebatalla.getTropas().get(campodebatalla.getPropietario());
		
		ServicioCombate.combatir(banderadestino, banderaenemiga);
		}
		
		ServicioTerritorios.actualizaTerritorio(campodebatalla, j);
		
		
		
		ServicioMensajes.tropasContendientes(e.getTerritorios().get(idterritorio));
		*/
	}

}
