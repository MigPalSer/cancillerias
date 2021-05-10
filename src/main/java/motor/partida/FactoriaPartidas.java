package motor.partida;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import modelo.escenario.Escenario;
import modelo.escenario.FactoriaEscenario;
import modelo.escenario.FactoriaEscenario.Mapas;
import modelo.jugador.Jugador;
import motor.activaciones.Activacion;
import motor.activaciones.ActivacionAsaltar;
import motor.activaciones.ActivacionInvadir;
import motor.activaciones.ActivacionMovimientoAmigo;
import motor.activaciones.ActivacionReforzar;
import motor.decisor.Decision;
import vista.consola.ControladorVentanaBasico;

public class FactoriaPartidas {

	public enum Partidas{
		ALFA1
	}
	
	public static Partida crear(Partidas partida) {
		Partida p=null;
		
		switch (partida) {
		case ALFA1:
			
			Escenario escenario=FactoriaEscenario.crear(Mapas.ALFA1);
			
			
			Activacion asaltar=new ActivacionAsaltar();
			Activacion invadir=new ActivacionInvadir(); 
			Activacion movimientoamigo=new ActivacionMovimientoAmigo();
			Activacion reforzar=new ActivacionReforzar(); 
			ArrayList<Jugador> ordendejuego=new ArrayList<Jugador>();
			Jugador alemania=escenario.getJugadores().get("Alemania");
			Jugador francia=escenario.getJugadores().get("Francia");
			ordendejuego.add(alemania);
			ordendejuego.add(francia);
			Decision controlador=new ControladorVentanaBasico();
			alemania.setControlador(controlador);
			francia.setControlador(controlador);
			
			p=new Partida(escenario, asaltar, invadir, movimientoamigo, reforzar, ordendejuego);
			break;

		default:
			break;
		}
		
		return p;

		
	}

}