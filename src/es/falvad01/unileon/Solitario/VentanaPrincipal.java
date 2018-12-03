package es.falvad01.unileon.Solitario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

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

	/* Botones principales */

	private JButton btnArchivo;
	private JButton btnEditar;
	private JButton btnHistorial;
	private JButton btnAyuda;
	private JLabel lblEditar;
	private JLabel lblHistorial;
	private JLabel lblAyuda;
	private JLabel lblAyuda_1;

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

		ayuda = new JPanel();
		ayuda.setBackground(Color.MAGENTA);
		ayuda.setBounds(10, 142, 614, 330);
		getContentPane().add(ayuda);
		ayuda.setLayout(null);
		
		

		historial = new JPanel();
		historial.setBackground(Color.CYAN);
		historial.setBounds(10, 142, 614, 330);
		getContentPane().add(historial);
		historial.setLayout(null);

		editar = new JPanel();
		editar.setBackground(Color.BLUE);
		editar.setBounds(10, 142, 614, 330);
		getContentPane().add(editar);
		editar.setLayout(null);

		/////////////////////////// PANELES/////////////////////////////////////////
		archivo = new JPanel();
		archivo.setBackground(Color.YELLOW);
		archivo.setBounds(10, 142, 614, 330);
		getContentPane().add(archivo);
		archivo.setLayout(null);
		archivo.setVisible(true);

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
		btnHistorial.addActionListener(this);

		btnAyuda = new JButton("Ayuda");
		btnAyuda.setBounds(496, 32, buttonW, buttonH);
		getContentPane().add(btnAyuda);
		btnAyuda.addActionListener(this);
		
		
		
		initArchivoComponents(); // Llamamos a los componentes de cada panel
		initEditarComponents();
		initHistorialComponenets();
		initAyudaComponents();

	}

	private void initArchivoComponents() {

		JLabel lblArchivo = new JLabel("ARCHIVO");
		lblArchivo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblArchivo.setBounds(10, 264, 165, 66);
		archivo.add(lblArchivo);
		

	}

	private void initEditarComponents() {

		lblEditar = new JLabel("EDITAR");
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEditar.setBounds(10, 194, 225, 114);
		editar.add(lblEditar);
		editar.setVisible(false);

	}

	private void initHistorialComponenets() {
		lblHistorial = new JLabel("HISTORIAL");
		lblHistorial.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHistorial.setBounds(20, 231, 171, 88);
		historial.add(lblHistorial);

	}

	private void initAyudaComponents() {
		lblAyuda_1 = new JLabel("AYUDA");
		lblAyuda_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAyuda_1.setBounds(10, 200, 200, 130);
		ayuda.add(lblAyuda_1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnArchivo) {
			archivo.setVisible(true);
			editar.setVisible(false);
			historial.setVisible(false);
			ayuda.setVisible(false);

		} else if (e.getSource() == btnEditar) {
			archivo.setVisible(false);
			editar.setVisible(true);
			historial.setVisible(false);
			ayuda.setVisible(false);

		}else if(e.getSource() == btnHistorial) {
			archivo.setVisible(false);
			editar.setVisible(false);
			historial.setVisible(true);
			ayuda.setVisible(false);

		}else if(e.getSource() == btnAyuda) {
			archivo.setVisible(false);
			editar.setVisible(false);
			historial.setVisible(false);
			ayuda.setVisible(true);

		}

	}
}
