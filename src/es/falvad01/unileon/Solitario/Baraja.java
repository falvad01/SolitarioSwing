package es.falvad01.unileon.Solitario;

import java.awt.Image;

//TODO BORRAR EL ARRAY DE CARTAS Y VOLVER A PONER NEXT A 0 CUANDO SE INICIE OTRO PANEL
import javax.swing.ImageIcon;

public class Baraja {

	private EJuego juego;
	private CartaEspañola[] barajaEspañola;
	private CartaFrancesa[] barajaFrancesa;
	
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
	public CartaEspañola[] getBarajaEspañola() {
		return barajaEspañola;
	}
	
	public CartaFrancesa[] getBarajaFrancesa() {
		return barajaFrancesa;
	}

	/**
	 * 
	 * @param baraja
	 */
	public void setBaraja(CartaEspañola[] baraja) {
		this.barajaEspañola = baraja;
	}

	public void crearBarajaF() {
		System.out.println("CREAR BARAJA FRANCESA");
		CartaFrancesa carta;
		StringBuilder pathBuilder = new StringBuilder();
		String path;
		barajaFrancesa = new CartaFrancesa[52];
		ImageIcon imagen = null;
		ImageIcon reverso = null;

		char[] paloF = { 'C', 'D', 'H', 'S' };
		char[] numeroF = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };

		for (int i = 0; i < paloF.length; i++) {
			for (int j = 0; j < numeroF.length; j++) {

				pathBuilder.append("/imagenesBarajaF/" + numeroF[j] + "" + paloF[i] + ".png"); // Obtenemos la ruta de
																								// la imagen
				path = pathBuilder.toString();
				
				try {
					imagen = new ImageIcon(getClass().getResource(path));
				} catch (Exception e) {
					System.out.println("Carta no encontrada");
				}
				
				try {
					reverso = new ImageIcon(getClass().getResource("/imagenesBarajaF/reversoF.jpg"));
				} catch (Exception e) {
					System.out.println("Reverso no encontrada");
				}
				carta = new CartaFrancesa(numeroF[j], paloF[i], imagen, reverso);

				barajaFrancesa[next++] = carta;

				pathBuilder = new StringBuilder();

			}
		}

	}

	public void crearBarajaE() {
		System.out.println("CREAR BARAJA ESPAÑOLA");

		CartaEspañola carta;
		StringBuilder pathBuilder = new StringBuilder();
		String path;
		barajaEspañola = new CartaEspañola[40];
		ImageIcon imagen = null;

		char[] paloE = { 'O', 'C', 'E', 'B' };
		char[] numeroE = { 'A', '2', '3', '4', '5', '6', '7', 'S', 'C', 'R' };

		for (int j = 0; j < paloE.length; j++) {
			for (int i = 0; i < numeroE.length; i++) {
				
				pathBuilder.append("/imagenesBarajaE/" + numeroE[i] + "" + paloE[j] + ".jpg"); // Obtenemos la ruta de
																								// la imagen

				path = pathBuilder.toString();
				//System.out.println(path);
				try {
					imagen = new ImageIcon(getClass().getResource(path));
				} catch (Exception e) {
					System.out.println("Carta no encontrada");
				}

				carta = new CartaEspañola(numeroE[i], paloE[j], imagen);

				barajaEspañola[next++] = carta;

				pathBuilder = new StringBuilder();

			}
		}

	}

	public void barajarF() {

		int random;
		CartaFrancesa buffer = new CartaFrancesa('Z', 'Z', null,null);

		int[] randomArray = new int[52];
		for (int i = 0; i < barajaFrancesa.length; i++) {

			random = (int) (Math.random() * 51) + 1 - 1;

			randomArray[i] = random;

			buffer = barajaFrancesa[random];
			barajaFrancesa[random] = barajaFrancesa[i];
			barajaFrancesa[i] = buffer;

		}

	}

	public void barajarE() {

		int random;
		CartaEspañola buffer = new CartaEspañola('H', 'H', null);

		int[] randomArray = new int[40];
		for (int i = 0; i < barajaEspañola.length; i++) {

			random = (int) (Math.random() * 39) + 1 - 1;

			randomArray[i] = random;

			buffer = barajaEspañola[random];
			barajaEspañola[random] = barajaEspañola[i];
			barajaEspañola[i] = buffer;

		}

	}

	public String toString() {

		StringBuilder out = new StringBuilder();

		for (int i = 0; i < barajaEspañola.length; i++) {

			out.append(barajaEspañola[i] + " ");
		}

		return out.toString();

	}

}
