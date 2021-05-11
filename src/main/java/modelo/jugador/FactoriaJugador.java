package modelo.jugador;

import modelo.jugador.plantillas.FactoriaPlantillaProduccion;
import modelo.jugador.plantillas.FactoriaPlantillaProduccion.Plantilla;
import modelo.tropas.cadena.FactoriaCadena;

public class FactoriaJugador {

	public static Jugador createJugador(String s) {
		Jugador j=new Jugador();
		
		j.setDinero(0);
		j.setNombre(s);		
		j.setCadena(FactoriaCadena.instanciaCadena());
		j.setPlantillaProduccion(FactoriaPlantillaProduccion.creaPlantilla(Plantilla.ESTANDAR));
		
		return j;
	}
	
	public static Jugador createJugador(int dinero, String s) {
		Jugador j=FactoriaJugador.createJugador(s);
		j.setDinero(dinero);
		return j;
	}

}
