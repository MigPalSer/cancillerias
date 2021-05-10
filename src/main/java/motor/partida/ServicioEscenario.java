package motor.partida;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import modelo.escenario.Escenario;
import modelo.jugador.*;
import modelo.territorio.Territorio;
import motor.vista.ServicioMensajes;

//Lógica de negocio del escenario, ver que terrenos se pueden activar
public class ServicioEscenario {	
	
	public static List<Integer> terrenosActivables(Escenario e){
		ArrayList<Integer> lista=new ArrayList<Integer>();
		
		for (Territorio t : e.getTerritorios().values()) {
			if(!t.isActivado()) {lista.add(t.getId());
			//TORE ¿?
			ServicioMensajes.println(t.getNombre()+" está disponible");
			}
		}
		
		return lista;
	}
	

}
