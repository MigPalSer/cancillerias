package modelo.jugador.plantillas.tropasterrestres;

import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.tropas.ServicioColeccionTropas;

public class Aviones extends EquipoAbstracto {


	public Aviones() {
		super();
		tipo="aviones";
		colaInicial=1; 
		costeDinero=10;
		valorAtaque=8; 
		valorDefensa=8;
		aviacion=true;
	}

	
	@Override
	public String sobrevive(int tirada) {
		return ServicioColeccionTropas.supervivenciaEstandar(tirada);
	}

}
