package modelo.tropas;

import java.util.HashMap;

public class ColeccionTropas {
	
	public int getInfanteria() {
		if(!tropas.containsKey("reclutas"))tropas.put("reclutas", 0);
		return tropas.get("reclutas");
	}
	public void setInfanteria(int infanteria) {
		tropas.put("reclutas", infanteria);
	}
	public int getArtilleria() {
		if(!tropas.containsKey("artilleria"))tropas.put("artilleria", 0);

		return tropas.get("artilleria");
	}
	public void setArtilleria(int artilleria) {
		tropas.put("artilleria", artilleria);
	}
	
	public ColeccionTropas() {
		tropas=new HashMap<String, Integer>();
		/* //Instanciamos todas las claves
		tropas.put("reclutas", 0);
		tropas.put("artilleria", 0);
		tropas.put("carros", 0);*/

	}
	
	public ColeccionTropas(int infanteria, int artilleria) {
		tropas=new HashMap<String, Integer>();
		//Instanciamos todas las claves
		tropas.put("reclutas", infanteria);
		tropas.put("artilleria", artilleria);
		tropas.put("carros", 0);

	}
	
	protected HashMap<String, Integer> tropas;
	
	public HashMap<String, Integer> getTropas() {
		return tropas;
	}
	public void setTropas(HashMap<String, Integer> tropas) {
		this.tropas = tropas;
	}
	
	public int get(String s) {
		if(!tropas.containsKey(s))tropas.put(s, 0);
		return tropas.get(s);
	}
	public void set(String s, int n) {
		tropas.put(s, n);
	}
	
	public String toString() {
		String s="";
		for(String str : tropas.keySet()) {
			s+="-"+str+" "+tropas.get(str)+" - \n ";
		}
		return s;
	}
	
}
