package motor.activaciones;

import java.util.HashSet;

import modelo.escenario.Escenario;
import modelo.jugador.*;
import modelo.territorio.Territorio;
import modelo.tropas.Bandera;
import motor.activaciones.despliegue.ServicioDespliegue;
import motor.activaciones.mover.ServicioConexiones;
import motor.activaciones.mover.ServicioMovimientos;
import motor.vista.*;

public class ActivacionReforzar extends Activacion {

	//No tiene responsabilidad de comprobar que el territorio a activar sea disputado 	
	//Elige tropas y las mueve con distancia 2, simplemente.
	
	public void activar(Escenario e, Jugador j, int idterritorio) {
		
		//Traemos el territorio activado
		Territorio t=e.getTerritorios().get(idterritorio);
		
		//Mensaje que anuncie la activacion
		ServicioMensajes.println(j.getNombre()+" refuerza en "+t.getNombre());
		
		//Crea la bandera si es necesario y la trae
		ServicioMovimientos.crearBanderaSiEsNecesario(j, t);
		Bandera banderadestino=t.getTropas().get(j);

		//Obtiene el hashset de territorios de origen posibles
		HashSet<Territorio> ts=ServicioConexiones.territoriosOrigen(e, j, t, 2, false);
		
		//Realiza las decisiones de reforzar
		ServicioMovimientos.ReforzarDesdeTerritoriosDeOrigen(ts, j, banderadestino);
		
		//Pasa por consola el estado final
		ServicioMensajes.tropasContendientes(t);
		
		//Produce si corresponde
		if(t.getPropietario().equals(j)) {
			ServicioDespliegue.despliegue(j, t);
			}
		}

}
