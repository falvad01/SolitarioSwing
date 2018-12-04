package es.falvad01.unileon.Solitario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;

public class VentanaPrincipal extends JFrame implements ActionListener {

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
	
	
	JComboBox comboBox;
	String gameSelected;
	private JButton btnNewButton_4;
	
	

	public VentanaPrincipal() {

		screen = Toolkit.getDefaultToolkit();
		Dimension screenSize = screen.getScreenSize();
		screenHeight = screenSize.height;
		screenWith = screenSize.width;

		// setBounds(panelWith / 2, panelHeight / 2, panelWith, panelHeight);

		setBounds(1024 / 4, 768 / 4, 650, 550);

		setTitle("Solitario");

		setResizable(false);

		Image icono = screen.getImage("etc/images/interface/icono.jpg");

		setIconImage(icono);

		initComponents();

	}

	private void initComponents() {

		getContentPane().setLayout(null);
		Image backGround = screen.getImage("etc/images/interface(backGround.jpg");
		
		archivo = new JPanel();
		archivo.setBackground(Color.YELLOW);
		archivo.setBounds(10, 0, 624, 510);
		getContentPane().add(archivo);
		archivo.setLayout(null);
		archivo.setVisible(false);
		
				
				
				

		

		

		/////////////////////////// PANELES/////////////////////////////////////////

		menu = new JPanel();
		menu.setBackground(null);
		menu.setBounds(10, 0, 624, 510);
		getContentPane().add(menu);
		menu.setLayout(null);
		menu.setVisible(true);

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



		initMenuComponents();
		initArchivoComponents(); // Llamamos a los componentes de cada panel
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
		comboBox.addItem("Saltos");
		comboBox.addItem("Clasico");
		
		JButton btnNewButton = new JButton("Nuevo");
		btnNewButton.setBounds(214, 123, 157, 23);
		archivo.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cargar");
		btnNewButton_1.setBounds(214, 181, 157, 23);
		archivo.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Salvar");
		btnNewButton_2.setBounds(214, 243, 157, 23);
		archivo.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Salvar como");
		btnNewButton_3.setBounds(214, 318, 157, 23);
		archivo.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Salir");
		btnNewButton_4.setBounds(200, 409, 171, 23);
		archivo.add(btnNewButton_4);
		comboBox.addActionListener(this);
		

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
		
		
		if(e.getSource() == comboBox) {
			
			gameSelected = (String) comboBox.getSelectedItem();
			
		}

	}
}
