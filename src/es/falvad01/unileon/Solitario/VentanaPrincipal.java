package es.falvad01.unileon.Solitario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private int screenHeight;
	private int screenWith;
	private int panelHeight;
	private int panelWith;

	private int buttonW = 128;
	private int buttonH = 62;
	private JPanel archivo;
	private JPanel editar;
	private JPanel historial;
	private JPanel ayuda;

	/*Botones principales*/
	
	private JButton btnArchivo;
	private JButton btnEditar;
	private JButton btnHistorial;
	private JButton btnAyuda;
	
	JButton btnNewButton;
	JButton btnNewButton_1;
	private JPanel panel;
	

	public VentanaPrincipal() {

		Toolkit screen = Toolkit.getDefaultToolkit();
		Dimension screenSize = screen.getScreenSize();
		screenHeight = screenSize.height;
		screenWith = screenSize.width;
		panelWith = screenWith / 2;
		panelHeight = screenHeight / 2;

		setBounds(panelWith / 2, panelHeight / 2, panelWith, panelHeight);

		setTitle("Solitario");

		setResizable(false);

		initComponents();

	}

	private void initComponents() {

		getContentPane().setLayout(null);

///////////////////////////PANELES/////////////////////////////////////////
		archivo = new JPanel();
		archivo.setBackground(Color.YELLOW);
		archivo.setBounds(10, 142, 614, 330);
		getContentPane().add(archivo);
		archivo.setLayout(null);
		archivo.setVisible(true);

		editar = new JPanel();
		editar.setBackground(Color.BLUE);
		editar.setBounds(10, 142, 614, 330);
		getContentPane().add(editar);
		editar.setLayout(null);
		editar.setVisible(false);
		
		ayuda = new JPanel();
		ayuda.setBounds(281, 105, 10, 10);
		getContentPane().add(ayuda);
		ayuda.setLayout(null);
		
		
///////////////////////////BOTONES/////////////////////////////////////////
		
		btnArchivo = new JButton("Archivo");
		btnArchivo.setBounds(10, 32, buttonW, buttonH);
		getContentPane().add(btnArchivo);
		btnArchivo.addActionListener(this);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(163, 32, buttonW, buttonH);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(this);
		
		
		
		btnHistorial = new JButton("Historial");
		btnHistorial.setBounds(336, 32, buttonW, buttonH);
		getContentPane().add(btnHistorial);

		btnAyuda = new JButton("Ayuda");
		btnAyuda.setBounds(496, 32, buttonW, buttonH);
		getContentPane().add(btnAyuda);
		
		panel = new JPanel();
		panel.setBounds(206, 108, 10, 10);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		

		initArchivoComponents(); // Llamamos a los componentes de cada panel
		initEditarComponents();

	}

	private void initArchivoComponents() {

		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 11, buttonW, buttonH);
		archivo.add(btnNewButton);

	}

	private void initEditarComponents() {

		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(46, 41, buttonW, buttonH);
		editar.add(btnNewButton_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnArchivo) {
			archivo.setVisible(true);
			editar.setVisible(false);

		} else if (e.getSource() == btnEditar) {
			archivo.setVisible(false);
			editar.setVisible(true);
		}

	}

}
