package modelo.territorio;

import java.util.HashMap;

import modelo.bandera.Bandera;
import modelo.jugador.Jugador;

public class Territorio {

	//Responsabilidad: contener los datos estáticos (identificación, recursos, nombre)
	//y dinamicos (propietario, disputado, ya activado, las diferentes tropas en el territorio)
	
	int produccion, id, soldadesca;
	String nombre;
	Jugador propietario;
	HashMap<Jugador, Bandera> tropas;
	boolean activado, disputado;
	
	
	
	public int getSoldadesca() {
		return soldadesca;
	}
	public void setSoldadesca(int soldadesca) {
		this.soldadesca = soldadesca;
	}
	public int getProduccion() {
		return produccion;
	}
	public void setProduccion(int produccion) {
		this.produccion = produccion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Jugador getPropietario() {
		return propietario;
	}
	public void setPropietario(Jugador propietario) {
		this.propietario = propietario;
	}
	public HashMap<Jugador, Bandera> getTropas() {
		return tropas;
	}
	public void setTropas(HashMap<Jugador, Bandera> tropas) {
		this.tropas = tropas;
	}
	public boolean isActivado() {
		return activado;
	}
	public void setActivado(boolean activado) {
		this.activado = activado;
	}
	public boolean isDisputado() {
		return disputado;
	}
	public void setDisputado(boolean disputado) {
		this.disputado = disputado;
	}
	public Territorio() {
	}

	
	
	
}
