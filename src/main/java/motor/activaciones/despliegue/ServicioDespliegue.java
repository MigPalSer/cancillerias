package motor.activaciones.despliegue;

import java.util.Collection;
import java.util.HashMap;

import modelo.jugador.*;
import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.territorio.Territorio;
import modelo.tropas.Bandera;
import modelo.tropas.ServicioColeccionTropas;
import modelo.tropas.cadena.SeccionCadena;
import motor.vista.ServicioMensajes;

public class ServicioDespliegue {
	
	
	//Método que gestiona toda la compra y despliegue de unidades
	static public void despliegue(Jugador j, Territorio t) {
		
		//Extraemos las capacidades máximas de produccion de soldadesca y equipo
		int soldadesca=t.getSoldadesca();
		int produccionindustrial=t.capacidadProduccionEquipo();
		SeccionCadena ct=j.getCadena().getActual();
		Bandera b=t.getTropas().get(j);
		
		ServicioMensajes.println("Tropas en reserva "+ct.toString());
		
		HashMap<String, Integer> tropasCola=ct.getTropas();
		Collection<String> tipos=tropasCola.keySet();
		
		for (String string : tipos) {
			//Descartamos los tipos que no tengan tropas en la cola
			if(tropasCola.get(string)!=0) {
				//Pedimos al jugador cuantas quiere desplegar
				ServicioMensajes.println("Despliegue: introduce "+string+" ; max. soldadesca: "+soldadesca+" ; max produccion industrial "+produccionindustrial);
				
				int peticion=j.getControlador().decidir("producir "+string);
				
				//Incluimos un if para evitar procesar peticiones nulas
				if(peticion>0) {
				int tropasdesplegadas=0;
					
				//Limitamos por las actuales
				int tropasdisponibles=peticion>ct.get(string)?ct.get(string):peticion;
				
				//Limitamos por las tropas del territorio y reducimos su capacidad de despliegue
				if(j.getModelo(string).getClasificacion()==Clasificacion.TERRESTRE_INFANTERIA) {
					
					tropasdesplegadas=tropasdisponibles>soldadesca?soldadesca:tropasdisponibles;
					soldadesca-=tropasdesplegadas;
					
				}else if(j.getModelo(string).getClasificacion()==Clasificacion.TERRESTRE_EQUIPO){
					
					tropasdesplegadas=tropasdisponibles>produccionindustrial?produccionindustrial:tropasdisponibles;
					produccionindustrial-=tropasdesplegadas;
					
				}else {
					ServicioMensajes.println("ERROR: Tipo de tropa no registrado");
				}
				
				//Aumentamos la bandera lo correspondiente y disminuimos la cola
				ServicioColeccionTropas.aumentar(b, tropasdesplegadas, string);
				ServicioColeccionTropas.reducir(ct, tropasdesplegadas, string);

				}
			}
		}
		
		
	
		
	}
	
}
