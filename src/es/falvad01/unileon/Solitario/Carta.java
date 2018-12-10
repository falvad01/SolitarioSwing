package es.falvad01.unileon.Solitario;

import java.awt.Image;

//TODO FALTA EL ATRIBUTO COLOR, POR LO QUE CONVENDRIA HACER DOS CLASES CARTA PARA CAD TIPO DE CARTA
public class Carta {

	private char palo;
	private char numero;
	private String ruta;

	/**
	 * 
	 * @param palo
	 * @param num
	 */

	public Carta(char palo, char num, String ruta) {

		setPalo(palo);
		setNumero(num);

	}

	/**
	 * 
	 * @param num
	 */
	private void setNumero(char num) {
		this.numero = num;
	}

	/**
	 * 
	 * @param palo
	 */
	private void setPalo(char palo) {
		this.palo = palo;
	}
	/**
	 * 
	 * @return
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * 
	 * @param ruta
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * 
	 * @return
	 */
	public char getPalo() {
		return this.palo;
	}

	/**
	 * 
	 * @return
	 */
	public char getNumero() {
		return this.numero;
	}

	public String toString() {

		return getNumero() + "" + getPalo();
	}

}
