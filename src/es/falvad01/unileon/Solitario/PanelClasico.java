package es.falvad01.unileon.Solitario;

import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 * TODO SELECCIONAR LA CARTA CLIKADA CON UN SOMBREADO
 * 
 */

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelClasico extends JPanel {

	private JPanel clasico;
	private Baraja baraja;
	private CartaFrancesa[] ABaraja;
	private String rutaJuego = null;
	BufferedImage[] cartaImageArray;
	Graphics2D g;

	private JButton[] barajaInicial;
	private JButton[] barajaDescartes;
	private JButton[] fin1;
	private JButton[] fin2;
	private JButton[] fin3;
	private JButton[] fin4;
	private JButton[] monton1;
	private JButton[] monton2;
	private JButton[] monton3;
	private JButton[] monton4;
	private JButton[] monton5;
	private JButton[] monton6;
	private JButton[] monton7;

	public PanelClasico(JPanel panel) {

		this.clasico = panel;
		this.baraja = new Baraja(EJuego.Clasico);
		barajaInicial = new JButton[23];
		barajaDescartes = new JButton[24];

		fin1 = new JButton[13];
		fin2 = new JButton[13];
		fin3 = new JButton[13];
		fin4 = new JButton[13];

		monton1 = new JButton[52];
		monton2 = new JButton[52];
		monton3 = new JButton[52];
		monton4 = new JButton[52];
		monton5 = new JButton[52];
		monton6 = new JButton[52];
		monton7 = new JButton[52];

	}

	public String getRutaJuego() {
		return this.rutaJuego;
	}

	public void iniciarJuegoClasico() {
		System.out.println("ESTO ES UN METODO DE PRUEBA");
		baraja.crearBarajaF();
		baraja.barajarF();
		ABaraja = baraja.getBarajaFrancesa();
		for (int i = 0; i < ABaraja.length; i++) {
			System.out.print(ABaraja[i] + " ");
		}

		System.out.println("/n");

		pintarCartas();

	}

	private void pintarCartas() {

		StringBuilder nombre = new StringBuilder();
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		int i5 = 0;
		int i6 = 0;
		int i7 = 0;

		for (int i = 0; i < 52; i++) {

			nombre.append(ABaraja[i]);

			Image img = ABaraja[i].getImagen().getImage();

			img = img.getScaledInstance(95, 120, Image.SCALE_SMOOTH);

			ImageIcon icono = new ImageIcon(img);

			if (i < 23) {
				barajaInicial[i] = new JButton(nombre.toString());
				// System.out.println(nombre.toString());
				System.out.println(i);

			} else if (i == 23) {
				
				barajaDescartes[0] = new JButton(nombre.toString());
				//System.out.println(nombre.toString());
				System.out.println(i);
				
			} else if (i == 24) {
				
				monton1[i1] = new JButton(nombre.toString());
			//	System.out.println(nombre.toString());
				System.out.println(i);
				i1++;
				
			} else if (i > 24 && i < 27) {
				
				monton2[i2] = new JButton(nombre.toString());
				System.out.println("f2-" + i2);
				i2++;
				System.out.println(i);
				
			} else if (i >= 28 && i <= 30) {
				
				monton3[i3] = new JButton(nombre.toString());
				System.out.println("f3-" + i3);
				i3++;
				System.out.println(i);
				
			} else if (i >= 31 && i <= 34) {
				
				monton4[i4] = new JButton(nombre.toString());
				System.out.println("f4-" + i4);
				i4++;
				System.out.println(i);
				
			} else if (i >= 35 && i <= 39) {
				
				monton5[i5] = new JButton(nombre.toString());
				System.out.println("f5-" + i5);
				i5++;
				System.out.println(i);
				
			} else if (i >= 40 && i <= 45) {
				
				monton6[i6] = new JButton(nombre.toString());
				System.out.println("f6-" + i6);
				i6++;
				System.out.println(i);
				
			} else if (i >= 46 && i < 52) {
				//TODO FALLA AQUI, NO SUBE A F7-6
				monton7[i7] = new JButton(nombre.toString());
				System.out.println("f7-" + i7);
				i7++;
				System.out.println(i);
				
			} 
		}

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
