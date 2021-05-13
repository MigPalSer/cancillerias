package modelo.jugador.plantillas;

import java.util.HashSet;

import modelo.jugador.plantillas.PlantillaProduccion.Opcion;
import modelo.jugador.plantillas.tropasterrestres.Artilleria;
import modelo.jugador.plantillas.tropasterrestres.Aviones;
import modelo.jugador.plantillas.tropasterrestres.Carros;
import modelo.jugador.plantillas.tropasterrestres.Reclutas;

public class FactoriaPlantillaProduccion {

	public enum Plantilla{
		ESTANDAR;
	}
	
	public static PlantillaProduccion creaPlantilla(Plantilla p) {
		PlantillaProduccion pp=new PlantillaProduccion();
		pp.setOpciones(new HashSet<ModeloUnidad>());
		if(p==Plantilla.ESTANDAR) {
			pp.getOpciones().add(new Reclutas());
			pp.getOpciones().add(new Artilleria());
			pp.getOpciones().add(new Carros());
			pp.getOpciones().add(new Aviones());

		}
		
		return pp;
	}
	
}
