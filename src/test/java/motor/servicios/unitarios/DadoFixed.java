package motor.servicios.unitarios;

import motor.dado.Dado;

public class DadoFixed implements Dado {

	//Siempre aciertan
	
	public boolean tira(int dificultad) {
		return true;
	}

	@Override
	public int generaIndice(int maximo) {
		//No implementado
		return 0;
	}

	@Override
	public int resultadod8() {
		return 8;
	}

}
