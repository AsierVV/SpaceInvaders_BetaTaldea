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

public class PuntuazioSistemaPantaila extends JPanel implements Observer, KeyListener{

	private JFrame punFrame = new JFrame("Space Invaders - Puntuazio Sistema");
	private static PuntuazioSistemaPantaila nireEMA = null;
	
	private PuntuazioSistemaPantaila() {
		setBackground(new Color(0, 0, 0));
		add(getPuntuazioSistema());
		
		punFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		punFrame.setContentPane(this);
		punFrame.pack();
		punFrame.setLocationRelativeTo(null);
		punFrame.setVisible(true);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        
	    JokoKudeatzailea.getEMA().addObserver(this);

	}
	
	public static PuntuazioSistemaPantaila getEMA() {
		if (nireEMA == null) {
			nireEMA = new PuntuazioSistemaPantaila();
		}
		return nireEMA;
	}
	
	public JPanel getPuntuazioSistema() {
	    JPanel konI = new JPanel();
	    konI.setLayout(new BorderLayout());
	    konI.setBackground(Color.BLACK);

	    ImageIcon puntuazioa = new ImageIcon(getClass().getResource("puntuazioSistema.png"));
	    JLabel lblPuntuazio = new JLabel(puntuazioa);
	    lblPuntuazio.setHorizontalAlignment(JLabel.CENTER);

	    konI.add(lblPuntuazio, BorderLayout.CENTER);
	    return konI;
	}
	
	public void erakutsi() {
	    punFrame.setVisible(true);
	    this.requestFocusInWindow();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_P:
			punFrame.setVisible(false);
			HasierakoPantaila.getEMA().erakutsi();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void update(Observable o, Object arg) {}
	
}
