package es.falvad01.unileon.Solitario;

import java.awt.Image;

//TODO BORRAR EL ARRAY DE CARTAS Y VOLVER A PONER NEXT A 0 CUANDO SE INICIE OTRO PANEL
import javax.swing.ImageIcon;

public class Baraja {

	private EJuego juego;
	private Carta[] baraja;
	private int next;

	/**
	 * 
	 * @param juego
	 */
	public Baraja(EJuego juego) {
		this.next = 0;
		setJuego(juego);

	}

	/**
	 * 
	 * @param juego
	 */
	public void setJuego(EJuego juego) {
		this.juego = juego;
	}

	/**
	 * 
	 * @return
	 */
	public Carta[] getBaraja() {
		return baraja;
	}

	/**
	 * 
	 * @param baraja
	 */
	public void setBaraja(Carta[] baraja) {
		this.baraja = baraja;
	}

	public void crearBarajaF() {
		System.out.println("CREAR BARAJA FRANCESA");
		Carta carta;
		Image sprite;
		StringBuilder pathBuilder = new StringBuilder();
		String path;
		baraja = new Carta[52];
		ImageIcon imagen = null;

		char[] paloF = { 'C', 'D', 'H', 'S' };
		char[] numeroF = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };

		for (int i = 0; i < paloF.length; i++) {
			for (int j = 0; j < numeroF.length; j++) {

				pathBuilder.append("etc/images/french/" + numeroF[i] + "" + paloF[j] + ".jpg"); // Obtenemos la ruta de
																								// la imagen

				path = pathBuilder.toString();

				// imagen = new ImageIcon(getClass().getResource(path));

				// TODO REVISAR LA RUTA, ALGUNA PUEDE FALTAR
				carta = new Carta(numeroF[i], paloF[j], imagen);

				baraja[next++] = carta;

				pathBuilder = new StringBuilder();

			}
		}

	}

	public void crearBarajaE() {
		System.out.println("CREAR BARAJA ESPAÑOLA");

		Carta carta;
		Image sprite;
		StringBuilder pathBuilder = new StringBuilder();
		String path;
		baraja = new Carta[40];
		ImageIcon imagen = null;

		char[] paloE = { 'O', 'C', 'E', 'B' };
		char[] numeroE = { 'A', '2', '3', '4', '5', '6', '7', 'S', 'C', 'R' };

		for (int j = 0; j < paloE.length; j++) {
			for (int i = 0; i < numeroE.length; i++) {
				
				pathBuilder.append("/imagenesBarajaE/" + numeroE[i] + "" + paloE[j] + ".jpg"); // Obtenemos la ruta de
																								// la imagen

				path = pathBuilder.toString();
				System.out.println(path);
				try {
					imagen = new ImageIcon(getClass().getResource(path));
				} catch (Exception e) {
					System.out.println("Carta no encontrada");
				}

				carta = new Carta(numeroE[i], paloE[j], imagen);

				baraja[next++] = carta;

				pathBuilder = new StringBuilder();

			}
		}

	}

	public void barajarF() {

		int random;
		Carta buffer = new Carta('Z', 'Z', null);

		int[] randomArray = new int[52];
		for (int i = 0; i < baraja.length; i++) {

			random = (int) (Math.random() * 51) + 1 - 1;

			randomArray[i] = random;

			buffer = baraja[random];
			baraja[random] = baraja[i];
			baraja[i] = buffer;

		}

	}

	public void barajarE() {

		int random;
		Carta buffer = new Carta('H', 'H', null);

		int[] randomArray = new int[40];
		for (int i = 0; i < baraja.length; i++) {

			random = (int) (Math.random() * 39) + 1 - 1;

			randomArray[i] = random;

			buffer = baraja[random];
			baraja[random] = baraja[i];
			baraja[i] = buffer;

		}

	}

	public String toString() {

		StringBuilder out = new StringBuilder();

		for (int i = 0; i < baraja.length; i++) {

			out.append(baraja[i] + " ");
		}

		return out.toString();

	}

}
