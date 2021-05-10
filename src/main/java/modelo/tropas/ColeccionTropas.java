package modelo.tropas;

public class ColeccionTropas {

	//Representa una coleccion de tropas relativamente abstracta y sirve como base para clases
	//hijas
	
	protected int infanteria, artilleria;
	
	public int getInfanteria() {
		return infanteria;
	}
	public void setInfanteria(int infanteria) {
		this.infanteria = infanteria;
	}
	public int getArtilleria() {
		return artilleria;
	}
	public void setArtilleria(int artilleria) {
		this.artilleria = artilleria;
	}
	
	public ColeccionTropas() {
		infanteria=0;
		artilleria=0;
	}
	
	public ColeccionTropas(int infanteria, int artilleria) {
		super();
		this.infanteria = infanteria;
		this.artilleria = artilleria;
	}
	
	
	
}
