package motor.servicios.unitarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.PrintStream;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.bandera.Bandera;
import modelo.bandera.ServicioBanderas;
import modelo.jugador.*;
import modelo.territorio.Territorio;
import motor.activaciones.producir.ServicioProduccion;

class ServiciosProduccionDespliegueTest {
	
	Territorio territorio=null;
	Territorio territorio2=null;
	Territorio territorio3=null;
	Bandera bandera=null;
	Jugador jugador=null;
	
	
	@BeforeEach
	void setUp() {
		jugador=new Jugador(0, "Dummy");
		territorio=new Territorio();
		territorio.setPropietario(jugador);
		territorio.setTropas(new HashMap<Jugador, Bandera>());
		bandera=new Bandera(jugador);
		territorio.getTropas().put(jugador, bandera);
		territorio.setSoldadesca(1000);
		
		territorio2=new Territorio();
		territorio2.setPropietario(jugador);
		territorio2.setTropas(new HashMap<Jugador, Bandera>());
		territorio.setSoldadesca(1000);
		
		territorio3=new Territorio();
		territorio3.setPropietario(null);
		territorio3.setTropas(new HashMap<Jugador, Bandera>());
		territorio.setSoldadesca(1000);
		
		bandera.setInfanteria(5);
		bandera.setArtilleria(3);
	}
	
	@Test
	void testDespliegue() {
		ServicioBanderas.desplegar(jugador, territorio, 10, 10);
		assertThat(territorio.getTropas().get(jugador).getInfanteria(), equalTo(15));
		assertThat(territorio.getTropas().get(jugador).getArtilleria(), equalTo(13));

	}
	
	@Test
	void testDespliegueTerrenoVacio() {
		ServicioBanderas.desplegar(jugador, territorio3, 10, 10);
		assertThat(territorio3.getTropas().get(jugador).getInfanteria(), equalTo(10));
		assertThat(territorio3.getTropas().get(jugador).getArtilleria(), equalTo(10));
	}

	@Test
	void testCompraEnTerreno() {
		jugador.setDinero(200);
		ServicioProduccion.producir(jugador, territorio, 10, 10);
		assertThat(territorio.getTropas().get(jugador).getInfanteria(), equalTo(15));
		assertThat(territorio.getTropas().get(jugador).getArtilleria(), equalTo(13));
		assertThat(jugador.getDinero(), equalTo(130));
	}
	
	@Test
	void testCompraSinDinero() {
		System.setOut(new ImpresionVacia(System.out));
		jugador.setDinero(10);
		ServicioProduccion.producir(jugador, territorio, 10, 10);
		assertThat(territorio.getTropas().get(jugador).getInfanteria(), equalTo(5));
		assertThat(territorio.getTropas().get(jugador).getArtilleria(), equalTo(3));
		assertThat(jugador.getDinero(), equalTo(10));
		System.setOut(new PrintStream(System.out));
	}
	
	@Test
	void testCompraSinControlar() {
		System.setOut(new ImpresionVacia(System.out));
		jugador.setDinero(10);
		ServicioProduccion.producir(jugador, territorio3, 10, 10);
		assertThat(jugador.getDinero(), equalTo(10));
		System.setOut(new PrintStream(System.out));
	}
	
	@AfterEach
	void tearDown() {
		bandera.setInfanteria(0);
		bandera.setArtilleria(0);
		jugador.setDinero(0);
	}
}
