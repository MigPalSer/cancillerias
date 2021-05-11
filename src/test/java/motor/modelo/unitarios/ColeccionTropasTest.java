package motor.modelo.unitarios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.jugador.Jugador;
import modelo.tropas.Bandera;

class ColeccionTropasTest {

	//Bandera vacia
	Bandera b1=null;
	Jugador j1=null;
	
	@BeforeEach
	void setUp() {
		b1=new Bandera(j1);
	}
	
	@Test
	void testVacia() {
		assertTrue(b1.isVacia());
		b1.setArtilleria(10);
		assertFalse(b1.isVacia());
		b1.setArtilleria(0);
		assertTrue(b1.isVacia());
	}
	
	@AfterEach
	void tearDown() {
		Bandera b1=null;
		Jugador j1=null;
	}
}
