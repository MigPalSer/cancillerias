package motor.servicios.unitarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.jugador.FactoriaJugador;
import modelo.jugador.Jugador;
import modelo.tropas.Bandera;
import motor.activaciones.combatir.ServicioCombate;
import motor.activaciones.combatir.ServicioDanho;

class ServicioCombateTest {

	Bandera b1=null;
	Bandera b2=null;
	
	@BeforeEach
	void setUp() {
		ServicioCombate.setDado(new DadoFixed());
		b1=new Bandera(FactoriaJugador.createJugador(0, "1"));
		b2=new Bandera(FactoriaJugador.createJugador(0, "2"));
		b1.setInfanteria(10);
		b2.setInfanteria(5);
		b2.setArtilleria(2);
	}
	
	@Test
	void testDisparoInfanteria() {
		int impactos=ServicioCombate.disparoTerreste(5, 5);
		assertThat(impactos, equalTo(5));
	}
	
	
	@Test
	void testasignacionimpactos() {
		ServicioDanho.asignacionImpactos(b1, 3);
		assertThat(b1.getInfanteria(), equalTo(7));
		assertThat(b1.getArtilleria(), equalTo(0));
		ServicioDanho.asignacionImpactos(b1, 4);
		assertThat(b1.getInfanteria(), equalTo(3));
		ServicioDanho.asignacionImpactos(b2, 30);
		assertThat(b2.getInfanteria(), equalTo(0));
		assertThat(b2.getArtilleria(), equalTo(0));
	}
	
	@Test
	void testCombate() {
		ServicioCombate.combateTerrestre(b1, b2);
		assertThat(b1.getInfanteria(), equalTo(3));
		assertThat(b2.getInfanteria(), equalTo(0));
		assertThat(b2.getArtilleria(), equalTo(0));

	}
	
	@Test
	void testCombateAereo() {
		Bandera ba1, ba2;
		ba1=new Bandera(FactoriaJugador.createJugador(0, "1"));
		ba2=new Bandera(FactoriaJugador.createJugador(0, "2"));
		ba1.set("aviones", 5);
		ba2.set("aviones", 3);
		ServicioCombate.combateAereo(ba1, ba2);
		assertThat(ba1.get("aviones"), equalTo(2));
		assertThat(ba2.get("aviones"), equalTo(0));
	}

	
	
	@AfterEach
	void tearDown() {
		b1=null;
		b2=null;
	}
	
}
