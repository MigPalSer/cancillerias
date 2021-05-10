package motor.vista;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import modelo.bandera.Bandera;
import modelo.escenario.Escenario;
import modelo.jugador.Jugador;
import modelo.territorio.Territorio;

import java.util.Set;

public class ServicioMensajes {

	// El servicio mensajes envia a sus vistas observadoras los datos necesarios

	static ArrayList<ObservadorVista> vistas;
	
	static {
		vistas=new ArrayList<ObservadorVista>();
	}
	
	public static void addVista(ObservadorVista observador) {
		vistas.add(observador);
	}
	
	public static void removeVista(ObservadorVista observador) {
		vistas.remove(observador);
	}
	
	public static void restartVista(ObservadorVista observador) {
		vistas.clear();
	}
	
	public static void out(String s) {
		for (ObservadorVista observadorVista : vistas) {
			observadorVista.input(s);
		}
	}
	
	public static void tropasQuePuedenReforzar(Territorio t, Jugador j) {
		Bandera b = t.getTropas().get(j);
		out("El territorio " + t.getNombre() + " puede reforzar con " + 
		b.getInfanteria() + "/"	+ b.getArtilleria());
	}

	public static void tropasFinales(Territorio t, Jugador j) {
		Bandera b = t.getTropas().get(j);
		out("El territorio " + t.getNombre() + " tiene " + b.getInfanteria() + "/"
		+ b.getArtilleria());
	}

	public static void tropasContendientes(Territorio t) {
		out("El territorio " + t.getNombre() + " tiene las siguientes tropas");
		Set<Entry<Jugador, Bandera>> trop = t.getTropas().entrySet();
		for (Entry<Jugador, Bandera> entry : trop) {
			String nombrejugador = entry.getKey().getNombre();
			Bandera b = entry.getValue();
			out(nombrejugador + " tiene " + b.getInfanteria() + "/" 
			+ b.getArtilleria());
		}
	}

	public static void cambioTitularidad(Territorio t) {
		out(
				"El territorio " + t.getNombre() + " tiene nuevo propietario " + 
		t.getPropietario().getNombre());

	}

	public static void nuevaActivacion() {
		out("Elige terreno para activar: ");
	}

	public static void nuevaActivacionDeTerrenoYaActivado() {
		out("Ese terreno ya fue activado este turno. Elige otro terreno para activar: ");
	}

	public static void activacionesPosibles(String s) {
		String m = "";

		switch (s) {
		case "disputado":
			m = "activaciones posibles: 1: reforzar, 2: asaltar";
			break;
		case "amigo":
			m = "activaciones posibles: 3: mover";
			break;
		case "enemigo":
			m = "activaciones posibles: 4: invadir";
			break;
		default:
			break;
		}
		out(m);
	}

	public static void eligeActivacion() {
		out("Elige que activacion quieres");
	}

	public static void estadoGeneral(Escenario e) {
		Collection<Jugador> jug = e.getJugadores().values();

		out("********************************");
		
		out("Jugadores: ");
		for (Jugador jugador : jug) {
			out(jugador.getNombre() + " con dinero " + jugador.getDinero() 
			+ " - ");
		}

		Collection<Territorio> ter = e.getTerritorios().values();
		
		for (Territorio t : ter) {
			out(t.getId() + "- Territorio " + t.getNombre() + ", propietario "
					+ t.getPropietario().getNombre() + ", produccion " + t.getProduccion());
			Collection<Bandera> banderas = t.getTropas().values();
			for (Bandera b : banderas) {
				out(b.getPropietario().getNombre() + " - " + b.getInfanteria() 
						+ "/" + b.getArtilleria());
			}
		}
		out("********************************");
	}
	
	public static void println(String s) {
		out(s);
	}
	
}
