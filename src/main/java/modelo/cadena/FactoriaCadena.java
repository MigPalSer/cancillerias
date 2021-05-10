package modelo.cadena;

public class FactoriaCadena {

	public CadenaProduccion instanciaCadena() {
	
		return new CadenaProduccion(colaVacia(), colaVacia(), colaVacia());
	}
	
	private ColaTropas colaVacia() {
		ColaTropas cola=new ColaTropas();
		cola.setInfanteria(0);
		cola.setArtilleria(0);
		
		return cola;
	}
}
