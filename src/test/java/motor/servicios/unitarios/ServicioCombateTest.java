package motor.servicios.unitarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.bandera.Bandera;
import motor.activaciones.combatir.ServicioCombate;

class ServicioCombateTest {

	Bandera b1=null;
	Bandera b2=null;
	
	@BeforeEach
	void setUp() {
		ServicioCombate.setDado(new DadoFixed());
		b1=new Bandera(null);
		b2=new Bandera(null);
		b1.setInfanteria(10);
		b2.setInfanteria(5);
		b2.setArtilleria(2);
	}
	
	@Test
	void testDisparoInfanteria() {
		int impactos=ServicioCombate.disparosInfanteria(5, false);
		assertThat(impactos, equalTo(5));
		impactos=ServicioCombate.disparosInfanteria(3, true);
		assertThat(impactos, equalTo(3));
	}
	
	@Test
	void testDisparoArtilleria() {
		int impactos=ServicioCombate.disparosArtilleria(5, false);
		assertThat(impactos, equalTo(5));
		impactos=ServicioCombate.disparosArtilleria(3, true);
		assertThat(impactos, equalTo(3));
	}
	
	@Test
	void testasignacionimpactos() {
		ServicioCombate.asignacionimpactos(b1, 3);
		assertThat(b1.getInfanteria(), equalTo(7));
		assertThat(b1.getArtilleria(), equalTo(0));
		ServicioCombate.asignacionimpactos(b1, 4);
		assertThat(b1.getInfanteria(), equalTo(3));
		ServicioCombate.asignacionimpactos(b2, 30);
		assertThat(b2.getInfanteria(), equalTo(0));
		assertThat(b2.getArtilleria(), equalTo(0));
	}
	
	@Test
	void testCombate() {
		ServicioCombate.combatir(b1, b2);
		assertThat(b1.getInfanteria(), equalTo(3));
		assertThat(b2.getInfanteria(), equalTo(0));
		assertThat(b2.getArtilleria(), equalTo(0));

	}

	@AfterEach
	void tearDown() {
		b1=null;
		b2=null;
	}
	
}
