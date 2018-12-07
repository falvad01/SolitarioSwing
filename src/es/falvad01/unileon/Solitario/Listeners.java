package es.falvad01.unileon.Solitario;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

public class Listeners implements ActionListener {

	File loadGame;
	File saveGame;

	Baraja baraja;
	Container panel;

	JPanel juegoClasico;
	JPanel juegoSaltos;

	public Listeners(Container container, JPanel juegoClasico, JPanel juegoSaltos) {

		this.panel = container;
		this.juegoClasico = juegoClasico;
		this.juegoSaltos = juegoSaltos;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/////////////////////////////// ARCHIVO/////////////////////////////////////////
		if (e.getActionCommand().equals("Clasico")) {

			juegoClasico.setVisible(true);
			juegoSaltos.setVisible(false);

			baraja = new Baraja(EJuego.Clasico);
			System.out.println(baraja.toString());
			baraja.barajarF();
			System.out.println(baraja.toString());

		} else if (e.getActionCommand().equals("Saltos")) {

			juegoClasico.setVisible(false);
			juegoSaltos.setVisible(true);

			baraja = new Baraja(EJuego.Saltos);
			System.out.println(baraja.toString());
			baraja.barajarE();
			System.out.println(baraja.toString());

		} else if (e.getActionCommand().equals("Cargar")) {

			System.out.println("Cargar archivo");

			JFileChooser select = new JFileChooser();
			select.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			int i = select.showSaveDialog(panel);// Cargamos el archivo

			loadGame = select.getSelectedFile();

			if ((loadGame == null)
					|| !(loadGame.getName().substring(loadGame.getName().lastIndexOf(".") + 1).equals("txt"))) {
				JOptionPane.showMessageDialog(panel, "Tipo de archivo incorrecto", "Tipo de archivo incorrecto",
						JOptionPane.ERROR_MESSAGE);// Comprobamos que la extension del archivo sea la correcta
			} else {
				System.out.println(loadGame.getPath());
				// TODO DESDE AQUI MANDAMOS LA RUTA DEL ARCHIVO PARA CARGARLO EN EL JUEGO
			}

			// System.out.println(loadGame.getPath());

		} else if (e.getActionCommand().equals("Salvar")) {

			// TODO AQUI DEBERIA LLEGAR LA RUTA DEL ULTIMO ARCHIVO JUGADO

		} else if (e.getActionCommand().equals("Salvar Como")) {
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
		} else if (e.getActionCommand().equals("Salir")) {

			if (JOptionPane.showConfirmDialog(panel, "¿Desea cerrar el programa?", "¿Desea cerrar el programa?",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // Pregunta si esta seguro de que desea
																			// salir
				System.exit(0);
			}
		}

		/////////////////////////////// EDITAR/////////////////////////////////////////

		/////////////////////////////// HISTORIAL/////////////////////////////////////////

		/////////////////////////////// AYUDA/////////////////////////////////////////

		if (e.getActionCommand().equals("Informacion")) {
			// TODO REVISAR ESTE MENSAJE DE MIERDA
			JOptionPane.showMessageDialog(panel,
					"APLICACION DESARROLADA POR EL ALUMNO FRANCISCO JAVIER ALVAREZ, PARA LA ASIGNATURA PROGRAMACION II, \n "
							+ "LA CUAL CONSISTE EN LA PROGRAMACION DE UN JUEGO DE CARTAS LLAMADO SOLITARIO EN DOS DE SUS VARIANTES, MODO CLASICO Y MODO SALTOS \n"
							+ "EL JUEGO DISPONE DE LA FUNCION DE CREAR CUALQUIERA DE LOS DOS JUEGOS Y DE GUARDARLOS, A SU VEZ TAMBIEN CUETA CON UN SITEMA SENCILLO DE ESTADISTICAS POR PARTIDA Y EN GLOBAL");

		}

	}

}
