package es.falvad01.unileon.Solitario;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("deprecation")
public class PanelClasico extends JPanel implements ActionListener {

	private JLayeredPane clasico;
	private Baraja baraja;
	private CartaFrancesa[] ABaraja;
	private String rutaJuego = null;
	BufferedImage[] cartaImageArray;

	private JButton[] barajaInicial;
	private JButton[] barajaDescartes;
	private JButton[] fin1;
	private JButton[] fin2;
	private JButton[] fin3;
	private JButton[] fin4;
	private JButton[] monton1;
	private JButton[] monton2;
	private JButton[] monton3;
	private JButton[] monton4;
	private JButton[] monton5;
	private JButton[] monton6;
	private JButton[] monton7;

	private ImageIcon[] icono;
	private ImageIcon reverso;

	boolean primeraPulsacion = false;
	boolean segundaPulsacion = false;

	private String strAMover;
	private String strDestino;

	private String mazoAMover;
	private String mazoDestino;

	private int posCartaAMover = 0;
	private int posCartaDestino = 0;

	CartaFrancesa cartaAMover;
	CartaFrancesa cartaDestino;

	private int borrarInicio = 22;

	public PanelClasico(JLayeredPane juegoClasico2) {

		this.clasico = juegoClasico2;
		this.baraja = new Baraja(EJuego.Clasico);
		barajaInicial = new JButton[23];
		barajaDescartes = new JButton[24];

		fin1 = new JButton[14];// Tiene uno mas de tamaño de lo que deberia debido a la carta cebo que esta en
								// primer lugar
		fin2 = new JButton[14];
		fin3 = new JButton[14];
		fin4 = new JButton[14];

		monton1 = new JButton[20];
		monton2 = new JButton[20];
		monton3 = new JButton[20];
		monton4 = new JButton[20];
		monton5 = new JButton[20];
		monton6 = new JButton[20];
		monton7 = new JButton[20];

		icono = new ImageIcon[52];

	}

	public String getRutaJuego() {
		return this.rutaJuego;
	}

	public void iniciarJuegoClasico() {

		baraja.crearBarajaF();
		baraja.barajarF();
		ABaraja = baraja.getBarajaFrancesa();
		for (int i = 0; i < ABaraja.length; i++) {
			System.out.print(ABaraja[i] + " ");
		}

		// System.out.println("/n");

		pintarCartas();

	}

	/**
	 * TODO MIRAR LA COLOCACION DE LAS CARTAS, ENCIMA Y DEBAJO
	 */
	private void pintarCartas() {

		StringBuilder nombre = new StringBuilder();

		int i1 = 19;
		int i2 = 19;
		int i3 = 19;
		int i4 = 19;
		int i5 = 19;
		int i6 = 19;
		int i7 = 19;

		ImageIcon nul = null;

		for (int i = 0; i < 52; i++) {

			nombre.append(ABaraja[i]);

			Image img = ABaraja[i].getImagen().getImage();
			Image imgReverso = ABaraja[i].getReverso().getImage();

			img = img.getScaledInstance(90, 120, Image.SCALE_SMOOTH);
			imgReverso = imgReverso.getScaledInstance(90, 120, Image.SCALE_SMOOTH);

			icono[i] = new ImageIcon(img);
			reverso = new ImageIcon(imgReverso);

			if (i < 23) {// Baraja Principal
				barajaInicial[i] = new JButton(nombre.toString());
				barajaInicial[i].setIcon(icono[i]);
				barajaInicial[0].setBounds(20, 10, 90, 120);

			} else if (i == 23) { // Baraja Descartes

				barajaDescartes[0] = new JButton(nombre.toString());
				barajaDescartes[0].setIcon(icono[i]);
				barajaDescartes[0].setBounds(125, 10, 90, 120);
				clasico.add(barajaDescartes[0]);
				barajaDescartes[0].addActionListener(this);

				for (int j = 1; j < barajaDescartes.length; j++) {// Rellenamos el resto del array con nulos
					barajaDescartes[j] = new JButton("NuLo");
					barajaDescartes[j].setIcon(nul);
				}
				// TODO PONER EL REVERSO A LAS CARTAS ANTES DE ENTREGAR

			} else if (i == 24) { // MOntones de cartas

				monton1[i1] = new JButton(nombre.toString());
				monton1[i1].setIcon(icono[i]);
				monton1[i1].setBounds(20, 500 - (i1 * 19), 90, 120);
				monton1[i1].addActionListener(this);
				clasico.add(monton1[i1], i1);

			} else if (i > 24 && i < 27) {

				monton2[i2] = new JButton(nombre.toString());

				if (i2 >= 19) {
					monton2[i2].setIcon(reverso);
					monton2[i2].setEnabled(false);
				} else {

					monton2[i2].setIcon(icono[i]);
				}
				monton2[i2].setBounds(125, 500 - (i2 * 19), 90, 120);
				monton2[i2].addActionListener(this);
				clasico.add(monton2[i2], i2);
				i2--;

			} else if (i >= 27 && i <= 29) {

				monton3[i3] = new JButton(nombre.toString());
				if (i3 >= 18) {
					monton3[i3].setIcon(reverso);
					monton3[i3].setEnabled(false);
				} else {

					monton3[i3].setIcon(icono[i]);
				}
				monton3[i3].setBounds(230, 500 - (i3 * 19), 90, 120);
				monton3[i3].addActionListener(this);
				clasico.add(monton3[i3], i3);
				i3--;

			} else if (i >= 30 && i <= 33) {

				monton4[i4] = new JButton(nombre.toString());
				if (i4 >= 17) {
					monton4[i4].setIcon(reverso);
					monton4[i4].setEnabled(false);
				} else {

					monton4[i4].setIcon(icono[i]);
				}
				monton4[i4].setBounds(335, 500 - (i4 * 19), 90, 120);
				monton4[i4].addActionListener(this);
				clasico.add(monton4[i4], i4);
				i4--;

			} else if (i >= 34 && i <= 38) {

				monton5[i5] = new JButton(nombre.toString());

				if (i5 >= 16) {
					monton5[i5].setIcon(reverso);
					monton5[i5].setEnabled(false);
				} else {

					monton5[i5].setIcon(icono[i]);
				}
				monton5[i5].setBounds(440, 500 - (i5 * 19), 90, 120);
				monton5[i5].addActionListener(this);
				clasico.add(monton5[i5], i5);
				i5--;

			} else if (i >= 39 && i <= 44) {

				monton6[i6] = new JButton(nombre.toString());
				if (i6 >= 15) {
					monton6[i6].setIcon(reverso);
					monton6[i6].setEnabled(false);
				} else {

					monton6[i6].setIcon(icono[i]);
				}
				monton6[i6].setBounds(545, 500 - (i6 * 19), 90, 120);
				monton6[i6].addActionListener(this);
				clasico.add(monton6[i6], i6);
				i6--;

			} else if (i >= 45 && i <= 51) {

				monton7[i7] = new JButton(nombre.toString());

				if (i7 >= 14) {
					monton7[i7].setIcon(reverso);
					monton7[i7].setEnabled(false);
				} else {

					monton7[i7].setIcon(icono[i]);
				}

				monton7[i7].setBounds(650, 500 - (i7 * 19), 90, 120);
				monton7[i7].addActionListener(this);
				clasico.add(monton7[i7], i7);
				i7--;

			}

			nombre.delete(0, nombre.length());
		}

		fin1[0] = new JButton("F1");
		fin1[0].setBounds(335, 10, 90, 120);
		clasico.add(fin1[0]);
		fin1[0].addActionListener(this);

		fin2[0] = new JButton("F2");
		fin2[0].setBounds(440, 10, 90, 120);
		clasico.add(fin2[0]);
		fin2[0].addActionListener(this);

		fin3[0] = new JButton("F3");
		fin3[0].setBounds(545, 10, 90, 120);
		clasico.add(fin3[0]);
		fin3[0].addActionListener(this);

		fin4[0] = new JButton("F4");
		fin4[0].setBounds(650, 10, 90, 120);
		clasico.add(fin4[0]);
		fin4[0].addActionListener(this);

		for (int k = 18; k >= 0; k--) {// Relleno monton 1

			monton1[k] = new JButton("vacio");
			monton1[k].setIcon(nul);

			monton1[k].setBounds(20, 500 - (k * 19), 90, 120);
			monton1[k].addActionListener(this);
			monton1[k].setVisible(false);
			clasico.add(monton1[k], k);

		}

		for (int k = 17; k >= 0; k--) {// Relleno monton 2

			monton2[k] = new JButton("vacio");
			monton2[k].setIcon(nul);

			monton2[k].setBounds(125, 500 - (k * 19), 90, 120);
			monton2[k].addActionListener(this);
			monton2[k].setVisible(false);
			clasico.add(monton2[k], k);

		}

		for (int l = 16; l >= 0; l--) {// Relleno monton 3

			monton3[l] = new JButton("vacio");
			monton3[l].setIcon(nul);

			monton3[l].setBounds(230, 500 - (l * 19), 90, 120);
			monton3[l].addActionListener(this);
			monton3[l].setVisible(false);
			clasico.add(monton3[l], l);

		}

		for (int n = 15; n >= 0; n--) {// Relleno monton 4

			monton4[n] = new JButton("vacio");
			monton4[n].setIcon(nul);

			monton4[n].setBounds(335, 500 - (n * 19), 90, 120);
			monton4[n].addActionListener(this);
			monton4[n].setVisible(false);
			clasico.add(monton4[n], n);

		}

		for (int l = 14; l >= 0; l--) {// Relleno monton 5

			monton5[l] = new JButton("vacio");
			monton5[l].setIcon(nul);

			monton5[l].setBounds(440, 500 - (l * 19), 90, 120);
			monton5[l].addActionListener(this);
			monton5[l].setVisible(false);

			clasico.add(monton5[l], l);

		}

		for (int m = 13; m >= 0; m--) {// Relleno monton

			monton6[m] = new JButton("vacio");
			monton6[m].setIcon(nul);

			monton6[m].setBounds(545, 500 - (m * 19), 90, 120);
			monton6[m].addActionListener(this);
			monton6[m].setVisible(false);

			clasico.add(monton6[m], m);

		}

		for (int l = 12; l >= 0; l--) {// Relleno monton 7

			monton7[l] = new JButton("vacio");
			monton7[l].setIcon(nul);

			monton7[l].setBounds(650, 500 - (l * 19), 90, 120);
			monton7[l].addActionListener(this);
			monton7[l].setVisible(false);

			clasico.add(monton7[l], l);

		}

		for (int j = 1; j < fin1.length; j++) {// Rellenamos el resto del array con nulos
			fin1[j] = new JButton("NuLo");
			fin1[j].setIcon(nul);
			fin2[j] = new JButton("NuLo");
			fin2[j].setIcon(nul);
			fin3[j] = new JButton("NuLo");
			fin3[j].setIcon(nul);
			fin4[j] = new JButton("NuLo");
			fin4[j].setIcon(nul);
		}

		printArrays();
		clasico.add(barajaInicial[0]);

		barajaInicial[0].addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Border borde = BorderFactory.createMatteBorder(1, 5, 1, 1, Color.red);

		if ((primeraPulsacion) && (!segundaPulsacion)) {

			segundaPulsacion = true;
			strDestino = ((JButton) e.getSource()).getLabel();
			mazoDestino = buscarMazo(strDestino);
			posCartaDestino = buscarPosicion(strDestino);
			cartaDestino = buscaCarta(strDestino);
			System.out.println(mazoDestino + " " + posCartaDestino);

			System.out.println("Destino:" + strDestino);
			System.out.println();
		}

		if (!primeraPulsacion) {
			primeraPulsacion = true;
			strAMover = ((JButton) e.getSource()).getLabel();
			mazoAMover = buscarMazo(strAMover);
			posCartaAMover = buscarPosicion(strAMover);
			cartaAMover = buscaCarta(strAMover);

			// TODO SOMBREAR EN LOS MANTONES LAS CARTAS QUE NO ESTA DESABILITADAS CON
			// .isEnabled()
			// for(int i = 0; i < mazoAMover.getClass().getName().length();i++) {

			// }

			System.out.println(mazoAMover + " " + posCartaAMover);
			System.out.println("Origen: " + strAMover);
			System.out.println();

		}

		if (primeraPulsacion && segundaPulsacion) {

			switch (mazoAMover) {
			case "barajaInicial"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton2" || mazoDestino == "monton3"
						|| mazoDestino == "monton4" || mazoDestino == "monton5" || mazoDestino == "monton6"
						|| mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && ((cartaAMover.establecerValor() + 1) == cartaDestino.establecerValor())
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) {// Comprobamos que la carta
						// que movemos sea de un
						// valor inmediatamente
						// inferior y que el color
						// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setIcon(barajaInicial[0].getIcon());
							monton1[posCartaDestino - 1].setLabel(barajaInicial[0].getLabel());
							monton1[posCartaDestino - 1].setVisible(true);

						} else if (mazoDestino == "monton2") {

							monton2[posCartaDestino - 1].setIcon(barajaInicial[0].getIcon());
							monton2[posCartaDestino - 1].setLabel(barajaInicial[0].getLabel());
							monton2[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton3") {

							monton3[posCartaDestino - 1].setIcon(barajaInicial[0].getIcon());
							monton3[posCartaDestino - 1].setLabel(barajaInicial[0].getLabel());
							monton3[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton4") {

							monton4[posCartaDestino - 1].setIcon(barajaInicial[0].getIcon());
							monton4[posCartaDestino - 1].setLabel(barajaInicial[0].getLabel());
							monton4[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton5") {

							monton5[posCartaDestino - 1].setIcon(barajaInicial[0].getIcon());
							monton5[posCartaDestino - 1].setLabel(barajaInicial[0].getLabel());
							monton5[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton6") {

							monton6[posCartaDestino - 1].setIcon(barajaInicial[0].getIcon());
							monton6[posCartaDestino - 1].setLabel(barajaInicial[0].getLabel());
							monton6[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton7") {

							monton7[posCartaDestino - 1].setIcon(barajaInicial[0].getIcon());
							monton7[posCartaDestino - 1].setLabel(barajaInicial[0].getLabel());
							monton7[posCartaDestino - 1].setVisible(true);
						}

						subirBarajaInicial();
					} else {
						System.out.println("ERROR, MOVIMIENTO DESDE INICIO INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {

					boolean comFin = comprobarMovFin();

					if (comFin) {
						System.out.println("HOla");
						// TODO en proceso de terminar fin para cunado ya hay cartas
						if (mazoDestino == "fin1") {
							System.out.println("Mazo 1");
							for (int i = 13; i >= 0; i--) {

								if (i < 13) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(barajaInicial[0].getIcon());
							fin1[0].setLabel(barajaInicial[0].getLabel());

						} else if (mazoDestino == "fin2") {

							System.out.println("Mazo 2");
							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(barajaInicial[0].getIcon());
							fin2[0].setLabel(barajaInicial[0].getLabel());

						} else if (mazoDestino == "fin3") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(barajaInicial[0].getIcon());
							fin3[0].setLabel(barajaInicial[0].getLabel());

						} else if (mazoDestino == "fin4") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(barajaInicial[0].getIcon());
							fin4[0].setLabel(barajaInicial[0].getLabel());
						}

						subirBarajaInicial();

					}

				} else if (mazoDestino == "barajaDescartes") {
					System.out.println("Descartando Carta desde el " + mazoAMover);
					descartarCarta();
				} else if (mazoDestino == "barajaInicial") {// Comprovacion de movimiento a si misma
					System.out.println("ERROR, NO SE PUEDE MOVER UNA BARAJA A SI MISMA");
				}
				break;
			case "barajaDescartes"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton2" || mazoDestino == "monton3"
						|| mazoDestino == "monton4" || mazoDestino == "monton5" || mazoDestino == "monton6"
						|| mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");
						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setIcon(barajaDescartes[0].getIcon());
							monton1[posCartaDestino - 1].setLabel(barajaDescartes[0].getLabel());
							monton1[posCartaDestino - 1].setVisible(true);

						} else if (mazoDestino == "monton2") {

							monton2[posCartaDestino - 1].setIcon(barajaDescartes[0].getIcon());
							monton2[posCartaDestino - 1].setLabel(barajaDescartes[0].getLabel());
							monton2[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton3") {

							monton3[posCartaDestino - 1].setIcon(barajaDescartes[0].getIcon());
							monton3[posCartaDestino - 1].setLabel(barajaDescartes[0].getLabel());
							monton3[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton4") {

							monton4[posCartaDestino - 1].setIcon(barajaDescartes[0].getIcon());
							monton4[posCartaDestino - 1].setLabel(barajaDescartes[0].getLabel());
							monton4[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton5") {

							monton5[posCartaDestino - 1].setIcon(barajaDescartes[0].getIcon());
							monton5[posCartaDestino - 1].setLabel(barajaDescartes[0].getLabel());
							monton5[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton6") {

							monton6[posCartaDestino - 1].setIcon(barajaDescartes[0].getIcon());
							monton6[posCartaDestino - 1].setLabel(barajaDescartes[0].getLabel());
							monton6[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton7") {

							monton7[posCartaDestino - 1].setIcon(barajaDescartes[0].getIcon());
							monton7[posCartaDestino - 1].setLabel(barajaDescartes[0].getLabel());
							monton7[posCartaDestino - 1].setVisible(true);
						}

						subirBarajaDescartes();

					} else {
						System.out.println("ERROR, MOVIMIENTO DESDE DESCARTES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					boolean comFin = comprobarMovFin();

					if (comFin) {
						if (mazoDestino == "fin1") {
							System.out.println("Mazo 1");
							for (int i = 13; i >= 0; i--) {

								if (i < 13) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(barajaDescartes[0].getIcon());
							fin1[0].setLabel(barajaDescartes[0].getLabel());

						} else if (mazoDestino == "fin2") {

							System.out.println("Mazo 2");
							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(barajaDescartes[0].getIcon());
							fin2[0].setLabel(barajaDescartes[0].getLabel());

						} else if (mazoDestino == "fin3") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(barajaDescartes[0].getIcon());
							fin3[0].setLabel(barajaDescartes[0].getLabel());

						} else if (mazoDestino == "fin4") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(barajaDescartes[0].getIcon());
							fin4[0].setLabel(barajaDescartes[0].getLabel());
						}

						subirBarajaDescartes();

					}

				} else if (mazoDestino == "barajaInicial") {
					System.out.println("ERROR, NO SE PUEDE MOVER NADA A LA BARAJA INICIAL");
				} else if (mazoDestino == "barajaDescartes") {
					System.out.println("ERROR, NO SE PUEDE MOVER UNA CARTA A SI MISMA");
				}

				break;
			case "monton1"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton2" || mazoDestino == "monton3" || mazoDestino == "monton4"
						|| mazoDestino == "monton5" || mazoDestino == "monton6" || mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton2") {

							monton2[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());

						}

						operacionesMontones("monton1");

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {

					boolean comFin = comprobarMovFin();

					if (comFin) {

						if (mazoDestino == "fin1") {
							System.out.println("Mazo 1");
							for (int i = 13; i >= 0; i--) {

								if (i < 13) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(monton1[posCartaAMover].getIcon());
							fin1[0].setLabel(monton1[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin2") {

							System.out.println("Mazo 2");
							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(monton1[posCartaAMover].getIcon());
							fin2[0].setLabel(monton1[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin3") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(monton1[posCartaAMover].getIcon());
							fin3[0].setLabel(monton1[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin4") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(monton1[posCartaAMover].getIcon());
							fin4[0].setLabel(monton1[posCartaAMover].getLabel());
						}

						operacionesMontones("monton1");
					}

				} else if (mazoDestino == "barajaInicial") {
					System.out.println("ERROR, NO SE PUEDE MOVER NADA A LA BARAJA INICIAL");
				} else if (mazoDestino == "barajaDescartes") {
					System.out.println("ERROR, NO SE PUEDEN DESCARTAR CARTAS DESDE" + mazoAMover);
				} else if (mazoDestino == "monton1") {
					System.out.println("ERROR, NO SE PUEDE MOVER LA CARTA A SI MISMA");
				}

				break;
			case "monton2"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton3" || mazoDestino == "monton4"
						|| mazoDestino == "monton5" || mazoDestino == "monton6" || mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());

						}

						operacionesMontones("monton2");

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {

					boolean comFin = comprobarMovFin();
					if (comFin) {

						if (mazoDestino == "fin1") {
							System.out.println("Mazo 1");
							for (int i = 13; i >= 0; i--) {

								if (i < 13) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(monton2[posCartaAMover].getIcon());
							fin1[0].setLabel(monton2[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin2") {

							System.out.println("Mazo 2");
							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(monton2[posCartaAMover].getIcon());
							fin2[0].setLabel(monton2[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin3") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(monton2[posCartaAMover].getIcon());
							fin3[0].setLabel(monton2[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin4") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(monton2[posCartaAMover].getIcon());
							fin4[0].setLabel(monton2[posCartaAMover].getLabel());
						}

						operacionesMontones("monton2");
					}

				} else if (mazoDestino == "barajaInicial") {
					System.out.println("ERROR, NO SE PUEDE MOVER NADA A LA BARAJA INICIAL");
				} else if (mazoDestino == "barajaDescartes") {
					System.out.println("ERROR, NO SE PUEDEN DESCARTAR CARTAS DESDE" + mazoAMover);
				} else if (mazoDestino == "monton2") {
					System.out.println("ERROR, NO SE PUEDE MOVER LA CARTA A SI MISMA");
				}

				break;
			case "monton3"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton2" || mazoDestino == "monton4"
						|| mazoDestino == "monton5" || mazoDestino == "monton6" || mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());

						}

						operacionesMontones("monton3");

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					boolean comFin = comprobarMovFin();
					if (comFin) {
						System.out.println("HOla");

						if (mazoDestino == "fin1") {
							System.out.println("Mazo 1");
							for (int i = 13; i >= 0; i--) {

								if (i < 13) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(monton3[posCartaAMover].getIcon());
							fin1[0].setLabel(monton3[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin2") {

							System.out.println("Mazo 2");
							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(monton3[posCartaAMover].getIcon());
							fin2[0].setLabel(monton3[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin3") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(monton3[posCartaAMover].getIcon());
							fin3[0].setLabel(monton3[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin4") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(monton3[posCartaAMover].getIcon());
							fin4[0].setLabel(monton3[posCartaAMover].getLabel());
						}

						operacionesMontones("monton3");
					}

				} else if (mazoDestino == "barajaInicial") {
					System.out.println("ERROR, NO SE PUEDE MOVER NADA A LA BARAJA INICIAL");
				} else if (mazoDestino == "barajaDescartes") {
					System.out.println("ERROR, NO SE PUEDEN DESCARTAR CARTAS DESDE" + mazoAMover);
				} else if (mazoDestino == "monton3") {
					System.out.println("ERROR, NO SE PUEDE MOVER LA CARTA A SI MISMA");
				}

				break;
			case "monton4"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton2" || mazoDestino == "monton3"
						|| mazoDestino == "monton5" || mazoDestino == "monton6" || mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());

						}

						operacionesMontones("monton4");

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					boolean comFin = comprobarMovFin();

					if (comFin) {

						if (mazoDestino == "fin1") {
							System.out.println("Mazo 1");
							for (int i = 13; i >= 0; i--) {

								if (i < 13) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(monton4[posCartaAMover].getIcon());
							fin1[0].setLabel(monton4[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin2") {

							System.out.println("Mazo 2");
							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(monton4[posCartaAMover].getIcon());
							fin2[0].setLabel(monton4[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin3") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(monton4[posCartaAMover].getIcon());
							fin3[0].setLabel(monton4[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin4") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(monton4[posCartaAMover].getIcon());
							fin4[0].setLabel(monton4[posCartaAMover].getLabel());
						}

						operacionesMontones("monton4");

					}

				} else if (mazoDestino == "barajaInicial") {
					System.out.println("ERROR, NO SE PUEDE MOVER NADA A LA BARAJA INICIAL");
				} else if (mazoDestino == "barajaDescartes") {
					System.out.println("ERROR, NO SE PUEDEN DESCARTAR CARTAS DESDE" + mazoAMover);
				} else if (mazoDestino == "monton4") {
					System.out.println("ERROR, NO SE PUEDE MOVER LA CARTA A SI MISMA");
				}

				break;
			case "monton5"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton2" || mazoDestino == "monton3"
						|| mazoDestino == "monton4" || mazoDestino == "monton6" || mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());

						}

						operacionesMontones("monton5");

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					boolean comFin = comprobarMovFin();
					if (comFin) {
						System.out.println("HOla");

						if (mazoDestino == "fin1") {
							System.out.println("Mazo 1");
							for (int i = 13; i >= 0; i--) {

								if (i < 13) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(monton5[posCartaAMover].getIcon());
							fin1[0].setLabel(monton5[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin2") {

							System.out.println("Mazo 2");
							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(monton5[posCartaAMover].getIcon());
							fin2[0].setLabel(monton5[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin3") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(monton5[posCartaAMover].getIcon());
							fin3[0].setLabel(monton5[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin4") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(monton5[posCartaAMover].getIcon());
							fin4[0].setLabel(monton5[posCartaAMover].getLabel());
						}

						operacionesMontones("monton5");

					}

				} else if (mazoDestino == "barajaInicial") {
					System.out.println("ERROR, NO SE PUEDE MOVER NADA A LA BARAJA INICIAL");
				} else if (mazoDestino == "barajaDescartes") {
					System.out.println("ERROR, NO SE PUEDEN DESCARTAR CARTAS DESDE" + mazoAMover);
				} else if (mazoDestino == "monton5") {
					System.out.println("ERROR, NO SE PUEDE MOVER LA CARTA A SI MISMA");
				}

				break;
			case "monton6"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton2" || mazoDestino == "monton3"
						|| mazoDestino == "monton4" || mazoDestino == "monton5" || mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");
						
						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());

						}

						operacionesMontones("monton6");
						
						

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					boolean comFin = comprobarMovFin();

					if (comFin) {
						System.out.println("HOla");

						if (mazoDestino == "fin1") {
							System.out.println("Mazo 1");
							for (int i = 13; i >= 0; i--) {

								if (i < 13) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(monton6[posCartaAMover].getIcon());
							fin1[0].setLabel(monton6[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin2") {

							System.out.println("Mazo 2");
							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(monton6[posCartaAMover].getIcon());
							fin2[0].setLabel(monton6[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin3") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(monton6[posCartaAMover].getIcon());
							fin3[0].setLabel(monton6[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin4") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(monton6[posCartaAMover].getIcon());
							fin4[0].setLabel(monton6[posCartaAMover].getLabel());
						}

						operacionesMontones("monton6");

					}

				} else if (mazoDestino == "barajaInicial") {
					System.out.println("ERROR, NO SE PUEDE MOVER NADA A LA BARAJA INICIAL");
				} else if (mazoDestino == "barajaDescartes") {
					System.out.println("ERROR, NO SE PUEDEN DESCARTAR CARTAS DESDE" + mazoAMover);
				} else if (mazoDestino == "monton6") {
					System.out.println("ERROR, NO SE PUEDE MOVER LA CARTA A SI MISMA");
				}

				break;
			case "monton7"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton2" || mazoDestino == "monton3"
						|| mazoDestino == "monton4" || mazoDestino == "monton5") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");
						
						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());

						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());

						}

						operacionesMontones("monton7");

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {

					boolean comFin = comprobarMovFin();

					if (comFin) {
						System.out.println("HOla");

						if (mazoDestino == "fin1") {
							System.out.println("Mazo 1");
							for (int i = 13; i >= 0; i--) {

								if (i < 13) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(monton7[posCartaAMover].getIcon());
							fin1[0].setLabel(monton7[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin2") {

							System.out.println("Mazo 2");
							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(monton7[posCartaAMover].getIcon());
							fin2[0].setLabel(monton7[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin3") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(monton7[posCartaAMover].getIcon());
							fin3[0].setLabel(monton7[posCartaAMover].getLabel());

						} else if (mazoDestino == "fin4") {

							for (int i = 13; i >= 0; i--) {
								if (i < 13) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(monton7[posCartaAMover].getIcon());
							fin4[0].setLabel(monton7[posCartaAMover].getLabel());
						}

						operacionesMontones("monton7");

					}

				} else if (mazoDestino == "barajaInicial") {
					System.out.println("ERROR, NO SE PUEDE MOVER NADA A LA BARAJA INICIAL");
				} else if (mazoDestino == "barajaDescartes") {
					System.out.println("ERROR, NO SE PUEDEN DESCARTAR CARTAS DESDE" + mazoAMover);
				} else if (mazoDestino == "monton7") {
					System.out.println("ERROR, NO SE PUEDE MOVER LA CARTA A SI MISMA");
				}

				break;

			case "fin1"://////////////////////////////////////////////////////////////////////////////////
			case "fin2":
			case "fin3":
			case "fin4":
				System.out.println("ERROR, NO SE PEUDE SACAR NADA DE ESTOS MAZOS");
				break;

			default:

				System.out.println("ERROR INESPERADO A OCURRIDO");
				break;
			}
			printArrays();
			primeraPulsacion = false;
			segundaPulsacion = false;
		}

	}

	/**
	 * Comprobamos si el movimiento a fin seria correcto
	 */
	private boolean comprobarMovFin() {
		System.out.println(mazoAMover + "->" + mazoDestino);

		if (cartaDestino.getPalo() == 'F' && cartaDestino.getNum() == 'F') {
			System.out.println("No hay inguna carta en el mazon fin");

			if (cartaAMover.getNum() == 'A') {
				System.out.println("Movimiento inicial a fin correcto");
				return true;

			} else {
				System.out.println("ERROR, SI FIN ESTA VACIO SOLO SE PUEDE MOVER EL AS");
				return false;
			}
		} else {
			System.out.println("Hay cartas en el mazo fin");

			if ((cartaAMover.getPalo() == cartaDestino.getPalo())
					&& (cartaAMover.establecerValor() == (cartaDestino.establecerValor() + 1))) {

				System.out.println("Movimiento a fin correcto");
				return true;

			} else {
				System.out.println("ERROR, MOVIMIENTO A FIN INCORRECTO");
				return false;
			}
		}

	}

	/**
	 * Metodo para descartar cartas
	 */
	private void descartarCarta() {

		for (int i = 22; i >= 0; i--) {
			if (i < 22) {

				barajaDescartes[i + 1].setIcon(barajaDescartes[i].getIcon());
				barajaDescartes[i + 1].setLabel(barajaDescartes[i].getLabel());

			}
		}

		barajaDescartes[0].setIcon(barajaInicial[0].getIcon());
		barajaDescartes[0].setLabel(barajaInicial[0].getLabel());

		subirBarajaInicial();

	}

	private void subirBarajaInicial() {

		for (int i = 0; i < 22; i++) {

			if (barajaInicial[i] != null) {
				barajaInicial[i].setIcon(barajaInicial[i + 1].getIcon());
				barajaInicial[i].setLabel(barajaInicial[i + 1].getLabel());
			}
		}

		barajaInicial[borrarInicio--] = new JButton("NuLo");
	}

	private void subirBarajaDescartes() {

		for (int i = 0; i < 22; i++) {

			if (barajaDescartes[i] != null) {
				barajaDescartes[i].setIcon(barajaDescartes[i + 1].getIcon());
				barajaDescartes[i].setLabel(barajaDescartes[i + 1].getLabel());
			}
		}
	}

	private void operacionesMontones(String monton) {

		if (monton == "monton1") {
			// int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton1[posCartaAMover +
			// 1].getLabel()));

			if (posCartaAMover == 19) {
				monton1[posCartaAMover].setIcon(null);
				monton1[posCartaAMover].setLabel("Vacio");
			} else {
				monton1[posCartaAMover].setIcon(null);
				monton1[posCartaAMover].setLabel("Vacio");
				monton1[posCartaAMover].setVisible(false);
				monton1[posCartaAMover + 1].setEnabled(true);
				// monton1[posCartaAMover + 1].setIcon(icono[posSiguiente]);

			}

		} else if (monton == "monton2") {
			int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton2[posCartaAMover + 1].getLabel()));

			if (posCartaAMover == 19) {
				monton2[posCartaAMover].setIcon(null);
				monton2[posCartaAMover].setLabel("Vacio");
			} else {
				monton2[posCartaAMover].setIcon(null);
				monton2[posCartaAMover].setLabel("Vacio");
				monton2[posCartaAMover].setVisible(false);
				monton2[posCartaAMover + 1].setEnabled(true);
				monton2[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton3") {
			int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton3[posCartaAMover + 1].getLabel()));

			if (posCartaAMover == 19) {
				monton3[posCartaAMover].setIcon(null);
				monton3[posCartaAMover].setLabel("Vacio");
			} else {
				monton3[posCartaAMover].setIcon(null);
				monton3[posCartaAMover].setLabel("Vacio");
				monton3[posCartaAMover].setVisible(false);
				monton3[posCartaAMover + 1].setEnabled(true);
				monton3[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton4") {
			int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton4[posCartaAMover + 1].getLabel()));

			if (posCartaAMover == 19) {
				monton4[posCartaAMover].setIcon(null);
				monton4[posCartaAMover].setLabel("Vacio");
			} else {
				monton4[posCartaAMover].setIcon(null);
				monton4[posCartaAMover].setLabel("Vacio");
				monton4[posCartaAMover].setVisible(false);
				monton4[posCartaAMover + 1].setEnabled(true);
				monton4[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton5") {
			int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton5[posCartaAMover + 1].getLabel()));

			if (posCartaAMover == 19) {
				monton5[posCartaAMover].setIcon(null);
				monton5[posCartaAMover].setLabel("Vacio");
			} else {
				monton5[posCartaAMover].setIcon(null);
				monton5[posCartaAMover].setLabel("Vacio");
				monton5[posCartaAMover].setVisible(false);
				monton5[posCartaAMover + 1].setEnabled(true);
				monton5[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton6") {
			int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton6[posCartaAMover + 1].getLabel()));

			if (posCartaAMover == 19) {
				monton6[posCartaAMover].setIcon(null);
				monton6[posCartaAMover].setLabel("Vacio");
			} else {
				monton6[posCartaAMover].setIcon(null);
				monton6[posCartaAMover].setLabel("Vacio");
				monton6[posCartaAMover].setVisible(false);
				monton6[posCartaAMover + 1].setEnabled(true);
				monton6[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton7") {
			int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton7[posCartaAMover + 1].getLabel()));

			if (posCartaAMover == 19) {
				monton7[posCartaAMover].setIcon(null);
				monton7[posCartaAMover].setLabel("Vacio");
			} else {
				monton7[posCartaAMover].setIcon(null);
				monton7[posCartaAMover].setLabel("Vacio");
				monton7[posCartaAMover].setVisible(false);
				monton7[posCartaAMover + 1].setEnabled(true);
				monton7[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		}
	}

	private CartaFrancesa buscaCarta(String cartaStr) {

		String[] parts = cartaStr.split("");

		if (cartaStr.equals("F1") || cartaStr.equals("F2") || cartaStr.equals("F3") || cartaStr.equals("F4")) {
			return new CartaFrancesa('F', 'F', null, null);
		} else {

			char chtNum = parts[0].charAt(0);
			char chtPalo = parts[1].charAt(0);
			CartaFrancesa cartaRt = null;

			for (int i = 0; i < ABaraja.length; i++) {

				if (ABaraja[i].getNum() == chtNum && ABaraja[i].getPalo() == chtPalo) {

					cartaRt = new CartaFrancesa(ABaraja[i]);// Copiamos la carta
				}

			}
			return cartaRt;
		}

	}

	private String buscarMazo(String carta) {

		String ret = "Error";
		System.out.println(carta);
		for (int i = 0; i < 20; i++) {

			if (monton1[i] != null && monton1[i].getLabel().equals(carta)) {
				ret = "monton1";
			} else if (monton2[i] != null && monton2[i].getLabel().equals(carta)) {
				ret = "monton2";
			} else if (monton3[i] != null && monton3[i].getLabel().equals(carta)) {
				ret = "monton3";
			} else if (monton4[i] != null && monton4[i].getLabel().equals(carta)) {
				ret = "monton4";
			} else if (monton5[i] != null && monton5[i].getLabel().equals(carta)) {
				ret = "monton5";
			} else if (monton6[i] != null && monton6[i].getLabel().equals(carta)) {
				ret = "monton6";
			} else if (monton7[i] != null && monton7[i].getLabel().equals(carta)) {
				ret = "monton7";
			}

		}

		for (int i = 0; i < 23; i++) {

			if (barajaInicial[i] != null && barajaInicial[i].getLabel().equals(carta)) {
				ret = "barajaInicial";
			}
		}

		for (int i = 0; i < 24; i++) {

			if (barajaDescartes[i] != null && barajaDescartes[i].getLabel().equals(carta)) {
				ret = "barajaDescartes";
			}
		}

		for (int i = 0; i < 13; i++) {
			if (fin1[i] != null && fin1[i].getLabel().equals(carta)) {
				ret = "fin1";
			} else if (fin2[i] != null && fin2[i].getLabel().equals(carta)) {
				ret = "fin2";
			} else if (fin3[i] != null && fin3[i].getLabel().equals(carta)) {
				ret = "fin3";
			} else if (fin4[i] != null && fin4[i].getLabel().equals(carta)) {
				ret = "fin4";
			}
		}

		return ret;
	}

	private int buscarPosicion(String carta) {

		int ret = -1;

		for (int i = 0; i < 20; i++) {

			if (monton1[i] != null && monton1[i].getLabel().equals(carta)) {
				ret = i;
			} else if (monton2[i] != null && monton2[i].getLabel().equals(carta)) {
				ret = i;
			} else if (monton3[i] != null && monton3[i].getLabel().equals(carta)) {
				ret = i;
			} else if (monton4[i] != null && monton4[i].getLabel().equals(carta)) {
				ret = i;
			} else if (monton5[i] != null && monton5[i].getLabel().equals(carta)) {
				ret = i;
			} else if (monton6[i] != null && monton6[i].getLabel().equals(carta)) {
				ret = i;
			} else if (monton7[i] != null && monton7[i].getLabel().equals(carta)) {
				ret = i;
			}

		}

		for (int i = 0; i < 23; i++) {

			if (barajaInicial[i] != null && barajaInicial[i].getLabel().equals(carta)) {
				ret = i;
			}
		}

		for (int i = 0; i < 24; i++) {

			if (barajaDescartes[i] != null && barajaDescartes[i].getLabel().equals(carta)) {
				ret = i;
			}
		}

		for (int i = 0; i < 13; i++) {
			if (fin1[i] != null && fin1[i].getLabel().equals(carta)) {
				ret = i;
			} else if (fin2[i] != null && fin2[i].getLabel().equals(carta)) {
				ret = i;
			} else if (fin3[i] != null && fin3[i].getLabel().equals(carta)) {
				ret = i;
			} else if (fin4[i] != null && fin4[i].getLabel().equals(carta)) {
				ret = i;
			}
		}

		return ret;
	}

	private int buscarPosBarajaInicial(CartaFrancesa carta) {
		int ret = -1;
		for (int i = 0; i < ABaraja.length; i++) {

			if (carta.getPalo() == ABaraja[i].getPalo() && carta.getNum() == ABaraja[i].getNum()) {
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
			pw.println("Solitario Clasico");

			// TODO ESCRIBIR AQUI TODO LO QUE SE VAYA A GUARDAR EN EL ARCHIVO

			fichero.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void printArrays() {
		/* DEBUSG */
		System.out.println("Baraja inicial");
		for (int i = 0; i < barajaInicial.length; i++) {
			if (barajaInicial[i] != null) {
				System.out.print(barajaInicial[i].getLabel() + " ");

			}
		}

		System.out.println();
		System.out.println("Baraja descartes");
		for (int i = 0; i < barajaDescartes.length; i++) {
			// if (barajaDescartes[i] != null) {
			System.out.print(barajaDescartes[i].getLabel() + " ");
			// }
		}

		System.out.println();
		System.out.println("Monton 1");
		for (int i = 0; i < monton1.length; i++) {
			if (monton1[i] != null) {
				System.out.print(monton1[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 2");
		for (int i = 0; i < monton2.length; i++) {
			if (monton2[i] != null) {

				System.out.print(monton2[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 3");
		for (int i = 0; i < monton3.length; i++) {
			if (monton3[i] != null) {
				System.out.print(monton3[i].getLabel() + " ");
			}
		}
		System.out.println();
		System.out.println("Monton 4");

		for (int i = 0; i < monton4.length; i++) {
			if (monton4[i] != null) {
				System.out.print(monton4[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 5");
		for (int i = 0; i < monton5.length; i++) {

			if (monton5[i] != null) {
				System.out.print(monton5[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 6");
		for (int i = 0; i < monton6.length; i++) {
			if (monton6[i] != null) {
				System.out.print(monton6[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Monton 7");

		for (int i = 0; i < monton7.length; i++) {
			if (monton7[i] != null) {
				System.out.print(monton7[i].getLabel() + " ");
			}
		}

		System.out.println();
		System.out.println("Fin 1");

		for (int i = 0; i < fin1.length; i++) {
			System.out.print(fin1[i].getLabel() + " ");
		}

		System.out.println();
		System.out.println("Fin 2 ");

		for (int i = 0; i < fin2.length; i++) {
			System.out.print(fin2[i].getLabel() + " ");

		}
		System.out.println();
		System.out.println("Fin 3");

		for (int i = 0; i < fin3.length; i++) {
			System.out.print(fin3[i].getLabel() + " ");

		}

		System.out.println();
		System.out.println("Fin 4");

		for (int i = 0; i < fin4.length; i++) {
			System.out.print(fin4[i].getLabel() + " ");

		}
		System.out.println();
		/* FIN DEBUG */
	}

}
