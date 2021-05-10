package modelo.bandera;

import modelo.jugador.Jugador;
import modelo.territorio.Territorio;

//Se encarga de mover tropas o desplegar nuevas entre las banderas
//Solo modifica, no tiene lógica de negocio detrás
public class ServicioBanderas {

	public static void transferir(Bandera origen, Bandera destino, int envioinfanterias, int envioartillerias) {
	
		int infanteriasorigen=origen.getInfanteria();
		int infanteriasdestino=destino.getInfanteria();
		int infanteriastransferidas=infanteriasorigen>=envioinfanterias?envioinfanterias:infanteriasorigen;
		destino.setInfanteria(infanteriasdestino+infanteriastransferidas);
		origen.setInfanteria(infanteriasorigen-infanteriastransferidas);
		
		int artilleriasorigen=origen.getArtilleria();
		int artilleriasdestino=destino.getArtilleria();
		int artilleriastransferidas=artilleriasorigen>=envioartillerias?envioartillerias:artilleriasorigen;
		destino.setArtilleria(artilleriasdestino+artilleriastransferidas);
		origen.setArtilleria(artilleriasorigen-artilleriastransferidas);
	}

	public static void desplegar(Jugador j, Territorio t, int infanteria, int artilleria) {
		if(!t.getTropas().containsKey(j)) {
			t.getTropas().put(j, new Bandera(j));
		}
		
		int nuevasinf=infanteria+t.getTropas().get(j).getInfanteria();
		int nuevasart=artilleria+t.getTropas().get(j).getArtilleria();
		
		t.getTropas().get(j).setInfanteria(nuevasinf);
		t.getTropas().get(j).setArtilleria(nuevasart);
	}
	
}
