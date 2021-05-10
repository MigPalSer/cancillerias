package motor.servicios.unitarios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.bandera.Bandera;
import modelo.bandera.ServicioBanderas;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class ServicioBanderasTest {
	
	Bandera bandera1, bandera2;
	
	@BeforeEach
	void setup() {
	bandera1=new Bandera(null);
	bandera1.setInfanteria(5);
	bandera1.setArtilleria(2);
	
	bandera2=new Bandera(null);
	bandera2.setInfanteria(2);
	}
	
	@Test
	void testTraspasoNormal() {
		assertThat(bandera1.getInfanteria(), equalTo(5));
		ServicioBanderas.transferir(bandera2, bandera1, 2, 0);
		assertThat(bandera1.getInfanteria(), equalTo(7));
		assertThat(bandera2.getInfanteria(), equalTo(0));

	}
	
	@Test
	void testTraspasoBestial() {
		assertThat(bandera1.getInfanteria(), equalTo(5));
		ServicioBanderas.transferir(bandera2, bandera1, 200, 200);
		assertThat(bandera1.getInfanteria(), equalTo(7));
		assertThat(bandera1.getArtilleria(), equalTo(2));
		assertThat(bandera2.getInfanteria(), equalTo(0));
	}
	
	@Test
	void testTraspasoDeNuevas() {
		assertThat(bandera1.getInfanteria(), equalTo(5));
		ServicioBanderas.transferir(bandera1, bandera2, 200, 200);
		assertThat(bandera2.getInfanteria(), equalTo(7));
		assertThat(bandera2.getArtilleria(), equalTo(2));
		assertThat(bandera1.getInfanteria(), equalTo(0));
	}

	@AfterEach
	void tearDown() {
		bandera1=null;
		bandera2=null;
	}
	
}
