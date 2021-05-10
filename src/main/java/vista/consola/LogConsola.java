package vista.consola;

import motor.vista.ObservadorVista;

public class LogConsola implements ObservadorVista {

	//El log es el encargado de imprimir todas las comunicaciones de la partida, así como las solicitudes de informacion
	
	@Override
	public void input(String s) {
		System.out.println(s);
	}

	
}
