package motor.partida;

import java.util.Collection;

import modelo.bandera.Bandera;
import modelo.escenario.Escenario;
import modelo.jugador.Jugador;
import modelo.territorio.Territorio;

public class ServicioTerritorios {

	public static void desactivarTodosTerritorios(Escenario e) {
		//Desactiva todos los territorios del escenario para el fin de turno
		for (Territorio t : e.getTerritorios().values()) {
			desactivaTerritorio(t);
		}
	}
	
	public static void actualizaTerritorio(Territorio t, Jugador atacante) {
		//Invoca las comprobaciones de disputa y propietario
		actualizaDisputa(t);
		actualizaPropietario(t, atacante);
	}
	
	public static void activaTerritorio(Territorio t) {
		t.setActivado(true);
	}
	public static void desactivaTerritorio(Territorio t) {
		t.setActivado(false);
	}

	public static void actualizaDisputa(Territorio t) {
		byte numerocontendientes=0;
		for (Bandera bandera : t.getTropas().values()) {
			if(!bandera.isVacia()) {
				numerocontendientes++;
			}
		}
		boolean disputa=numerocontendientes>1?true:false;
		t.setDisputado(disputa);
	}
	
	public static void actualizaPropietario (Territorio t, Jugador atacante) {
		//De momento solo cambia si el territorio ya no está disputado y el atacante conserva bandera en el terreno
		if(!t.isDisputado()&&!t.getTropas().get(atacante).isVacia()) {
			t.setPropietario(atacante);
		}
	}
}
