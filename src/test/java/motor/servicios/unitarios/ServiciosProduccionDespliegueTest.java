package motor.servicios.unitarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.edificios.Edificios;
import modelo.jugador.*;
import modelo.jugador.plantillas.PlantillaProduccion;
import modelo.jugador.plantillas.PlantillaProduccion.Opcion;
import modelo.territorio.Territorio;
import modelo.tropas.Bandera;
import modelo.tropas.ServicioColeccionTropas;
import modelo.tropas.cadena.SeccionCadena;
import motor.activaciones.despliegue.ServicioDespliegue;
import motor.integracion.ControladorFixed100;

class ServiciosProduccionDespliegueTest {
	
	Territorio territorio=null;
	Territorio territorio2=null;
	Territorio territorio3=null;
	Bandera bandera=null;
	Jugador jugador=null;
	
	
	@BeforeEach
	void setUp() {
		jugador=FactoriaJugador.createJugador("Dummy");
		territorio=new Territorio();
		territorio.setPropietario(jugador);
		territorio.setTropas(new HashMap<Jugador, Bandera>());
		bandera=new Bandera(jugador);
		territorio.getTropas().put(jugador, bandera);
		territorio.setSoldadesca(1000);
		territorio.setEdificios(new Edificios());
		
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
		
		jugador.setControlador(new ControladorFixed100());
	}
	
	@Test
	void testDespliegue() {
		ServicioColeccionTropas.desplegar(jugador, territorio, 10, 10);
		assertThat(territorio.getTropas().get(jugador).getInfanteria(), equalTo(15));
		assertThat(territorio.getTropas().get(jugador).getArtilleria(), equalTo(13));

	}
	
	
	@Test
	void testDespliegueTerrenoVacio() {
		ServicioColeccionTropas.desplegar(jugador, territorio3, 10, 10);
		assertThat(territorio3.getTropas().get(jugador).getInfanteria(), equalTo(10));
		assertThat(territorio3.getTropas().get(jugador).getArtilleria(), equalTo(10));
	}

	@Test
	void testDesplegar() {
		
		SeccionCadena ct=jugador.getCadena().getActual();
		ct.setInfanteria(30);
		
		assertThat(jugador.getCadena().getActual().get("reclutas"), equalTo(30));
		
		ServicioDespliegue.despliegue(jugador, territorio);
		
		
		assertThat(territorio.getTropas().get(jugador).getInfanteria(), equalTo(35));
		assertThat(territorio.getTropas().get(jugador).getArtilleria(), equalTo(3));
		assertThat(jugador.getCadena().getActual().get("reclutas"), equalTo(0));

	}
	
	/*
	@Test
	void testCompraEnTerreno() {
		jugador.setDinero(200);
		ServicioDespliegue.producir(jugador, territorio, 10, 10);
		assertThat(territorio.getTropas().get(jugador).getInfanteria(), equalTo(15));
		assertThat(territorio.getTropas().get(jugador).getArtilleria(), equalTo(13));
		assertThat(jugador.getDinero(), equalTo(130));
	}
	
	@Test
	void testCompraSinDinero() {
		System.setOut(new ImpresionVacia(System.out));
		jugador.setDinero(10);
		ServicioDespliegue.producir(jugador, territorio, 10, 10);
		assertThat(territorio.getTropas().get(jugador).getInfanteria(), equalTo(5));
		assertThat(territorio.getTropas().get(jugador).getArtilleria(), equalTo(3));
		assertThat(jugador.getDinero(), equalTo(10));
		System.setOut(new PrintStream(System.out));
	}
	
	@Test
	void testCompraSinControlar() {
		System.setOut(new ImpresionVacia(System.out));
		jugador.setDinero(10);
		ServicioDespliegue.producir(jugador, territorio3, 10, 10);
		assertThat(jugador.getDinero(), equalTo(10));
		System.setOut(new PrintStream(System.out));
	}
	*/
	
	@AfterEach
	void tearDown() {
		bandera.setInfanteria(0);
		bandera.setArtilleria(0);
		jugador.setDinero(0);
	}
}
