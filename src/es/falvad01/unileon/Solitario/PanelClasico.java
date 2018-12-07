package es.falvad01.unileon.Solitario;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelClasico extends JPanel {

	private JPanel clasico;
	
	Baraja baraja;
	private Carta[] ABaraja;

	public PanelClasico(JPanel panel) {

		this.clasico = panel;
		
		
		baraja = new Baraja(EJuego.Clasico);
		baraja.barajarF();
		ABaraja = baraja.getBaraja();
		
		for(int i = 0; i < ABaraja.length; i++) {
			System.out.print(" " + ABaraja[i]);
		}

	}

	public void initComponents() {

		JLabel lblClasico = new JLabel("Clasico");
		lblClasico.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblClasico.setBounds(10, 439, 93, 61);
		clasico.add(lblClasico);

	}
	
	public void obtenerBaraja() {
		
		
	}

}
