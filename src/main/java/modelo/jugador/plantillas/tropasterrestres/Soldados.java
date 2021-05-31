package modelo.jugador.plantillas.tropasterrestres;

import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.tropas.ServicioColeccionTropas;

public class Soldados extends InfanteriaAbstracta {


	public Soldados() {
		super();
		nombre="soldados";
		colaInicial=1; 
		costeDinero=4;
		valorAtaque=7; 
		valorDefensa=6;
	}

	
	@Override
	public String sobrevive(int tirada) {
		return ServicioColeccionTropas.supervivenciaEstandar(tirada);
	}


	@Override
	public String mejora() {
		return "veteranos";
	}


	@Override
	public boolean puedeMejorar() {
		return true;
	}

}
