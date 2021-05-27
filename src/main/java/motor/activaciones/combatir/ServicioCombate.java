package motor.activaciones.combatir;

import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import modelo.jugador.Jugador;
import modelo.jugador.plantillas.TablaValores;
import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.territorio.Territorio;
import modelo.tropas.Bandera;
import modelo.tropas.ServicioColeccionTropas;
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

	public static void combateTerrestre1a1(Bandera banda_atacante, Bandera banda_defensora) {
		
		//Si ambas bandas tienen aviones se resuelve primero un combate aereo
		if(banda_atacante.tieneAviones()&&banda_defensora.tieneAviones()) {
			combateAereo(banda_atacante, banda_defensora);
		}
		//Extraemos los valores de cuánta infanteria tiene cada bando, servirán para las mejora
		int infanteria_atacante=banda_atacante.numeroInfanterias();
		int infanteria_defensora=banda_defensora.numeroInfanterias();		

		//Calculamos cuántos impactos hace cada bandera
		int impactos_al_atacante=calcularImpactosTotales(banda_defensora, false);
		int impactos_al_defensor=calcularImpactosTotales(banda_atacante, true);
		
		//Comunicamos resultados
		ServicioMensajes.println("El atacante ha causado"+impactos_al_defensor+"impactos");
		ServicioMensajes.println("El defensor ha causado"+impactos_al_atacante+"impactos");

		//Asignamos los impactos
		ServicioDanho.asignacionImpactos(banda_atacante, impactos_al_atacante);
		ServicioDanho.asignacionImpactos(banda_defensora, impactos_al_defensor);
		
		//Mejoramos a los supervivientes
		banda_atacante.mejorarTropas(numeroMejoras(infanteria_defensora));
		banda_defensora.mejorarTropas(numeroMejoras(infanteria_atacante));

		//Disparamos la captura de equipo si corresponde
		capturarEquipo(banda_atacante, banda_defensora);

	}
	
	//Método encargado de realizar combates terrestres entre dos grupos de banderas
	public static void combateTerrestreMultiple(Set<Bandera> bandas_atacantes, Set<Bandera> bandas_defensoras) {
		
		
		//Si ambas bandas tienen aviones se resuelve primero un combate aereo
		boolean avionesatacantes=bandas_atacantes.stream().anyMatch(b->b.tieneAviones());
		boolean avionesdefensores=bandas_defensoras.stream().anyMatch(b->b.tieneAviones());

		if(avionesatacantes&&avionesdefensores) {
			combateAereoMultiple(bandas_atacantes, bandas_defensoras);
		}
		
		//Extraemos los valores de cuánta infanteria tiene cada bando, servirán para las mejora
		int infanteria_atacante=bandas_atacantes.stream().mapToInt(b->b.numeroInfanterias()).sum();
		int infanteria_defensora=bandas_defensoras.stream().mapToInt(b->b.numeroInfanterias()).sum();		

		//Calculamos cuántos impactos hace cada bandera
		int impactos_al_atacante=bandas_defensoras.stream()
				.mapToInt(b->calcularImpactosTotales(b, false)).sum();
		int impactos_al_defensor=bandas_atacantes
				.stream().mapToInt(b->calcularImpactosTotales(b, true)).sum();

		
		//Comunicamos resultados
		ServicioMensajes.println("Los atacantes han causado"+impactos_al_defensor+"impactos");
		ServicioMensajes.println("Los defensores han causado"+impactos_al_atacante+"impactos");

				HashMap<Bandera, Integer> impactosalosatacantes=ServicioColeccionTropas.asignarAlAzarBanderasSegunUnidades(dado, impactos_al_atacante, bandas_atacantes);
				HashMap<Bandera, Integer> impactosalosdefensores=ServicioColeccionTropas.asignarAlAzarBanderasSegunUnidades(dado, impactos_al_defensor, bandas_defensoras);

				//Asignamos los impactos
		bandas_atacantes.stream().forEach(b->ServicioDanho.asignacionImpactos(b, impactosalosatacantes.get(b)));
		bandas_defensoras.stream().forEach(b->ServicioDanho.asignacionImpactos(b, impactosalosdefensores.get(b)));
		//TODO CONTINUAR 
		/*
		//Mejoramos a los supervivientes
		banda_atacante.mejorarTropas(numeroMejoras(infanteria_defensora));
		banda_defensora.mejorarTropas(numeroMejoras(infanteria_atacante));

		//Disparamos la captura de equipo si corresponde
		capturarEquipo(banda_atacante, banda_defensora);
		*/
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

	
	public static void combateAereoMultiple(Set<Bandera> bandas_atacantes, Set<Bandera> bandas_defensoras) {

		//El combate aereo modifica directamente los aviones de las bandas, son 3 rondas actualizando bajas
		//y NO tiene supervivencia, se realiza en los combates terrestres si ambas bandas tienen aviones
		
		int aviones_atacantes=bandas_atacantes.stream().mapToInt(b->b.get("aviones")).sum();
		int aviones_defensores=bandas_defensoras.stream().mapToInt(b->b.get("aviones")).sum();
	
		//Creamos unos mapas que nos ayuden a asignar las bajas
		HashMap<String, Bandera> mapaatacantes=new HashMap<String, Bandera>();
		bandas_atacantes.stream().forEach(b->mapaatacantes.put(b.getPropietario().getNombre(), b));
		
		HashMap<String, Bandera> mapadefensores=new HashMap<String, Bandera>();
		bandas_defensoras.stream().forEach(b->mapadefensores.put(b.getPropietario().getNombre(), b));

		
		ServicioMensajes.println("Combate aereo múltiple iniciado");
		
		int rondas=0;
		
		do {
			rondas++;
			
			//Lanzamos los impactos
			int impactos_al_atacante=bandas_defensoras.stream()
					.mapToInt(b->disparoTerreste(b.get("aviones"), b.getValorAtaque("aviones", true)))
					.sum();
			int impactos_al_defensor=bandas_atacantes.stream()
					.mapToInt(b->disparoTerreste(b.get("aviones"), b.getValorAtaque("aviones", true)))
					.sum();
			
			//Creamos una lista de strings para distribuir los daños al azar y la barajamos
			List<String> aviones_por_propietario_atacantes=new ArrayList<String>();
			bandas_atacantes.stream().forEach(b->{
				String propietario=b.getPropietario().getNombre();
			for(int i = 0; i < b.get("aviones"); i++) {
				aviones_por_propietario_atacantes.add(propietario);
			}});
			Collections.shuffle(aviones_por_propietario_atacantes);
			
			List<String> aviones_por_propietario_defensores=new ArrayList<String>();
			bandas_defensoras.stream().forEach(b->{
				String propietario=b.getPropietario().getNombre();
			for(int i = 0; i < b.get("aviones"); i++) {
				aviones_por_propietario_defensores.add(propietario);
			}});
			Collections.shuffle(aviones_por_propietario_defensores);
			
			//Vamos asignando impactos en las listas ya barajadas
			asignarImpactosCombateAereoMúltiple(mapaatacantes, impactos_al_atacante, aviones_por_propietario_atacantes);
			asignarImpactosCombateAereoMúltiple(mapadefensores, impactos_al_defensor, aviones_por_propietario_defensores);
			
			//Recalculamos los aviones por bando

			aviones_atacantes=bandas_atacantes.stream().mapToInt(b->b.get("aviones")).sum();
			aviones_defensores=bandas_defensoras.stream().mapToInt(b->b.get("aviones")).sum();
		
			//Se hace otra ronda si quedan aviones de ambos bandos y no se ha llegado a las 3 rondas
		} while (aviones_atacantes>0&&aviones_defensores>0&&rondas<3);
		
		
	}
	
	public static void asignarImpactosCombateAereoMúltiple(HashMap<String, Bandera> mapa, int impactos, List<String> aviones_por_propietario) {
		if(impactos>aviones_por_propietario.size()) {
			mapa.values().stream().forEach(b->b.set("aviones", 0));
		}else {
			for (int i = 0; i < impactos; i++) {
				String s=aviones_por_propietario.get(i);
				mapa.get(s).reducir(1, "aviones");
			}
		}
	}
	
	//Este método se utiliza para que si una bandera se ha quedado sin infanterias pero la otra aún tiene (tras el combate) logre capturar todas las piezas de equipo
	public static void capturarEquipo(Bandera banda_atacante, Bandera banda_defensora) {
		int infa=banda_atacante.numeroInfanterias();
		int infd=banda_defensora.numeroInfanterias();
		if(infa!=0&&infd==0) {
			ServicioColeccionTropas.transferirTodo(banda_defensora, banda_atacante);
		}else if(infa==0&&infd!=0) {
			ServicioColeccionTropas.transferirTodo(banda_atacante, banda_defensora);
		}
		
	}

	public static int calcularImpactosTotales(Bandera b, boolean atacante) {
		int impactos=0;
		for (String s : b.getTropas().keySet()) {
			int numero=b.get(s);
			if(numero>0) {
				//Reduccion del número de equipos que intervienen, maximo por infanterias
			if(b.getModelo(s).getClasificacion().equals(Clasificacion.TERRESTRE_EQUIPO))numero=b.numeroInfanterias()>=numero?numero:b.numeroInfanterias();	
			
			int valor=b.getValorAtaque(s, atacante);
			impactos+=disparoTerreste(valor, numero);}
		}
		
		return impactos;
	}
	

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
	
	
	
	public static int numeroMejoras(int n) {
		//TORE
		ServicioMensajes.println("intentamos mejorar "+n);
		int mejoras=0;
		for (int i = 0; i < n; i++) {
			if(dado.tira(8))mejoras++;
		}
		//TORE
		ServicioMensajes.println("mejoramos "+mejoras);
		return mejoras;
	}
	
}
