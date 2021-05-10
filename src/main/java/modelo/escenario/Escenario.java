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
	
	/* Funcionalidad pasada a partida TD
	//Pone en orden los jugadores para el turno
	private ArrayList<String> ordenJugadores;
	private byte jugadoractual, numeroderondas;


	public ArrayList<String> getOrdenJugadores() {
		return ordenJugadores;
	}
	public void setOrdenJugadores(ArrayList<String> ordenJugadores) {
		this.ordenJugadores = ordenJugadores;
	}
	public byte getJugadoractual() {
		return jugadoractual;
	}
	public void setJugadoractual(byte jugadoractual) {
		this.jugadoractual = jugadoractual;
	}
	public byte getNumeroderondas() {
		return numeroderondas;
	}
	public void setNumeroderondas(byte numeroderondas) {
		this.numeroderondas = numeroderondas;
	}
	*/
	
	
}
