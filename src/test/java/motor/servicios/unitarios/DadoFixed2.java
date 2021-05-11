package motor.servicios.unitarios;

import motor.dado.Dado;

public class DadoFixed2 implements Dado {

	//Siempre fallan
	
	public boolean tira(int dificultad) {
		return false;
	}

	@Override
	public int generaIndice(int maximo) {
		//No implementado
		return 0;
	}

	@Override
	public int resultadod8() {
		return 1;
	}

}
