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
	private String motaAukeratua = "GREEN";
	//private JLabel lblAukera;
	
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

	    /*
	    // TEXTO ABAJO
	    lblAukera = new JLabel("Aukeratuta: GREEN", JLabel.CENTER);
	    lblAukera.setForeground(Color.WHITE);

	    has.add(lblAukera, BorderLayout.SOUTH);
		*/
		
	    
	    return has;
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
			JokoKudeatzailea.getEMA().irekiJokoa(motaAukeratua);
			break;
	    case KeyEvent.VK_G:
	        motaAukeratua = "GREEN";
	        //lblAukera.setText("Aukeratuta: GREEN");
	        break;

	    case KeyEvent.VK_B:
	        motaAukeratua = "BLUE";
	        //lblAukera.setText("Aukeratuta: BLUE");
	        break;

	    case KeyEvent.VK_R:
	        motaAukeratua = "RED";
	        //lblAukera.setText("Aukeratuta: RED");
	        break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
