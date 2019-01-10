package es.falvad01.unileon.Solitario;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JPanel;

import jdk.nashorn.internal.ir.JoinPredecessorExpression;

import javax.swing.JOptionPane;
import javax.print.attribute.standard.JobKOctetsProcessed;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;

public class Listeners implements ActionListener {

	File loadGame;
	File saveGame;
	EJuego juego = null;

	Baraja baraja;
	Container panel;

	JLayeredPane juegoClasico;
	JPanel juegoSaltos;

	PanelClasico pClasico;
	PanelSaltos pSaltos;

	public Listeners(Container container, JLayeredPane juegoClasico2, JPanel juegoSaltos) {

		this.panel = container;
		this.juegoClasico = juegoClasico2;
		this.juegoSaltos = juegoSaltos;
		pClasico = new PanelClasico(juegoClasico2, container);
		pSaltos = new PanelSaltos(juegoSaltos);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/////////////////////////////// ARCHIVO/////////////////////////////////////////
		if (e.getActionCommand().equals("Clasico")) {
			juego = EJuego.Clasico;
			juegoClasico.setVisible(true);
			juegoSaltos.setVisible(false);

			pClasico.iniciarJuegoClasico();

		} else if (e.getActionCommand().equals("Saltos")) {
			juego = EJuego.Saltos;
			juegoClasico.setVisible(false);
			juegoSaltos.setVisible(true);
			
			pSaltos.iniciarJuegoSaltos();

		} else if (e.getActionCommand().equals("Cargar")) {

			System.out.println("Cargar archivo");

			JFileChooser select = new JFileChooser();
			select.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			int i = select.showOpenDialog(panel);// Cargamos el archivo

			loadGame = select.getSelectedFile();

			if ((loadGame == null)
					|| !(loadGame.getName().substring(loadGame.getName().lastIndexOf(".") + 1).equals("txt"))) {
				JOptionPane.showMessageDialog(panel, "Tipo de archivo incorrecto", "Tipo de archivo incorrecto",
						JOptionPane.ERROR_MESSAGE);// Comprobamos que la extension del archivo sea la correcta
			} else {
				
				pSaltos.cargarJuego(loadGame.getPath());
				juego = EJuego.Saltos;
				juegoClasico.setVisible(false);
				juegoSaltos.setVisible(true);
			}

		} else if (e.getActionCommand().equals("Salvar")) {

			String ruta;

			if (juego == EJuego.Clasico) {
				ruta = pClasico.getRutaJuego();
				if (ruta == null) {// actua como guardar como
					guardarComo();
				} else if (ruta != null) {
					pClasico.guardar(ruta);

				}

			} else if (juego == EJuego.Saltos) {
				ruta = pSaltos.getRutaJuego();
				if (ruta == null) {// actua como guardar como
					guardarComo();
				} else if (ruta != null) {
					pSaltos.guardar(ruta);

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

		/////////////////////////////// EDITAR//////////////////////////////////////////

		if (e.getActionCommand().equals("Resolver")) {

			if (juego == EJuego.Saltos) {

				if (pSaltos.resolverAuto(1, 0)) {
					JOptionPane.showMessageDialog(panel, "Resuelto");
				}
			} else if (juego == EJuego.Clasico) {

			} else {
				JOptionPane.showMessageDialog(panel, "ERROR, ningun solitario cargado");
			}

		} else if (e.getActionCommand().equals("Deshacer")) {
			if (juego == EJuego.Saltos) {

				pSaltos.deshacer();
			} else if (juego == EJuego.Clasico) {

			} else {
				JOptionPane.showMessageDialog(panel, "ERROR, ningun solitario cargado");
			}
			
		} else if (e.getActionCommand().equals("Hacer")) {
			if (juego == EJuego.Saltos) {

				if(pSaltos.hacer(1,0)){
					
				}
			} else if (juego == EJuego.Clasico) {

			} else {
				JOptionPane.showMessageDialog(panel, "ERROR, ningun solitario cargado");
			}
		}

		/////////////////////////////// HISTORIAL///////////////////////////////////////

		if (e.getActionCommand().equals("Estadisticas")) {

			String fichero = "./Estadisticas/Estadisticas.txt";
			String[] linea = new String[6];

			try {
				FileReader fr = new FileReader(fichero);
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
				System.out.println("Error leyendo fichero " + fichero + ": " + a);
			}

		} else if (e.getActionCommand().equals("Estadisticas Fichero"))

			/////////////////////////////// AYUDA///////////////////////////////////////////

			if (e.getActionCommand().equals("Informacion")) {
				// TODO REVISAR ESTE MENSAJE DE MIERDA
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

						pClasico.guardar(saveGame.getAbsolutePath());

						System.out.println(saveGame.getAbsolutePath());
					}
				} else {

					pClasico.guardar(saveGame.getAbsolutePath());

					System.out.println(saveGame.getAbsolutePath());

				}

			} else if (juego == EJuego.Saltos) {

				if (new File(saveGame.getPath()).exists()) {

					if (JOptionPane.YES_NO_OPTION == JOptionPane.showConfirmDialog(panel,
							"El fichero existe,deseas reemplazarlo?", "Titulo", JOptionPane.YES_NO_OPTION)) {

						pSaltos.guardar(saveGame.getAbsolutePath());

						System.out.println(saveGame.getAbsolutePath());
					}
				} else {
					pSaltos.guardar(saveGame.getAbsolutePath());

					System.out.println(saveGame.getAbsolutePath());
				}
			}
		}

	}// Fin del metodo guardarComo

}
