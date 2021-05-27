package motor.activaciones;

import modelo.escenario.Escenario;
import modelo.jugador.*;

public abstract class Activacion {
	
	//La activacion se ejecuta en el escenario por parte del jugador y en el territorio correspondiente
	public abstract void activar(Escenario e, Jugador j, int idterritorio);
	
	//La activaci�n va a referenciar a tres m�todos o secciones de interacci�n, si corresponden: movimiento, combate y produccion
	
}
