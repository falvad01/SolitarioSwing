package es.falvad01.unileon.Solitario;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VentanaClasico extends JFrame implements ActionListener {
	
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

	
	JLayeredPane juegoClasico;

	PanelClasico clasico;

	public VentanaClasico()  {

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

		juegoClasico = new JLayeredPane();
		clasico = new PanelClasico(juegoClasico, getContentPane());

		juegoClasico.setBackground(new Color(0, 120, 0));
		juegoClasico.setBounds(0, 0, PHEIGH, PWIDTH);
		juegoClasico.setVisible(false);
		juegoClasico.setLayout(null);

		getContentPane().add(juegoClasico);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		archivo = new JMenu("Archivo");
		menuBar.add(archivo);

		nuevo = new JMenu("Nuevo");
		archivo.add(nuevo);

		btnNuevoSaltos = new JMenuItem("Clasico");
		nuevo.add(btnNuevoSaltos);
		btnNuevoSaltos.addActionListener(this);

		btnCargar = new JMenuItem("Cargar");
		archivo.add(btnCargar);
		btnCargar.addActionListener(this);

		btnSalvar = new JMenuItem("Salvar");
		archivo.add(btnSalvar);
		btnSalvar.addActionListener(this);

		btnSalvarComo = new JMenuItem("Salvar Como");
		archivo.add(btnSalvarComo);
		btnSalvarComo.addActionListener(this);

		btnSalir = new JMenuItem("Salir");
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

		historial = new JMenu("Historial");
		menuBar.add(historial);

		btnEstadisticas = new JMenuItem("Estadisticas");
		historial.add(btnEstadisticas);
		btnEstadisticas.addActionListener(this);
		btnEstadisticasFichero = new JMenuItem("Estadisticas Fichero");
		historial.add(btnEstadisticasFichero);
		btnEstadisticasFichero.addActionListener(this);

		ayuda = new JMenu("Ayuda");
		menuBar.add(ayuda);
		btnInfo = new JMenuItem("Informacion");
		ayuda.add(btnInfo);
		btnInfo.addActionListener(this);

	}

	private void initPanel() {

		
		juegoClasico.setVisible(true);
		clasico.iniciarJuegoClasico();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Clasico")) {
			clasico.iniciarJuegoClasico();

		}

	}

}
