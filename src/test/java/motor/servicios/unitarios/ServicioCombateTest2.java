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

class ServicioCombateTest2 {

	//Igual que el primero, pero fallando todos los dados
	
	Bandera b1=null;
	Bandera b2=null;
	
	@BeforeEach
	void setUp() {
		ServicioCombate.setDado(new DadoFixed2());
		b1=new Bandera(FactoriaJugador.createJugador(0, "1"));
		b2=new Bandera(FactoriaJugador.createJugador(0, "2"));
		b1.setInfanteria(10);
		b2.setInfanteria(5);
		b2.setArtilleria(2);
	}
	
	@Test
	void testDisparoInfanteria() {
		int impactos=ServicioCombate.disparoTerreste(5, 5);
		assertThat(impactos, equalTo(0));
		
	}
	
	
	
	
	@Test
	void testCombate() {
		ServicioCombate.combateTerrestre(b1, b2);
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
