package vista.consola;

import java.util.Scanner;

import javax.swing.JOptionPane;

import motor.decisor.Decision;

public class ControladorVentanaBasico implements Decision {

	public ControladorVentanaBasico() {
	}

	public int decidir(String s) {
		
		int respuesta=0;
		switch (s) {

		default:
			
			respuesta=Integer.parseInt(JOptionPane.showInputDialog(s));
		}
		
		return respuesta;
	}

}
