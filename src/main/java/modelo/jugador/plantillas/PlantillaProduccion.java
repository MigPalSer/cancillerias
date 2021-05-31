package modelo.jugador.plantillas;

import java.util.HashMap;
import java.util.HashSet;

public class PlantillaProduccion {

	public enum Opcion{
		RECLUTAS(0, 3, "infanteria"), ARTILLERIA(1, 4, "artilleria"), CARROS(1, 6, "carros");
		private int colaInicial, costeDinero;
		private String tipo;
		private Opcion(int cola, int coste, String tipo) {
			this.colaInicial=cola; this.costeDinero=coste; this.tipo=tipo;
		}
		public int getCola() {
			return colaInicial;
		}
		public int getCoste() {
			return costeDinero;
		}
		public String getTipo() {
			return tipo;
		}
		
	}
	
	HashSet<ModeloUnidad> opciones;
	HashMap<String, ModeloUnidad> mapaOpciones;

	public HashSet<ModeloUnidad> getOpciones() {
		return opciones;
	}

	public void setOpciones(HashSet<ModeloUnidad> opciones) {
		this.opciones = opciones;
		generaMapaOpciones();
	}
	
	public ModeloUnidad get(String s) {
		if(!mapaOpciones.containsKey(s))generaMapaOpciones();
		return mapaOpciones.get(s);
	}
	
	void generaMapaOpciones() {
		mapaOpciones=new HashMap<String, ModeloUnidad>();
		for (ModeloUnidad modeloUnidad : opciones) {
			mapaOpciones.put(modeloUnidad.getNombre(), modeloUnidad);
		}
	}
}
