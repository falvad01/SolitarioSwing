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

import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("deprecation")
public class PanelClasico extends JPanel implements ActionListener {

	private JPanel clasico;
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

	public PanelClasico(JPanel panel) {

		this.clasico = panel;
		this.baraja = new Baraja(EJuego.Clasico);
		barajaInicial = new JButton[23];
		barajaDescartes = new JButton[24];

		fin1 = new JButton[13];
		fin2 = new JButton[13];
		fin3 = new JButton[13];
		fin4 = new JButton[13];

		monton1 = new JButton[52];
		monton2 = new JButton[52];
		monton3 = new JButton[52];
		monton4 = new JButton[52];
		monton5 = new JButton[52];
		monton6 = new JButton[52];
		monton7 = new JButton[52];

		icono = new ImageIcon[52];

	}

	public String getRutaJuego() {
		return this.rutaJuego;
	}

	public void iniciarJuegoClasico() {

		baraja.crearBarajaF();
		baraja.barajarF();
		ABaraja = baraja.getBarajaFrancesa();
//		for (int i = 0; i < ABaraja.length; i++) {
//			System.out.print(ABaraja[i] + " ");
//		}

		// System.out.println("/n");

		pintarCartas();

	}

	private void pintarCartas() {

		StringBuilder nombre = new StringBuilder();

		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		int i5 = 0;
		int i6 = 0;
		int i7 = 0;

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

				monton1[i1].setBounds(20, 135, 90, 120);

				if (i1 == 0) {
					monton1[i1].setIcon(icono[i]);
				} else {
					// monton1[i1].setIcon(reverso);
					monton1[i1].setIcon(icono[i]);
					monton1[i1].setEnabled(false);
				}
				clasico.add(monton1[i1]);
				monton1[i1].addActionListener(this);
				i1++;

			} else if (i > 24 && i < 27) {

				monton2[i2] = new JButton(nombre.toString());

				monton2[i2].setBounds(125, 155 - (i2 * 20), 90, 120);

				if (i2 == 0) {
					monton2[i2].setIcon(icono[i]);
				} else {
					// monton2[i2].setIcon(reverso);
					monton2[i2].setIcon(icono[i]);
					monton2[i2].setEnabled(false);
				}
				clasico.add(monton2[i2]);
				monton2[i2].addActionListener(this);
				i2++;

			} else if (i >= 27 && i <= 29) {

				monton3[i3] = new JButton(nombre.toString());

				monton3[i3].setBounds(230, 175 - (i3 * 20), 90, 120);

				if (i3 == 0) {
					monton3[i3].setIcon(icono[i]);
				} else {
					// monton3[i3].setIcon(reverso);
					monton3[i3].setIcon(icono[i]);
					monton3[i3].setEnabled(false);
				}
				clasico.add(monton3[i3]);
				monton3[i3].addActionListener(this);
				i3++;

			} else if (i >= 30 && i <= 33) {

				monton4[i4] = new JButton(nombre.toString());

				monton4[i4].setBounds(335, 195 - (i4 * 20), 90, 120);
				if (i4 == 0) {
					monton4[i4].setIcon(icono[i]);
				} else {
					// monton4[i4].setIcon(reverso);
					monton4[i4].setIcon(icono[i]);
					monton4[i4].setEnabled(false);
				}
				clasico.add(monton4[i4]);
				monton4[i4].addActionListener(this);
				i4++;

			} else if (i >= 34 && i <= 38) {

				monton5[i5] = new JButton(nombre.toString());

				monton5[i5].setBounds(440, 215 - (i5 * 20), 90, 120);
				if (i5 == 0) {
					monton5[i5].setIcon(icono[i]);
				} else {
					monton5[i5].setIcon(reverso);
					monton5[i5].setEnabled(false);
				}
				clasico.add(monton5[i5]);
				monton5[i5].addActionListener(this);
				i5++;

			} else if (i >= 39 && i <= 44) {

				monton6[i6] = new JButton(nombre.toString());

				monton6[i6].setBounds(545, 235 - (i6 * 20), 90, 120);
				if (i6 == 0) {
					monton6[i6].setIcon(icono[i]);
				} else {
					monton6[i6].setIcon(reverso);
					monton6[i6].setEnabled(false);
				}
				clasico.add(monton6[i6]);
				monton6[i6].addActionListener(this);
				i6++;

			} else if (i >= 45 && i <= 51) {

				monton7[i7] = new JButton(nombre.toString());

				monton7[i7].setBounds(650, 255 - (i7 * 20), 90, 120);
				if (i7 == 0) {
					monton7[i7].setIcon(icono[i]);
				} else {
					monton7[i7].setIcon(reverso);
					monton7[i7].setEnabled(false);
				}
				clasico.add(monton7[i7]);
				monton7[i7].addActionListener(this);
				i7++;

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

			// TODO BUSCAR LA FORMA DE HACER LO QUE PREGUNTP XIAN Y SOLUCIONO CON $
			// TODO SOMBREAR EN LOS MANTONES LAS CARTAS QUE NO ESTA DESABILITADAS CON
			// .isEnabled()
			// for(int i = 0; i < mazoAMover.getClass().getName().length();i++) {

			// }

			System.out.println(mazoAMover + " " + posCartaAMover);
			System.out.println("Origen: " + strAMover);
			System.out.println();

		}

		if (primeraPulsacion && segundaPulsacion) {

//			System.out.println(cartaAMover.establecerValor());
//			System.out.println(cartaDestino.establecerValor());

			switch (mazoAMover) {
			case "barajaInicial"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton2" || mazoDestino == "monton3"
						|| mazoDestino == "monton4" || mazoDestino == "monton5" || mazoDestino == "monton6"
						|| mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaAMover != null && ((cartaAMover.establecerValor() - 1) == cartaDestino.establecerValor())
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) {// Comprobamos que la carta
						// que movemos sea de un
						// valor inmediatamente
						// inferior y que el color
						// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

					} else {
						System.out.println("ERROR, MOVIMIENTO DESDE INICIO INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {

					String comFin = comprobarMovFin();

					

					if (comFin.equals("vacio") || comFin.equals("conCartas")) {
						System.out.println("HOla");
						//TODO en proceso de terminar fin para cunado ya hay cartas
						if (mazoDestino == "fin1") {

							for (int i = 12; i >= 0; i--) {
								if (i < 12) {

									fin1[i + 1].setIcon(fin1[i].getIcon());
									fin1[i + 1].setLabel(fin1[i].getLabel());

								}
							}

							fin1[0].setIcon(barajaInicial[0].getIcon());
							fin1[0].setLabel(barajaInicial[0].getLabel());

						} else if (mazoDestino == "fin2") {
							System.out.println("Mazo 2");
							for (int i = 12; i >= 0; i--) {
								if (i < 12) {

									fin2[i + 1].setIcon(fin2[i].getIcon());
									fin2[i + 1].setLabel(fin2[i].getLabel());

								}
							}

							fin2[0].setIcon(barajaInicial[0].getIcon());
							fin2[0].setLabel(barajaInicial[0].getLabel());

						} else if (mazoDestino == "fin3") {
							for (int i = 12; i >= 0; i--) {
								if (i < 12) {

									fin3[i + 1].setIcon(fin3[i].getIcon());
									fin3[i + 1].setLabel(fin3[i].getLabel());

								}
							}

							fin3[0].setIcon(barajaInicial[0].getIcon());
							fin3[0].setLabel(barajaInicial[0].getLabel());
						} else if (mazoDestino == "fin4") {
							for (int i = 12; i >= 0; i--) {
								if (i < 12) {

									fin4[i + 1].setIcon(fin4[i].getIcon());
									fin4[i + 1].setLabel(fin4[i].getLabel());

								}
							}

							fin4[0].setIcon(barajaInicial[0].getIcon());
							fin4[0].setLabel(barajaInicial[0].getLabel());
						}

						subirBarajaInicial();

					} else if (comFin.equals("conCartas")) {
//						
//						if(cartaAMover.establecerValor() == cartaDestino.establecerValor() + 1) {
//							
//							if (mazoDestino == "fin1") {
//
//								for (int i = 12; i >= 0; i--) {
//									if (i < 12) {
//
//										fin1[i + 1].setIcon(fin1[i].getIcon());
//										fin1[i + 1].setLabel(fin1[i].getLabel());
//
//									}
//								}
//
//								fin1[0].setIcon(barajaInicial[0].getIcon());
//								fin1[0].setLabel(barajaInicial[0].getLabel());
//
//							} else if (mazoDestino == "fin2") {
//								System.out.println("Mazo 2");
//								for (int i = 12; i >= 0; i--) {
//									if (i < 12) {
//
//										fin2[i + 1].setIcon(fin2[i].getIcon());
//										fin2[i + 1].setLabel(fin2[i].getLabel());
//
//									}
//								}
//
//								fin2[0].setIcon(barajaInicial[0].getIcon());
//								fin2[0].setLabel(barajaInicial[0].getLabel());
//
//							} else if (mazoDestino == "fin3") {
//								for (int i = 12; i >= 0; i--) {
//									if (i < 12) {
//
//										fin3[i + 1].setIcon(fin3[i].getIcon());
//										fin3[i + 1].setLabel(fin3[i].getLabel());
//
//									}
//								}
//
//								fin3[0].setIcon(barajaInicial[0].getIcon());
//								fin3[0].setLabel(barajaInicial[0].getLabel());
//							} else if (mazoDestino == "fin4") {
//								for (int i = 12; i >= 0; i--) {
//									if (i < 12) {
//
//										fin4[i + 1].setIcon(fin4[i].getIcon());
//										fin4[i + 1].setLabel(fin4[i].getLabel());
//
//									}
//								}
//
//								fin4[0].setIcon(barajaInicial[0].getIcon());
//								fin4[0].setLabel(barajaInicial[0].getLabel());
//							}
//							
//						}
						
						
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

					} else {
						System.out.println("ERROR, MOVIMIENTO DESDE DESCARTES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					comprobarMovFin();

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

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					comprobarMovFin();

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

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					comprobarMovFin();

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

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					comprobarMovFin();

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

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					comprobarMovFin();

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

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					comprobarMovFin();

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

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {
					comprobarMovFin();

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

					} else {
						System.out.println("ERROR, MOVIMIENTO ENTRE MONTONES INCORRECTO");
					}

				} else if (mazoDestino == "fin1" || mazoDestino == "fin2" || mazoDestino == "fin3"
						|| mazoDestino == "fin4") {

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
	private String comprobarMovFin() {
		System.out.println(mazoAMover + "->" + mazoDestino);

		if (cartaDestino.getPalo() == 'F' && cartaDestino.getNum() == 'F') {
			System.out.println("No hay inguna carta en el mazon fin");

			if (cartaAMover.getNum() == 'A') {
				System.out.println("Movimiento inicial a fin correcto");
				return "vacio";

			} else {
				System.out.println("ERROR, SI FIN ESTA VACIO SOLO SE PUEDE MOVER EL AS");
			}
		} else {
			System.out.println("Hay cartas en el mazo fin");

			if ((cartaAMover.getPalo() == cartaDestino.getPalo())
					&& (cartaAMover.establecerValor()  == (cartaDestino.establecerValor() + 1))) {

				System.out.println("Movimiento a fin correcto");
				return "ConCartas";

			} else {
				System.out.println("ERROR, MOVIMIENTO A FIN INCORRECTO");
			}
		}

		return null;
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

		for (int i = 0; i < 52; i++) {

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

		for (int i = 0; i < 52; i++) {

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
