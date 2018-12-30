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

	private ImageIcon[] icono;
	private ImageIcon reverso;

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
		icono = new ImageIcon[52];

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
			Image imgReverso = ABaraja[i].getReverso().getImage();

			img = img.getScaledInstance(90, 120, Image.SCALE_SMOOTH);
			imgReverso = imgReverso.getScaledInstance(90, 120, Image.SCALE_SMOOTH);

			icono[i] = new ImageIcon(img);
			reverso = new ImageIcon(imgReverso);

			if (i < 23) {// Baraja Principal
				barajaInicial[i] = new JButton(nombre.toString());
				barajaInicial[i].setIcon(icono[i]);
				barajaInicial[0].setBounds(20, 10, 90, 120);
				clasico.add(barajaInicial[0]);

			} else if (i == 23) { // Baraja Descartes

				barajaDescartes[0] = new JButton(nombre.toString());
				barajaDescartes[0].setIcon(icono[i]);
				barajaDescartes[0].setBounds(125, 10, 90, 120);
				clasico.add(barajaDescartes[0]);

			} else if (i == 24) { // MOntones de cartas

				monton1[i1] = new JButton(nombre.toString());
				
				monton1[i1].setBounds(20, 135, 90, 120);

				if (i1 == 0) {
					monton1[i1].setIcon(icono[i]);
				} else {
					monton1[i1].setIcon(reverso);
					monton1[i1].setEnabled(false);
				}
				clasico.add(monton1[i1]);
				i1++;

			} else if (i > 24 && i < 27) {

				monton2[i2] = new JButton(nombre.toString());
				
				monton2[i2].setBounds(125, 155 - (i2 * 20), 90, 120);
				
				if (i2 == 0) {
					monton2[i2].setIcon(icono[i]);
				} else {
					monton2[i2].setIcon(reverso);
					monton2[i2].setEnabled(false);
				}
				clasico.add(monton2[i2]);
				i2++;

			} else if (i >= 27 && i <= 29) {

				monton3[i3] = new JButton(nombre.toString());
				
				monton3[i3].setBounds(230, 175 - (i3 * 20), 90, 120);
				
				if (i3 == 0) {
					monton3[i3].setIcon(icono[i]);
				} else {
					monton3[i3].setIcon(reverso);
					monton3[i3].setEnabled(false);
				}
				clasico.add(monton3[i3]);
				i3++;

			} else if (i >= 30 && i <= 33) {

				monton4[i4] = new JButton(nombre.toString());
				
				monton4[i4].setBounds(335, 195 - (i4 * 20), 90, 120);
				if (i4 == 0) {
					monton4[i4].setIcon(icono[i]);
				} else {
					monton4[i4].setIcon(reverso);
					monton4[i4].setEnabled(false);
				}
				clasico.add(monton4[i4]);
				i4++;

			} else if (i >= 34 && i <= 38) {

				monton5[i5] = new JButton(nombre.toString());
				
				monton5[i5].setBounds(440, 215 - (i5 * 20), 90, 120);
				if (i5 == 0) {
					monton5[i5].setIcon(icono[i]);
				} else {
					monton5[i5].setIcon(reverso);
					monton5[i5].setEnabled(false);
				}
				clasico.add(monton5[i5]);
				i5++;

			} else if (i >= 39 && i <= 44) {

				monton6[i6] = new JButton(nombre.toString());
				
				monton6[i6].setBounds(545, 235 - (i6 * 20), 90, 120);
				if (i6 == 0) {
					monton6[i6].setIcon(icono[i]);
				} else {
					monton6[i6].setIcon(reverso);
					monton6[i6].setEnabled(false);
				}
				clasico.add(monton6[i6]);
				i6++;

			} else if (i >= 45 && i <= 51) {

				monton7[i7] = new JButton(nombre.toString());
				
				monton7[i7].setBounds(650, 255 - (i7 * 20), 90, 120);
				if (i7 == 0) {
					monton7[i7].setIcon(icono[i]);
				} else {
					monton7[i7].setIcon(reverso);
					monton7[i7].setEnabled(false);
				}
				clasico.add(monton7[i7]);
				i7++;

			}

			nombre.delete(0, nombre.length());
		}
		
		
		fin1[0] = new JButton("º1º");
		fin1[0].setBounds(335,10,90,120);
		clasico.add(fin1[0]);
		
		fin2[0] = new JButton("º2º");
		fin2[0].setBounds(440,10,90,120);
		clasico.add(fin2[0]);
		
		fin3[0] = new JButton("º3º");
		fin3[0].setBounds(545,10,90,120);
		clasico.add(fin3[0]);
		
		fin4[0] = new JButton("º4º");
		fin4[0].setBounds(650,10,90,120);
		clasico.add(fin4[0]);

		/* DEBUSG */
		System.out.println("Baraja inicial");
		for (int i = 0; i < barajaInicial.length; i++) {
			if (barajaInicial[i] != null) {
				System.out.print(barajaInicial[i].getLabel() + " ");

			}
		}

		System.out.println();
		System.out.println("Baraja descartes");
		for (int i = 0; i < barajaDescartes.length; i++) {
			if (barajaDescartes[i] != null) {
				System.out.print(barajaDescartes[i].getLabel() + " ");
			}
		}

		barajaInicial[0].setBounds(20, 10, 90, 120);
		clasico.add(barajaInicial[0]);

		System.out.println();
		System.out.println("Monton 1");
		for (int i = 0; i < monton1.length; i++) {
			if (monton1[i] != null) {
				System.out.print(monton1[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 2");
		for (int i = 0; i < monton2.length; i++) {
			if (monton2[i] != null) {

				System.out.print(monton2[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 3");
		for (int i = 0; i < monton3.length; i++) {
			if (monton3[i] != null) {
				System.out.print(monton3[i].getLabel() + " ");
			}
		}
		System.out.println();
		System.out.println("Monton 4");

		for (int i = 0; i < monton4.length; i++) {
			if (monton4[i] != null) {
				System.out.print(monton4[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 5");
		for (int i = 0; i < monton5.length; i++) {

			if (monton5[i] != null) {
				System.out.print(monton5[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 6");
		for (int i = 0; i < monton6.length; i++) {
			if (monton6[i] != null) {
				System.out.print(monton6[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 7");

		for (int i = 0; i < monton7.length; i++) {
			if (monton7[i] != null) {
				System.out.print(monton7[i].getLabel() + " ");
			}
		}
		/* FIN DEBUG */

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
