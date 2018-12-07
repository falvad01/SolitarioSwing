package es.falvad01.unileon.Solitario;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSaltos extends JPanel {

	private JPanel saltos;
	private int PWIDTH;
	private int PHEIGH;

	public PanelSaltos(JPanel panel, int h, int w) {

		this.saltos = panel;
		this.PHEIGH = h;
		this.PWIDTH = w;

	}
	
	public void initComponets() {
		
		JLabel lblSaltos = new JLabel("Saltos");
		lblSaltos.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSaltos.setBounds(0, 417, 173, 80);
		saltos.add(lblSaltos);
	}

}
