package modelo.jugador.plantillas;

public abstract class ModeloUnidad {

	public enum Clasificacion{
		TERRESTRE_INFANTERIA, TERRESTRE_EQUIPO, MARINA, EDIFICIO, OTRO;
	}
	
	protected String tipo;
	protected Clasificacion clasificacion;

	//Propiedades de construccion
	//cola inicial -1 querra decir que no se puede construir
	protected int colaInicial, costeDinero;

	//Propiedades de combate
	protected int valorAtaque, valorDefensa;
	
	//Debe sobreescribirse
	public abstract String sobrevive(int tirada);

	////Getters y setters
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public int getColaInicial() {
		return colaInicial;
	}

	public void setColaInicial(int colaInicial) {
		this.colaInicial = colaInicial;
	}

	public int getCosteDinero() {
		return costeDinero;
	}

	public void setCosteDinero(int costeDinero) {
		this.costeDinero = costeDinero;
	}

	public int getValorAtaque() {
		return valorAtaque;
	}

	public void setValorAtaque(int valorAtaque) {
		this.valorAtaque = valorAtaque;
	}

	public int getValorDefensa() {
		return valorDefensa;
	}

	public void setValorDefensa(int valorDefensa) {
		this.valorDefensa = valorDefensa;
	}
	
	//Propiedades para ver si es un avion o se beneficia del bono de aviones (como la artilleria) en el combate terrestre
	protected boolean aviacion, bonoAviacion;

	public boolean isAviacion() {
		return aviacion;
	}

	public void setAviacion(boolean aviacion) {
		this.aviacion = aviacion;
	}

	public boolean isBonoAviacion() {
		return bonoAviacion;
	}

	public void setBonoAviacion(boolean bonoAviacion) {
		this.bonoAviacion = bonoAviacion;
	}

	//Constructor para declarar falsas las propiedades especiales de cualquier unidad.
	public ModeloUnidad() {
		super();
		this.aviacion=false;
		this.bonoAviacion=false;
	}
	
	public abstract String mejora();
	
	public abstract boolean puedeMejorar();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clasificacion == null) ? 0 : clasificacion.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		ModeloUnidad other = (ModeloUnidad) obj;
		if (clasificacion != other.clasificacion)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	
	
}
