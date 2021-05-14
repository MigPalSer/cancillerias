package modelo.tropas;

import modelo.jugador.Jugador;
import modelo.jugador.plantillas.ModeloUnidad;
import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;

public class Bandera extends ColeccionTropas {

	//Su responsabilidad es darnos la información de las fuerzas que hay en un determinado lugar
	//siempre interactuará a través de su territorio.
	
	protected Jugador propietario;
	
	
	public Jugador getPropietario() {
		return propietario;
	}
	public void setPropietario(Jugador propietario) {
		this.propietario = propietario;
	}
	
	//Pequeña funcionalidad para ver si una bandera está vacía
	public boolean isVacia() {
		boolean vacia=true;
		
		for (Integer inte : tropas.values()) {
			if(inte>0)vacia=false;
		}
		
		return vacia;
	}
	
	public Bandera(Jugador propietario) {
		super();
		this.propietario = propietario;
		
	}
	
	public Bandera(Jugador propietario, int infanteria, int artilleria) {
		super(infanteria, artilleria);		
		this.propietario = propietario;

	}
	
	public int numeroInfanterias() {
		int infanterias=0;
		for (String s : tropas.keySet()) {
			//Dependencia de la plantilla, pero bueno
			if(propietario.getModelo(s).getClasificacion()==Clasificacion.TERRESTRE_INFANTERIA)infanterias+=tropas.get(s);
		}
		return infanterias;
	}
	public String toStringLog() {

		String s="Bandera de "+propietario.getNombre()+" : ";
		for(String str : tropas.keySet()) {
			s+=str+" "+tropas.get(str)+" -/- ";
		}
		return s;
		
	}
	public boolean tieneAviones() {
		boolean aviones=false;
		
		for (String s : tropas.keySet()) {
			if(tropas.get(s)>0&&getModelo(s).isAviacion()) {
				aviones=true;
			}
		}
		
		return aviones;
	}
	
	public ModeloUnidad getModelo(String s) {
		return propietario.getModelo(s);
	}

	
}
