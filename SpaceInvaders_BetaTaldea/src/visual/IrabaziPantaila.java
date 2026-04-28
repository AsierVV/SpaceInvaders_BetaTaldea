package visual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class IrabaziPantaila extends JPanel{

	public IrabaziPantaila(JFrame bukFrame) {
		setBackground(new Color(0, 0, 0));
		add(getBukaera());
		
        bukFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bukFrame.setContentPane(this);
        bukFrame.pack();
        bukFrame.setLocationRelativeTo(null);
        bukFrame.setVisible(true);
        
        audioaJarri();
        
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
	
	private void audioaJarri() {
		AudioInputStream audioa;
		try {
			audioa = AudioSystem.getAudioInputStream(getClass().getResource("irabazi.wav"));
			Clip c = AudioSystem.getClip();
			c.open(audioa);
			c.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}