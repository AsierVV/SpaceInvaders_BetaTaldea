package visual;

import javax.swing.JPanel;

import modeloa.JokoKudeatzailea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Kontrolak extends JPanel implements Observer, KeyListener{
	
	private JFrame konFrame = new JFrame("Space Invaders - Kontrolak");
	private static Kontrolak nireEMA = null;
	
	private Kontrolak() {
		setBackground(new Color(0, 0, 0));
		add(getKontrolak());
		
		konFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		konFrame.setContentPane(this);
		konFrame.pack();
		konFrame.setLocationRelativeTo(null);
		konFrame.setVisible(true);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        
	    JokoKudeatzailea.getEMA().addObserver(this);

	}
	
	public static Kontrolak getEMA() {
		if (nireEMA == null) {
			nireEMA = new Kontrolak();
		}
		return nireEMA;
	}
	
	public JPanel getKontrolak() {
	    JPanel konI = new JPanel();
	    konI.setLayout(new BorderLayout());
	    konI.setBackground(Color.BLACK);

	    ImageIcon hasiera = new ImageIcon(getClass().getResource("kontrolak.png"));
	    JLabel lblAlien = new JLabel(hasiera);
	    lblAlien.setHorizontalAlignment(JLabel.CENTER);

	    konI.add(lblAlien, BorderLayout.CENTER);
	    return konI;
	}
	
	public void erakutsi() {
	    konFrame.setVisible(true);
	    this.requestFocusInWindow();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_I:
			konFrame.setVisible(false);
			HasierakoPantaila.getEMA().erakutsi();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}