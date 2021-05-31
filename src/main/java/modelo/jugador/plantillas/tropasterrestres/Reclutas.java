package modelo.jugador.plantillas.tropasterrestres;

import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.tropas.ServicioColeccionTropas;

public class Reclutas extends InfanteriaAbstracta {


	public Reclutas() {
		super();
		nombre="reclutas";
		colaInicial=0; 
		costeDinero=3;
		valorAtaque=8; 
		valorDefensa=6;
	}

	
	@Override
	public String sobrevive(int tirada) {
		return ServicioColeccionTropas.supervivenciaEstandar(tirada);
	}


	@Override
	public String mejora() {
		return "soldados";
	}


	@Override
	public boolean puedeMejorar() {
		return true;
	}

}
