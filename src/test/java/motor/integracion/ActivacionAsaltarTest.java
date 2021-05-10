package motor.integracion;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

import decisor.consola.ControladorConsolaBasico;
import modelo.bandera.Bandera;
import modelo.escenario.Escenario;
import modelo.escenario.FactoriaEscenario;
import modelo.escenario.FactoriaEscenario.Mapas;
import modelo.jugador.Jugador;
import motor.activaciones.Activacion;
import motor.activaciones.ActivacionAsaltar;
import motor.activaciones.ActivacionInvadir;
import motor.activaciones.ActivacionMovimientoAmigo;
import motor.activaciones.combatir.ServicioCombate;
import motor.servicios.unitarios.DadoFixed;

class ActivacionAsaltarTest {

	void setUp() {
		ServicioCombate.setDado(new DadoFixed());
	}
	
	@Test
	void test() {
		Escenario e=FactoriaEscenario.crear(Mapas.ALFA1);
		Jugador francia=e.getJugadores().get("Francia");
		Jugador alemania=e.getJugadores().get("Alemania");

		francia.setControlador(new ControladorFixed100());
		alemania.setControlador(new ControladorFixed100());

		Activacion a=new ActivacionAsaltar();
		a.activar(e, francia, 3);
		assertThat(e.getTerritorios().get(3).getTropas().get(francia).getInfanteria(), equalTo(0));
		assertThat(e.getTerritorios().get(3).getTropas().get(alemania).getInfanteria()+e.getTerritorios().get(3).getTropas().get(alemania).getArtilleria(), equalTo(13));
		assertThat(e.getTerritorios().get(3).getPropietario(), is(alemania));
		a.activar(e, alemania, 2);
		assertThat(e.getTerritorios().get(2).getTropas().get(francia).getInfanteria(), equalTo(0));
		assertThat(e.getTerritorios().get(3).getTropas().get(alemania).getInfanteria()+e.getTerritorios().get(3).getTropas().get(alemania).getArtilleria(), equalTo(0));
		assertThat(e.getTerritorios().get(2).getTropas().get(alemania).getInfanteria()+e.getTerritorios().get(2).getTropas().get(alemania).getArtilleria(), equalTo(13));
		assertThat(e.getTerritorios().get(2).getPropietario(), is(alemania));
	}

}
