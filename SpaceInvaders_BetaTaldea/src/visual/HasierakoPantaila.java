package visual;

import javax.swing.*;

import controller.TeklatuKontroladorea;
import modeloa.JokoKudeatzailea;
import modeloa.Tableroa;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class HasierakoPantaila extends JPanel implements Observer, KeyListener{
	
	private JPanel pnlTitulua;
	private JButton btnHasi;
	private JPanel panel;
	private JFrame hasiFrame = new JFrame("Space Invaders - Hasiera");
	private static HasierakoPantaila nireEMA = null;
	
	private HasierakoPantaila() {
		setBackground(new Color(0, 0, 0));
		add(getHasiera());
        hasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hasiFrame.setContentPane(this);
        hasiFrame.pack();
        hasiFrame.setLocationRelativeTo(null);
        hasiFrame.setVisible(true);
        hasiFrame.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        JokoKudeatzailea.getEMA().addObserver(this);
	}
	
	public static HasierakoPantaila getEMA() {
		if (nireEMA == null) {
			nireEMA = new HasierakoPantaila();
		}
		return nireEMA;
	}
	
	private JPanel getHasiera() {
	    JPanel has = new JPanel();
	    has.setBackground(new Color(0, 0, 0));

	    ImageIcon hasiera = new ImageIcon(getClass().getResource("hasiera.png"));
	    JLabel lblAlien = new JLabel(hasiera);

	    has.add(lblAlien);
	    return has;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		hasiFrame.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			JokoKudeatzailea.getEMA().irekiJokoa();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
