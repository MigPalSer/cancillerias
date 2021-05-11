package motor.modelo.unitarios;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.jugador.Jugador;
import modelo.jugador.plantillas.PlantillaProduccion;
import modelo.jugador.plantillas.PlantillaProduccion.Opcion;
import modelo.tropas.cadena.CadenaProduccion;
import modelo.tropas.cadena.FactoriaCadena;
import modelo.tropas.cadena.ServicioCadena;
import motor.integracion.ControladorFixed100;
import motor.produccion.ProduccionJugador;

class CadenaTest {

	//Testeamos serviciocadena y factoria
	//Desacoplar de jugador
	
	CadenaProduccion c=null;
	
	@BeforeEach
	void StatUp() {
		c=FactoriaCadena.instanciaCadena();
		c.getActual().setInfanteria(10);
		c.getProximo_turno().setInfanteria(5);
		c.getDos_turnos().setInfanteria(2);
	}
	
	@Test
	void testAvanceCadena() {
		
		
		assertThat(c.getActual().get("reclutas"), equalTo(10));
		assertThat(c.getProximo_turno().get("reclutas"), equalTo(5));
		assertThat(c.getDos_turnos().get("reclutas"), equalTo(2));

		ServicioCadena.avanzarCadena(c);
		
		assertThat(c.getActual().get("reclutas"), equalTo(15));
		assertThat(c.getProximo_turno().get("reclutas"), equalTo(2));
		assertThat(c.getDos_turnos().get("reclutas"), equalTo(0));
		
		ServicioCadena.avanzarCadena(c);
		
		assertThat(c.getActual().get("reclutas"), equalTo(17));
		assertThat(c.getProximo_turno().get("reclutas"), equalTo(0));
		assertThat(c.getDos_turnos().get("reclutas"), equalTo(0));
	}
}
