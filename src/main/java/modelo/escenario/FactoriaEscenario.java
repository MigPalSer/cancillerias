package modelo.escenario;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import modelo.jugador.FactoriaJugador;
import modelo.jugador.Jugador;
import modelo.territorio.FactoriaTerritorio;
import modelo.territorio.Territorio;
import modelo.tropas.Bandera;

public class FactoriaEscenario {

	public enum Mapas{
		ALFA1
	}
	
	public static Escenario crear(Mapas mapa) {
		Escenario e=new Escenario();
		
		switch (mapa) {
		case ALFA1:
			
			//Creamos y añadimos los jugadores al escenario
			
			Jugador franceses=FactoriaJugador.createJugador(10, "Francia");
			Jugador alemanes=FactoriaJugador.createJugador(10, "Alemania");
			
			HashMap<String, Jugador> jugadores=new HashMap<String,Jugador>();
			jugadores.put("Alemania", alemanes);
			jugadores.put("Francia", franceses);
			
			e.setJugadores(jugadores);
			
			//Creamos y añadimos los terrenos al escenario
			
			HashMap<Integer, Territorio> territorios=new HashMap<Integer, Territorio>();
			territorios.put(1, FactoriaTerritorio.crear(FactoriaTerritorio.Terrenos.PARIS, franceses));
			territorios.put(2, FactoriaTerritorio.crear(FactoriaTerritorio.Terrenos.BRUSELAS, franceses));
			territorios.put(3, FactoriaTerritorio.crear(FactoriaTerritorio.Terrenos.RENANIA, alemanes));

			e.setTerritorios(territorios);
			
			//Creamos y añadimos las conexiones de los terrenos al escenario
			
			HashMap<Integer, HashSet<Integer>> conexiones=new HashMap<Integer, HashSet<Integer>>();
			conexiones.put(1, new HashSet<Integer>());
			conexiones.put(2, new HashSet<Integer>());
			conexiones.put(3, new HashSet<Integer>());
			
			conexiones.get(1).add(2);
			conexiones.get(2).add(1);
			conexiones.get(2).add(3);
			conexiones.get(3).add(2);
			
			e.setConexiones(conexiones);
			
			//Hasta aquí la creación básica, introducimos ahora las banderas
			
			Bandera bandera1=new Bandera(franceses);
			bandera1.setInfanteria(5);
			bandera1.setArtilleria(2);
			e.getTerritorios().get(1).getTropas().put(franceses, bandera1);
			
			Bandera bandera2=new Bandera(franceses);
			bandera2.setInfanteria(5);
			bandera2.set("carros", 5);
			bandera2.set("aviones", 3);
			e.getTerritorios().get(2).getTropas().put(franceses, bandera2);
			
			Bandera bandera3=new Bandera(alemanes);
			bandera3.setInfanteria(10);
			bandera3.setArtilleria(5);
			bandera3.set("aviones", 3);
			e.getTerritorios().get(3).getTropas().put(alemanes, bandera3);
			
			break;

		default:
			break;
		}
		
		return e;

		
	}

}