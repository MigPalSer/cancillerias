package motor.activaciones.combatir;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.jugador.Jugador;
import modelo.tropas.Bandera;
import modelo.tropas.ColeccionTropas;
import modelo.tropas.ServicioColeccionTropas;
import motor.dado.Dado;
import motor.dado.Dado8;
import motor.vista.ServicioMensajes;

public class ServicioDanho {
	

	static Dado dado;
	
	static {
		dado=new Dado8();
	}
	/* Implementacion aleatoria que por ahora no vamos a utilizar
	public static ColeccionTropas tropasImpactadas(Dado d, Bandera b, int impactos) {
		//Si no indicamos dado, usamos el dado estandar de 8 caras
		return tropasImpactadas(dado, b, impactos);
		
	}
	*/
	
	public static void asignacionImpactos(Bandera b, int impactos) {
	
		/*Se asigna cada impacto individualmente a una nueva coleccion tropas y se
		 * retira de la bandera original
		*/
		ColeccionTropas bajas=tropasImpactadas(b, impactos);
		
		ServicioColeccionTropas.retirarTodo(b, bajas);
		
		/*Para cada tipo de baja a continuación utilizaremos los métodos asociados al tipo de
		 * tropa y jugador para ver a donde van, las tropas que resistan impactos son reincorporadas
		 * a la bandera
		 * */
		
		resolucionImpactos(b, bajas);
		
	}
	

	
	
	public static ColeccionTropas tropasImpactadas(Bandera b, int impactos) {
		//El método es vulgar y dependemos de la fuente aleatoria de la clase Collections,
		//En el futuro tal vez debamos mejorar esto
		
		ColeccionTropas bajas=new ColeccionTropas();
		//Clonamos las tropas de la bandera en las bajas, luego le iremos quitando en función
		//De la diferencia entre los impactos y el tamaño
		ServicioColeccionTropas.aumentarTodo(b, bajas);

		int tamanhoBandera=0;
		for (Integer integer : b.getTropas().values()) {
			tamanhoBandera+=integer;
		}
		
		if(impactos>=tamanhoBandera) {
			//if vacio, toda la bandera ha sido alcanzada
		}else if(impactos==0){
			//Nos ahorramos el calculo, devolvemos las bajas vacias
			bajas=new ColeccionTropas();
		}else {
			List<String> listabandera=ServicioColeccionTropas.toStringList(b);
			Collections.shuffle(listabandera);
		for (int i = 0; i < tamanhoBandera-impactos; i++) {
			String tipo=listabandera.get(i);
			int reduccion=bajas.get(tipo)-1;
			bajas.set(tipo, reduccion);
		}
		}
		return bajas;
		
	}
	
	public static void resolucionImpactos(Bandera b, ColeccionTropas bajas) {

	resolucionImpactos(b, bajas, dado);	
		
	}
	
	//Gestiona las resoluciones especiales (volver a cola o anular impactos)
	public static void resolucionImpactos(Bandera b, ColeccionTropas bajas, Dado d) {

		Jugador j=b.getPropietario();
		ColeccionTropas c1=j.getCadena().getProximo_turno();
		ColeccionTropas c2=j.getCadena().getDos_turnos();

		
		for (String s : bajas.getTropas().keySet()) {
			for (int i = 0; i < bajas.getTropas().get(s); i++) {
				int tirada_supervivencia=d.resultadod8();
				String resultadosupervivencia=j.supervivencia(s, tirada_supervivencia);
				
				if(resultadosupervivencia==null)resultadosupervivencia="";
				
				switch (resultadosupervivencia) {
				case "impactoanulado":
					ServicioColeccionTropas.aumentar(b, 1, s);
					//TORE
					ServicioMensajes.println(s+" ha anulado el impacto");
					break;
				case "cola+1":
					ServicioColeccionTropas.aumentar(c1, 1, s);
					//TORE
					ServicioMensajes.println(s+" requiere reparación, cola +1");
					break;
				case "cola+2":
					ServicioColeccionTropas.aumentar(c2, 1, s);
					//TORE
					ServicioMensajes.println(s+" requiere reparación, cola +2");
					break;
				default:
					break;
				}
				
			}
		}
		
	}
	
	
}
