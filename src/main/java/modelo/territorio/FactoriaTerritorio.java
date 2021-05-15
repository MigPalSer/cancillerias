package modelo.territorio;

import java.util.HashMap;

import modelo.edificios.Edificios;
import modelo.jugador.Jugador;
import modelo.tropas.Bandera;


public class FactoriaTerritorio {

	public enum Terrenos{
		PARIS(4, 1, 3, 1), BRUSELAS(2, 2, 1, 0), RENANIA(6, 3, 3, 2), ALSACIA(4, 4, 1, 2), SAJONIA(3, 5, 2, 1);
		private int produccion, id, soldadesca, fabricas;
		private Terrenos(int a, int b, int c, int d) {
			produccion=a;
			id=b;
			soldadesca=c;
			fabricas=d;
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
		public int getFabricas() {
			return fabricas;
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
		//Creamos el hashmap de tropas
		t.setTropas(new HashMap<Jugador, Bandera>());
		t.setSoldadesca(terreno.getSoldadesca());
		//Creamos una nueva instancia de edificios con las fabricas correspondientes
		Edificios edificios=new Edificios();
		edificios.setFabricas(terreno.getFabricas());
		t.setEdificios(edificios);
		
		return t;
	}

}


