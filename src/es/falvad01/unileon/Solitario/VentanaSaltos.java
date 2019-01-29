package es.falvad01.unileon.Solitario;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaSaltos extends JFrame implements ActionListener {

	Toolkit screen;
	private static final int PWIDTH = 550;
	private static final int PHEIGH = 1000;

	private JMenu archivo;
	private JMenu editar;
	private JMenu historial;
	private JMenu ayuda;

	private JMenu nuevo;

	private JMenuItem btnNuevoSaltos;
	private JMenuItem btnCargar;
	private JMenuItem btnSalvar;
	private JMenuItem btnSalvarComo;
	private JMenuItem btnSalir;

	private JMenuItem btnDeshacer;
	private JMenuItem btnHacer;
	private JMenuItem btnResolver;

	private JMenuItem btnEstadisticas;
	private JMenuItem btnEstadisticasFichero;

	private JMenuItem btnInfo;

	JPanel juegoSaltos;

	PanelSaltos saltos;

	File loadGame;
	File saveGame;

	public VentanaSaltos() {

		screen = Toolkit.getDefaultToolkit();

		// setBounds(panelWith / 2, panelHeight / 2, panelWith, panelHeight);

		setBounds(1024 / 4, 768 / 6, PHEIGH, PWIDTH);

		setTitle("Solitario Saltos");

		setResizable(false);

		Image icono = screen.getImage("etc/images/interface/icono.jpg");

		setIconImage(icono);

		initComponents();
		initPanel();

	}

	private void initComponents() {

		juegoSaltos = new JPanel();
		saltos = new PanelSaltos(juegoSaltos);
		juegoSaltos.setBackground(new Color(0, 120, 0));
		juegoSaltos.setBounds(0, 0, PHEIGH, PWIDTH);
		juegoSaltos.setLayout(null);
		juegoSaltos.setVisible(false);

		getContentPane().add(juegoSaltos);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		archivo = new JMenu("Archivo");
		menuBar.add(archivo);

		btnCargar = new JMenuItem("Cargar");
		archivo.add(btnCargar);
		btnCargar.addActionListener(this);

		btnSalvar = new JMenuItem("Salvar");
		archivo.add(btnSalvar);
		btnSalvar.addActionListener(this);

		btnSalvarComo = new JMenuItem("Salvar Como");
		archivo.add(btnSalvarComo);
		btnSalvarComo.addActionListener(this);

		btnSalir = new JMenuItem("Salir de saltos");
		archivo.add(btnSalir);
		btnSalir.addActionListener(this);

		editar = new JMenu("Editar");
		menuBar.add(editar);

		btnDeshacer = new JMenuItem("Deshacer");
		editar.add(btnDeshacer);
		btnDeshacer.addActionListener(this);

		btnHacer = new JMenuItem("Hacer");
		editar.add(btnHacer);
		btnHacer.addActionListener(this);

		btnResolver = new JMenuItem("Resolver");
		editar.add(btnResolver);
		btnResolver.addActionListener(this);

//		historial = new JMenu("Historial");
//		menuBar.add(historial);
//
//		btnEstadisticas = new JMenuItem("Estadisticas");
//		historial.add(btnEstadisticas);
//		btnEstadisticas.addActionListener(this);
//		btnEstadisticasFichero = new JMenuItem("Estadisticas Fichero");
//		historial.add(btnEstadisticasFichero);
//		btnEstadisticasFichero.addActionListener(this);

//		ayuda = new JMenu("Ayuda");
//		menuBar.add(ayuda);
//		btnInfo = new JMenuItem("Informacion");
//		ayuda.add(btnInfo);
//		btnInfo.addActionListener(this);

	}

	private void initPanel() {

		juegoSaltos.setVisible(true);
		saltos.iniciarJuegoSaltos();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Salir de saltos")) {

			dispose();

		} else if (e.getActionCommand().equals("Resolver")) {

			if (saltos.resolverAuto(1, 0)) {
				JOptionPane.showMessageDialog(saltos, "Resuelto");
			}
		} else if (e.getActionCommand().equals("Salvar")) {

			String ruta;

			ruta = saltos.getRutaJuego();
			if (ruta == null) {// actua como guardar como
				guardarComo();
			} else if (ruta != null) {
				saltos.guardar(ruta);

			}

		} else if (e.getActionCommand().equals("Salvar Como")) {

			guardarComo();

		} else if (e.getActionCommand().equals("Cargar")) {

			System.out.println("Cargar archivo");

			JFileChooser select = new JFileChooser();
			select.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			int i = select.showOpenDialog(juegoSaltos);// Cargamos el archivo

			loadGame = select.getSelectedFile();

			if ((loadGame == null)
					|| !(loadGame.getName().substring(loadGame.getName().lastIndexOf(".") + 1).equals("txt"))) {
				JOptionPane.showMessageDialog(juegoSaltos, "Tipo de archivo incorrecto", "Tipo de archivo incorrecto",
						JOptionPane.ERROR_MESSAGE);// Comprobamos que la extension del archivo sea la correcta
			} else {

				String[] guardar = new String[42];
				String cadena;
				int hy = 0;

				try {
					FileReader fr = new FileReader(loadGame.getPath());
					BufferedReader br = new BufferedReader(fr);
					System.out.println(loadGame.getPath());

					while ((cadena = br.readLine()) != null) {
						guardar[hy] = cadena;

						hy++;
					}
					br.close();

				} catch (Exception a) {
					System.out.println("Error leyendo fichero " + loadGame.getPath() + ": " + a);
				}

				if (guardar[0].equals("Solitario saltos")) {

					System.out.println("CARGANDO SOLITARIO SALTOS");
					setVisible(true);
					saltos.cargarJuego(loadGame.getPath());

				} else if (guardar[0].equals("Solitario clásico")) {

					System.out.println("CARGANDO SOLITARIO Clasico");

				}
			}

		}

	}

	private void guardarComo() {

		System.out.println("LISTENRS GUARDAR COMO");
		JFileChooser save = new JFileChooser();

		if (save.showSaveDialog(null) == save.APPROVE_OPTION) {
			saveGame = save.getSelectedFile();

			if (new File(saveGame.getPath()).exists()) {

				if (JOptionPane.YES_NO_OPTION == JOptionPane.showConfirmDialog(juegoSaltos,
						"El fichero existe,deseas reemplazarlo?", "Titulo", JOptionPane.YES_NO_OPTION)) {

					saltos.guardar(saveGame.getAbsolutePath());

					System.out.println(saveGame.getAbsolutePath());
				}

			} else {
				saltos.guardar(saveGame.getAbsolutePath());
				System.out.println(saveGame.getAbsolutePath());
			}

		}

	}// Fin del metodo guardarComo

	public void cargarJuego(String ruta) {

		setVisible(true);
		saltos.cargarJuego(ruta);
	}

}
