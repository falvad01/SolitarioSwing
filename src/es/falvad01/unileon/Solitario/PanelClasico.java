package es.falvad01.unileon.Solitario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.awt.Color;
import java.awt.Container;
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

@SuppressWarnings({ "deprecation", "serial" })
public class PanelClasico extends JPanel implements ActionListener {

	private JLayeredPane clasico;
	private boolean primeraPartida = true;
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
	private boolean flagIntentos = false;

	public PanelClasico(JLayeredPane juegoClasico, Container container) {

		this.clasico = juegoClasico;

		barajaInicial = new JButton[23];
		barajaDescartes = new JButton[24];
		fin1 = new JButton[14];// Tiene uno mas de tamaño de lo que deberia debido a la carta cebo que esta en
								// // primer lugar
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
		juegoClasico.setBackground(Color.BLUE);
	}

	public String getRutaJuego() {
		return this.rutaJuego;
	}

	public void iniciarJuegoClasico() {
		Baraja baraja = new Baraja(EJuego.Clasico);
		baraja.crearBarajaF();
		baraja.barajarF();
		ABaraja = baraja.getBarajaFrancesa();
		for (int i = 0; i < ABaraja.length; i++) {
			System.out.print(ABaraja[i] + " ");
		}

		// System.out.println("/n");

		if (primeraPartida) {
			primeraPartida = false;
			pintarCartas();

		} else {
			System.out.println(primeraPartida);
			// repintarCartas();
		}

	}

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

			icono[i] = new ImageIcon(img, nombre.toString());
			reverso = new ImageIcon(imgReverso);

			if (i < 23) {// Baraja Principal
				barajaInicial[i] = new JButton(nombre.toString());
				barajaInicial[i].setIcon(icono[i]);
				barajaInicial[0].setBounds(20, 10, 90, 122);

			} else if (i == 23) { // Baraja Descartes

				barajaDescartes[0] = new JButton(nombre.toString());
				
				barajaDescartes[0].setBounds(125, 10, 90, 120);
				barajaDescartes[0].setIcon(icono[i]);
				clasico.add(barajaDescartes[0]);
				barajaDescartes[0].addActionListener(this);

				for (int j = 1; j < barajaDescartes.length; j++) {// Rellenamos el resto del array con nulos
					barajaDescartes[j] = new JButton("NuLo");
					barajaDescartes[j].setIcon(nul);
				}

			} else if (i == 24) { // MOntones de cartas

				monton1[i1] = new JButton(nombre.toString());
				monton1[i1].setIcon(icono[i]);
				monton1[i1].setBounds(20, 500 - (i1 * 19), 90, 120);
				monton1[i1].addActionListener(this);
				clasico.add(monton1[i1], i1);
				clasico.setPosition(monton1[i1], 1);

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
				clasico.setPosition(monton2[i2], 1);
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
				clasico.setPosition(monton3[i3], 1);

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
				clasico.setPosition(monton4[i4], 1);
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
				clasico.add(monton5[i5], 0);
				clasico.setPosition(monton5[i5], 1);

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
				clasico.setPosition(monton6[i6], 1);

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

		rellenarMontones();
		sumarEstadisticas();
		printArrays();
		clasico.add(barajaInicial[0]);

		barajaInicial[0].addActionListener(this);

	}

	private void rellenarMontones() {
		ImageIcon nul = null;
		for (int q = 18; q >= 0; q--) {// Relleno monton 1

			monton1[q] = new JButton("vacio");
			monton1[q].setIcon(nul);

			monton1[q].setBounds(20, 500 - (q * 19), 90, 120);
			monton1[q].addActionListener(this);
			monton1[q].setVisible(false);
			clasico.add(monton1[q], q);

		}

		for (int k = 17; k >= 0; k--) {// Relleno monton 2

			monton2[k] = new JButton("vacio");
			monton2[k].setIcon(nul);

			monton2[k].setBounds(125, 500 - (k * 19), 90, 120);
			monton2[k].addActionListener(this);
			monton2[k].setVisible(false);
			clasico.add(monton2[k], k);

		}

		for (int p = 16; p >= 0; p--) {// Relleno monton 3

			monton3[p] = new JButton("vacio");
			monton3[p].setIcon(nul);

			monton3[p].setBounds(230, 500 - (p * 19), 90, 120);
			monton3[p].addActionListener(this);
			monton3[p].setVisible(false);
			clasico.add(monton3[p], p);

		}

		for (int n = 15; n >= 0; n--) {// Relleno monton 4

			monton4[n] = new JButton("vacio");
			monton4[n].setIcon(nul);

			monton4[n].setBounds(335, 500 - (n * 19), 90, 120);
			monton4[n].addActionListener(this);
			monton4[n].setVisible(false);
			clasico.add(monton4[n], n);

		}

		for (int o = 14; o >= 0; o--) {// Relleno monton 5

			monton5[o] = new JButton("vacio");
			monton5[o].setIcon(nul);

			monton5[o].setBounds(440, 500 - (o * 19), 90, 120);
			monton5[o].addActionListener(this);
			monton5[o].setVisible(false);

			clasico.add(monton5[o], o);

		}

		for (int m = 13; m >= 0; m--) {// Relleno monton 6

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

			if (mazoAMover == "barajaInicial") {
				barajaInicial[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "barajaDescartes") {
				barajaDescartes[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "monton1") {
				monton1[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "monton2") {
				monton2[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "monton3") {
				monton3[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "monton4") {
				monton4[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "monton5") {
				monton5[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "monton6") {
				monton6[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "monton7") {
				monton7[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "fin1") {
				fin1[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "fin2") {
				fin2[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "fin3") {
				fin3[posCartaAMover].setBorderPainted(false);
			} else if (mazoAMover == "fin4") {
				fin4[posCartaAMover].setBorderPainted(false);
			}

			System.out.println("Destino:" + strDestino);
			System.out.println();
		}

		if (!primeraPulsacion) {
			primeraPulsacion = true;
			strAMover = ((JButton) e.getSource()).getLabel();
			mazoAMover = buscarMazo(strAMover);
			posCartaAMover = buscarPosicion(strAMover);
			cartaAMover = buscaCarta(strAMover);

			if (mazoAMover == "barajaInicial") {
				barajaInicial[posCartaAMover].setBorder(borde);
				barajaInicial[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "barajaDescartes") {
				barajaDescartes[posCartaAMover].setBorder(borde);
				barajaDescartes[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "monton1") {
				monton1[posCartaAMover].setBorder(borde);
				monton1[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "monton2") {
				monton2[posCartaAMover].setBorder(borde);
				monton2[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "monton3") {
				monton3[posCartaAMover].setBorder(borde);
				monton3[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "monton4") {
				monton4[posCartaAMover].setBorder(borde);
				monton4[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "monton5") {
				monton5[posCartaAMover].setBorder(borde);
				monton5[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "monton6") {
				monton6[posCartaAMover].setBorder(borde);
				monton6[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "monton7") {
				monton7[posCartaAMover].setBorder(borde);
				monton7[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "fin1") {
				fin1[posCartaAMover].setBorder(borde);
				fin1[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "fin2") {
				fin2[posCartaAMover].setBorder(borde);
				fin2[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "fin3") {
				fin3[posCartaAMover].setBorder(borde);
				fin3[posCartaAMover].setBorderPainted(true);
			} else if (mazoAMover == "fin4") {
				fin4[posCartaAMover].setBorder(borde);
				fin4[posCartaAMover].setBorderPainted(true);
			}
			System.out.println(mazoAMover + " " + posCartaAMover);
			System.out.println("Origen: " + strAMover);

			System.out.println();

		}

		if (primeraPulsacion && segundaPulsacion) {
			sumarEstadisticas();
			switch (mazoAMover) {
			case "barajaInicial"://////////////////////////////////////////////////////////////////////////////////

				if (mazoDestino == "monton1" || mazoDestino == "monton2" || mazoDestino == "monton3"
						|| mazoDestino == "monton4" || mazoDestino == "monton5" || mazoDestino == "monton6"
						|| mazoDestino == "monton7") {

					System.out.println(mazoAMover + "->" + mazoDestino);

					if (cartaDestino.toString().equals("VA")) {

						System.out.println("MOVIMIENTO A VACIO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino].setIcon(barajaInicial[0].getIcon());
							monton1[posCartaDestino].setLabel(barajaInicial[0].getLabel());
							monton1[posCartaDestino].setVisible(true);

						} else if (mazoDestino == "monton2") {

							monton2[posCartaDestino].setIcon(barajaInicial[0].getIcon());
							monton2[posCartaDestino].setLabel(barajaInicial[0].getLabel());
							monton2[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton3") {

							monton3[posCartaDestino].setIcon(barajaInicial[0].getIcon());
							monton3[posCartaDestino].setLabel(barajaInicial[0].getLabel());
							monton3[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton4") {

							monton4[posCartaDestino].setIcon(barajaInicial[0].getIcon());
							monton4[posCartaDestino].setLabel(barajaInicial[0].getLabel());
							monton4[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton5") {

							monton5[posCartaDestino].setIcon(barajaInicial[0].getIcon());
							monton5[posCartaDestino].setLabel(barajaInicial[0].getLabel());
							monton5[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton6") {

							monton6[posCartaDestino].setIcon(barajaInicial[0].getIcon());
							monton6[posCartaDestino].setLabel(barajaInicial[0].getLabel());
							monton6[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton7") {

							monton7[posCartaDestino].setIcon(barajaInicial[0].getIcon());
							monton7[posCartaDestino].setLabel(barajaInicial[0].getLabel());
							monton7[posCartaDestino].setVisible(true);
						}

						subirBarajaInicial();
					} else if (cartaAMover != null
							&& ((cartaAMover.establecerValor() + 1) == cartaDestino.establecerValor())
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

					if (cartaDestino.toString().equals("VA")) {

						System.out.println("MOVIMIENTO A VACIO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino].setIcon(barajaDescartes[0].getIcon());
							monton1[posCartaDestino].setLabel(barajaDescartes[0].getLabel());
							monton1[posCartaDestino].setVisible(true);

						} else if (mazoDestino == "monton2") {

							monton2[posCartaDestino].setIcon(barajaDescartes[0].getIcon());
							monton2[posCartaDestino].setLabel(barajaDescartes[0].getLabel());
							monton2[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton3") {

							monton3[posCartaDestino].setIcon(barajaDescartes[0].getIcon());
							monton3[posCartaDestino].setLabel(barajaDescartes[0].getLabel());
							monton3[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton4") {

							monton4[posCartaDestino].setIcon(barajaDescartes[0].getIcon());
							monton4[posCartaDestino].setLabel(barajaDescartes[0].getLabel());
							monton4[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton5") {

							monton5[posCartaDestino].setIcon(barajaDescartes[0].getIcon());
							monton5[posCartaDestino].setLabel(barajaDescartes[0].getLabel());
							monton5[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton6") {

							monton6[posCartaDestino].setIcon(barajaDescartes[0].getIcon());
							monton6[posCartaDestino].setLabel(barajaDescartes[0].getLabel());
							monton6[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton7") {

							monton7[posCartaDestino].setIcon(barajaDescartes[0].getIcon());
							monton7[posCartaDestino].setLabel(barajaDescartes[0].getLabel());
							monton7[posCartaDestino].setVisible(true);
						}

						subirBarajaDescartes();
					} else if (cartaAMover != null
							&& (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
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

					if (cartaDestino.toString().equals("VA")) {

						System.out.println("MOVIMIENTO A VACIO CORRECTO");

						if (mazoDestino == "monton2") {

							monton2[posCartaDestino].setLabel(monton1[posCartaAMover].getLabel());
							monton2[posCartaDestino].setIcon(monton1[posCartaAMover].getIcon());
							monton2[posCartaDestino].setVisible(true);

						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino].setLabel(monton1[posCartaAMover].getLabel());
							monton3[posCartaDestino].setIcon(monton1[posCartaAMover].getIcon());
							monton3[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino].setLabel(monton1[posCartaAMover].getLabel());
							monton4[posCartaDestino].setIcon(monton1[posCartaAMover].getIcon());
							monton4[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino].setLabel(monton1[posCartaAMover].getLabel());
							monton5[posCartaDestino].setIcon(monton1[posCartaAMover].getIcon());
							monton5[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino].setLabel(monton1[posCartaAMover].getLabel());
							monton6[posCartaDestino].setIcon(monton1[posCartaAMover].getIcon());
							monton6[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino].setLabel(monton1[posCartaAMover].getLabel());
							monton7[posCartaDestino].setIcon(monton1[posCartaAMover].getIcon());
							monton7[posCartaDestino].setVisible(true);
						}

						operacionesMontones("monton1");

					} else if (cartaAMover != null
							&& (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton2") {

							monton2[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());
							monton2[posCartaDestino - 1].setVisible(true);

						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());
							monton3[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());
							monton4[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());
							monton5[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());
							monton6[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton1[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton1[posCartaAMover].getIcon());
							monton7[posCartaDestino - 1].setVisible(true);
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

					if (cartaDestino.toString().equals("VA")) {

						System.out.println("MOVIMIENTO A VACIO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino].setLabel(monton2[posCartaAMover].getLabel());
							monton1[posCartaDestino].setIcon(monton2[posCartaAMover].getIcon());
							monton1[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino].setLabel(monton2[posCartaAMover].getLabel());
							monton3[posCartaDestino].setIcon(monton2[posCartaAMover].getIcon());
							monton3[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino].setLabel(monton2[posCartaAMover].getLabel());
							monton4[posCartaDestino].setIcon(monton2[posCartaAMover].getIcon());
							monton4[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino].setLabel(monton2[posCartaAMover].getLabel());
							monton5[posCartaDestino].setIcon(monton2[posCartaAMover].getIcon());
							monton5[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino].setLabel(monton2[posCartaAMover].getLabel());
							monton6[posCartaDestino].setIcon(monton2[posCartaAMover].getIcon());
							monton6[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino].setLabel(monton2[posCartaAMover].getLabel());
							monton7[posCartaDestino].setIcon(monton2[posCartaAMover].getIcon());
							monton7[posCartaDestino].setVisible(true);
						}

						operacionesMontones("monton2");

					} else if (cartaAMover != null
							&& (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());
							monton1[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());
							monton3[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());
							monton4[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());
							monton5[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());
							monton6[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton2[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton2[posCartaAMover].getIcon());
							monton7[posCartaDestino - 1].setVisible(true);
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

					if (cartaDestino.toString().equals("VA")) {

						System.out.println("MOVIMIENTO A VACIO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino].setLabel(monton3[posCartaAMover].getLabel());
							monton1[posCartaDestino].setIcon(monton3[posCartaAMover].getIcon());
							monton1[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino].setLabel(monton3[posCartaAMover].getLabel());
							monton2[posCartaDestino].setIcon(monton3[posCartaAMover].getIcon());
							monton2[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino].setLabel(monton3[posCartaAMover].getLabel());
							monton4[posCartaDestino].setIcon(monton3[posCartaAMover].getIcon());
							monton4[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino].setLabel(monton3[posCartaAMover].getLabel());
							monton5[posCartaDestino].setIcon(monton3[posCartaAMover].getIcon());
							monton5[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino].setLabel(monton3[posCartaAMover].getLabel());
							monton6[posCartaDestino].setIcon(monton3[posCartaAMover].getIcon());
							monton6[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino].setLabel(monton3[posCartaAMover].getLabel());
							monton7[posCartaDestino].setIcon(monton3[posCartaAMover].getIcon());
							monton7[posCartaDestino].setVisible(true);
						}

						operacionesMontones("monton3");

					} else if (cartaAMover != null
							&& (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());
							monton1[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());
							monton2[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());
							monton4[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());
							monton5[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());
							monton6[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton3[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton3[posCartaAMover].getIcon());
							monton7[posCartaDestino - 1].setVisible(true);
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
					if (cartaDestino.toString().equals("VA")) {

						System.out.println("MOVIMIENTO A VACIO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino].setLabel(monton4[posCartaAMover].getLabel());
							monton1[posCartaDestino].setIcon(monton4[posCartaAMover].getIcon());
							monton1[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino].setLabel(monton4[posCartaAMover].getLabel());
							monton2[posCartaDestino].setIcon(monton4[posCartaAMover].getIcon());
							monton2[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino].setLabel(monton4[posCartaAMover].getLabel());
							monton3[posCartaDestino].setIcon(monton4[posCartaAMover].getIcon());
							monton3[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino].setLabel(monton4[posCartaAMover].getLabel());
							monton5[posCartaDestino].setIcon(monton4[posCartaAMover].getIcon());
							monton5[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino].setLabel(monton4[posCartaAMover].getLabel());
							monton6[posCartaDestino].setIcon(monton4[posCartaAMover].getIcon());
							monton6[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino].setLabel(monton4[posCartaAMover].getLabel());
							monton7[posCartaDestino].setIcon(monton4[posCartaAMover].getIcon());
							monton7[posCartaDestino].setVisible(true);
						}

						operacionesMontones("monton4");
					} else if (cartaAMover != null
							&& (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());
							monton1[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());
							monton2[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());
							monton3[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());
							monton5[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());
							monton6[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton4[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton4[posCartaAMover].getIcon());
							monton7[posCartaDestino - 1].setVisible(true);
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

					if (cartaDestino.toString().equals("VA")) {

						System.out.println("MOVIMIENTO A VACIO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino].setLabel(monton5[posCartaAMover].getLabel());
							monton1[posCartaDestino].setIcon(monton5[posCartaAMover].getIcon());
							monton1[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino].setLabel(monton5[posCartaAMover].getLabel());
							monton2[posCartaDestino].setIcon(monton5[posCartaAMover].getIcon());
							monton2[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino].setLabel(monton5[posCartaAMover].getLabel());
							monton3[posCartaDestino].setIcon(monton5[posCartaAMover].getIcon());
							monton3[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino].setLabel(monton5[posCartaAMover].getLabel());
							monton4[posCartaDestino].setIcon(monton5[posCartaAMover].getIcon());
							monton4[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino].setLabel(monton5[posCartaAMover].getLabel());
							monton6[posCartaDestino].setIcon(monton5[posCartaAMover].getIcon());
							monton6[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino].setLabel(monton5[posCartaAMover].getLabel());
							monton7[posCartaDestino].setIcon(monton5[posCartaAMover].getIcon());
							monton7[posCartaDestino].setVisible(true);
						}

						operacionesMontones("monton5");

					} else if (cartaAMover != null
							&& (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());
							monton1[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());
							monton2[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());
							monton3[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());
							monton4[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());
							monton6[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton5[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton5[posCartaAMover].getIcon());
							monton7[posCartaDestino - 1].setVisible(true);
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

					if (cartaDestino.toString().equals("VA")) {

						System.out.println("MOVIMIENTO A VACIO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino].setLabel(monton6[posCartaAMover].getLabel());
							monton1[posCartaDestino].setIcon(monton6[posCartaAMover].getIcon());
							monton1[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino].setLabel(monton6[posCartaAMover].getLabel());
							monton2[posCartaDestino].setIcon(monton6[posCartaAMover].getIcon());
							monton2[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino].setLabel(monton6[posCartaAMover].getLabel());
							monton3[posCartaDestino].setIcon(monton6[posCartaAMover].getIcon());
							monton3[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino].setLabel(monton6[posCartaAMover].getLabel());
							monton4[posCartaDestino].setIcon(monton6[posCartaAMover].getIcon());
							monton4[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino].setLabel(monton6[posCartaAMover].getLabel());
							monton5[posCartaDestino].setIcon(monton6[posCartaAMover].getIcon());
							monton5[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino].setLabel(monton6[posCartaAMover].getLabel());
							monton7[posCartaDestino].setIcon(monton6[posCartaAMover].getIcon());
							monton7[posCartaDestino].setVisible(true);
						}

						operacionesMontones("monton6");

					} else if (cartaAMover != null
							&& (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());
							monton1[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());
							monton2[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());
							monton3[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());
							monton4[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());
							monton5[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton7") {
							monton7[posCartaDestino - 1].setLabel(monton6[posCartaAMover].getLabel());
							monton7[posCartaDestino - 1].setIcon(monton6[posCartaAMover].getIcon());
							monton7[posCartaDestino - 1].setVisible(true);
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

					if (cartaDestino.toString().equals("VA")) {

						System.out.println("MOVIMIENTO A VACIO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino].setLabel(monton7[posCartaAMover].getLabel());
							monton1[posCartaDestino].setIcon(monton7[posCartaAMover].getIcon());
							monton1[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino].setLabel(monton7[posCartaAMover].getLabel());
							monton2[posCartaDestino].setIcon(monton7[posCartaAMover].getIcon());
							monton2[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino].setLabel(monton7[posCartaAMover].getLabel());
							monton3[posCartaDestino].setIcon(monton7[posCartaAMover].getIcon());
							monton3[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino].setLabel(monton7[posCartaAMover].getLabel());
							monton4[posCartaDestino].setIcon(monton7[posCartaAMover].getIcon());
							monton4[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino].setLabel(monton7[posCartaAMover].getLabel());
							monton5[posCartaDestino].setIcon(monton7[posCartaAMover].getIcon());
							monton5[posCartaDestino].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino].setLabel(monton7[posCartaAMover].getLabel());
							monton6[posCartaDestino].setIcon(monton7[posCartaAMover].getIcon());
							monton6[posCartaDestino].setVisible(true);
						}

						operacionesMontones("monton7");

					} else if (cartaAMover != null
							&& (cartaAMover.establecerValor() == (cartaDestino.establecerValor() - 1))
							&& !(cartaAMover.getColor().equals(cartaDestino.getColor()))) { // Comprobamos que la carta
																							// que movemos sea de un
																							// valor inmediatamente
																							// inferior y que el color
																							// sea distinto

						System.out.println("MOVIMIENTO CORRECTO");

						if (mazoDestino == "monton1") {

							monton1[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton1[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());
							monton1[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton2") {
							monton2[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton2[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());
							monton2[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton3") {
							monton3[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton3[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());
							monton3[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton4") {
							monton4[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton4[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());
							monton4[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton5") {
							monton5[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton5[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());
							monton5[posCartaDestino - 1].setVisible(true);
						} else if (mazoDestino == "monton6") {
							monton6[posCartaDestino - 1].setLabel(monton7[posCartaAMover].getLabel());
							monton6[posCartaDestino - 1].setIcon(monton7[posCartaAMover].getIcon());
							monton6[posCartaDestino - 1].setVisible(true);
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
				monton1[posCartaAMover].setLabel("vacio");
			} else {
				monton1[posCartaAMover].setIcon(null);
				monton1[posCartaAMover].setLabel("vacio");
				monton1[posCartaAMover].setVisible(false);
				monton1[posCartaAMover + 1].setEnabled(true);
				// monton1[posCartaAMover + 1].setIcon(icono[posSiguiente]);

			}

		} else if (monton == "monton2") {

			if (posCartaAMover == 19) {
				monton2[posCartaAMover].setIcon(null);
				monton2[posCartaAMover].setLabel("vacio");
			} else {
				int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton2[posCartaAMover + 1].getLabel()));
				monton2[posCartaAMover].setIcon(null);
				monton2[posCartaAMover].setLabel("vacio");
				monton2[posCartaAMover].setVisible(false);
				monton2[posCartaAMover + 1].setEnabled(true);
				monton2[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton3") {
			int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton3[posCartaAMover + 1].getLabel()));

			if (posCartaAMover == 19) {
				monton3[posCartaAMover].setIcon(null);
				monton3[posCartaAMover].setLabel("vacio");
			} else {
				monton3[posCartaAMover].setIcon(null);
				monton3[posCartaAMover].setLabel("vacio");
				monton3[posCartaAMover].setVisible(false);
				monton3[posCartaAMover + 1].setEnabled(true);
				monton3[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton4") {

			if (posCartaAMover == 19) {
				monton4[posCartaAMover].setIcon(null);
				monton4[posCartaAMover].setLabel("vacio");
			} else {
				int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton4[posCartaAMover + 1].getLabel()));
				monton4[posCartaAMover].setIcon(null);
				monton4[posCartaAMover].setLabel("vacio");
				monton4[posCartaAMover].setVisible(false);
				monton4[posCartaAMover + 1].setEnabled(true);
				monton4[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton5") {

			if (posCartaAMover == 19) {
				monton5[posCartaAMover].setIcon(null);
				monton5[posCartaAMover].setLabel("vacio");
			} else {
				int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton5[posCartaAMover + 1].getLabel()));
				monton5[posCartaAMover].setIcon(null);
				monton5[posCartaAMover].setLabel("vacio");
				monton5[posCartaAMover].setVisible(false);
				monton5[posCartaAMover + 1].setEnabled(true);
				monton5[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton6") {

			if (posCartaAMover == 19) {
				monton6[posCartaAMover].setIcon(null);
				monton6[posCartaAMover].setLabel("vacio");
			} else {
				int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton6[posCartaAMover + 1].getLabel()));

				monton6[posCartaAMover].setIcon(null);
				monton6[posCartaAMover].setLabel("vacio");
				monton6[posCartaAMover].setVisible(false);
				monton6[posCartaAMover + 1].setEnabled(true);
				monton6[posCartaAMover + 1].setIcon(icono[posSiguiente]);
			}

		} else if (monton == "monton7") {

			if (posCartaAMover == 19) {
				monton7[posCartaAMover].setIcon(null);
				monton7[posCartaAMover].setLabel("vacio");
			} else {
				int posSiguiente = buscarPosBarajaInicial(buscaCarta(monton7[posCartaAMover + 1].getLabel()));

				monton7[posCartaAMover].setIcon(null);
				monton7[posCartaAMover].setLabel("vacio");
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
		} else if (cartaStr.equals("vacio")) {
			return new CartaFrancesa('V', 'A', null, null);
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
		int[] enabled = { 0, 0, 0, 0, 0, 0, 0 };

		try {
			FileWriter fichero = new FileWriter(rutaJuego);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println("Solitario clásico");

			/*
			 * Guardar inicio y descartes
			 */
			for (int i = barajaInicial.length - 1; i >= 0; i--) {

				if (barajaInicial[i].getLabel() != "NuLo") {
					pw.print(barajaInicial[i].getLabel() + " ");
				}
			}

			pw.println();

			for (int i = barajaDescartes.length - 1; i >= 0; i--) {

				if (barajaDescartes[i].getLabel() != "NuLo") {
					pw.print(barajaDescartes[i].getLabel() + " ");
				}
			}

			for (int i = 0; i < monton1.length; i++) {

				if (!monton1[i].isEnabled()) {
					enabled[0]++;
				}
				if (!monton2[i].isEnabled()) {
					enabled[1]++;
				}
				if (!monton3[i].isEnabled()) {
					enabled[2]++;
				}
				if (!monton4[i].isEnabled()) {
					enabled[3]++;
				}
				if (!monton5[i].isEnabled()) {
					enabled[4]++;
				}
				if (!monton6[i].isEnabled()) {
					enabled[5]++;
				}
				if (!monton7[i].isEnabled()) {
					enabled[6]++;
				}
			}
			/*
			 * Guardar montones
			 */
			pw.println();
			pw.print("* ");
			for (int i = monton1.length - 1; i >= 0; i--) {
				if (monton1[i].getLabel() != "vacio") {
					pw.write(monton1[i].getLabel() + " ");
				}
			}

			pw.println();

			for (int i = monton2.length - 1; i >= 0; i--) {
				if (monton2[i].getLabel() != "vacio") {

					if (i == monton2.length - enabled[1] - 1) {
						pw.print("* ");
					}
					pw.write(monton2[i].getLabel() + " ");
				}
			}

			pw.println();

			for (int i = monton3.length - 1; i >= 0; i--) {
				if (monton3[i].getLabel() != "vacio") {

					if (i == monton3.length - enabled[2] - 1) {
						pw.print("* ");
					}
					pw.write(monton3[i].getLabel() + " ");
				}
			}

			pw.println();

			for (int i = monton4.length - 1; i >= 0; i--) {
				if (monton4[i].getLabel() != "vacio") {

					if (i == monton4.length - enabled[3] - 1) {
						pw.print("* ");
					}
					pw.write(monton4[i].getLabel() + " ");
				}
			}

			pw.println();

			for (int i = monton5.length - 1; i >= 0; i--) {
				if (monton5[i].getLabel() != "vacio") {

					if (i == monton5.length - enabled[4] - 1) {
						pw.print("* ");
					}
					pw.write(monton5[i].getLabel() + " ");
				}
			}

			pw.println();

			for (int i = monton6.length - 1; i >= 0; i--) {
				if (monton6[i].getLabel() != "vacio") {

					if (i == monton6.length - enabled[5] - 1) {
						pw.print("* ");
					}
					pw.write(monton6[i].getLabel() + " ");
				}
			}

			pw.println();

			for (int i = monton7.length - 1; i >= 0; i--) {
				if (monton7[i].getLabel() != "vacio") {

					if (i == monton7.length - enabled[6] - 1) {
						pw.print("* ");
					}
					pw.write(monton7[i].getLabel() + " ");
				}
			}

			pw.println();
			/*
			 * Guardar montones finales
			 */
			for (int i = 0; i < fin1.length; i++) {

				if (fin1[i].getLabel() != "NuLo" && fin1[i].getLabel() != "F1") {
					pw.print(fin1[i].getLabel() + " ");

				}
			}
			pw.println();

			for (int i = 0; i < fin2.length; i++) {

				if (fin2[i].getLabel() != "NuLo" && fin2[i].getLabel() != "F2") {
					pw.print(fin2[i].getLabel() + " ");

				}
			}

			pw.println();

			for (int i = 0; i < fin3.length; i++) {

				if (fin3[i].getLabel() != "NuLo" && fin3[i].getLabel() != "F3") {
					pw.print(fin3[i].getLabel() + " ");

				}
			}

			pw.println();

			for (int i = 0; i < fin4.length; i++) {

				if (fin4[i].getLabel() != "NuLo" && fin4[i].getLabel() != "F4") {
					pw.print(fin4[i].getLabel() + " ");

				}
			}
			pw.println();
			fichero.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void cargarJuego(String ruta) {

		String[] guardar = new String[15];
		String cadena;

		try {
			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr);
			System.out.println(ruta);
			int i = 0;

			while ((cadena = br.readLine()) != null) {
				guardar[i] = cadena;

				i++;
			}
			br.close();

		} catch (Exception a) {
			System.out.println("Error leyendo fichero " + ruta + ": " + a);
		}

		for (int j = 0; j < guardar.length; j++) {
			System.out.println(guardar[j]);
		}

		String[] iniciales = guardar[1].split(" ");
		String[] descartadas = guardar[2].split(" ");
		String[] mon1 = guardar[3].split("\\* ");
		String[] mon2 = guardar[4].split("\\* ");
		String[] mon3 = guardar[5].split("\\* ");
		String[] mon4 = guardar[6].split("\\* ");
		String[] mon5 = guardar[7].split("\\* ");
		String[] mon6 = guardar[8].split("\\* ");
		String[] mon7 = guardar[9].split("\\* ");
		String[] f1 = guardar[10].split(" ");
		String[] f2 = guardar[11].split(" ");
		String[] f3 = guardar[12].split(" ");
		String[] f4 = guardar[13].split(" ");

		int h = 0;
		for (int i = iniciales.length - 1; i >= 0; i--) {

			barajaInicial[h].setLabel(iniciales[i]);
			barajaInicial[h].setIcon(buscarIcono(iniciales[i]));

			h++;
		}

		for (int i = h; i < 23; i++) {
			barajaInicial[i].setLabel("NuLo");
			barajaInicial[i].setIcon(null);
		}

		int j = 0;
		for (int i = descartadas.length - 1; i >= 0; i--) {

			barajaDescartes[j].setLabel(descartadas[i]);
			barajaDescartes[j].setIcon(buscarIcono(descartadas[i]));
			j++;
		}
		// carga de montones intermedios
		String[] mon11 = mon1[0].split(" ");
		String[] mon12 = mon1[1].split(" ");

		String[] mon21 = mon2[0].split(" ");
		String[] mon22 = mon2[1].split(" ");

		String[] mon31 = mon3[0].split(" ");
		String[] mon32 = mon3[1].split(" ");

		String[] mon41 = mon4[0].split(" ");
		String[] mon42 = mon4[1].split(" ");

		String[] mon51 = mon5[0].split(" ");
		String[] mon52 = mon5[1].split(" ");

		String[] mon61 = mon6[0].split(" ");
		String[] mon62 = mon6[1].split(" ");

		String[] mon71 = mon7[0].split(" ");
		String[] mon72 = mon7[1].split(" ");

		
		//carga monton1
		j = 19;
		for (int i = 0 ; i < mon12.length; i++) {

			monton1[j].setLabel(mon12[i]);
			monton1[j].setIcon(buscarIcono(mon12[i]));
			monton1[j].setVisible(true);
			j--;
		}
		
		//carga monton 2
		
		j = 19;
		for (int i = 0; i < mon21.length; i++) {

			monton2[j].setLabel(mon21[i]);
			monton2[j].setIcon(reverso);
			monton2[j].setEnabled(false);
			monton2[j].setVisible(true);
			j--;
		}
		
		for(int i = 0; i < mon22.length; i++) {
			monton2[j].setLabel(mon22[i]);
			monton2[j].setIcon(buscarIcono(mon22[i]));
			monton2[j].setEnabled(true);
			monton2[j].setVisible(true);
			j--;
		}
		
		//carga monton 3
		
		j = 19;
		for (int i = 0; i < mon31.length; i++) {

			monton3[j].setLabel(mon31[i]);
			monton3[j].setIcon(reverso);
			monton3[j].setEnabled(false);
			monton3[j].setVisible(true);
			j--;
		}
		
		for(int i = 0; i < mon32.length; i++) {
			monton3[j].setLabel(mon32[i]);
			monton3[j].setIcon(buscarIcono(mon32[i]));
			monton3[j].setEnabled(true);
			monton3[j].setVisible(true);
			j--;
		}
		
		
		//carga monton 4
		
		
		j = 19;
		for (int i = 0; i < mon41.length; i++) {

			monton4[j].setLabel(mon41[i]);
			monton4[j].setIcon(reverso);
			monton4[j].setEnabled(false);
			monton4[j].setVisible(true);
			j--;
		}
		
		for(int i = 0; i < mon42.length; i++) {
			monton4[j].setLabel(mon42[i]);
			monton4[j].setIcon(buscarIcono(mon42[i]));
			monton4[j].setEnabled(true);
			monton4[j].setVisible(true);
			j--;
		}
		
		//carga monton 5
		

		j = 19;
		for (int i = 0; i < mon51.length; i++) {

			monton5[j].setLabel(mon51[i]);
			monton5[j].setIcon(reverso);
			monton5[j].setEnabled(false);
			monton5[j].setVisible(true);
			j--;
		}
		
		for(int i = 0; i < mon52.length; i++) {
			monton5[j].setLabel(mon52[i]);
			monton5[j].setIcon(buscarIcono(mon52[i]));
			monton5[j].setEnabled(true);
			monton5[j].setVisible(true);
			j--;
		}
		
		//carga monton 6
		
		j = 19;
		for (int i = 0; i < mon61.length; i++) {

			monton6[j].setLabel(mon61[i]);
			monton6[j].setIcon(reverso);
			monton6[j].setEnabled(false);
			monton6[j].setVisible(true);
			
			j--;
		}
		
		for(int i = 0; i < mon62.length; i++) {
			monton6[j].setLabel(mon62[i]);
			monton6[j].setIcon(buscarIcono(mon62[i]));
			monton6[j].setEnabled(true);
			monton6[j].setVisible(true);
			j--;
		}
		
		//carga monton 7
		
		j = 19;
		for (int i = 0; i < mon71.length; i++) {

			monton7[j].setLabel(mon71[i]);
			monton7[j].setIcon(reverso);
			monton7[j].setEnabled(false);
			monton7[j].setVisible(true);
			j--;
		}
		
		for(int i = 0; i < mon72.length; i++) {
			monton7[j].setLabel(mon72[i]);
			monton7[j].setIcon(buscarIcono(mon72[i]));
			monton7[j].setEnabled(true);
			monton7[j].setVisible(true);
			j--;
		}
		
		
		// carga de montones finales
		j = 0;

		for (int i = 0; i < f1.length; i++) {

			fin1[j].setLabel(f1[i]);
			fin1[j].setIcon(buscarIcono(f1[i]));
			j++;
		}

		j = 0;
		for (int i = 0; i < f2.length; i++) {
			fin2[j].setLabel(f2[i]);
			fin2[j].setIcon(buscarIcono(f2[i]));
			j++;
		}

		j = 0;
		for (int i = 0; i < f3.length; i++) {
			fin3[j].setLabel(f3[i]);
			fin3[j].setIcon(buscarIcono(f3[i]));
			j++;
		}

		j = 0;
		for (int i = 0; i < f4.length; i++) {
			fin4[j].setLabel(f4[i]);
			fin4[j].setIcon(buscarIcono(f4[i]));
			j++;
		}

		if (f1.length == 1) {
			fin1[f1.length + 1].setLabel("F1");
			fin1[f1.length + 1].setIcon(null);
		} else {
			fin1[f1.length].setLabel("F1");
			fin1[f1.length].setIcon(null);
		}

		if (f2.length == 1) {
			fin2[f2.length + 1].setLabel("F2");
			fin2[f2.length + 1].setIcon(null);
		} else {
			fin2[f2.length].setLabel("F2");
			fin2[f2.length].setIcon(null);
		}

		if (f3.length == 1) {
			fin3[f3.length + 1].setLabel("F3");
			fin3[f3.length + 1].setIcon(null);
		} else {
			fin3[f3.length].setLabel("F3");
			fin3[f3.length].setIcon(null);
		}

		if (f4.length == 1) {
			fin4[f4.length + 1].setLabel("F4");
			fin4[f4.length + 1].setIcon(null);
		} else {
			fin4[f4.length].setLabel("F4");
			fin4[f4.length].setIcon(null);
		}

		printArrays();

	}

	private ImageIcon buscarIcono(String card) {

		ImageIcon ret = null;

		for (int i = 0; i < 52; i++) {

			if (icono[i].getDescription().equals(card)) {

				ret = icono[i];
			}
		}

		return ret;
	}

	public void sumarEstadisticas() {

		URL resource = getClass().getResource("/estadisticas/Estadisticas.txt");
		String[] linea = new String[6];
		String aImprimir1 = "HOLA";
		String aImprimir2 = "GOLa";
		// System.out.println(rutaEstadisticas);
		try {
			InputStream is = resource.openStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			for (int i = 0; i < 6; i++) {

				linea[i] = br.readLine();

			}
			aImprimir1 = linea[4];
			aImprimir2 = linea[5];
			int intentos = Integer.parseInt(linea[4]);
			int intentosExito = Integer.parseInt(linea[5]);

			if (!flagIntentos) {
				intentos = intentos + 1;

				aImprimir1 = Integer.toString(intentos);
				flagIntentos = true;

			}

			int nulo = 0;
			for (int i = 0; i < 13; i++) {
				if (monton1[i].getLabel().equals("NuLo")) {
					nulo++;
				}
				if (monton2[i].getLabel().equals("NuLo")) {
					nulo++;
				}
				if (monton3[i].getLabel().equals("NuLo")) {
					nulo++;
				}
				if (monton4[i].getLabel().equals("NuLo")) {
					nulo++;
				}

			}

			System.out.println("NuLo" + nulo);
			if (nulo == 0) {
				intentosExito = intentosExito + 1;
				aImprimir2 = Integer.toString(intentosExito);
			}

		} catch (Exception a) {
			System.out.println("Error leyendo fichero " + resource.getFile() + ": " + a);
		}

		try {
			FileWriter fichero2 = new FileWriter(resource.getFile());
			PrintWriter pw = new PrintWriter(fichero2);

			pw.println(linea[0]);
			pw.println(linea[1]);
			pw.println(linea[2]);
			pw.println(linea[3]);
			pw.println(aImprimir1);
			pw.println(aImprimir2);

			pw.close();
		} catch (IOException e) {
			System.out.println("Error escribiendo fichero " + resource.getFile() + ": " + e);

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
