package motor.dado;

public interface Dado {

	//Nos dice si una tirada a esa dificultad (sacar tanto o más) se ha conseguido con un dado (normalmente de 8 caras)
	public boolean tira(int dificultad);
	
	//Nos genera un indice entre 0 y el numero máximo
	public int generaIndice(int maximo);

	//Devuelve el resultado de un d8
	public int resultadod8();
	
}
