package modelo.territorio;

import java.util.HashMap;

import modelo.bandera.Bandera;
import modelo.jugador.Jugador;


public class FactoriaTerritorio {

	public enum Terrenos{
		PARIS(4, 1, 3), BRUSELAS(2, 2, 1), RENANIA(6, 3, 3);
		private int produccion, id, soldadesca;
		private Terrenos(int a, int b, int c) {
			produccion=a;
			id=b;
			soldadesca=c;
		}
		public int getProduccion() {
			return produccion;
		}
		public int getId() {
			return id;
		}
		public int getSoldadesca() {
			return soldadesca;
		}
	}
	
	public static Territorio crear(Terrenos terreno, Jugador jugador) {
		Territorio t=new Territorio();
		t.setActivado(false);
		t.setDisputado(false);
		t.setId(terreno.getId());
		t.setNombre(terreno.toString());
		t.setProduccion(terreno.getProduccion());
		t.setPropietario(jugador);
		t.setTropas(new HashMap<Jugador, Bandera>());
		t.setSoldadesca(terreno.getSoldadesca());
		return t;
	}

}


