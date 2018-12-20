package es.falvad01.unileon.Solitario;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.BufferPoolMXBean;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthSpinnerUI;

public class PanelSaltos extends JPanel implements ActionListener {

	private JPanel saltos;
	private Baraja baraja;
	private Carta[] ABaraja;

	private String rutaJuego = null;
	BufferedImage cartaImage;
	Graphics2D g;

	private JButton[][] arrayBotones = new JButton[40][40];

	boolean primeraPulsacion = false;
	boolean segundaPulsacion = false;

	String strAMover;
	String strDestino;

	int posToMove;
	int posCartaAMover = 0;
	int posCartaDestino = 0;

	int posToDelete = 39;

	public PanelSaltos(JPanel panel) {
		// setPreferredSize(new Dimension(200, 200));
		setBackground(Color.red);
		setOpaque(true);
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

	protected void paintComponent(Graphics g) {

		System.out.println("HOLAAAA");
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Graphics2D g2d = (Graphics2D) g;// Graficos en 2d

		g2d.setColor(Color.red);
		g2d.fillOval(0, 0, 500, 500);

		// g.drawImage(ABaraja[0].getImage().getImage(),40,40,this);

	}

	public void pintarCartas() {

		StringBuilder nombre = new StringBuilder();
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;

		for (int i = 0; i < 40; i++) {

			nombre.append(ABaraja[i]);

			Image img = ABaraja[i].getImageIcon().getImage();

			img = img.getScaledInstance(95, 120, Image.SCALE_SMOOTH);// Reescalamos la imagen

			ImageIcon icono = new ImageIcon(img);

			if (i < 10) {// fila 1

				arrayBotones[i][0] = new JButton(nombre.toString());
				arrayBotones[i][0].setBounds(20 + (i1 * 95), 10, 90, 120);
				i1++;
			} else if (i >= 10 && i < 20) { // fila 2

				arrayBotones[i][0] = new JButton(nombre.toString());
				arrayBotones[i][0].setBounds(20 + (i2 * 95), 130, 90, 120);
				i2++;

			} else if (i >= 20 && i < 30) {// fila 3

				arrayBotones[i][0] = new JButton(nombre.toString());
				arrayBotones[i][0].setBounds(20 + (i3 * 95), 250, 90, 120);
				i3++;

			} else if (i >= 30 && i < 40) {// fila 4

				arrayBotones[i][0] = new JButton(nombre.toString());
				arrayBotones[i][0].setBounds(20 + (i4 * 95), 370, 90, 120);
				i4++;
			}

			saltos.add(arrayBotones[i][0]);
			arrayBotones[i][0].setIcon(icono);
			arrayBotones[i][0].addActionListener(this);

			nombre.delete(0, nombre.length());

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ((primeraPulsacion) && (!segundaPulsacion)) {

			segundaPulsacion = true;
			strDestino = ((JButton) e.getSource()).getLabel();
			posCartaDestino = posicion(strDestino);
			System.out.println("POS: " + posCartaDestino);
			System.out.println("Destino:" + strDestino);
			System.out.println();
		}

		if (!primeraPulsacion) {
			primeraPulsacion = true;
			strAMover = ((JButton) e.getSource()).getLabel();
			posCartaAMover = posicion(strAMover);
			System.out.println("POS: " + posCartaAMover);
			System.out.println("Origen: " + strAMover);
			System.out.println();
		}

		if (primeraPulsacion && segundaPulsacion) {
			posToMove = posCartaAMover - posCartaDestino;
			System.out.println("Posiciones a mover " + posToMove);

			if (posToMove == 1) {
				System.out.println("JUGADA VALIDA POR MOVIMIENTOS, movemos 1");
				Point point = arrayBotones[posCartaDestino][0].getLocation(); // Guardamos la posicion de donde tenemos
																				// que mover la carta
			
				Carta cartaAMover = buscaCarta(strAMover);
				Carta cartaDestino = buscaCarta(strDestino);

				if (cartaAMover.getNumero() == cartaDestino.getNumero()
						|| cartaAMover.getPalo() == cartaDestino.getPalo()) {

					System.out.println("CARTA COORECTA");
					System.out.println(cartaAMover.toString() + "->" + cartaDestino.toString());
					
					arrayBotones[posCartaAMover][0].setLocation(point);// Movemos la carta a la posicion de la carta destino

				} else {
					System.out.println("JUGADA INCORRECTA");
				}

				

			} else if (posToMove == 3) {

				System.out.println("JUGADA VALIDA POR MOVIMIENTOS, movemos 3");

			} else {

				System.out.println("JUGADA NO VALIDA, movimientos incorrectos");

			}
			primeraPulsacion = false;
			segundaPulsacion = false;
		}

	}

	private Carta buscaCarta(String cartaStr) {

		String[] parts = cartaStr.split("");

		char chtNum = parts[0].charAt(0);
		char chtPalo = parts[1].charAt(0);
		Carta cartaRt = null;

		for (int i = 0; i < ABaraja.length; i++) {

			if (ABaraja[i].getNumero() == chtNum && ABaraja[i].getPalo() == chtPalo) {

				cartaRt = new Carta(ABaraja[i]);// Copiamos la carta
			}

		}

		return cartaRt;
	}

	private int posicion(String carta) {

		int ret = -1;
		for (int i = 0; i < 40; i++) {

			if (arrayBotones[i][0].getLabel().equals(carta)) {

				ret = i;

			}
		}
		return ret;
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
