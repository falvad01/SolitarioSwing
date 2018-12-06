package es.falvad01.unileon.Solitario;

import java.awt.Image;

public class Carta {

	private char palo;
	private char numero;
	private Image sprite;

	/**
	 * 
	 * @param palo
	 * @param num
	 */

	public Carta(char palo, char num/* ,Image image */) {

		setPalo(palo);
		setNumero(num);
		// this.sprite = image;
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
