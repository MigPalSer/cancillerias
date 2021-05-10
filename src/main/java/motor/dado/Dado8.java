package motor.dado;

import java.util.Random;

public class Dado8 implements Dado {

	Random r;
	
	public Dado8(){
		r=new Random();
	}
	
	public boolean tira(int dificultad) {
		return (r.nextInt(8)+1)>=dificultad;
	}

}
