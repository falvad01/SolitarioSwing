package es.falvad01.unileon.Solitario;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelClasico extends JPanel {

	private JPanel clasico;
	private int PWIDTH;
	private int PHEIGH;

	public PanelClasico(JPanel panel, int h, int w) {

		this.clasico = panel;
		this.PHEIGH = h;
		this.PWIDTH = w;

		
	}

	public void initComponents() {

		JLabel lblClasico = new JLabel("Clasico");
		lblClasico.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblClasico.setBounds(10, 439, 93, 61);
		clasico.add(lblClasico);

	}

}
