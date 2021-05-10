package modelo.jugador;

//Modifican las finanzas del jugador, sin lógica de negocio
public class ServicioJugador {

	public static void ingresar(Jugador j, int dinero) {
		int saldo=j.getDinero();
		saldo+=dinero;
		j.setDinero(saldo);
	}
	
	public static void gastar(Jugador j, int dinero) {
		int saldo=j.getDinero();
		saldo-=dinero;
		j.setDinero(saldo);
	}

}
