package motor.servicios.unitarios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.jugador.FactoriaJugador;
import modelo.jugador.Jugador;
import modelo.jugador.plantillas.ModeloUnidad;
import modelo.jugador.plantillas.PlantillaProduccion;
import modelo.jugador.plantillas.PlantillaProduccion.Opcion;
import modelo.jugador.plantillas.tropasterrestres.Reclutas;
import motor.integracion.ControladorFixed100;
import motor.produccion.ProduccionJugador;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class ProduccionJugadorTest {

	Jugador dummy=null;
	
	@BeforeEach
	void StatUp() {
		dummy=FactoriaJugador.createJugador(300, "Dummy");
		dummy.setControlador(new ControladorFixed100());
		PlantillaProduccion pp=new PlantillaProduccion();
		pp.setOpciones(new HashSet<ModeloUnidad>());
		pp.getOpciones().add(new Reclutas());
		dummy.setPlantillaProduccion(pp);
	}
	
	@Test
	void testProduccionBasico() {
		ProduccionJugador.produccion(dummy);
		//Producimos 10 infanterias y comprobamos que esté en la cadena actual y que el dinero haya bajado a 0
		assertThat(dummy.getCadena().getActual().get("reclutas"), equalTo(100));
		assertThat(dummy.getCadena().getProximo_turno().get("reclutas"), equalTo(0));
		assertThat(dummy.getCadena().getDos_turnos().get("reclutas"), equalTo(0));

		assertThat(dummy.getDinero(), equalTo(0));
	}

}
