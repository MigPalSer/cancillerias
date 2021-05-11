package motor.produccion;

import java.util.HashSet;

import modelo.jugador.Jugador;
import modelo.jugador.ServicioJugador;
import modelo.jugador.plantillas.ModeloUnidad;
import modelo.jugador.plantillas.PlantillaProduccion;
import modelo.jugador.plantillas.PlantillaProduccion.Opcion;
import modelo.tropas.ServicioColeccionTropas;
import modelo.tropas.cadena.CadenaProduccion;
import modelo.tropas.cadena.SeccionCadena;
import motor.vista.ServicioMensajes;

//Se encarga de realizar la produccion del jugador para alimentar a su cadena de produccion
public class ProduccionJugador {

	public static void produccion(Jugador j) {
		
		//La idea es que el jugador tiene una serie de recursos y de opciones para comprar que
		//se iran añadiendo a sus diferentes colas, la idea sería crear un método facilmente ampliable
		//Con lo cual deberíamos tener: coste, tipo de unidad, cola a la que va
		HashSet<ModeloUnidad> opciones=j.getPlantillaProduccion().getOpciones();
		ServicioMensajes.println(j.getNombre()+" con dinero "+j.getDinero()+" produce:");
		
		//Para cada una de las opciones de produccion
		for (ModeloUnidad opc : opciones) {
			if(opc.getColaInicial()!=-1) {
			int coste=opc.getCosteDinero();
			String tipo=opc.getTipo();
			//Preguntamos cuantos queremos construir (OJO QUE NO NOS SACA EL MAXIMO SI FALLAMOS)
			ServicioMensajes.println("Cuantas "+tipo+" a coste "+coste);
			int cantidad=j.getControlador().decidir("producir");
			int precio=cantidad*coste;
			//Si tenemos dinero para pagarlo activa la compra, si no da un mensaje de fallo
			if(precio<=j.getDinero()) {
				//Hacemos que el jugador pague lo que ha consumido
				ServicioJugador.gastar(j, precio);
				//Elegimos la cola correspondiente
				SeccionCadena cola=selectorCola(j, opc.getColaInicial());
				//Lo añadimos a la cola
				ServicioColeccionTropas.aumentar(cola, cantidad, tipo);
				
			}else {
				ServicioMensajes.println("compra fallida");
			}
				
		}}
	}
	
	//Convierte el entero de la opcion en la cola a la que hay que asignar la tropa
	public static SeccionCadena selectorCola(Jugador j, int cola) {
		SeccionCadena ct=null;
		CadenaProduccion cp=j.getCadena();
		switch (cola) {
		case 0:
			ct=cp.getActual();
			break;
		case 1:
			ct=cp.getProximo_turno();
			break;
		case 2:
			ct=cp.getDos_turnos();
			break;
		default:
			break;
		}
		
		return ct;
	}
	
}
