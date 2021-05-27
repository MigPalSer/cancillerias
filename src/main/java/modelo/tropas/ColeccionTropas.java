package modelo.tropas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColeccionTropas {

	public int getInfanteria() {
		if (!tropas.containsKey("reclutas"))
			tropas.put("reclutas", 0);
		return tropas.get("reclutas");
	}

	public void setInfanteria(int infanteria) {
		tropas.put("reclutas", infanteria);
	}

	public int getArtilleria() {
		if (!tropas.containsKey("artilleria"))
			tropas.put("artilleria", 0);

		return tropas.get("artilleria");
	}

	public void setArtilleria(int artilleria) {
		tropas.put("artilleria", artilleria);
	}

	public ColeccionTropas() {
		tropas = new HashMap<String, Integer>();
		/*
		 * //Instanciamos todas las claves tropas.put("reclutas", 0);
		 * tropas.put("artilleria", 0); tropas.put("carros", 0);
		 */

	}

	public ColeccionTropas(int infanteria, int artilleria) {
		tropas = new HashMap<String, Integer>();
		// Instanciamos todas las claves
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
		if (!tropas.containsKey(s))
			tropas.put(s, 0);
		return tropas.get(s);
	}

	public void set(String s, int n) {
		tropas.put(s, n);
	}

	public String toString() {
		String s = "";
		for (String str : tropas.keySet()) {
			s += "-" + str + " " + tropas.get(str) + " - \n ";
		}
		return s;
	}

	public void aumentar(int cantidad, String tipo) {
		int tropasfinales = cantidad + this.get(tipo);
		this.set(tipo, tropasfinales);
	}

	// Sí asegura que ningun tipo de tropa quede en negativo
	public void reducir(int cantidad, String tipo) {
		int tropasfinales = this.get(tipo) - cantidad;
		if (tropasfinales < 0)
			tropasfinales = 0;
		this.set(tipo, tropasfinales);
	}

	// Devuelve un array de strings con los strings repetidos segun su proporcion
	public List<String> toStringList() {

		ArrayList<String> lista = new ArrayList<String>();

		// Recorremos con dos bucles anidados para generar una entrada por cada tropa
		for (String string : tropas.keySet()) {

			for (int i = 0; i < tropas.get(string); i++) {
				lista.add(string);
			}

		}
		return lista;
	}

}
