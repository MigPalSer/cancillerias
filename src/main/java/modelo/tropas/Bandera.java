package modelo.tropas;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import modelo.jugador.Jugador;
import modelo.jugador.plantillas.ModeloUnidad;
import modelo.jugador.plantillas.ModeloUnidad.Clasificacion;
import modelo.jugador.plantillas.tropasterrestres.InfanteriaAbstracta;
import motor.vista.ServicioMensajes;

public class Bandera extends ColeccionTropas {

	//Su responsabilidad es darnos la información de las fuerzas que hay en un determinado lugar
	//siempre interactuará a través de su territorio.
	
	protected Jugador propietario;
	
	
	public Jugador getPropietario() {
		return propietario;
	}
	public void setPropietario(Jugador propietario) {
		this.propietario = propietario;
	}
	
	//Pequeña funcionalidad para ver si una bandera está vacía
	public boolean isVacia() {
		boolean vacia=true;
		
		for (Integer inte : tropas.values()) {
			if(inte>0)vacia=false;
		}
		
		return vacia;
	}
	
	public Bandera(Jugador propietario) {
		super();
		this.propietario = propietario;
		
	}
	
	public Bandera(Jugador propietario, int infanteria, int artilleria) {
		super(infanteria, artilleria);		
		this.propietario = propietario;

	}
	
	public int numeroInfanterias() {
		int infanterias=0;
		for (String s : tropas.keySet()) {
			//Dependencia de la plantilla, pero bueno
			if(propietario.getModelo(s).getClasificacion()==Clasificacion.TERRESTRE_INFANTERIA)infanterias+=tropas.get(s);
		}
		return infanterias;
	}
	public String toStringLog() {

		String s="Bandera de "+propietario.getNombre()+" : ";
		for(String str : tropas.keySet()) {
			s+=str+" "+tropas.get(str)+" -/- ";
		}
		return s;
		
	}
	public boolean tieneAviones() {
		boolean aviones=false;
		
		for (String s : tropas.keySet()) {
			if(tropas.get(s)>0&&getModelo(s).isAviacion()) {
				aviones=true;
			}
		}
		
		return aviones;
	}
	
	public ModeloUnidad getModelo(String s) {
		return propietario.getModelo(s);
	}

	//Mejorar tropas de cualquier tipo
	public void mejorarTropas(int n) {
	
		while(n>0&&isMejorable()) {
			n--;
			
			//Hacemos un barajado de las tropas de la bandera para elegir una mejorable al azar
			//Al tener la condicion isMejorable nos aseguramos que al menos una cumpla
			List<String> tropasString=ServicioColeccionTropas.toStringList(this);
			Collections.shuffle(tropasString);
			
			Optional<String> str=tropasString.stream().
			map(this::getModelo).
			filter((m)->m.puedeMejorar()).
			map((m)->m.getTipo()).
			findFirst();
			
			asciendeUnidad(str.get());
			
			
		}}
	
	
	//Método responsable de quitar uno de s y añadir uno de su mejora
	//NO es responsable de verificar que sea mejorable ni de que haya al menos uno que mejorar
	public void asciendeUnidad(String s) {
		String mejora=getModelo(s).mejora();
		ServicioColeccionTropas.aumentar(this, 1, mejora);
		ServicioColeccionTropas.reducir(this, 1, s);
	}
	
	//Devuelve verdadero si hay alguna tropa que se pueda mejorar
	public boolean isMejorable() {
		boolean hayTropasMejorables=false;		
		for (String s : tropas.keySet()) {
			if(tropas.get(s)>0&&getModelo(s).puedeMejorar()){
				hayTropasMejorables=true;
			}
		}
		return hayTropasMejorables;
	}
	
	public int getValorAtaque(String tipo, boolean atacante) {
		int valor=9;
		ModeloUnidad modelo=getModelo(tipo);
		if(atacante) {
			valor=modelo.getValorAtaque();
		}else {
			valor=modelo.getValorDefensa();
		}
		if(modelo.isBonoAviacion()&&this.tieneAviones())valor--;
		return valor;
	}
	
	public Set<String> setTropas(){
		return tropas.keySet();
	}
	
	//Devuelve el número de tropas de tierra en la bandera
	public int size() {
		int unidades=0;
		for (String s : tropas.keySet()) {
			//Dependencia de la plantilla, pero bueno
			if(propietario.getModelo(s).getClasificacion()==Clasificacion.TERRESTRE_INFANTERIA||this.getModelo(s).getClasificacion()==Clasificacion.TERRESTRE_EQUIPO)unidades+=tropas.get(s);
		}
		return unidades;
	}
}
