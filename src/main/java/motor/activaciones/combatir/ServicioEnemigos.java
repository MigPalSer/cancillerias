package motor.activaciones.combatir;

import modelo.bandera.Bandera;
import modelo.jugador.Jugador;
import modelo.territorio.Territorio;

//Su responsabilidad es devolvernos las banderas enemigas en cada caso
public class ServicioEnemigos {

	public static Bandera tropasEnemigas(Territorio t, Jugador j) {
		Bandera banderaenemiga=null;
		if(j.equals(t.getPropietario())) {
			banderaenemiga=ServicioEnemigos.tropasInvasoras(t);
		}else {
			banderaenemiga=ServicioEnemigos.tropasPropietarias(t);
		}
		return banderaenemiga;
	}

	public static Bandera tropasInvasoras(Territorio t) {
		Bandera banderaenemiga=null;
	
		for(Bandera b: t.getTropas().values()) {
			if(!b.getPropietario().equals(t.getPropietario())) {
				banderaenemiga=b;
			}
		}
		
		return banderaenemiga;
	}

	public static Bandera tropasPropietarias(Territorio t) {
		Bandera banderaenemiga=null;
	
		banderaenemiga=t.getTropas().get(t.getPropietario());
		
		return banderaenemiga;
	
	}

}
