package modelo.tropas;

public class ServicioColeccionTropas {

	public static void transferirTodo(ColeccionTropas emisor, ColeccionTropas receptor) {
		
		//Sumamos ambas cantidades para cada tipo
		int infanteriatotal=emisor.getInfanteria()+receptor.getInfanteria();
		int artilleriatotal=emisor.getArtilleria()+receptor.getArtilleria();
		
		//Establecemos el emisor
		emisor.setInfanteria(0);
		emisor.setArtilleria(0);
		
		//Establecemos el receptor
		receptor.setInfanteria(infanteriatotal);
		receptor.setArtilleria(artilleriatotal);
		
	}
	
}
