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
	
	/*Este método mira que tipo de combate utilizar en una activación con combate
	 *Si el jugador no tiene bandera, no hay combate
	 *Se hace una cuenta de banderas, que no esten vacías y un set de alianzas
	 *Si hay una sola alianza no hay combate
	 *Si hay dos alianzas se ve si se hace combate múltiple o simple
	 *Si hay tres o más alianzas se elige a quien se ataca y se va al efecto de 2 alianzas*/
	public static void SelectorCombateTerrestre(Jugador j, Territorio t) {
		//TODO
	}
	
}
