package motor.activaciones.combatir;

import java.util.Set;
import java.util.HashMap;
import java.util.Map.Entry;

import modelo.jugador.Jugador;
import modelo.jugador.plantillas.TablaValores;
import modelo.territorio.Territorio;
import modelo.tropas.Bandera;
import motor.dado.Dado;
import motor.dado.Dado8;
import motor.partida.ServicioTerritorios;
import motor.vista.ServicioMensajes;

public class ServicioCombate {

	private static Dado dado;
	
	static {
		dado=new Dado8();
	}
	
	public static Dado getDado() {
		return dado;
	}

	public static void setDado(Dado dado) {
		ServicioCombate.dado = dado;
	}

	//Consideramos por defecto que la bandera 1 es la que ataca y la b2 la que defiende
	public static void combateTerrestre(Bandera banda_atacante, Bandera banda_defensora) {
		
		if(banda_atacante.tieneAviones()&&banda_defensora.tieneAviones()) {
			combateAereo(banda_atacante, banda_defensora);
		}
		
		//TODO implementar limitacion equipo
		int infanteria_atacante=banda_atacante.numeroInfanterias();
		int infanteria_defensora=banda_defensora.numeroInfanterias();
		
		/*HashMap<String, Integer> tropasatacantes=banda_atacante.getTropas();
		HashMap<String, Integer> tropasdefensoras=banda_defensora.getTropas();
		
		for (String s : tropasatacantes.keySet()) {
			int numero=tropasatacantes.get(s);
			if(numero>0)impactos_al_defensor+=disparoTerrestre(s, numero, true);
		}
		
		for (String s : tropasdefensoras.keySet()) {
			int numero=tropasdefensoras.get(s);
			if(numero>0)impactos_al_atacante+=disparoTerrestre(s, numero, false);
		}
		*/
		

		int impactos_al_atacante=calcularImpactosTotales(banda_defensora, false);
		int impactos_al_defensor=calcularImpactosTotales(banda_atacante, true);
		
		ServicioMensajes.println("El atacante ha causado"+impactos_al_defensor+"impactos");
		ServicioMensajes.println("El defensor ha causado"+impactos_al_atacante+"impactos");

		ServicioDanho.asignacionImpactos(banda_atacante, impactos_al_atacante);
		ServicioDanho.asignacionImpactos(banda_defensora, impactos_al_defensor);
	}
	
	public static void combateAereo(Bandera banda_atacante, Bandera banda_defensora) {

		//El combate aereo modifica directamente los aviones de las bandas, son 3 rondas actualizando bajas
		//y NO tiene supervivencia, se realiza en los combates terrestres si ambas bandas tienen aviones
		
		//TODO generalizar para que pueda haber otros tipos de aviones
		int aviones_atacantes=banda_atacante.get("aviones");
		int aviones_defensores=banda_defensora.get("aviones");
		
		int valor_atacantes=banda_atacante.getModelo("aviones").getValorAtaque();
		int valor_defensores=banda_defensora.getModelo("aviones").getValorDefensa();
		
		ServicioMensajes.println("Combate aereo iniciado, los atacantes de "+banda_atacante.getPropietario().getNombre()+" con "+aviones_atacantes+" aviones, se enfrentan a los aviones defensores de "+
		banda_defensora.getPropietario().getNombre()+" con "+aviones_defensores+" aviones");
		
		int rondas=0;
		
		do {
			rondas++;
			
			//Lanzamos los impactos
			int impactos_al_atacante=disparoTerreste(valor_defensores, aviones_defensores);
			int impactos_al_defensor=disparoTerreste(valor_atacantes, aviones_atacantes);
			
			//Aplicamos las bajas o las situamos a 0 si han sobrepasado el número
			aviones_atacantes=aviones_atacantes>impactos_al_atacante?aviones_atacantes-impactos_al_atacante:0;
			aviones_defensores=aviones_defensores>impactos_al_defensor?aviones_defensores-impactos_al_defensor:0;
			
			//Se hace otra ronda si quedan aviones de ambos bandos y no se ha llegado a las 3 rondas
		} while (aviones_atacantes>0&&aviones_defensores>0&&rondas<3);
		
		ServicioMensajes.println("Combate aereo de "+rondas+" rondas, los atacantes de "+banda_atacante.getPropietario().getNombre()+" mantienen "+aviones_atacantes+" aviones; los aviones defensores de "+
		banda_defensora.getPropietario().getNombre()+" mantienen "+aviones_defensores+" aviones");
		
		//Actualizamos con los aviones supervivientes
		banda_atacante.set("aviones", aviones_atacantes);
		banda_defensora.set("aviones", aviones_defensores);
	}

	public static int calcularImpactosTotales(Bandera b, boolean atacante) {
		int impactos=0;
		for (String s : b.getTropas().keySet()) {
			int numero=b.get(s);
			int valor=obtenerValorAtaque(b, s, atacante);
			if(numero>0)impactos+=disparoTerreste(valor, numero);
		}
		
		return impactos;
	}
	/*
	public static int disparoTerreste(String tipo, int numero, boolean atacante, Dado dado) {
		int impactos=0;
		int dificultadimpacto=0;
		if(atacante) {
			dificultadimpacto=TablaValores.valorAtaqueTerrestre(tipo);
		}else {
			dificultadimpacto=TablaValores.valorDefensaTerrestre(tipo);
		}
		for (int i = 0; i < numero; i++) {
			if(dado.tira(dificultadimpacto))impactos++;
		}
		return impactos;
	}
	
	public static int disparoTerrestre (String tipo, int numero, boolean atacante) {
		return disparoTerreste(tipo, numero, atacante, dado);
	}*/
	

	public static int disparoTerreste(int valor, int numero, Dado dado) {
		int impactos=0;
		int dificultadimpacto=valor;
		for (int i = 0; i < numero; i++) {
			if(dado.tira(dificultadimpacto))impactos++;
		}
		return impactos;
	}
	
	
	public static int disparoTerreste(int valor, int numero) {
		return disparoTerreste(valor, numero, dado);
	}
	
	public static int obtenerValorAtaque(Bandera b, String tipo, boolean atacante) {
		int valor=9;
		if(atacante) {
			valor=b.getPropietario().getModelo(tipo).getValorAtaque();
		}else {
			valor=b.getPropietario().getModelo(tipo).getValorDefensa();
		}
		return valor;
	}
	
	
	
}
