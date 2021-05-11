package modelo.tropas.cadena;

//Describe los tres estadios de la cadena de produccion
public class CadenaProduccion {

	SeccionCadena actual, proximo_turno, dos_turnos;

	public SeccionCadena getActual() {
		return actual;
	}

	public void setActual(SeccionCadena actual) {
		this.actual = actual;
	}

	public SeccionCadena getProximo_turno() {
		return proximo_turno;
	}

	public void setProximo_turno(SeccionCadena proximo_turno) {
		this.proximo_turno = proximo_turno;
	}

	public SeccionCadena getDos_turnos() {
		return dos_turnos;
	}

	public void setDos_turnos(SeccionCadena dos_turnos) {
		this.dos_turnos = dos_turnos;
	}

	public CadenaProduccion(SeccionCadena actual, SeccionCadena proximo_turno, SeccionCadena dos_turnos) {
		super();
		this.actual = actual;
		this.proximo_turno = proximo_turno;
		this.dos_turnos = dos_turnos;
	}

	
	
	public CadenaProduccion() {
	}

	@Override
	public String toString() {
		return "Cadena de produccion actual: "+actual.toString()+"\n Cadena de produccion proximo turno: "+proximo_turno.toString()+"\\n Cadena de produccion proximos 2 turnos: "+dos_turnos.toString();
	}
	
	
	
}
