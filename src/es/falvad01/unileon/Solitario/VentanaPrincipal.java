package es.falvad01.unileon.Solitario;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;

public class VentanaPrincipal extends JFrame {

	Toolkit screen;
	private static final int PWIDTH = 550;
	private static final int PHEIGH = 1000;

	private JMenu archivo;
	private JMenu editar;
	private JMenu historial;
	private JMenu ayuda;

	private JMenu nuevo;
	private JMenuItem btnNuevoClasico;
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

	JPanel juegoClasico;
	JPanel juegoSaltos;
	Listeners listen;

	public VentanaPrincipal() {

		screen = Toolkit.getDefaultToolkit();

		// setBounds(panelWith / 2, panelHeight / 2, panelWith, panelHeight);

		setBounds(1024 / 4, 768 / 6, PHEIGH, PWIDTH);

		setTitle("Solitario");

		setResizable(false);

		Image icono = screen.getImage("etc/images/interface/icono.jpg");

		setIconImage(icono);

		initComponents();

	}

	private void initComponents() {

		getContentPane().setLayout(null);

		juegoSaltos = new JPanel();
		PanelSaltos saltos = new PanelSaltos(juegoSaltos);
		juegoSaltos.setBackground(new Color(0, 120, 0));
		juegoSaltos.setBounds(0, 0, PHEIGH, PWIDTH);
		juegoSaltos.setLayout(null);
		juegoSaltos.setVisible(false);
		saltos.initComponets();

		juegoClasico = new JPanel();
		PanelClasico clasico = new PanelClasico(juegoClasico);

		juegoClasico.setBackground(new Color(0, 128, 0));
		juegoClasico.setBounds(0, 0, PHEIGH, PWIDTH);
		juegoClasico.setLayout(null);
		juegoClasico.setVisible(false);
		clasico.initComponents();

		getContentPane().add(juegoClasico);
		getContentPane().add(juegoSaltos);

		listen = new Listeners(this.getContentPane(), juegoClasico, juegoSaltos);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		archivo = new JMenu("Archivo");
		menuBar.add(archivo);

		nuevo = new JMenu("Nuevo");
		archivo.add(nuevo);

		btnNuevoClasico = new JMenuItem("Clasico");
		nuevo.add(btnNuevoClasico);
		btnNuevoClasico.addActionListener(listen);

		btnNuevoSaltos = new JMenuItem("Saltos");
		nuevo.add(btnNuevoSaltos);
		btnNuevoSaltos.addActionListener(listen);

		btnCargar = new JMenuItem("Cargar");
		archivo.add(btnCargar);
		btnCargar.addActionListener(listen);

		btnSalvar = new JMenuItem("Salvar");
		archivo.add(btnSalvar);
		btnSalvar.addActionListener(listen);

		btnSalvarComo = new JMenuItem("Salvar Como");
		archivo.add(btnSalvarComo);
		btnSalvarComo.addActionListener(listen);

		btnSalir = new JMenuItem("Salir");
		archivo.add(btnSalir);
		btnSalir.addActionListener(listen);

		editar = new JMenu("Editar");
		menuBar.add(editar);

		btnDeshacer = new JMenuItem("Deshacer");
		editar.add(btnDeshacer);
		btnDeshacer.addActionListener(listen);

		btnHacer = new JMenuItem("Hacer");
		editar.add(btnHacer);
		btnHacer.addActionListener(listen);

		btnResolver = new JMenuItem("Resolver");
		editar.add(btnResolver);
		btnResolver.addActionListener(listen);

		historial = new JMenu("Historial");
		menuBar.add(historial);

		btnEstadisticas = new JMenuItem("Estadisticas");
		historial.add(btnEstadisticas);
		btnEstadisticas.addActionListener(listen);
		btnEstadisticasFichero = new JMenuItem("Estadisticas FIchero");
		historial.add(btnEstadisticasFichero);
		btnEstadisticasFichero.addActionListener(listen);

		ayuda = new JMenu("Ayuda");
		menuBar.add(ayuda);
		btnInfo = new JMenuItem("Informacion");
		ayuda.add(btnInfo);
		btnInfo.addActionListener(listen);

	}
}