package es.falvad01.unileon.Solitario;

import java.awt.Image;

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

		if (this.juego == juego.Saltos) {

			this.baraja = new Carta[40];
			crearBarajaE();

		} else if (this.juego == EJuego.Clasico) {

			this.baraja = new Carta[52];
			crearBarajaF();

		}
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

		Carta carta;
		Image sprite;
		StringBuilder path = new StringBuilder();

		char[] paloF = { 'C', 'D', 'H', 'S' };
		char[] numeroF = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };

		for (int i = 0; i < paloF.length; i++) {
			for (int j = 0; j < numeroF.length; j++) {
				path.append("etc/images/french/" + paloF[i] + "" + numeroF[j] + ".jpg"); // Obtenemos la ruta de la
																							// imagen

				// TODO CARGAR LA IMAGEN ASOCIADA A CADA CARTA
				carta = new Carta(paloF[i], numeroF[j]/* ,sprite */);
				baraja[next++] = carta;

				path = new StringBuilder();

			}
		}

	}

	private void crearBarajaE() {

		Carta carta;
		Image sprite;
		StringBuilder path = new StringBuilder();

		char[] paloE = { 'O', 'C', 'E', 'B' };
		char[] numeroE = { 'A', '2', '3', '4', '5', '6', '7', 'S', 'C', 'R' };

		for (int i = 0; i < paloE.length; i++) {
			for (int j = 0; j < numeroE.length; j++) {
				path.append("etc/images/french/" + paloE[i] + "" + numeroE[j] + ".jpg"); // Obtenemos la ruta de la
																							// imagen

				// TODO CARGAR LA IMAGEN ASOCIADA A CADA CARTA
				carta = new Carta(paloE[i], numeroE[j]/* ,sprite */);
				baraja[next++] = carta;

				path = new StringBuilder();

			}
		}

	}

	public void barajarF() {

		int random;
		Carta buffer = new Carta('Z', 'Z');

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
		Carta buffer = new Carta('H', 'H');

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
