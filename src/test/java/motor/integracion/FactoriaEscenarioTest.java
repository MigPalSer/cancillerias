package motor.integracion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import modelo.escenario.Escenario;
import modelo.escenario.FactoriaEscenario;
import modelo.jugador.Jugador;
import modelo.territorio.Territorio;
import modelo.tropas.Bandera;
import motor.activaciones.mover.ServicioConexiones;
import motor.partida.ServicioEscenario;
import motor.partida.ServicioIngresos;
import motor.partida.ServicioTerritorios;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class FactoriaEscenarioTest {

	//Test integracion
	//Incluimos el test del servicioingresos al estar intimamente relacionado con el escenario
	//Testeamos también el servicio de conexiones
	
	@Test
	void testAlfa1() {
		Escenario e=FactoriaEscenario.crear(FactoriaEscenario.Mapas.ALFA1);
		
		assertThat(e.getTerritorios().size(), equalTo(3));		
		assertThat(e.getJugadores().size(), equalTo(2));

		assertThat(e.getConexiones().get(1), hasItem(2));
		assertThat(e.getConexiones().get(2), hasItem(3));

		assertThat(e.getTerritorios().get(1).getTropas().get(e.getJugadores().get("Francia")).getInfanteria(), equalTo(5));
		
	}
	
	@Test
	void ServicioIngresosTest() {
		Escenario c=FactoriaEscenario.crear(FactoriaEscenario.Mapas.ALFA1);
		Jugador al=c.getJugadores().get("Alemania");
		assertThat(al.getDinero(), equalTo(10));	
		ServicioIngresos.producir(c, al);
		assertThat(al.getDinero(), equalTo(16));	
		c.getTerritorios().get(3).setDisputado(true);
		ServicioIngresos.producir(c, al);
		assertThat(al.getDinero(), equalTo(16));
	}

	@Test
	void ServicioEscenarioTest() {
		Escenario d=FactoriaEscenario.crear(FactoriaEscenario.Mapas.ALFA1);
		d.getTerritorios().get(1).setActivado(true);
		assertTrue(d.getTerritorios().get(1).isActivado());
		assertFalse(d.getTerritorios().get(2).isActivado());
		d.getTerritorios().get(2).getTropas().put(d.getJugadores().get("Alemania"), new Bandera(d.getJugadores().get("Alemania")));
		d.getTerritorios().get(2).getTropas().get(d.getJugadores().get("Alemania")).setInfanteria(100);
		assertFalse(d.getTerritorios().get(2).isDisputado());
		ServicioTerritorios.desactivarTodosTerritorios(d);
		ServicioTerritorios.actualizaDisputa(d.getTerritorios().get(1));
		ServicioTerritorios.actualizaDisputa(d.getTerritorios().get(2));

		assertFalse(d.getTerritorios().get(1).isActivado());
		assertFalse(d.getTerritorios().get(1).isDisputado());
		assertTrue(d.getTerritorios().get(2).isDisputado());

	}
	
	
	@Test
	void ServicioConexionesTest() {
		Escenario f=FactoriaEscenario.crear(FactoriaEscenario.Mapas.ALFA1);
		
		HashSet<Territorio> conexiones1=ServicioConexiones.territoriosConectados(f, f.getTerritorios().get(1), 1);
		assertThat(conexiones1, hasItem(f.getTerritorios().get(2)));
		assertThat(conexiones1, not(hasItem(f.getTerritorios().get(3))));
		assertThat(conexiones1, not(hasItem(f.getTerritorios().get(1))));

		HashSet<Territorio> conexiones2=ServicioConexiones.territoriosConectados(f, f.getTerritorios().get(1), 2);
		assertThat(conexiones2, hasItem(f.getTerritorios().get(2)));
		assertThat(conexiones2, hasItem(f.getTerritorios().get(3)));
		assertThat(conexiones2, not(hasItem(f.getTerritorios().get(1))));
	}
}
