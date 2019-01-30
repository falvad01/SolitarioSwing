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

	
	private JMenuItem btnSalvar;
	private JMenuItem btnSalvarComo;
	private JMenuItem btnSalir;

	private JMenuItem btnDeshacer;
	private JMenuItem btnHacer;
	private JMenuItem btnResolver;
	
	JPanel juegoSaltos;

	PanelSaltos saltos;

	File loadGame;
	File saveGame;
	
	static String rutaEstadisticas;

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
	
	public static void rutaStadisticas(String ruta) {
		rutaEstadisticas = ruta;
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


	}

	private void initPanel() {

		juegoSaltos.setVisible(true);
		saltos.iniciarJuegoSaltos();
		saltos.rutaStadisticas(rutaEstadisticas);

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
