package modelo.tropas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import modelo.jugador.Jugador;
import modelo.territorio.Territorio;
import motor.dado.Dado;

public class ServicioColeccionTropas {

	//Tiene responsabilidad de no acabar con tropas negativas
	
	public static void transferirTodo(ColeccionTropas emisor, ColeccionTropas receptor) {
		
		HashMap<String, Integer> tropasareducir=emisor.getTropas();
		
		for (String str : tropasareducir.keySet()) {
			int numero=tropasareducir.get(str);
			if(numero>0) {
				//A�adimos las tropas a la bandera receptora y las fijamos a 0 en la emisora
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

	//TODO Refactorizar referencias y sustituir por el m�todo propio de colecciontropas
	@Deprecated
	public static void aumentar(ColeccionTropas tropa, int cantidad, String tipo) {
		tropa.aumentar(cantidad, tipo);
	}
	
	
	//TODO Refactorizar referencias y sustituir por el m�todo propio de colecciontropas
	@Deprecated
	public static void reducir(ColeccionTropas tropa, int cantidad, String tipo) {
		tropa.reducir(cantidad, tipo);
	}
	

	//TODO Unificar el estilo con el anterior
	public static void clonarYAumentarTodo(ColeccionTropas emisor, ColeccionTropas receptor) {
		
	//CUIDADO, con este m�todo no retiramos las tropas del emisor, sino que las a�adimos "clonadas" al receptor

		HashMap<String, Integer> tropasareducir=emisor.getTropas();
		
		for (String str : tropasareducir.keySet()) {
			int numero=tropasareducir.get(str);
			if(numero>0) {
				//A�adimos las tropas a la bandera receptora, pero NO las fijamos a 0 en la emisora
				//Ver transferir
				ServicioColeccionTropas.aumentar(receptor, numero, str);
			}
		}
	
	}
	
	public static void clonarYRetirarTodo(ColeccionTropas original, ColeccionTropas reduccion) {
	//Cuidado!, con este m�todo no reducimos las tropas de la reduccion, solo las de la original
		
		HashMap<String, Integer> tropasareducir=reduccion.getTropas();
		
		for (String str : tropasareducir.keySet()) {
			int numero=tropasareducir.get(str);
			if(numero>0) {
				ServicioColeccionTropas.reducir(original, numero, str);
			}
		}
		
	}
	
	//TODO sustituir
	//Devuelve un array de strings con los strings repetidos segun su proporcion
	@Deprecated
	public static List<String> toStringList(ColeccionTropas ct) {
		
		return ct.toStringList();
	}

	
public static String supervivenciaEstandar(int tirada_supervivencia) {
		
		//Aseguramos un bono m�ximo de +4 en las tiradas
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
	
	//Aseguramos un bono m�ximo de +4 en las tiradas
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
	
	//Reparte una cantidad de algo, como impactos, en funci�n del n�mero de unidades, hasta un m�ximo de 1 por unidad.
	public static HashMap<Bandera, Integer> asignarAlAzarBanderasSegunUnidades
	(Dado dado, int cantidad, Set<Bandera> banderas){
		
		//Este m�todo es m�s feo y chapucero que pegar a un padre con un calcet�n sudao
		//Cambiar un d�a que est� m�s despejado //TODO
		
		HashMap<Bandera, Integer> mapa_unidades=new HashMap<Bandera, Integer>();		
		final HashMap<Bandera, Integer> mapa_asignaciones=new HashMap<Bandera, Integer>();
		HashMap<Integer, Bandera> mapa_orden=new HashMap<Integer, Bandera>();
		//Chapuza para que no se estropee el ministream de ir asignando los �rdenes. lo quitamos despu�s
		mapa_orden.put(0, null);
		
			banderas.stream().forEach(b->{
			mapa_unidades.put(b, b.size());			
			mapa_asignaciones.put(b, 0);
			int orden_banderas=mapa_orden.keySet().stream().mapToInt(j->j).max().getAsInt()+1;
			mapa_orden.put(orden_banderas, b);
		});
		
			mapa_orden.remove(0, null);
		
		int total=mapa_unidades.values().stream().mapToInt(i->i).sum();
		
		if(cantidad>=total) {
		mapa_asignaciones.clear();
		mapa_asignaciones.putAll(mapa_unidades);
		}else {
		int actual=total;
		for (int j = 0; j < cantidad; j++) {
			int resultado=dado.generaIndice(actual);
			
			boolean asignado=false;
			int indice=1;
			do {
				Bandera band=mapa_orden.get(indice);
				int tamanho=mapa_unidades.get(band);
				if(tamanho>=resultado) {
					asignado=true;
					int nuevasunidades=mapa_unidades.get(band)-1;
					mapa_unidades.put(band, nuevasunidades);
					int nuevasasignaciones=mapa_asignaciones.get(band)+1;
					mapa_asignaciones.put(band, nuevasasignaciones);
				}else {
					resultado-=tamanho;
				indice++;
				}
			} while (!asignado);
			
			actual--;
		}
		}
		return mapa_asignaciones;
	}
	
}
