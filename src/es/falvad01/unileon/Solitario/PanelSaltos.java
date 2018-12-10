package es.falvad01.unileon.Solitario;

import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSaltos extends JPanel {

	private JPanel saltos;
	private Baraja baraja;
	private Carta[] ABaraja;
	private String rutaJuego = null;

	public PanelSaltos(JPanel panel) {

		this.saltos = panel;
		this.baraja = new Baraja(EJuego.Saltos);

	}

	public String getRutaJuego() {
		return this.rutaJuego;
	}

	private void obtenerBaraja() {
		baraja.barajarE();
		ABaraja = baraja.getBaraja();
	}

	public void prueba() {
		obtenerBaraja();
		System.out.println("ESTO ES UN METODO DE PRUEBA");
		for (int i = 0; i < ABaraja.length; i++) {
			System.out.print(ABaraja[i] + " ");
		}
		System.out.println("/n");
	}

	public void initComponets() {

		JLabel lblSaltos = new JLabel("Saltos");
		lblSaltos.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSaltos.setBounds(0, 417, 173, 80);
		saltos.add(lblSaltos);
	}

	public void guardar(String saveGame) {

		rutaJuego = saveGame;

		try {
			FileWriter fichero = new FileWriter(rutaJuego);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println("Solitario Saltos");

			// TODO ESCRIBIR AQUI TODO LO QUE SE VAYA A GUARDAR EN EL ARCHIVO

			fichero.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
