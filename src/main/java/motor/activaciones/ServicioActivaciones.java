package motor.activaciones;

import modelo.jugador.*;
import modelo.territorio.Territorio;
import motor.activaciones.despliegue.ServicioDespliegue;

//Servicios menores para las activaciones, lógicas recurrentes
public class ServicioActivaciones {
	
	/*Este método mira que tipo de combate utilizar en una activación con combate
	 *Si el jugador no tiene bandera, no hay combate
	 *Se hace una cuenta de banderas, que no esten vacías y un set de alianzas
	 *Si hay una sola alianza no hay combate
	 *Si hay dos alianzas se ve si se hace combate múltiple o simple
	 *Si hay tres o más alianzas se elige a quien se ataca y se va al efecto de 2 alianzas*/
	public static void SelectorCombateTerrestre(Jugador j, Territorio t) {
		//Descartamos el combate si la bandera del jugador está vacía
		if(!t.getTropas().get(j).isVacia()) {
			
			long banderas=contarBanderas(t);
			long alianzas=contarAlianzas(t);
			
			//TODO
		}
	}
	
	public static long contarBanderas(Territorio t) {
		return t.getTropas().values().stream()
				.filter(b->!b.isVacia())
				.count();
	}
	
	public static Long contarAlianzas(Territorio t) {
		return t.getTropas().values().stream()
				.filter(b->!b.isVacia())				
				.map(b->b.getPropietario().getTagAlianza())
				.sorted()
				.distinct()
				.count();

	}
	
}
