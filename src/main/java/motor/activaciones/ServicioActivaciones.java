package motor.activaciones;

import modelo.jugador.*;
import modelo.territorio.Territorio;
import motor.activaciones.despliegue.ServicioDespliegue;

//Servicios menores para las activaciones, lógicas recurrentes
public class ServicioActivaciones {

	//Decide si hay despliegue de tropas o no
	public static void Despliegue(Jugador j, Territorio t) {
		
		if(t.getPropietario().equals(j)) {
			ServicioDespliegue.despliegue(j, t);
			}
	}
	
}
