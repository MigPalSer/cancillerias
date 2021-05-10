package decisor.consola;

import java.util.Scanner;

import javax.swing.JOptionPane;

import motor.decisor.Decision;

public class ControladorConsolaBasico implements Decision {

	public ControladorConsolaBasico() {
	}

	public int decidir(String s) {
		Scanner scan=new Scanner(System.in);
		int respuesta=0;
		switch (s) {

		default:
			//System.out.println(s);
			respuesta=Integer.parseInt(JOptionPane.showInputDialog(s));
		}
		scan.close();
		return respuesta;
	}

}
