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
	private String maila = "Erraza";
	private String jokalariIzena = "";
	
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

	    
	    // Azpiko testua
	    lblAukera = new JLabel("", JLabel.CENTER);
	    eguneratuTestua();
	    lblAukera.setForeground(Color.WHITE);

	    has.add(lblAukera, BorderLayout.SOUTH);
		
	    return has;
	}
	
	private void eguneratuTestua() {
	    lblAukera.setText("Hegazkina: " + motaHegazkina + " | Etsaia: " + motaEtsaia + " | Maila: " + maila);
	}
	
	public void setJokalariIzena(String pIzena) {
		jokalariIzena = pIzena;
	}

	@Override
	public void update(Observable o, Object arg) {
		if ("JOKOA_HASI".equals(arg)) {
			hasiFrame.setVisible(false);
		} else if ("RESET".equals(arg)) {
			hasiFrame.setVisible(true);
			hasiFrame.toFront();
		    requestFocusInWindow();
		}
	}

	public void erakutsi() {
	    hasiFrame.setVisible(true);
	    this.requestFocusInWindow();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
	    switch (e.getKeyCode()) {
	    case KeyEvent.VK_SPACE:
	        JokoKudeatzailea.getEMA().irekiJokoa(motaHegazkina, motaEtsaia, maila, jokalariIzena);
	        break;
	    case KeyEvent.VK_1:
	    	maila = "Erraza";
	    	eguneratuTestua();
	        break;
	        
	    case KeyEvent.VK_2:
	    	maila = "Normala";
	    	eguneratuTestua();
	        break;
	        
	    case KeyEvent.VK_3:
	    	maila = "Zaila";
	    	eguneratuTestua();
	        break;
	        
	    case KeyEvent.VK_4:
	    	maila = "Ezinezkoa";
	    	eguneratuTestua();
	        break;
	        
	    case KeyEvent.VK_5:
	    	maila = "Progresiboa";
	    	eguneratuTestua();
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
	    case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;        
	    case KeyEvent.VK_I:
	    	hasiFrame.setVisible(false);
	    	Kontrolak.getEMA().erakutsi();
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
