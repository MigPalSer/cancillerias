package motor.servicios.unitarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.bandera.Bandera;
import motor.activaciones.combatir.ServicioCombate;

class ServicioCombateTest2 {

	//Igual que el primero, pero fallando todos los dados
	
	Bandera b1=null;
	Bandera b2=null;
	
	@BeforeEach
	void setUp() {
		ServicioCombate.setDado(new DadoFixed2());
		b1=new Bandera(null);
		b2=new Bandera(null);
		b1.setInfanteria(10);
		b2.setInfanteria(5);
		b2.setArtilleria(2);
	}
	
	@Test
	void testDisparoInfanteria() {
		int impactos=ServicioCombate.disparosInfanteria(5, false);
		assertThat(impactos, equalTo(0));
		impactos=ServicioCombate.disparosInfanteria(3, true);
		assertThat(impactos, equalTo(0));
	}
	
	@Test
	void testDisparoArtilleria() {
		int impactos=ServicioCombate.disparosArtilleria(5, false);
		assertThat(impactos, equalTo(0));
		impactos=ServicioCombate.disparosArtilleria(3, true);
		assertThat(impactos, equalTo(0));
	}
	
	
	@Test
	void testCombate() {
		ServicioCombate.combatir(b1, b2);
		assertThat(b1.getInfanteria(), equalTo(10));
		assertThat(b2.getInfanteria(), equalTo(5));
		assertThat(b2.getArtilleria(), equalTo(2));

	}

	@AfterEach
	void tearDown() {
		b1=null;
		b2=null;
	}
	
}
