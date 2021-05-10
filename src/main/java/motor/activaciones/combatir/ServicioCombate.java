package motor.activaciones.combatir;

import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import modelo.bandera.Bandera;
import modelo.jugador.Jugador;
import modelo.territorio.Territorio;
import motor.dado.Dado;
import motor.dado.Dado8;
import motor.partida.ServicioTerritorios;
import motor.vista.ServicioMensajes;

public class ServicioCombate {

	private static Dado dado;
	
	static {
		dado=new Dado8();
	}
	
	public static Dado getDado() {
		return dado;
	}

	public static void setDado(Dado dado) {
		ServicioCombate.dado = dado;
	}

	//Consideramos por defecto que la bandera 1 es la que ataca y la b2 la que defiende
	public static void combatir(Bandera b1, Bandera b2) {
		
		int impactosab1=0;
		int impactosab2=0;
		
		impactosab1+=disparosInfanteria(b2.getInfanteria(), false);
		impactosab1+=disparosArtilleria(b2.getArtilleria(), false);
		
		impactosab2+=disparosInfanteria(b1.getInfanteria(), true);
		impactosab2+=disparosArtilleria(b1.getArtilleria(), true);
		
		asignacionimpactos(b1, impactosab1);
		asignacionimpactos(b2, impactosab2);
	}
	
	public static void asignacionimpactos(Bandera b, int impactos) {
	
		int inf=b.getInfanteria();
		int art=b.getArtilleria();
		if(impactos>=(inf+art)) {
			b.setInfanteria(0);
			b.setArtilleria(0);
		}else {
			int total=inf+art;
			int infanteriaactual=inf;
			int impactosainfanteria=0;
			int impactosaartilleria=0;
			Random r=new Random();
			for (int i = 0; i < impactos; i++) {
				if((r.nextInt(total)+1)>infanteriaactual) {
					impactosaartilleria++;
				}else {
					impactosainfanteria++;
					infanteriaactual--;
				}
				total--;
			}
			b.setInfanteria(inf-impactosainfanteria);
			b.setArtilleria(art-impactosaartilleria);
		}
	}
	
	public static int disparosInfanteria(int infanterias, boolean atacante) {
		int impactos=0;
		int valor=atacante?7:6;
		for (int i = 0; i < infanterias; i++) {
			if(dado.tira(valor))impactos++;
		}
		return impactos;
	}
	
	public static int disparosArtilleria(int artillerias, boolean atacante) {
		int impactos=0;
		int valor=atacante?5:6;
		for (int i = 0; i < artillerias; i++) {
			if(dado.tira(valor))impactos++;
		}
		return impactos;
	}
	
	
	
}
