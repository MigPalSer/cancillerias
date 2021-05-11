package modelo.tropas.cadena;

public class FactoriaCadena {

	public static CadenaProduccion instanciaCadena() {
	
		return new CadenaProduccion(colaVacia(), colaVacia(), colaVacia());
	}
	
	private static SeccionCadena colaVacia() {
		SeccionCadena cola=new SeccionCadena();
		
		//TORE
		//cola.setInfanteria(0);
		//cola.setArtilleria(0);
		
		return cola;
	}
}
