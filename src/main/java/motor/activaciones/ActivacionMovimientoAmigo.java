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

public class ActivacionMovimientoAmigo extends Activacion {

	//No tiene responsabilidad de comprobar que el territorio a activar sea amigo
	//Elige tropas y las mueve con distancia 2, simplemente.
	
	public void activar(Escenario e, Jugador j, int idterritorio) {
		
				//Traemos el territorio activado
				Territorio t=e.getTerritorios().get(idterritorio);
				
				//Mensaje que anuncie la activacion
				ServicioMensajes.println(j.getNombre()+" mueve a "+t.getNombre());
				
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
				
				//TODE Anterior método activar
				/*
		HashSet<Territorio> ts=ServicioConexiones.territoriosConectados(e, e.getTerritorios().get(idterritorio), 2);
		
		Territorio t=e.getTerritorios().get(idterritorio);
		
		if(!t.getTropas().containsKey(j)) {
			t.getTropas().put(j, new Bandera(j));
		}
		
		Bandera banderadestino=t.getTropas().get(j);
		
		HashSet<Territorio> tr=new HashSet<Territorio>();
		for (Territorio territorio : ts) {
			if(territorio.isActivado()) {
				tr.add(territorio);
			}
			else if(!territorio.isDisputado()&&!territorio.getPropietario().equals(j)) {
				tr.add(territorio);
			}
		}
		ts.removeAll(tr);
		
		for (Territorio territorio : ts) {
			if(territorio.getTropas().containsKey(j)) {
			ServicioMensajes.tropasQuePuedenReforzar(territorio, j);
			int inf=j.getControlador().decidir("Cuánta infanteria");
			int art=j.getControlador().decidir("Cuánta artilleria");
			Bandera banderaorigen=territorio.getTropas().get(j);
			ServicioBanderas.transferir(banderaorigen, banderadestino, inf, art);
			}
		}
		
		
		ServicioMensajes.tropasFinales(t, j);
		
		ServicioProduccion.produccion(j, t);
		*/
	}

}
