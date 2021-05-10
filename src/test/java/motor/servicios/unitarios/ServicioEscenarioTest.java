package motor.servicios.unitarios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import modelo.escenario.Escenario;
import modelo.escenario.FactoriaEscenario;
import modelo.escenario.FactoriaEscenario.Mapas;
import modelo.territorio.Territorio;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import motor.partida.ServicioEscenario;
import motor.partida.ServicioTerritorios;
import motor.vista.*;

class ServicioEscenarioTest {

	@Test
	void testTerrenosActivablesYActualizacionTerrenos() {
		Escenario e=FactoriaEscenario.crear(Mapas.ALFA1);
		List<Integer> lista=ServicioEscenario.terrenosActivables(e);
		assertThat(lista.size(), equalTo(3));
		e.getTerritorios().get(1).setActivado(true);
		lista=ServicioEscenario.terrenosActivables(e);
		assertThat(lista.size(), equalTo(2));
		e.getTerritorios().get(2).setActivado(true);
		lista=ServicioEscenario.terrenosActivables(e);
		assertThat(lista.size(), equalTo(1));
		ServicioTerritorios.desactivarTodosTerritorios(e);
		lista=ServicioEscenario.terrenosActivables(e);
		assertThat(lista.size(), equalTo(3));
	}

}
