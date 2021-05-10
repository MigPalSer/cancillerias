package modelo.jugador;

import motor.decisor.Decision;

public class Jugador {

	//Responsabilidad: almacenar el nombre y el dinero de cada jugador. Servir para asignar
	//propiedad a otras clases (bandera, territorio...)
	
	int dinero;
	String nombre;
	Decision controlador;
	
	public int getDinero() {
		return dinero;
	}
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public Decision getControlador() {
		return controlador;
	}
	public void setControlador(Decision controlador) {
		this.controlador = controlador;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	public Jugador(int dinero, String nombre) {
		super();
		this.dinero = dinero;
		this.nombre = nombre;
		this.controlador=null;
	}
	
	
	
}
