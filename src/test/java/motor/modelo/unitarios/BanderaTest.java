package motor.modelo.unitarios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.jugador.FactoriaJugador;
import modelo.jugador.Jugador;
import modelo.jugador.plantillas.tropasterrestres.Reclutas;
import modelo.tropas.Bandera;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class BanderaTest {

	//Bandera vacia
	Bandera b1=null;
	Jugador j1=null;
	
	@BeforeEach
	void setUp() {
		j1=FactoriaJugador.createJugador("Paco");
		b1=new Bandera(j1);
	}
	
	@Test
	void obtenerModelo() {
		
		assertThat(b1.getModelo("reclutas"), equalTo(new Reclutas()));
		
	}
	
	@Test
	void obtenerMejora() {
		
		assertThat(b1.getModelo("reclutas").mejora(), equalTo("soldados"));
		
	}
	
	@Test
	void testAsciendeUnidades() {
		b1.set("reclutas", 1);
		assertThat(b1.get("reclutas"), equalTo(1));
		b1.asciendeUnidad("reclutas");
		assertThat(b1.get("reclutas"), equalTo(0));
		assertThat(b1.get("soldados"), equalTo(1));
		
	}
	
	@Test
	void testMejoraUnidades1() {
		b1.set("reclutas", 1);
		b1.mejorarTropas(1);
		assertThat(b1.get("reclutas"), equalTo(0));
		assertThat(b1.get("soldados"), equalTo(1));
	}
	
	@Test
	void testMejoraUnidades2Veces() {
		b1.set("reclutas", 1);
		b1.mejorarTropas(2);
		assertThat(b1.get("reclutas"), equalTo(0));
		assertThat(b1.get("veteranos"), equalTo(1));
	}
	
	@Test
	void testMejoraUnidadesMasVecesNecesarias() {
		b1.set("reclutas", 1);
		b1.mejorarTropas(20);
		assertThat(b1.get("reclutas"), equalTo(0));
		assertThat(b1.get("veteranos"), equalTo(1));
	}
	

	
	@AfterEach
	void tearDown() {
		Bandera b1=null;
		Jugador j1=null;
	}
}
