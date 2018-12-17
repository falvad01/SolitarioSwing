package es.falvad01.unileon.Solitario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSaltos extends JPanel {

	private JPanel saltos;
	private Baraja baraja;
	private Carta[] ABaraja;
	private String rutaJuego = null;
	BufferedImage cartaImage;
	Graphics2D g;
	private JButton[] Abotones = new JButton[40];

	public PanelSaltos(JPanel panel) {

		this.saltos = panel;
		this.baraja = new Baraja(EJuego.Saltos);

	}

	public String getRutaJuego() {
		return this.rutaJuego;
	}

	public void prueba() {

		baraja.crearBarajaE();
		baraja.barajarE();
		ABaraja = baraja.getBaraja();

		System.out.println("ESTO ES UN METODO DE PRUEBA");

		for (int i = 0; i < ABaraja.length; i++) {
			System.out.print(ABaraja[i] + " ");
		}

		System.out.println("/n");

		repaint();
		pintarCartas();

	}

	public void initComponets() {

		JLabel lblSaltos = new JLabel("Saltos");
		lblSaltos.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSaltos.setBounds(0, 417, 173, 80);
		saltos.add(lblSaltos);

	}

	public void paint(Graphics g) {

		System.out.println("HOLAAAA");
		super.paint(g);

		// Graphics2D g2d = (Graphics2D) g;// Graficos en 2d

		g.setColor(Color.red);
		g.fillOval(300, 300, 100, 100);

		// g.drawImage(ABaraja[0].getImage().getImage(),40,40,this);

	}

	public void pintarCartas() {

		StringBuilder nombre = new StringBuilder();

		for (int i = 0; i < Abotones.length; i++) {

			nombre.append("Carta");
			nombre.append(i);

			Image img = ABaraja[i].getImageIcon().getImage();

			img = img.getScaledInstance(90, 120, Image.SCALE_SMOOTH);

			ImageIcon icono = new ImageIcon(img);

			if (i < 10) {
				Abotones[i] = new JButton(nombre.toString());
				Abotones[i].setBounds(10 + (i * 95), 10, 90, 120);
				saltos.add(Abotones[i]);
				Abotones[i].setIcon(icono);
			} else {
				Abotones[i] = new JButton(nombre.toString());
				Abotones[i].setBounds(900, 370, 90, 120);
				saltos.add(Abotones[i]);
				Abotones[i].setIcon(icono);
			}

			nombre.delete(0, nombre.length());

		}

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
