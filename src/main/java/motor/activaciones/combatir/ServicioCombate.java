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
