package motor.partida;

import java.util.ArrayList;
import java.util.List;

import modelo.escenario.Escenario;
import modelo.jugador.*;
import modelo.territorio.Territorio;
import modelo.tropas.cadena.ServicioCadena;
import motor.activaciones.Activacion;
import motor.activaciones.ActivacionAsaltar;
import motor.activaciones.ActivacionInvadir;
import motor.activaciones.ActivacionMovimientoAmigo;
import motor.activaciones.ActivacionReforzar;
import motor.produccion.ProduccionJugador;
import motor.vista.ServicioMensajes;

public class Partida implements Runnable {

	Escenario escenario;
	Activacion asaltar, invadir, movimientoamigo, reforzar;
	ArrayList<Jugador> ordendejuego;

	public Partida(Escenario escenario, Activacion asaltar, Activacion invadir, Activacion movimientoamigo,
			Activacion reforzar, ArrayList<Jugador> ordendejuego) {
		super();
		this.escenario = escenario;
		this.asaltar = asaltar;
		this.invadir = invadir;
		this.movimientoamigo = movimientoamigo;
		this.reforzar = reforzar;
		this.ordendejuego = ordendejuego;
		
	}

	@Override
	public void run() {

		boolean partidaencurso = true;
		byte numerorondas = 1;

		ServicioMensajes.println("Ronda "+numerorondas);
		
		do {

			ronda();
			numerorondas++;

		} while (partidaencurso);

	}

	void ronda() {

		for (Jugador jugador : ordendejuego) {
			ServicioMensajes.println("Turno de "+jugador.getNombre());
			turno(jugador);
		}

	}

	void turno(Jugador j) {

		//Produccion inicial
		ProduccionJugador.produccion(j);
		
		ServicioMensajes.println(j.getCadena().toString());
		
		boolean turnoencurso = true;

		// Realiza activaciones mientras pueda

		do {
			
			ServicioMensajes.estadoGeneral(escenario);
			
			// Decidir si se finaliza el turno
			int continuar = j.getControlador().decidir("continuar turno - TC: 0 no, 1 sí");

			if (continuar < 1) {
				ServicioMensajes.println("Turno finalizado");
				turnoencurso = false;
			} else {
				// Ver si quedan terrenos disponibles
				List<Integer> territoriosdisponibles = ServicioEscenario.terrenosActivables(escenario);

				if (territoriosdisponibles.size() == 0) {
					turnoencurso = false;
				} else {
					// Se va a jugar turno, se debe elegir terreno, elegir activacion y resolver.

					int id = elegirTerritorio(j, territoriosdisponibles);

					Territorio t = escenario.getTerritorios().get(id);

					ServicioTerritorios.activaTerritorio(t);

					//TO RE
					System.out.println("activado? "+t.isActivado());
					
					Activacion act = elegirActivacion(t, j);

					act.activar(escenario, j, id);
				}
			}

		} while (turnoencurso);
		
		//Parte final, de desactivar, recaudar y avanzar la cola
		ServicioTerritorios.desactivarTodosTerritorios(escenario);
		ServicioIngresos.producir(escenario, j);
		ServicioCadena.avanzarCadena(j.getCadena());
	}

	int elegirTerritorio(Jugador jugador, List<Integer> territorioselegibles) {
		// Se mantiene eligiendo hasta que elija bien

		boolean eligiendo = true;

		int idterreno = 0;

		while (eligiendo) {
			idterreno = jugador.getControlador().decidir("elegir_id_terreno_activacion");
			if (territorioselegibles.contains(idterreno)) {
				eligiendo = false;
			}else {
			ServicioMensajes.println("Terreno ya activado");}
		}

		return idterreno;
	}

	Activacion elegirActivacion(Territorio t, Jugador j) {
		
		Activacion activacionelegida = null;

		String activacionesposibles = null;

		if (t.isDisputado()) {
			activacionesposibles = "disputado";
		} else if (t.getPropietario().equals(j)) {
			activacionesposibles = "amigo";
		} else {
			activacionesposibles = "enemigo";
		}

		// ServicioMensajes.activacionesPosibles(activacionesposibles);

		switch (activacionesposibles) {
		case "disputado":
			// ServicioMensajes.eligeActivacion();
			int activacion = j.getControlador().decidir("tipo_activacion_disputado TC 1 reforzar 2 asaltar");
			if (activacion == 1) {
				activacionelegida = reforzar;
				//ServicioMensajes.println(j.getNombre()+" refuerza en "+t.getNombre());
			} else {
				activacionelegida = asaltar;
				//ServicioMensajes.println(j.getNombre()+" asalta en "+t.getNombre());
			}
			break;
		case "amigo":
			activacionelegida = movimientoamigo;
			//ServicioMensajes.println(j.getNombre()+" mueve en "+t.getNombre());
			break;
		case "enemigo":
			activacionelegida = invadir;
			//ServicioMensajes.println(j.getNombre()+" invade en "+t.getNombre());
			break;

		default:
			// ¿¿¿???
			break;
		}

		return activacionelegida;

	}

}
