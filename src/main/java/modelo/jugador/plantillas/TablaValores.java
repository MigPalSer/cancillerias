package modelo.jugador.plantillas;

import motor.vista.ServicioMensajes;

public class TablaValores {

	public static int valorAtaqueTerrestre(String tipo) {
		int valor=0;
		
		switch (tipo) {
		case "infanteria":
			valor=8;
			break;
		case "artilleria":
			valor=5;
			break;
		case "carros":
			valor=7;
	break;
		default:
			ServicioMensajes.println("ERROR tabla de valores invocada para un tipo de tropa incorrecto: "+tipo);
			break;
		}
		
		return valor;
	}
	
	public static int valorDefensaTerrestre(String tipo) {
		int valor=0;
		
		switch (tipo) {
		case "infanteria":
			valor=6;
			break;
		case "artilleria":
			valor=6;
			break;
		case "carros":
			valor=7;
		default:
			ServicioMensajes.println("ERROR tabla de valores invocada para un tipo de tropa incorrecto: "+tipo);
			break;
		}
		
		return valor;
	}
	
}
