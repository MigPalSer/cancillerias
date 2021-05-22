package modelo.jugador;

import modelo.jugador.alianza.Alianza;
import modelo.jugador.plantillas.FactoriaPlantillaProduccion;
import modelo.jugador.plantillas.PlantillaProduccion;
import modelo.jugador.plantillas.TablaValores;
import modelo.jugador.plantillas.FactoriaPlantillaProduccion.Plantilla;
import modelo.jugador.plantillas.ModeloUnidad;
import modelo.tropas.cadena.CadenaProduccion;
import modelo.tropas.cadena.FactoriaCadena;
import motor.decisor.Decision;

public class Jugador {

	//Responsabilidad: almacenar el nombre y el dinero de cada jugador. Servir para asignar
	//propiedad a otras clases (bandera, territorio...)
	
	int dinero;
	String nombre;
	Decision controlador;
	CadenaProduccion cadena;
	PlantillaProduccion plantilla_produccion;
	Alianza alianza;
	
	//Permite saber si el jugador pertenece a alguna alianza normal
	public boolean haveAliance() {
		return alianza==null?false:true;
	}
	
	//Devuelve el tag de la alianza, en caso de no tener, devuelve su nombre como tag
	public String getTagAlianza() {
		return alianza==null?this.nombre:alianza.getTag();
	}
	
	public Alianza getAlianza() {
		return alianza;
	}
	
	//Automáticamente se va de la alianza y se incorpora a la nueva
	public void setAlianza(Alianza alianza) {
		if(alianza!=null)alianza.remove(this);
		this.alianza = alianza;
		if(alianza!=null)alianza.add(this);
	}
	
	public PlantillaProduccion getPlantillaProduccion() {
		return plantilla_produccion;
	}
	public void setPlantillaProduccion(PlantillaProduccion plantilla) {
		this.plantilla_produccion = plantilla;
	}
	public CadenaProduccion getCadena() {
		return cadena;
	}
	public void setCadena(CadenaProduccion cadena) {
		this.cadena = cadena;
	}
	public int getDinero() {
		return dinero;
	}
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public Decision getControlador() {
		return controlador;
	}
	public void setControlador(Decision controlador) {
		this.controlador = controlador;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	public Jugador() {
		
	}
	
	//Aun por implementar en más detalle con las tecnologias
	public String supervivencia(String s, int tirada_supervivencia) {
		return getModelo(s).sobrevive(tirada_supervivencia);
	}
	
	//Metodo para obtener las estadisticas de una tropa
	public ModeloUnidad getModelo(String s) {
		return plantilla_produccion.get(s);
	}
	
	public void ingresar(int ganancia) {
		this.dinero+=ganancia;
	}
	
	//No es responsable de evitar que el saldo sea negativo
	public void gastar(int gasto) {
			this.dinero-=gasto;
		}
	
	//Método encargado de ver si un jugador tiene dinero suficiente para pagar algo
	public boolean puedeGastar(int precio) {
		return this.dinero>=precio;
	}
}
