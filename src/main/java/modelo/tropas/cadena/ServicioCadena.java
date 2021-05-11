package modelo.tropas.cadena;

import modelo.tropas.ServicioColeccionTropas;

public class ServicioCadena {

	public static void avanzarCadena(CadenaProduccion c) {
		SeccionCadena c0=c.getActual();
		SeccionCadena c1=c.getProximo_turno();
		SeccionCadena c2=c.getDos_turnos();
		
		ServicioColeccionTropas.transferirTodo(c1, c0);
		ServicioColeccionTropas.transferirTodo(c2, c1);
	}
	
	
	
}
