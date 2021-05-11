package modelo.jugador.plantillas.tropasterrestres;

import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.tropas.ServicioColeccionTropas;

public class Artilleria extends EquipoAbstracto {


	public Artilleria() {
		super();
		tipo="artilleria";
		colaInicial=1; 
		costeDinero=4;
		valorAtaque=5; 
		valorDefensa=6;
	}

	
	@Override
	public String sobrevive(int tirada) {
		return ServicioColeccionTropas.supervivenciaEstandar(tirada);
	}

}
