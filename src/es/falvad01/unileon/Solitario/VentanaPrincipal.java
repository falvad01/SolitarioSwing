package es.falvad01.unileon.Solitario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class VentanaPrincipal extends JFrame implements ActionListener {

	// TODO REVISAR LAYOUT DE LA VENTANA, SERIA MAS EFECTIVO PARA EL USUARIO MENUS
	// DESPLEGABLES EN LA PARTE SUPERIOR DE LA VENTANA

	Toolkit screen;
	private int screenHeight;
	private int screenWith;

	private JPanel menu;
	private JPanel archivo;
	private JPanel editar;
	private JPanel historial;
	private JPanel ayuda;

	/* Botones principales */

	private JButton btnArchivo;
	private JButton btnEditar;
	private JButton btnHistorial;
	private JButton btnAyuda;

	private JLabel lblEditar;
	private JLabel lblHistorial;
	private JLabel lblAyuda;

	private JComboBox comboBox;
	private String gameSelected;

	private JButton btnNuevo;
	private JButton btnCargar;
	private JButton btnSalvar;
	private JButton btnSalvarComo;
	private JButton btnSalir;

	File loadGame;
	File saveGame;

	Baraja baraja;
	VentanaTablero vJuego;

	public VentanaPrincipal() {

		screen = Toolkit.getDefaultToolkit();
		Dimension screenSize = screen.getScreenSize();
		screenHeight = screenSize.height;
		screenWith = screenSize.width;

		// setBounds(panelWith / 2, panelHeight / 2, panelWith, panelHeight);

		setBounds(1024 / 4, 768 / 6, 650, 550);

		setTitle("Solitario");

		setResizable(false);

		Image icono = screen.getImage("etc/images/interface/icono.jpg");

		setIconImage(icono);

		initComponents();

	}

	private void initComponents() {

		getContentPane().setLayout(null);
		Image backGround = screen.getImage("etc/images/interface/backGround.jpg");

//////////////////////////PANELES/////////////////////////////////////////

		archivo = new JPanel();
		archivo.setBackground(Color.YELLOW);
		archivo.setBounds(10, 0, 624, 510);
		getContentPane().add(archivo);
		archivo.setLayout(null);
		archivo.setVisible(false);

		menu = new JPanel();
		menu.setBackground(null);
		menu.setBounds(10, 0, 624, 510);
		getContentPane().add(menu);
		menu.setLayout(null);

		editar = new JPanel();
		editar.setBackground(Color.BLUE);
		editar.setBounds(10, 0, 624, 510);
		getContentPane().add(editar);
		editar.setLayout(null);

		historial = new JPanel();
		historial.setBackground(Color.CYAN);
		historial.setBounds(10, 0, 624, 510);
		getContentPane().add(historial);
		historial.setLayout(null);

		ayuda = new JPanel();
		ayuda.setBackground(Color.MAGENTA);
		ayuda.setBounds(10, 0, 624, 510);
		getContentPane().add(ayuda);
		ayuda.setLayout(null);

		initMenuComponents(); // Llamamos a los componentes de cada panel
		initArchivoComponents();
		initEditarComponents();
		initHistorialComponenets();
		initAyudaComponents();

	}

	private void initMenuComponents() {

		btnArchivo = new JButton("Archivo");
		btnArchivo.setBounds(137, 55, 315, 62);
		menu.add(btnArchivo);
		btnArchivo.addActionListener(this);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(137, 170, 315, 62);
		menu.add(btnEditar);
		btnEditar.addActionListener(this);

		btnHistorial = new JButton("Historial");
		btnHistorial.setBounds(137, 277, 315, 62);
		menu.add(btnHistorial);
		btnHistorial.addActionListener(this);

		btnAyuda = new JButton("Ayuda");
		btnAyuda.setBounds(137, 391, 315, 62);
		menu.add(btnAyuda);
		btnAyuda.addActionListener(this);

	}

	private void initArchivoComponents() {

		JButton atras = new JButton("Atras");
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setVisible(true);
				archivo.setVisible(false);
				editar.setVisible(false);
				historial.setVisible(false);
				ayuda.setVisible(false);

				setTitle("Solitario");
			}
		});
		atras.setBounds(10, 11, 112, 37);
		archivo.add(atras);

		comboBox = new JComboBox();
		comboBox.setBounds(43, 124, 122, 20);
		archivo.add(comboBox);
		comboBox.addItem("--");
		comboBox.addItem("Saltos");
		comboBox.addItem("Clasico");
		comboBox.addActionListener(this);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(214, 123, 157, 23);
		archivo.add(btnNuevo);
		btnNuevo.addActionListener(this);

		btnCargar = new JButton("Cargar");
		btnCargar.setBounds(214, 181, 157, 23);
		archivo.add(btnCargar);
		btnCargar.addActionListener(this);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(214, 243, 157, 23);
		archivo.add(btnSalvar);

		btnSalvarComo = new JButton("Salvar como");
		btnSalvarComo.setBounds(214, 318, 157, 23);
		archivo.add(btnSalvarComo);
		btnSalvarComo.addActionListener(this);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(214, 379, 157, 23);
		archivo.add(btnSalir);
		btnSalir.addActionListener(this);

	}

	private void initEditarComponents() {

		lblEditar = new JLabel("EDITAR");
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEditar.setBounds(10, 194, 225, 114);
		editar.add(lblEditar);
		editar.setVisible(false);

		JButton atras = new JButton("Atras");
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setVisible(true);
				archivo.setVisible(false);
				editar.setVisible(false);
				historial.setVisible(false);
				ayuda.setVisible(false);

				setTitle("Solitario");

			}
		});
		atras.setBounds(10, 11, 112, 37);
		editar.add(atras);

	}

	private void initHistorialComponenets() {
		lblHistorial = new JLabel("HISTORIAL");
		lblHistorial.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHistorial.setBounds(20, 231, 171, 88);
		historial.add(lblHistorial);

		JButton atras = new JButton("Atras");
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setVisible(true);
				archivo.setVisible(false);
				editar.setVisible(false);
				historial.setVisible(false);
				ayuda.setVisible(false);

				setTitle("Solitario");

			}
		});
		atras.setBounds(10, 11, 112, 37);
		historial.add(atras);

	}

	private void initAyudaComponents() {
		lblAyuda = new JLabel("AYUDA");
		lblAyuda.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAyuda.setBounds(10, 200, 200, 130);
		ayuda.add(lblAyuda);

		JButton atras = new JButton("Atras");
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setVisible(true);
				archivo.setVisible(false);
				editar.setVisible(false);
				historial.setVisible(false);
				ayuda.setVisible(false);

				setTitle("Solitario");

			}
		});
		atras.setBounds(10, 11, 112, 37);
		ayuda.add(atras);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnArchivo) {
			menu.setVisible(false);
			archivo.setVisible(true);
			editar.setVisible(false);
			historial.setVisible(false);
			ayuda.setVisible(false);

			setTitle("Solitario-Archivo");

		} else if (e.getSource() == btnEditar) {

			menu.setVisible(false);
			archivo.setVisible(false);
			editar.setVisible(true);
			historial.setVisible(false);
			ayuda.setVisible(false);

			setTitle("Solitario-Editar");

		} else if (e.getSource() == btnHistorial) {

			menu.setVisible(false);
			archivo.setVisible(false);
			editar.setVisible(false);
			historial.setVisible(true);
			ayuda.setVisible(false);

			setTitle("Solitario-Historial");

		} else if (e.getSource() == btnAyuda) {

			menu.setVisible(false);
			archivo.setVisible(false);
			editar.setVisible(false);
			historial.setVisible(false);
			ayuda.setVisible(true);

			setTitle("Solitario-Ayuda");

		}
		/////////////////// BOTONES SUBMENU ARCHIVO////////////////////////////////

		if (e.getSource() == btnNuevo) {

			if (gameSelected == null || gameSelected.equals("--")) {
				System.out.println("Modo de juego no permitido"); // TODO METER EXCEPCION
				JOptionPane.showMessageDialog(this, "Modo de juego no permitido", "Modo de juego no permitido",
						JOptionPane.ERROR_MESSAGE);
			} else {
				System.out.println(gameSelected);
				// TODO DESDE AQUI LANZARIAMOS AL MODO DE JUEGO CORRESPONDIENTE

				if (gameSelected == "Saltos") {

					System.out.println("Juego saltos cargando");
					baraja = new Baraja(EJuego.Saltos);
					System.out.println(baraja.toString());
					baraja.barajarE();
					System.out.println(baraja.toString());

				} else if (gameSelected == "Clasico") {

					System.out.println("Juego clasico cargando");

					baraja = new Baraja(EJuego.Clasico);
					System.out.println(baraja.toString());
					baraja.barajarF();
					System.out.println(baraja.toString());

					vJuego = new VentanaTablero();

					vJuego.setVisible(true);
					vJuego.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

				}

			}

		} else if (e.getSource() == btnCargar) {

			System.out.println("Cargar archivo");

			JFileChooser select = new JFileChooser();
			select.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			int i = select.showSaveDialog(this);// Cargamos el archivo

			loadGame = select.getSelectedFile();

			if ((loadGame == null)
					|| !(loadGame.getName().substring(loadGame.getName().lastIndexOf(".") + 1).equals("txt"))) {
				JOptionPane.showMessageDialog(this, "Tipo de archivo incorrecto", "Tipo de archivo incorrecto",
						JOptionPane.ERROR_MESSAGE);// Comprobamos que la extension del archivo sea la correcta
			} else {
				System.out.println(loadGame.getPath());
				// TODO DESDE AQUI MANDAMOS LA RUTA DEL ARCHIVO PARA CARGARLO EN EL JUEGO
			}

			// System.out.println(loadGame.getPath());

		} else if (e.getSource() == btnSalvar) {

			// TODO AQUI DEBERIA LLEGAR LA RUTA DEL ULTIMO ARCHIVO JUGADO

		} else if (e.getSource() == btnSalvarComo) {
			/*
			 * JFileChooser save = new JFileChooser();
			 * 
			 * if (save.showSaveDialog(null) == save.APPROVE_OPTION) {
			 * 
			 * saveGame = save.getSelectedFile();
			 * 
			 * if (new File(saveGame.getPath()).exists()) {
			 * 
			 * if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this,
			 * "El fichero existe,deseas reemplazarlo?", "Titulo",
			 * JOptionPane.YES_NO_OPTION)) {
			 * 
			 * }
			 * 
			 * } }
			 */
		} else if (e.getSource() == btnSalir) {

			if (JOptionPane.showConfirmDialog(rootPane, "¿Desea cerrar el programa?", "¿Desea cerrar el programa?",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // Pregunta si esta seguro de que desea
																			// salir
				System.exit(0);
			}
		}

		if (e.getSource() == comboBox) {

			gameSelected = (String) comboBox.getSelectedItem(); // Guardamos la opcion e juego que queremos en un string

		}

	}
}
