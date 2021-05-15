package modelo.jugador.plantillas.tropasterrestres;

import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.tropas.ServicioColeccionTropas;

public class Veteranos extends InfanteriaAbstracta {


	public Veteranos() {
		super();
		tipo="veteranos";
		colaInicial=-1; 
		costeDinero=0;
		valorAtaque=7; 
		valorDefensa=5;
	}

	
	@Override
	public String sobrevive(int tirada) {
		return ServicioColeccionTropas.supervivenciaEstandar(tirada);
	}


	@Override
	public String mejora() {
		return null;
	}


	@Override
	public boolean puedeMejorar() {
		return false;
	}

}
