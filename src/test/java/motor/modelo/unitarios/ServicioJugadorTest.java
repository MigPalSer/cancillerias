package motor.modelo.unitarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.jugador.Jugador;
import modelo.jugador.ServicioJugador;
import modelo.tropas.Bandera;

class ServicioJugadorTest {

	Jugador j1=null;
	
	@BeforeEach
	void setUp() {
		j1=new Jugador();
	}
	
	@Test
	void testIngresar() {
		j1.setDinero(10);
		j1.ingresar(10);
		assertThat(j1.getDinero(), equalTo(20));
	}
	
	@Test
	void testGastar() {
		j1.setDinero(10);
		j1.gastar(10);
		assertThat(j1.getDinero(), equalTo(0));
		j1.gastar(10);
		assertThat(j1.getDinero(), equalTo(-10));
	}
	
	@AfterEach
	void tearDown() {
		Jugador j1=null;
	}
}
