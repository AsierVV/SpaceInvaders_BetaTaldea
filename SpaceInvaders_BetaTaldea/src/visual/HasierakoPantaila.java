package visual;

import javax.swing.*;

import modeloa.JokoKudeatzailea;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class HasierakoPantaila extends JPanel implements Observer, KeyListener{
	
	private JFrame hasiFrame = new JFrame("Space Invaders - Hasiera");
	private static HasierakoPantaila nireEMA = null;
	private String motaHegazkina = "GREEN";
	private String motaEtsaia = "RED";
	private JLabel lblAukera;
	
	private HasierakoPantaila() {
		setBackground(new Color(0, 0, 0));
		add(getHasiera());
		
        hasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hasiFrame.setContentPane(this);
        hasiFrame.pack();
        hasiFrame.setLocationRelativeTo(null);
        hasiFrame.setVisible(true);
        
        this.setFocusable(true);
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
	    has.setLayout(new BorderLayout());
	    has.setBackground(Color.BLACK);

	    ImageIcon hasiera = new ImageIcon(getClass().getResource("hasiera.png"));
	    JLabel lblAlien = new JLabel(hasiera);
	    lblAlien.setHorizontalAlignment(JLabel.CENTER);

	    has.add(lblAlien, BorderLayout.CENTER);

	    
	    // TEXTO ABAJO
	    lblAukera = new JLabel("", JLabel.CENTER);
	    eguneratuTestua();
	    lblAukera.setForeground(Color.WHITE);

	    has.add(lblAukera, BorderLayout.SOUTH);
		
		
	    
	    return has;
	}
	private void eguneratuTestua() {
	    lblAukera.setText("Hegazkina: " + motaHegazkina + " | Etsaia: " + motaEtsaia);
	}

	@Override
	public void update(Observable o, Object arg) {
		if ("JOKOA_HASI".equals(arg)) {
			hasiFrame.dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
	    switch (e.getKeyCode()) {
	    case KeyEvent.VK_SPACE:
	        JokoKudeatzailea.getEMA().irekiJokoa(motaHegazkina, motaEtsaia);
	        break;

	    case KeyEvent.VK_G:
	        motaHegazkina = "GREEN";
	        eguneratuTestua();
	        break;

	    case KeyEvent.VK_B:
	        motaHegazkina = "BLUE";
	        eguneratuTestua();
	        break;

	    case KeyEvent.VK_R:
	        motaHegazkina = "RED";
	        eguneratuTestua();
	        break;

	    case KeyEvent.VK_E:
	        motaEtsaia = "RED";
	        eguneratuTestua();
	        break;

	    case KeyEvent.VK_P:
	        motaEtsaia = "PURPLE";
	        eguneratuTestua();
	        break;

	    case KeyEvent.VK_C:
	        motaEtsaia = "CYAN";
	        eguneratuTestua();
	        break;
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
