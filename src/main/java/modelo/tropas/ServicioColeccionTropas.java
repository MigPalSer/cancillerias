package modelo.tropas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.jugador.Jugador;
import modelo.territorio.Territorio;

public class ServicioColeccionTropas {

	//Tiene responsabilidad de no acabar con tropas negativas
	
	public static void transferirTodo(ColeccionTropas emisor, ColeccionTropas receptor) {
		
		HashMap<String, Integer> tropasareducir=emisor.getTropas();
		
		for (String str : tropasareducir.keySet()) {
			int numero=tropasareducir.get(str);
			if(numero>0) {
				//Añadimos las tropas a la bandera receptora y las fijamos a 0 en la emisora
				ServicioColeccionTropas.aumentar(receptor, numero, str);
				tropasareducir.put(str, 0);
			}
		}
	
	}
	
	public static void transferirTipo(ColeccionTropas emisor, ColeccionTropas receptor, String tipo, int peticion) {
		
		//Evitamos peticiones que lleven a negativos
		int numero=peticion<=emisor.get(tipo)?peticion:emisor.get(tipo);
		
		reducir(emisor, numero, tipo);
		aumentar(receptor, numero, tipo);
	
	}

	//TODO Refactorizar referencias y sustituir por el método propio de colecciontropas
	public static void aumentar(ColeccionTropas tropa, int cantidad, String tipo) {
		tropa.aumentar(cantidad, tipo);
	}
	
	//TODO Refactorizar referencias y sustituir por el método propio de colecciontropas
	public static void reducir(ColeccionTropas tropa, int cantidad, String tipo) {
		tropa.reducir(cantidad, tipo);
	}
	

	//TODO Unificar el estilo con el anterior
	public static void clonarYAumentarTodo(ColeccionTropas emisor, ColeccionTropas receptor) {
		
	//CUIDADO, con este método no retiramos las tropas del emisor, sino que las añadimos "clonadas" al receptor

		HashMap<String, Integer> tropasareducir=emisor.getTropas();
		
		for (String str : tropasareducir.keySet()) {
			int numero=tropasareducir.get(str);
			if(numero>0) {
				//Añadimos las tropas a la bandera receptora, pero NO las fijamos a 0 en la emisora
				//Ver transferir
				ServicioColeccionTropas.aumentar(receptor, numero, str);
			}
		}
	
	}
	
	public static void clonarYRetirarTodo(ColeccionTropas original, ColeccionTropas reduccion) {
	//Cuidado!, con este método no reducimos las tropas de la reduccion, solo las de la original
		
		HashMap<String, Integer> tropasareducir=reduccion.getTropas();
		
		for (String str : tropasareducir.keySet()) {
			int numero=tropasareducir.get(str);
			if(numero>0) {
				ServicioColeccionTropas.reducir(original, numero, str);
			}
		}
		
	}
	
	//Devuelve un array de strings con los strings repetidos segun su proporcion
	public static List<String> toStringList(ColeccionTropas ct) {
		
		ArrayList<String> lista=new ArrayList<String>();
		
		HashMap<String, Integer> tropas=ct.getTropas();
		
		//Recorremos con dos bucles anidados para generar una entrada por cada tropa
		for (String string : tropas.keySet()) {
			
			for (int i = 0; i < tropas.get(string); i++) {
				lista.add(string);
			}
			
		}
		
		return lista;
	}

	
public static String supervivenciaEstandar(int tirada_supervivencia) {
		
		//Aseguramos un bono máximo de +4 en las tiradas
		if(tirada_supervivencia>12)tirada_supervivencia=12;
	
		String respuesta="";
		
			switch (tirada_supervivencia) {
			case 8:
			case 10:
			case 11:
				respuesta="cola+2";
				break;
			case 9:
			case 12:
				respuesta="impactoanulado";
				break;
			default:
				break;
			
		}
		
		return respuesta;
	}
	
public static String supervivenciaCarros(int tirada_supervivencia) {
	
	//Aseguramos un bono máximo de +4 en las tiradas
	if(tirada_supervivencia>12)tirada_supervivencia=12;
	
	String respuesta="";
	
	
		switch (tirada_supervivencia) {
		case 3:
		case 4:
		case 9:
			respuesta="cola+2";
			break;
		case 5:
		case 6:
		case 10:
		case 11:
			respuesta="cola+1";
			break;
		case 7:
		case 8:
		case 12:
			respuesta="impactoanulado";
			break;
		default:
			break;
		
	}
	
	return respuesta;
}

	//TODO Modificar los test de estos dos y eliminar
	@Deprecated
	public static void transferir(Bandera origen, Bandera destino, int envioinfanterias, int envioartillerias) {
	
		int infanteriasorigen=origen.getInfanteria();
		int infanteriasdestino=destino.getInfanteria();
		int infanteriastransferidas=infanteriasorigen>=envioinfanterias?envioinfanterias:infanteriasorigen;
		destino.setInfanteria(infanteriasdestino+infanteriastransferidas);
		origen.setInfanteria(infanteriasorigen-infanteriastransferidas);
		
		int artilleriasorigen=origen.getArtilleria();
		int artilleriasdestino=destino.getArtilleria();
		int artilleriastransferidas=artilleriasorigen>=envioartillerias?envioartillerias:artilleriasorigen;
		destino.setArtilleria(artilleriasdestino+artilleriastransferidas);
		origen.setArtilleria(artilleriasorigen-artilleriastransferidas);
	}

	@Deprecated
	public static void desplegar(Jugador j, Territorio t, int infanteria, int artilleria) {
		if(!t.getTropas().containsKey(j)) {
			t.getTropas().put(j, new Bandera(j));
		}
		
		int nuevasinf=infanteria+t.getTropas().get(j).getInfanteria();
		int nuevasart=artilleria+t.getTropas().get(j).getArtilleria();
		
		t.getTropas().get(j).setInfanteria(nuevasinf);
		t.getTropas().get(j).setArtilleria(nuevasart);
	}
	
	
	
}
