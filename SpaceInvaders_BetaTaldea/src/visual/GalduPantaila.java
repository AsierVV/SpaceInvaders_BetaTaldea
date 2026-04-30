package visual;

import javax.swing.JPanel;

import modeloa.JokoKudeatzailea;

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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class GalduPantaila extends JPanel implements Observer, KeyListener{

	private JFrame bukFrame = new JFrame("Game Over!");
	private static GalduPantaila nireEMA = null;
	
	
	//public GalduPantaila(JFrame pBukFrame) {
	public GalduPantaila() {
	
		//bukFrame = pBukFrame;
		
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
        this.addKeyListener(this);
        
	    JokoKudeatzailea.getEMA().addObserver(this);
	}
	
	public static GalduPantaila getEMA() {
		if (nireEMA == null) {
			nireEMA = new GalduPantaila();
		}
		return nireEMA;
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

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			JokoKudeatzailea.getEMA().jokoaReset();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void update(Observable o, Object arg) {
		if ("RESET".equals(arg)) {
			JokoKudeatzailea.getEMA().deleteObserver(this);
			bukFrame.dispose();
			nireEMA = null;
		}
	}
	
	private void audioaJarri() {
		AudioInputStream audioa;
		try {
			audioa = AudioSystem.getAudioInputStream(getClass().getResource("galdu.wav"));
			Clip c = AudioSystem.getClip();
			c.open(audioa);
			c.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
