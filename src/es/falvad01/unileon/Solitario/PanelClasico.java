package es.falvad01.unileon.Solitario;

import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * TODO TENER EN CUANTA CUANDO SE CLIKA EN UNA CARTA PERO NO SE CLICA EN OTR, ESE METODO DE ESPERA HAY QUE REFLEJARLO, HAY QUE TENER EN CUENTA EL ESTADO DE LA APLICACION
 * TODO SELECCIONAR LA CARTA CLIKADA CON UN SOMBREADO
 * TODO EL CLIAKDO PUEDE SER UN FLAG(TRUE,FALSE)
 */

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelClasico extends JPanel {

	private JPanel clasico;
	private Baraja baraja;
	private Carta[] ABaraja;
	private String rutaJuego = null;
	BufferedImage[] cartaImageArray;
	Graphics2D g;

	public PanelClasico(JPanel panel) {

		this.clasico = panel;
		this.baraja = new Baraja(EJuego.Clasico);

	}

	public String getRutaJuego() {
		return this.rutaJuego;
	}

	public void initComponents() {

		JLabel lblClasico = new JLabel("Clasico");
		lblClasico.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblClasico.setBounds(0, 417, 173, 80);
		clasico.add(lblClasico);

	}

	public void prueba() {
		
		baraja.crearBarajaF();
		ABaraja = baraja.getBaraja();
		for (int i = 0; i < ABaraja.length; i++) {
			System.out.print(ABaraja[i] + " ");
		}
		baraja.barajarF();
		ABaraja = baraja.getBaraja();
		
		System.out.println("ESTO ES UN METODO DE PRUEBA");
		for (int i = 0; i < ABaraja.length; i++) {
			System.out.print(ABaraja[i] + " ");
		}
		System.out.println("/n");

		paint(g);

	}

	public void paint(Graphics2D g) {

		System.out.println("Clasico");
		Graphics2D g2d = (Graphics2D) g;// Graficos en 2d

		// g2d.drawImage(ABaraja[0].getImage(), 10, 20,this);// Pintamos la imagen
	}

	public void guardar(String saveGame) {

		rutaJuego = saveGame;

		try {
			FileWriter fichero = new FileWriter(rutaJuego);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println("Solitario Clasico");

			// TODO ESCRIBIR AQUI TODO LO QUE SE VAYA A GUARDAR EN EL ARCHIVO

			fichero.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
