package es.falvad01.unileon.Solitario;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

import javax.swing.JPanel;

import jdk.nashorn.internal.ir.JoinPredecessorExpression;

import javax.swing.JOptionPane;
import javax.print.attribute.standard.JobKOctetsProcessed;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;

public class Listeners implements ActionListener {

	File loadStadistics;
	File saveGame;
	EJuego juego = null;
	Baraja baraja;
	Container panel;
	JLayeredPane juegoClasico;
	JPanel juegoSaltos;
	private String rutaEstadisticas = "src/estadisticas/Estadisticas.txt";

	public Listeners(Container container) {

		this.panel = container;
		// this.juegoSaltos = juegoSaltos;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/////////////////////////////// ARCHIVO/////////////////////////////////////////
		if (e.getActionCommand().equals("Clasico")) {

			VentanaClasico ventanaClasico = new VentanaClasico();
			ventanaClasico.setVisible(true);

		} else if (e.getActionCommand().equals("Saltos")) {

			VentanaSaltos ventanaSaltos = new VentanaSaltos();

			ventanaSaltos.setVisible(true);

		} else if (e.getActionCommand().equals("Cargar")) {

			System.out.println("Cargar archivo");

			JFileChooser select = new JFileChooser();
			select.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			int i = select.showOpenDialog(panel);// Cargamos el archivo

			loadStadistics = select.getSelectedFile();

			if ((loadStadistics == null) || !(loadStadistics.getName()
					.substring(loadStadistics.getName().lastIndexOf(".") + 1).equals("txt"))) {
				JOptionPane.showMessageDialog(panel, "Tipo de archivo incorrecto", "Tipo de archivo incorrecto",
						JOptionPane.ERROR_MESSAGE);// Comprobamos que la extension del archivo sea la correcta
			} else {

				String[] guardar = new String[42];
				String cadena;
				int hy = 0;

				try {
					FileReader fr = new FileReader(loadStadistics.getPath());
					BufferedReader br = new BufferedReader(fr);
					System.out.println(loadStadistics.getPath());

					while ((cadena = br.readLine()) != null) {
						guardar[hy] = cadena;

						hy++;
					}
					br.close();

				} catch (Exception a) {
					System.out.println("Error leyendo fichero " + loadStadistics.getPath() + ": " + a);
				}

				if (guardar[0].equals("Solitario saltos")) {

					System.out.println("CARGANDO SOLITARIO SALTOS");
					VentanaSaltos ventanaSaltos = new VentanaSaltos();
					ventanaSaltos.cargarJuego(loadStadistics.getPath());

				} else if (guardar[0].equals("Solitario clásico")) {

					System.out.println("CARGANDO SOLITARIO Clasico");

				}
			}

		} else if (e.getActionCommand().equals("Salvar Como")) {

			guardarComo();

		} else if (e.getActionCommand().equals("Salir")) {

			if (JOptionPane.showConfirmDialog(panel, "¿Desea cerrar el programa?", "¿Desea cerrar el programa?",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // Pregunta si esta seguro de que desea
																			// salir
				System.exit(0);
			}
		}

		/////////////////////////////// HISTORIAL///////////////////////////////////////

		if (e.getActionCommand().equals("Estadisticas")) {

			String[] linea = new String[6];

			try {
				FileReader fr = new FileReader(rutaEstadisticas);
				BufferedReader br = new BufferedReader(fr);

				for (int i = 0; i < 6; i++) {

					linea[i] = br.readLine();

				}

				StringBuilder message = new StringBuilder();
				message.append(linea[0]);
				message.append(System.getProperty("line.separator"));
				message.append("---------------------");
				message.append(System.getProperty("line.separator"));
				message.append("Intentos realizados " + linea[1]);
				message.append(System.getProperty("line.separator"));
				message.append("Partidas Ganadas " + linea[2]);
				message.append(System.getProperty("line.separator"));
				message.append(System.getProperty("line.separator"));
				message.append(linea[3]);
				message.append(System.getProperty("line.separator"));
				message.append("---------------------");
				message.append(System.getProperty("line.separator"));
				message.append("Intentos realizados " + linea[4]);
				message.append(System.getProperty("line.separator"));
				message.append("Partidas Ganadas " + linea[5]);
				message.append(System.getProperty("line.separator"));

				JOptionPane.showMessageDialog(panel, message.toString());

				fr.close();
			} catch (Exception a) {
				System.out.println("Error leyendo fichero " + rutaEstadisticas + ": " + a);
			}

		} else if (e.getActionCommand().equals("Estadisticas Fichero")) {

			JFileChooser select = new JFileChooser();
			select.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			int i = select.showOpenDialog(panel);// Cargamos el archivo

			loadStadistics = select.getSelectedFile();

			if ((loadStadistics == null) || !(loadStadistics.getName()
					.substring(loadStadistics.getName().lastIndexOf(".") + 1).equals("txt"))) {
				JOptionPane.showMessageDialog(panel, "Tipo de archivo incorrecto", "Tipo de archivo incorrecto",
						JOptionPane.ERROR_MESSAGE);// Comprobamos que la extension del archivo sea la correcta
			} else {

				try {
					FileReader fr = new FileReader(loadStadistics.getPath());
					BufferedReader br = new BufferedReader(fr);
					System.out.println(loadStadistics.getPath());

					VentanaSaltos.rutaStadisticas(loadStadistics.getPath());
					rutaEstadisticas = loadStadistics.getPath();

				} catch (Exception a) {
					System.out.println("Error leyendo fichero " + loadStadistics.getPath() + ": " + a);
				}
			}

			/////////////////////////////// AYUDA///////////////////////////////////////////

		} else if (e.getActionCommand().equals("Informacion")) {
			System.out.println("hola");
			JOptionPane.showMessageDialog(panel,
					"APLICACION DESARROLADA POR EL ALUMNO FRANCISCO JAVIER ALVAREZ, PARA LA ASIGNATURA PROGRAMACION II, \n "
							+ "LA CUAL CONSISTE EN LA PROGRAMACION DE UN JUEGO DE CARTAS LLAMADO SOLITARIO EN DOS DE SUS VARIANTES, MODO CLASICO Y MODO SALTOS \n"
							+ "EL JUEGO DISPONE DE LA FUNCION DE CREAR CUALQUIERA DE LOS DOS JUEGOS Y DE GUARDARLOS, A SU VEZ TAMBIEN CUETA CON UN SITEMA SENCILLO DE ESTADISTICAS POR PARTIDA Y EN GLOBAL");

		}

	}// Fin del metodo listener

	private void guardarComo() {

		System.out.println("LISTENRS GUARDAR COMO");
		JFileChooser save = new JFileChooser();

		if (save.showSaveDialog(null) == save.APPROVE_OPTION) {
			saveGame = save.getSelectedFile();
			if (juego == EJuego.Clasico) {

				if (new File(saveGame.getPath()).exists()) {

					if (JOptionPane.YES_NO_OPTION == JOptionPane.showConfirmDialog(panel,
							"El fichero existe,deseas reemplazarlo?", "Titulo", JOptionPane.YES_NO_OPTION)) {

						// pClasico.guardar(saveGame.getAbsolutePath());

						System.out.println(saveGame.getAbsolutePath());
					}
				} else {

					// pClasico.guardar(saveGame.getAbsolutePath());

					System.out.println(saveGame.getAbsolutePath());

				}

			} else if (juego == EJuego.Saltos) {

				if (new File(saveGame.getPath()).exists()) {

					if (JOptionPane.YES_NO_OPTION == JOptionPane.showConfirmDialog(panel,
							"El fichero existe,deseas reemplazarlo?", "Titulo", JOptionPane.YES_NO_OPTION)) {

						// pSaltos.guardar(saveGame.getAbsolutePath());

						System.out.println(saveGame.getAbsolutePath());
					}
				} else {
					// pSaltos.guardar(saveGame.getAbsolutePath());

					System.out.println(saveGame.getAbsolutePath());
				}
			}
		}

	}// Fin del metodo guardarComo

}
