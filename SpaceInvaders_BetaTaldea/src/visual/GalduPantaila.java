package visual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class GalduPantaila extends JPanel{
	private JFrame bukFrame = new JFrame("Space Invaders - Game Over");

	public GalduPantaila() {
		setBackground(new Color(0, 0, 0));
		add(getBukaera());
		
        bukFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bukFrame.setContentPane(this);
        bukFrame.pack();
        bukFrame.setLocationRelativeTo(null);
        bukFrame.setVisible(true);
        
        bukFrame.addWindowListener(new WindowAdapter(){
        	@Override
        	public void windowOpened(WindowEvent e) {
        		audioaJarri();
        	}
        });
        
        this.setFocusable(true);
        this.requestFocusInWindow();
	}
	
	public JPanel getBukaera() {
	    JPanel bukG = new JPanel();
	    bukG.setLayout(new BorderLayout());
	    bukG.setBackground(Color.BLACK);

	    ImageIcon hasiera = new ImageIcon(getClass().getResource("bukaeraGalduta.png"));
	    JLabel lblAlien = new JLabel(hasiera);
	    lblAlien.setHorizontalAlignment(JLabel.CENTER);

	    bukG.add(lblAlien, BorderLayout.CENTER);
	    return bukG;
	}
	
	private void audioaJarri() {
		AudioInputStream audioa;
		try {
			audioa = AudioSystem.getAudioInputStream(getClass().getResource("galduAudio.wav"));
			Clip c = AudioSystem.getClip();
			c.open(audioa);
			c.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
