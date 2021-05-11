package motor.activaciones.mover;

import java.util.ArrayList;
import java.util.HashSet;

import modelo.escenario.Escenario;
import modelo.jugador.*;
import modelo.territorio.Territorio;

//Proporciona territorios conectados bajo diferentes criterios
public class ServicioConexiones {

	//Su responsabilidad es proporcionar los terrenos conectados a un determinado terreno en una determinada distancia, independientemente de su estado
	public static HashSet<Territorio> territoriosConectados(Escenario e, Territorio t, int distancia){
		
		HashSet<Territorio> territorios=new HashSet<Territorio>();
		int id=t.getId();
		
		HashSet<Integer>hs=e.getConexiones().get(id);
		for (Integer integer : hs) {
			territorios.add(e.getTerritorios().get(integer));
			}
		
		for (int i = 2; i <= distancia; i++) {
			HashSet<Territorio> territoriosalejados=new HashSet<Territorio>();
			for (Territorio ter : territorios) {
				territoriosalejados.addAll(territoriosConectados(e, ter, 1));
			}
			territorios.addAll(territoriosalejados);
		}
		
		territorios.remove(t);
		return territorios;
	}

	//Busca desde qué territorios se puede mover al terreno activado teniendo en cuenta ya activaciones y terrenos enemigos
	public static HashSet<Territorio> territoriosOrigen(Escenario e, Jugador j, Territorio t, int i, boolean invasion){
		
		//Elige todos los territorios en la distancia correspondiente a la activacion
		HashSet<Territorio> ts=territoriosConectados(e, t, i);
		
		//Crea otro hashset a partir del anterior que reuna los territorios ya activados o enemigos
		//que no estén disputados y que no sea uno el propietario, para eliminarlos del primer hashset
		HashSet<Territorio> tr=new HashSet<Territorio>();
		for (Territorio territorio : ts) {
			//Quitamos los activados
			if(territorio.isActivado()) {
				tr.add(territorio);
			}
			//Quitamos los territorios enemigos que no estén disputados
			else if(!territorio.isDisputado()&&!territorio.getPropietario().equals(j)) {
				tr.add(territorio);
				//Si es una invasion, quitamos los territorios disputados
			}else if(invasion&&territorio.isDisputado()) {
				tr.add(territorio);
			}
		}
		ts.removeAll(tr);
	
		return ts;
	}

	
}
