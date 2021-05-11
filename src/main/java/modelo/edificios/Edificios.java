package modelo.edificios;

import java.util.HashMap;

public class Edificios {	

	HashMap<String, Integer> edificios;
	
	public int getFabricas() {
		return edificios.get("fabricas");
	}

	public void setFabricas(int fabricas) {
		edificios.put("fabricas", fabricas);
	}
	

	public Edificios() {
	edificios=new HashMap<String, Integer>();
	edificios.put("fabricas", 0);
	}
	
}
