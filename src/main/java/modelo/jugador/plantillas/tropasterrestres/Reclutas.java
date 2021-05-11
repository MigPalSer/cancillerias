package modelo.jugador.plantillas.tropasterrestres;

import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.tropas.ServicioColeccionTropas;

public class Reclutas extends InfanteriaAbstracta {


	public Reclutas() {
		super();
		tipo="reclutas";
		colaInicial=0; 
		costeDinero=3;
		valorAtaque=8; 
		valorDefensa=5;
	}

	
	@Override
	public String sobrevive(int tirada) {
		return ServicioColeccionTropas.supervivenciaEstandar(tirada);
	}

}
