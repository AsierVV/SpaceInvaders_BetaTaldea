package visual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class IrabaziPantaila extends JPanel{
	private JPanel panel; 
	private JLabel lblNewLabel;
	public IrabaziPantaila() {
		setLayout(new BorderLayout(0, 0));
		add(getPanel());
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(getLblNewLabel());
		}
		return panel;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("IRABAZI DUZU!");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 28));
		}
		return lblNewLabel;
	}
}