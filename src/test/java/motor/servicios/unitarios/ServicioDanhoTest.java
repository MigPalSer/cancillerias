package motor.servicios.unitarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import modelo.jugador.FactoriaJugador;
import modelo.jugador.Jugador;
import modelo.tropas.Bandera;
import modelo.tropas.ColeccionTropas;
import modelo.tropas.cadena.CadenaProduccion;
import modelo.tropas.cadena.FactoriaCadena;
import motor.activaciones.combatir.ServicioDanho;

class ServicioDanhoTest {

	
	Jugador mockedj=Mockito.mock(Jugador.class);
	Bandera b1=null;
	Bandera b2=null;
	
	
	
	@BeforeEach
	void setUp() {
		//j1=new Jugador(0, "Dummy");
		
		Mockito.when(mockedj.supervivencia("carros", 8)).thenReturn("impactoanulado");
		Mockito.when(mockedj.getCadena()).thenReturn(FactoriaCadena.instanciaCadena());
		Mockito.when(mockedj.supervivencia("infanteria", 8)).thenReturn("");

		b1=new Bandera(mockedj);
		b1.setInfanteria(10);
		b2=new Bandera(mockedj);
		b2.set("carros", 10);
	}
	
	@Test
	//Comprobamos que efectivamente una unidad sufre bajas
	void testAsignacionImpactos() {
		ServicioDanho.asignacionImpactos(b1, 4);
		assertThat(b1.getInfanteria(), equalTo(6));
	}

	@Test
	//Comprobamos que efectivamente una unidad sufre bajas
	void testCarros() {

		ColeccionTropas bajas=new ColeccionTropas();
		
		b2.setInfanteria(10);
		bajas.set("carros", 5);
		bajas.setInfanteria(4);
		
		ServicioDanho.resolucionImpactos(b2, bajas, new DadoFixed());
		
		assertThat(b2.getInfanteria(), equalTo(10));
		assertThat(b2.get("carros"), equalTo(15));

	}
	
	
}
