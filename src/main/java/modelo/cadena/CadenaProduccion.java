package modelo.cadena;

//Describe los tres estadios de la cadena de produccion
public class CadenaProduccion {

	ColaTropas actual, proximo_turno, dos_turnos;

	public ColaTropas getActual() {
		return actual;
	}

	public void setActual(ColaTropas actual) {
		this.actual = actual;
	}

	public ColaTropas getProximo_turno() {
		return proximo_turno;
	}

	public void setProximo_turno(ColaTropas proximo_turno) {
		this.proximo_turno = proximo_turno;
	}

	public ColaTropas getDos_turnos() {
		return dos_turnos;
	}

	public void setDos_turnos(ColaTropas dos_turnos) {
		this.dos_turnos = dos_turnos;
	}

	public CadenaProduccion(ColaTropas actual, ColaTropas proximo_turno, ColaTropas dos_turnos) {
		super();
		this.actual = actual;
		this.proximo_turno = proximo_turno;
		this.dos_turnos = dos_turnos;
	}
	
	
	
}
