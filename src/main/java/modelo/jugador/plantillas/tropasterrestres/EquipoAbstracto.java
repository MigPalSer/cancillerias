package modelo.jugador.plantillas.tropasterrestres;

import modelo.jugador.plantillas.ModeloUnidad;

public abstract class EquipoAbstracto extends ModeloUnidad {

	public EquipoAbstracto() {
		super();
		clasificacion=Clasificacion.TERRESTRE_EQUIPO;
	}

	public String mejora() {
		return null;
	};
	
	public boolean puedeMejorar() {
		return false;
	}

}
