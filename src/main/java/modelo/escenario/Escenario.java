package modelo.escenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import modelo.jugador.Jugador;
import modelo.territorio.Territorio;

public class Escenario {
	
	//Incluye los hashmap con los territorios y las conexiones, así como el hashset con los diferentes jugadores
	
	private HashMap<Integer, Territorio> territorios;
	//Mapea cada id con su territorio
	
	private HashMap<Integer, HashSet<Integer>> conexiones;
	//Mapea cada id de territorio con las ids de los territorios conectados
	
	private HashMap<String, Jugador> jugadores;
	
	
	public HashMap<Integer, Territorio> getTerritorios() {
		return territorios;
	}
	public void setTerritorios(HashMap<Integer, Territorio> territorios) {
		this.territorios = territorios;
	}

	public HashMap<String, Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(HashMap<String, Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	public HashMap<Integer, HashSet<Integer>> getConexiones() {
		return conexiones;
	}
	public void setConexiones(HashMap<Integer, HashSet<Integer>> conexiones) {
		this.conexiones = conexiones;
	}
	
	

	
	
}
