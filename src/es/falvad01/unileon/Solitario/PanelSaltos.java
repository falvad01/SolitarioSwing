package es.falvad01.unileon.Solitario;

import java.awt.Color;
import java.awt.Component;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSaltos extends JPanel implements ActionListener {

	private JPanel saltos;
	private Baraja baraja;
	private Carta[] ABaraja;

	private String rutaJuego = null;
	BufferedImage cartaImage;

	private JButton[][] matrizBotones = new JButton[40][40];

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

	public void iniciarJuego() {

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

				matrizBotones[0][i] = new JButton(nombre.toString());
				matrizBotones[0][i].setBounds(20 + (i1 * 95), 10, 90, 120);
				i1++;
			} else if (i >= 10 && i < 20) { // fila 2

				matrizBotones[0][i] = new JButton(nombre.toString());
				matrizBotones[0][i].setBounds(20 + (i2 * 95), 130, 90, 120);
				i2++;

			} else if (i >= 20 && i < 30) {// fila 3

				matrizBotones[0][i] = new JButton(nombre.toString());
				matrizBotones[0][i].setBounds(20 + (i3 * 95), 250, 90, 120);
				i3++;

			} else if (i >= 30 && i < 40) {// fila 4

				matrizBotones[0][i] = new JButton(nombre.toString());
				matrizBotones[0][i].setBounds(20 + (i4 * 95), 370, 90, 120);
				i4++;
			}

			saltos.add(matrizBotones[0][i], new Integer(-2));
			matrizBotones[0][i].setIcon(icono);
			matrizBotones[0][i].addActionListener(this);

			nombre.delete(0, nombre.length());

		

		}
		
		ImageIcon nul = new ImageIcon(getClass().getResource("/imagenesBarajaE/reversoE.jpg"));

		for (int j = 1; j < 40; j++) {
			for (int k = 0; k < 40; k++) {
					
				matrizBotones[j][k] = new JButton("Nulo");
				matrizBotones[j][k].setIcon(nul);
				
				
			}
		}

		printMatrix();

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
			posToMove = posCartaAMover - posCartaDestino;// Calculamos cuantas posiciones movemos las cartas
			System.out.println("Posiciones a mover " + posToMove);

			if (posToMove == 1) {// Valido por un movimiento
				System.out.println("JUGADA VALIDA POR MOVIMIENTOS, movemos 1");
				Point point = matrizBotones[0][posCartaDestino].getLocation(); // Guardamos la posicion de donde tenemos
																				// que mover la carta

				Carta cartaAMover = buscaCarta(strAMover);
				Carta cartaDestino = buscaCarta(strDestino);

				if (cartaAMover.getNumero() == cartaDestino.getNumero()
						|| cartaAMover.getPalo() == cartaDestino.getPalo()) { // La combinacion de cartas es correcta

					System.out.println("CARTA COORECTA");
					System.out.println(cartaAMover.toString() + "->" + cartaDestino.toString());

					comprobarMoviminetos(posCartaAMover, posToMove);

				} else {
					System.out.println("JUGADA INCORRECTA");// La combinacion de cartas no es correcta
				}

			} else if (posToMove == 3) {// Valido por dos movimientos

				System.out.println("JUGADA VALIDA POR MOVIMIENTOS, movemos 3");

			} else {// No valido por movimientos

				System.out.println("JUGADA NO VALIDA, movimientos incorrectos");

			}
			primeraPulsacion = false;
			segundaPulsacion = false;
		}

	}

	private void comprobarMoviminetos(int horizontal, int posAMover) {

		if (posAMover == 1) {

			if (matrizBotones[1][horizontal].getLabel() != "Nulo") {

				moveDown(horizontal - 1, 0);
				moveLeft(horizontal, 0);
				moveUp(horizontal, 0);

			} else {

				// TODO no funciona bien
				// MOVIMIENTOS GRAFICOS
				iconoAbajo(horizontal-1);
				iconoIzquierda(horizontal);
				todosIconosIzquierda(horizontal);
				matrizBotones[0][posToDelete--].setVisible(false);
				//matrizBotones[0][posToDelete].setLabel("");
				//matrizBotones[0][posToDelete].setVisible(false);
				

				// MOVIMIETOS INTERNOS
				// moveDown(horizontal - 1, 0);
				// moveLeft(horizontal, 0);
				// moveAllLeft(horizontal, 0);

				// matrizBotones[0][posToDelete] = null;
			}

		} else if (posAMover == 3) {

		}
		printMatrix();

	}

	private void iconoIzquierda(int horizontal) {

		matrizBotones[0][horizontal - 1].setIcon(matrizBotones[0][horizontal].getIcon());
		matrizBotones[0][horizontal - 1].setLabel(matrizBotones[0][horizontal].getLabel());

	}

	private void todosIconosIzquierda(int horizontal) {

		for (int i = horizontal; i < 39; i++) {

			matrizBotones[0][i].setIcon(matrizBotones[0][i + 1].getIcon());
			matrizBotones[0][i].setLabel(matrizBotones[0][i + 1].getLabel());
		}
	}

	private void iconoAbajo(int horizontal) {

		for (int i = 39; i >= 0; i--) {
			if (i < 39) {

				matrizBotones[i + 1][horizontal].setIcon(matrizBotones[i][horizontal].getIcon());
				matrizBotones[i + 1][horizontal].setLabel(matrizBotones[i][horizontal].getLabel());

			}
		}

	}

	private void moveAllLeft(int horizontal, int vertical) {

		for (int j = 0; j < 39; j++) {
			for (int i = horizontal; i < 39; i++) {
				matrizBotones[j][i] = matrizBotones[j][i + 1]; // Movemos a la izquierda toda la baraja
			}
		}

	}

	private void moveLeft(int horizontal, int vertical) {

		matrizBotones[vertical][horizontal - 1] = matrizBotones[vertical][horizontal];

	}

	private void moveDown(int horizontal, int vertical) {

		for (int i = 39; i >= 0; i--) {
			if (i < 39) {
				matrizBotones[i + 1][horizontal] = matrizBotones[i][horizontal];
			}
		}

	}

	private void moveUp(int horizontal, int vertical) {

		for (int i = 0; i < 40; i++) {
			matrizBotones[i][horizontal] = matrizBotones[i + 1][horizontal]; // Subimos las que tenia debajo la que
																				// movimos
			// a la izquierda
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

		System.out.println("CARTA EN POSICION" + carta);

		int ret = -1;
		for (int i = 0; i < 40; i++) {

			if (matrizBotones[0][i] != null) {

				if (matrizBotones[0][i].getLabel().equals(carta)) {

					ret = i;
				}
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

	public void printMatrix() {

		/**
		 * DEBUG
		 */
		for (int x = 0; x < matrizBotones.length; x++) {
			System.out.print("|");
			for (int y = 0; y < matrizBotones[x].length; y++) {
				if (matrizBotones[x][y] != null) {
					System.out.print(matrizBotones[x][y].getLabel());
				} else {
					System.out.print("Null");
				}
				if (y != matrizBotones[x].length - 1) {

					System.out.print("\t");

				}
			}

			System.out.println("");

		}

		/**
		 * FIN DEBUG
		 */

	}

}
