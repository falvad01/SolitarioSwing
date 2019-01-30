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
	
	private JMenuItem btnSalvar;
	private JMenuItem btnSalvarComo;
	private JMenuItem btnSalir;

	private JMenuItem btnDeshacer;
	private JMenuItem btnHacer;
	private JMenuItem btnResolver;



	
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

		btnSalvar = new JMenuItem("Salvar");
		archivo.add(btnSalvar);
		btnSalvar.addActionListener(this);

		btnSalvarComo = new JMenuItem("Salvar Como");
		archivo.add(btnSalvarComo);
		btnSalvarComo.addActionListener(this);

		btnSalir = new JMenuItem("Salir de clasico");
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

		
		juegoClasico.setVisible(true);
		clasico.iniciarJuegoClasico();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Salir de clasico")) {
			dispose();

		}

	}

}
