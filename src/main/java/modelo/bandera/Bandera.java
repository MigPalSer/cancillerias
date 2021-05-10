package modelo.bandera;

import modelo.jugador.Jugador;
import modelo.tropas.ColeccionTropas;

public class Bandera extends ColeccionTropas {

	//Su responsabilidad es darnos la informaci�n de las fuerzas que hay en un determinado lugar
	//siempre interactuar� a trav�s de su territorio.
	
	protected Jugador propietario;
	
	
	public Jugador getPropietario() {
		return propietario;
	}
	public void setPropietario(Jugador propietario) {
		this.propietario = propietario;
	}
	
	//Peque�a funcionalidad para ver si una bandera est� vac�a
	public boolean isVacia() {
		boolean vacia;
		if(infanteria==0&&artilleria==0) {
			vacia=true;
		}else {
			vacia=false;
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
	
	
}
