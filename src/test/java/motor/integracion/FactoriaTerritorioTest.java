package motor.integracion;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

import modelo.territorio.FactoriaTerritorio;
import modelo.territorio.Territorio;

class FactoriaTerritorioTest {

	@Test
	void testCreacion() {
		
		Territorio t=FactoriaTerritorio.crear(FactoriaTerritorio.Terrenos.PARIS, null);
		
		assertThat(t.getId(), equalTo(1));
		assertThat(t.getNombre(), equalToIgnoringCase("Paris"));
		assertFalse(t.isActivado());

		
	}

}
