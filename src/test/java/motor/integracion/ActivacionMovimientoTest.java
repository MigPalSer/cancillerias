package motor.integracion;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

import decisor.consola.ControladorConsolaBasico;
import modelo.escenario.Escenario;
import modelo.escenario.FactoriaEscenario;
import modelo.escenario.FactoriaEscenario.Mapas;
import modelo.jugador.Jugador;
import motor.activaciones.Activacion;
import motor.activaciones.ActivacionMovimientoAmigo;

class ActivacionMovimientoTest {

	/*
	@Test
	void test() {
		Escenario e=FactoriaEscenario.crear(Mapas.ALFA1);
		Jugador francia=e.getJugadores().get("Francia");
		francia.setControlador(new ControladorFixed100());
		Activacion a=new ActivacionMovimientoAmigo();
		a.activar(e, francia, 1);
		assertThat(e.getTerritorios().get(1).getTropas().get(francia).getInfanteria(), equalTo(7));
		assertThat(e.getTerritorios().get(1).getTropas().get(francia).getArtilleria(), equalTo(2));
		a.activar(e, francia, 2);
		assertThat(e.getTerritorios().get(2).getTropas().get(francia).getInfanteria(), equalTo(7));
		assertThat(e.getTerritorios().get(2).getTropas().get(francia).getArtilleria(), equalTo(2));
		assertThat(e.getTerritorios().get(1).getTropas().get(francia).getInfanteria(), equalTo(0));
		assertThat(e.getTerritorios().get(1).getTropas().get(francia).getArtilleria(), equalTo(0));
	}
*/
	
}
