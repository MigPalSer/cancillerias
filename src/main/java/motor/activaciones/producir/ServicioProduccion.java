package motor.activaciones.producir;

import modelo.bandera.ServicioBanderas;
import modelo.jugador.*;
import modelo.territorio.Territorio;
import motor.vista.ServicioMensajes;

public class ServicioProduccion {
	
	//Su responsabilidad es manejar la economia de cada jugador e interactuar con serviciodespliegue
	
	static int costeinfanteria, costeartilleria;
	
	//Declaracion de los costes de las tropas
	static {
		costeinfanteria=3;
		costeartilleria=4;
	}
	
	static public boolean puedePagar(Jugador j, int infanteria, int artilleria) {
		return j.getDinero()>=((infanteria*costeinfanteria)+(artilleria*costeartilleria));
	}
	
	static public void producir(Jugador j, Territorio t, int infanteria, int artilleria) {
		
		if(puedePagar(j, infanteria, artilleria)&&t.getPropietario().equals(j)&&infanteria<=t.getSoldadesca()) {
			int dinero=j.getDinero();
			int coste=(infanteria*costeinfanteria)+(artilleria*costeartilleria);
			j.setDinero(dinero-coste);
			
			ServicioBanderas.desplegar(j, t, infanteria, artilleria);
				
		}else {
			//ServicioMensajes.alerta("La producción no tuvo lugar");
		}
	}
	
	static public void produccion(Jugador j, Territorio t) {
		
		ServicioMensajes.println("Produccion: introduce infanteria");
		int infanteria=j.getControlador().decidir("producir infanteria");
		ServicioMensajes.println("Produccion: introduce artilleria");
		int artilleria=j.getControlador().decidir("producir artilleria");
		producir(j, t, infanteria, artilleria);
		
	}
	
}
