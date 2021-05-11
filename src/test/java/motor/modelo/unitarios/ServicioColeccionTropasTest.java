package motor.modelo.unitarios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.tropas.Bandera;
import modelo.tropas.ColeccionTropas;
import modelo.tropas.ServicioColeccionTropas;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class ServicioColeccionTropasTest {
	
	Bandera bandera1, bandera2;
	ColeccionTropas c1, c2;
	
	@BeforeEach
	void setup() {
	bandera1=new Bandera(null);
	bandera1.setInfanteria(5);
	bandera1.setArtilleria(2);
	
	bandera2=new Bandera(null);
	bandera2.setInfanteria(2);
	
	c1=new ColeccionTropas();
	c2=new ColeccionTropas();
	}
	
	@Test
	void testTransferirTodo() {
		//Transferimos una tropa ficticia entre dos colecciones
		c1.set("dummys", 10);
		c2.set("dummys", 0);
		assertThat(c2.get("dummys"), equalTo(0));
		ServicioColeccionTropas.transferirTodo(c1, c2);
		assertThat(c1.get("dummys"), equalTo(0));
		assertThat(c2.get("dummys"), equalTo(10));
	}
	
	@Test
	void testTransferirTipo() {
		//Transferimos una tropa ficticia entre dos colecciones 
		c1.set("dummys", 10);
		c2.set("dummys", 0);
		
	

		ServicioColeccionTropas.transferirTipo(c1, c2, "dummys", 5);
		
		assertThat(c1.get("dummys"), equalTo(5));
		assertThat(c2.get("dummys"), equalTo(5));
	}
	
	@Test
	void testTransferirTipoPidiendoExceso() {
		//Transferimos una tropa ficticia entre dos colecciones pero pidiendo más de las posibles
		c1.set("dummys", 10);
		c1.set("mummys", 10);
		c2.set("dummys", 0);
		c2.set("mummys", 0);
	

		ServicioColeccionTropas.transferirTipo(c1, c2, "mummys", 500);
		assertThat(c1.get("mummys"), equalTo(0));
		assertThat(c2.get("mummys"), equalTo(10));
		assertThat(c1.get("dummys"), equalTo(10));
		assertThat(c2.get("dummys"), equalTo(0));
	}
	
	
	
	///////////////////////////////////
	
	//TORE
		@Test
		void testTraspasoNormal() {
			assertThat(bandera1.getInfanteria(), equalTo(5));
			ServicioColeccionTropas.transferir(bandera2, bandera1, 2, 0);
			assertThat(bandera1.getInfanteria(), equalTo(7));
			assertThat(bandera2.getInfanteria(), equalTo(0));

		}
		
	//TORE
	@Test
	void testTraspasoBestial() {
		assertThat(bandera1.getInfanteria(), equalTo(5));
		ServicioColeccionTropas.transferir(bandera2, bandera1, 200, 200);
		assertThat(bandera1.getInfanteria(), equalTo(7));
		assertThat(bandera1.getArtilleria(), equalTo(2));
		assertThat(bandera2.getInfanteria(), equalTo(0));
	}
	
	//TORE
	@Test
	void testTraspasoDeNuevas() {
		assertThat(bandera1.getInfanteria(), equalTo(5));
		ServicioColeccionTropas.transferir(bandera1, bandera2, 200, 200);
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
