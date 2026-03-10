package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HasierakoPantaila extends JPanel{
	
	private JPanel pnlTitulua;
	private JButton btnHasi;
	private JPanel panel;
	
	public HasierakoPantaila() {
		setBackground(new Color(0, 0, 0));
		add(getHasiera());
	}
	
	private JPanel getHasiera() {
	    JPanel has = new JPanel();
	    has.setBackground(new Color(0, 0, 0));

	    ImageIcon hasiera = new ImageIcon(getClass().getResource("hasiera.png"));
	    JLabel lblAlien = new JLabel(hasiera);

	    has.add(lblAlien);
	    return has;
	}
	
	

}
