package visual;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PausePantaila extends JPanel {
	
	private JFrame framePause;
	
	public PausePantaila() {
		framePause = new JFrame("PAUSE");
		framePause.setBackground(new Color(0, 0, 0));
		framePause.setVisible(true);
	}
}