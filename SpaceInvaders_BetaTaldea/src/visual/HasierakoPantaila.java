package visual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HasierakoPantaila extends JPanel{
	
	private JPanel panel;
	private JLabel HASIERA;
	private JButton HASTEKO_BOTOIA;
	public HasierakoPantaila() {
		setLayout(new BorderLayout(0, 0));
		add(getPanel(), BorderLayout.SOUTH);
		add(getHASIERA(), BorderLayout.CENTER);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.add(getHASTEKO_BOTOIA());
		}
		return panel;
	}
	private JLabel getHASIERA() {
		if (HASIERA == null) {
			HASIERA = new JLabel("JOKO HASIERA");
		}
		return HASIERA;
	}
	private JButton getHASTEKO_BOTOIA() {
		if (HASTEKO_BOTOIA == null) {
			HASTEKO_BOTOIA = new JButton("HASI");
			HASTEKO_BOTOIA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return HASTEKO_BOTOIA;
	}
}