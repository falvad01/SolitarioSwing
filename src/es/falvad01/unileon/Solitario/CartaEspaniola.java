package es.falvad01.unileon.Solitario;

import java.awt.Image;

import javax.swing.ImageIcon;


public class CartaEspaniola {

	private char numero;
	private char palo;
	private ImageIcon imagen;

	/**
	 * 
	 * @param palo
	 * @param num
	 */

	public CartaEspaniola(char num, char palo, ImageIcon imagen) {

		setNumero(num);
		setPalo(palo);
		setImage(imagen);

	}

	public CartaEspaniola(CartaEspaniola carta) {
		this.numero = carta.getNumero();
		this.palo = carta.getPalo();
		this.imagen = carta.getImageIcon();
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
	public ImageIcon getImageIcon() {
		return this.imagen;
	}

	/**
	 * 
	 * @param ruta
	 */
	public void setImage(ImageIcon image) {
		this.imagen = image;
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
