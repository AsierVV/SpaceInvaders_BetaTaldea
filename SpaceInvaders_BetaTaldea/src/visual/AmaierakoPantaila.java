package visual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class AmaierakoPantaila extends JPanel{
	private JPanel panel; 
	private JTextField txtGalduDuzu;
	public AmaierakoPantaila() {
		setLayout(new BorderLayout(0, 0));
		add(getPanel());
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(getTxtGalduDuzu());
		}
		return panel;
	}
	private JTextField getTxtGalduDuzu() {
		if (txtGalduDuzu == null) {
			txtGalduDuzu = new JTextField();
			txtGalduDuzu.setText("GALDU DUZU!");
			txtGalduDuzu.setColumns(10);
		}
		return txtGalduDuzu;
	}
}
