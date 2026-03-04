package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HasierakoPantaila extends JPanel{
	
	private JLabel lblTitulua;
	private JButton btnHasi;
	
	public HasierakoPantaila() {
		setLayout(new BorderLayout());
		
		add(getLblTitulua(), BorderLayout.CENTER);
		
		JPanel behekoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		behekoPanel.add(getBtnHasi());
		add(behekoPanel, BorderLayout.SOUTH);
	}

	private JLabel getLblTitulua() {
		if (lblTitulua == null) {
			lblTitulua = new JLabel("SPACE INVADERS");
			lblTitulua.setFont(new Font("Arial", Font.BOLD, 28));
		}
		return lblTitulua;
	}
	
	private JButton getBtnHasi() {
		if (btnHasi == null) {
			btnHasi = new JButton("JOKOA HASI");
	        btnHasi.setFont(new Font("Arial", Font.BOLD, 18));
		}
		return btnHasi;
	}
	
	public void addHasiListener(ActionListener listener) {
	    getBtnHasi().addActionListener(listener);
	}
}