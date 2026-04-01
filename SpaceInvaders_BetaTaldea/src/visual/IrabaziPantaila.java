package visual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class IrabaziPantaila extends JPanel{
	private JFrame bukFrame = new JFrame("Space Invaders - You Won");

	public IrabaziPantaila() {
		setBackground(new Color(0, 0, 0));
		add(getBukaera());
		
        bukFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bukFrame.setContentPane(this);
        bukFrame.pack();
        bukFrame.setLocationRelativeTo(null);
        bukFrame.setVisible(true);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
	}
	
	public JPanel getBukaera() {
	    JPanel bukI = new JPanel();
	    bukI.setLayout(new BorderLayout());
	    bukI.setBackground(Color.BLACK);

	    ImageIcon hasiera = new ImageIcon(getClass().getResource("bukaeraIrabazita.png"));
	    JLabel lblAlien = new JLabel(hasiera);
	    lblAlien.setHorizontalAlignment(JLabel.CENTER);

	    bukI.add(lblAlien, BorderLayout.CENTER);
	    return bukI;
	}
}