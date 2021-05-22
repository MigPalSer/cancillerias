package modelo.jugador.alianza;

import java.util.HashSet;
import java.util.Set;

import modelo.jugador.Jugador;

/*Clase encargada de dar un "tag" de identificacion a los jugadores que se han agrupado en un
 *mismo bando. Cada jugador tiene una alianza, y un metodo getTagAlianza que si es nula devuelve su nombre (así cada jugador cuenta como una alianza en sí mismo).
 *Las alianzas se usan: en combates múltiples, en identificacion de tipos de terreno (de cara a movimiento y activaciones)
 *y en la fase de diplomacia. Siempre vendrán dadas por el Jugador correspondiente, con quien mantendrán un alto acoplamiento */
public class Alianza {

	String tag;
	Set<Jugador> integrantes;
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Set<Jugador> getIntegrantes() {
		return integrantes;
	}
	public void setIntegrantes(Set<Jugador> integrantes) {
		this.integrantes = integrantes;
	}
	public Alianza(String tag) {
		super();
		this.tag = tag;
		integrantes=new HashSet<Jugador>();
	}
	public boolean add(Jugador e) {
		return integrantes.add(e);
	}
	
	public boolean remove(Jugador o) {
		return integrantes.remove(o);
	}
	
	
	
}
