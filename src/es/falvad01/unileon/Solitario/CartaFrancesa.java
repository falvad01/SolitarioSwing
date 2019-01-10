package es.falvad01.unileon.Solitario;

import javax.swing.ImageIcon;

public class CartaFrancesa {

	private char num;
	private char palo;
	private String color;
	private ImageIcon imagen;
	private ImageIcon reverso;

	public CartaFrancesa(char num, char palo, ImageIcon imagen, ImageIcon reverso) {

		setNum(num);
		setPalo(palo);
		setImagen(imagen);
		setReverso(reverso);
		setColor(establecerColor(palo));
	}

	public CartaFrancesa(CartaFrancesa carta) {

		this.num = carta.getNum();
		this.palo = carta.getPalo();
		this.imagen = carta.getImagen();
		this.reverso = carta.getReverso();
		this.color = carta.getColor();
	}

	public char getNum() {
		return num;
	}

	public void setNum(char num) {
		this.num = num;
	}

	public char getPalo() {
		return palo;
	}

	public void setPalo(char palo) {
		this.palo = palo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	public ImageIcon getReverso() {
		return reverso;
	}

	public void setReverso(ImageIcon reverso) {
		this.reverso = reverso;
	}

	private String establecerColor(char palo) {

		String color = null;
		switch (palo) {
		case 'C':
		case 'S':
			color = "Negro";
			break;
		case 'H':
		case 'D':
			color = "Rojo";
			break;
		default:
			color = "Error";
			break;
		}

		return color;

	}

	public int establecerValor() {

		int ret = 0;
		switch (this.getNum()) {
		case 'A':
			ret = 1;
			break;
		case '2':
			ret = 2;
			break;
		case '3':
			ret = 3;
			break;
		case '4':
			ret = 4;
			break;
		case '5':
			ret = 5;
			break;
		case '6':
			ret = 6;
			break;
		case '7':
			ret = 7;
			break;
		case '8':
			ret = 8;
			break;
		case '9':
			ret = 9;
			break;
		case 'T':
			ret = 10;
			break;
		case 'J':
			ret = 11;
			break;
		case 'Q':
			ret = 12;
			break;
		case 'V': //Caso vacio
			ret = -1;
			break;
		case 'K':
			ret = 13;
			break;

		default:
			ret = 0;
			break;
		}

		return ret;
	}

	public String toString() {

		return getNum() + "" + getPalo();
	}

}
