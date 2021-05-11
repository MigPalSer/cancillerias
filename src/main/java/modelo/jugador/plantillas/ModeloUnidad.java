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
	
	
}
