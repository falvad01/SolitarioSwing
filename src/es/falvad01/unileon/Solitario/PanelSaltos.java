package es.falvad01.unileon.Solitario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

//TODO REFACTORIZAR NOMBRES
//TODO CENTRAR LA IMAGEN DE LOS BOTONES
//TODO COMENTAR EL CODIGO

@SuppressWarnings({ "deprecation", "serial" })
public class PanelSaltos extends JPanel implements ActionListener {

	private JPanel saltos;
	private Baraja baraja;
	private CartaEspaniola[] ABaraja;

	private String rutaJuego = null;
	private BufferedImage cartaImage;

	private JButton[][] matrizBotones = new JButton[40][40];

	private boolean primeraPulsacion = false;
	private boolean segundaPulsacion = false;

	private String strAMover;
	private String strDestino;

	private int posToMove;
	private int posCartaAMover = 0;
	private int posCartaDestino = 0;

	private int posToDelete = 39;

	private boolean flagIntentos = false;

	StringBuilder jugadas;

	/**
	 * 
	 * @param panel
	 * 
	 *              Contructor de clase
	 */
	public PanelSaltos(JPanel panel) {
		// setPreferredSize(new Dimension(200, 200));
		setBackground(Color.red);
		setOpaque(true);
		this.saltos = panel;
		this.baraja = new Baraja(EJuego.Saltos);
		jugadas = new StringBuilder();

	}

	/**
	 * 
	 * @return
	 */
	public String getRutaJuego() {
		return this.rutaJuego;
	}

	/**
	 * 
	 */
	public void iniciarJuegoSaltos() {

		baraja.crearBarajaE();
		baraja.barajarE();
		ABaraja = baraja.getBarajaEspaniola();

		for (int i = 0; i < ABaraja.length; i++) {
			System.out.print(ABaraja[i] + " ");
		}

		System.out.println("/n");

		repaint();
		pintarCartas();

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
		ImageIcon nul = null;
		try {
			nul = new ImageIcon(getClass().getResource("/imagenesBarajaE/reversoE.jpg"));
		} catch (Exception e) {
			System.out.println("CArta no encontrada");
		}
		for (int j = 1; j < 40; j++) {
			for (int k = 0; k < 40; k++) {

				matrizBotones[j][k] = new JButton("NuLo");
				matrizBotones[j][k].setIcon(nul);

			}
		}

		// printMatrix();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Border borde = BorderFactory.createMatteBorder(1, 5, 1, 1, Color.red);

		if ((primeraPulsacion) && (!segundaPulsacion)) {

			segundaPulsacion = true;
			strDestino = ((JButton) e.getSource()).getLabel();
			posCartaDestino = posicion(strDestino);
			matrizBotones[0][posCartaAMover].setBorderPainted(false);
			System.out.println("POS: " + posCartaDestino);
			System.out.println("Destino:" + strDestino);
			System.out.println();
		}

		if (!primeraPulsacion) {
			primeraPulsacion = true;
			strAMover = ((JButton) e.getSource()).getLabel();
			posCartaAMover = posicion(strAMover);
			matrizBotones[0][posCartaAMover].setBorder(borde);
			matrizBotones[0][posCartaAMover].setBorderPainted(true);
			System.out.println("POS: " + posCartaAMover);
			System.out.println("Origen: " + strAMover);
			System.out.println();

		}

		if (primeraPulsacion && segundaPulsacion) {
			posToMove = posCartaAMover - posCartaDestino;// Calculamos cuantas posiciones movemos las cartas
			System.out.println("Posiciones a mover " + posToMove);

			if (posToMove == 1) {// Valido por un movimiento
				System.out.println("JUGADA VALIDA POR MOVIMIENTOS, movemos 1");

				CartaEspaniola cartaAMover = buscaCarta(strAMover);
				CartaEspaniola cartaDestino = buscaCarta(strDestino);

				if (cartaAMover.getNumero() == cartaDestino.getNumero()
						|| cartaAMover.getPalo() == cartaDestino.getPalo()) { // La combinacion de cartas es correcta

					System.out.println("CARTA COORECTA");
					System.out.println(cartaAMover.toString() + "->" + cartaDestino.toString());
					jugadas.append(cartaAMover.toString() + "-" + cartaDestino.toString());
					jugadas.append(System.getProperty("line.separator"));
					System.out.println(jugadas.toString());

					comprobarMoviminetos(posCartaAMover, posToMove, false);

				} else {
					System.out.println("JUGADA INCORRECTA");// La combinacion de cartas no es correcta
				}

			} else if (posToMove == 3) {// Valido por dos movimientos

				System.out.println("JUGADA VALIDA POR MOVIMIENTOS, movemos 3");

				CartaEspaniola cartaAMover = buscaCarta(strAMover);
				CartaEspaniola cartaDestino = buscaCarta(strDestino);

				if (cartaAMover.getNumero() == cartaDestino.getNumero()
						|| cartaAMover.getPalo() == cartaDestino.getPalo()) { // La combinacion de cartas es correcta

					System.out.println("CARTA COORECTA");
					System.out.println(cartaAMover.toString() + "->" + cartaDestino.toString());

					comprobarMoviminetos(posCartaAMover, posToMove,false);
				}

			} else {// No valido por movimientos

				System.out.println("JUGADA NO VALIDA, movimientos incorrectos");

			}
			primeraPulsacion = false;
			segundaPulsacion = false;
		}

	}

	/**
	 * 
	 * @param horizontal ES LA CARTA QUE SE TIENE QUE MOVER
	 * @param posAMover
	 */
	private void comprobarMoviminetos(int horizontal, int posAMover, boolean auto) {

		if (posAMover == 1) {

			if (matrizBotones[1][horizontal].getLabel() != "NuLo") {

				iconoAbajo(horizontal - 1);
				iconoIzquierda(horizontal, 1);
				iconoArriba(horizontal);

			} else {

				iconoAbajo(horizontal - 1);
				iconoIzquierda(horizontal, 1);
				todosIconosIzquierda(horizontal);
				matrizBotones[0][posToDelete].setVisible(false);
				matrizBotones[0][posToDelete--] = null;

			}

		} else if (posAMover == 3) {

			if (matrizBotones[1][horizontal].getLabel() != "NuLo") {

				iconoAbajo(horizontal - 3);
				iconoIzquierda(horizontal, 3);
				iconoArriba(horizontal);

			} else {

				iconoAbajo(horizontal - 3);
				iconoIzquierda(horizontal, 3);
				todosIconosIzquierda(horizontal);
				matrizBotones[0][posToDelete].setVisible(false);
				matrizBotones[0][posToDelete--] = null;

			}

		}
		//printMatrix();
		
		if(auto) {
			resolverAuto(1, 0);
		}
	}

	private void iconoIzquierda(int horizontal, int mover) {

		

		matrizBotones[0][horizontal - mover].setIcon(matrizBotones[0][horizontal].getIcon());
		matrizBotones[0][horizontal - mover].setLabel(matrizBotones[0][horizontal].getLabel());

	}

	private void todosIconosIzquierda(int horizontal) {

		for (int i = 0; i < 39; i++) {
			for (int j = horizontal; j < 39; j++) {

				if (matrizBotones[i][j + 1] != null) {

					matrizBotones[i][j].setIcon(matrizBotones[i][j + 1].getIcon());
					matrizBotones[i][j].setLabel(matrizBotones[i][j + 1].getLabel());
				}
			}
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

	private void iconoArriba(int horizontal) {

		System.out.println("ARRIBA");

		for (int i = 0; i < 39; i++) {

			if (matrizBotones[i][horizontal] != null) {
				matrizBotones[i][horizontal].setIcon(matrizBotones[i + 1][horizontal].getIcon());
				matrizBotones[i][horizontal].setLabel(matrizBotones[i + 1][horizontal].getLabel());
			}
		}

	}

	private CartaEspaniola buscaCarta(String cartaStr) {

		String[] parts = cartaStr.split("");

		char chtNum = parts[0].charAt(0);
		char chtPalo = parts[1].charAt(0);
		CartaEspaniola cartaRt = null;

		for (int i = 0; i < ABaraja.length; i++) {

			if (ABaraja[i].getNumero() == chtNum && ABaraja[i].getPalo() == chtPalo) {

				cartaRt = new CartaEspaniola(ABaraja[i]);// Copiamos la carta
			}

		}

		return cartaRt;
	}

	private int posicion(String carta) {

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

		System.out.println("GUARDANDO");

		try {
			FileWriter fichero = new FileWriter(rutaJuego);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println("Solitario Saltos");

			for (int x = 0; x < 39; x++) {
				for (int y = 39; y >= 0; y--) {

					if (matrizBotones[y][x] != null) {
						if (matrizBotones[y][x].getLabel().equals("NuLo") == false)
							pw.print(matrizBotones[y][x].getLabel());
					}
				}

				pw.println();
			}
			fichero.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void sumarEstadisticas() {

		String fichero = "./Estadisticas/Estadisticas.txt";
		String[] linea = new String[6];

		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);

			for (int i = 0; i < 6; i++) {

				linea[i] = br.readLine();

			}

			int intentos = Integer.parseInt(linea[1]);
			int interntosExito = Integer.parseInt(linea[2]);

			if (!flagIntentos) {
				intentos++;
				flagIntentos = true;

				// TODO BUSCAR COMO ACTUALIZAR ESTOS DOS PARAMETROS

			}

			// TODO PONER CONDICION DE INTENTO CON EXITO

		} catch (Exception a) {
			System.out.println("Error leyendo fichero " + fichero + ": " + a);
		}

	}

	public void cargarJuego() {

		// TODO METODO PARA CARGAR LOS DATOS DEL ARCHIVO DEL JUEGO
	}

	public void limpiarPanel() {

		// TODO AQUI SE REINICIARIAN TODAS LA VARIABLES, Y SE ELIMINARIAN LOS BOTONES
		// PARA EMEPZAR UN JUEGO NUEVO

	}

	public boolean resolverAuto(int posX, int posY) {

		boolean checkOtherOption = true;

		if (posX == 40) {
			return true;
		} else {

			if ((posX >= 3) && checkOtherOption == true) { // Para mirar la primera a la izquierda la primera posicion
				// debe de ser la 3 y que la de la izquierda no se realizara
				
				if (matrizBotones[0][posX] != null && ((buscaCarta(matrizBotones[0][posX].getLabel())
						.getNumero() == (buscaCarta(matrizBotones[0][posX - 3].getLabel()).getNumero()))
						|| (buscaCarta(matrizBotones[0][posX].getLabel())
								.getPalo() == (buscaCarta(matrizBotones[0][posX - 3].getLabel()).getPalo())))) {// Miaramos
					// si 3
					// cartas a la
					// izquierda hay
					// una que
					// empareja
					System.out.println("En tres: " +  buscaCarta(matrizBotones[0][posX].getLabel()).toString() + "-" +  buscaCarta(matrizBotones[0][posX - 3].getLabel()).toString());
					comprobarMoviminetos(posX,3,true); // Enviamos la posicion destino
					checkOtherOption = false;// Cambiamos el falg para que no mire otras opciones
				}
			}

			if (checkOtherOption == true) {

				if (matrizBotones[0][posX] != null && ((buscaCarta(matrizBotones[0][posX].getLabel())
						.getNumero() == (buscaCarta(matrizBotones[0][posX - 1].getLabel()).getNumero()))
						|| (buscaCarta(matrizBotones[0][posX].getLabel())
								.getPalo() == (buscaCarta(matrizBotones[0][posX - 1].getLabel()).getPalo())))) { // Miramos
					// si
					// la
					// inmediatamente a
					// la
					// izquierda emparejan
					System.out.println("En uno: " +  buscaCarta(matrizBotones[0][posX].getLabel()).toString() + "-" +  buscaCarta(matrizBotones[0][posX - 1].getLabel()).toString());

					comprobarMoviminetos(posX, 1,true);// Enviamos la posicion destino
					checkOtherOption = false;// Cambiamos el falg para que no mire otras opciones

				}
			}
		}

		return resolverAuto(posX + 1, posY);

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
