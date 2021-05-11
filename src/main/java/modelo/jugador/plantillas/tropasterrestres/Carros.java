package modelo.jugador.plantillas.tropasterrestres;

import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.tropas.ServicioColeccionTropas;

public class Carros extends EquipoAbstracto {


	public Carros() {
		super();
		tipo="carros";
		colaInicial=1; 
		costeDinero=6;
		valorAtaque=7; 
		valorDefensa=7;
	}

	
	@Override
	public String sobrevive(int tirada) {
		return ServicioColeccionTropas.supervivenciaCarros(tirada);
	}

}
