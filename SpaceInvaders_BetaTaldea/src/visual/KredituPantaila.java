package visual;

import javax.swing.JPanel;

import modeloa.JokoKudeatzailea;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class KredituPantaila extends JPanel implements Observer, KeyListener{

	private JFrame kreFrame = new JFrame("Space Invaders - Kredituak");
	private static KredituPantaila nireEMA = null;
	
	private KredituPantaila() {
		setBackground(new Color(0, 0, 0));
		add(getKredituak());
		
		kreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kreFrame.setContentPane(this);
		kreFrame.pack();
		kreFrame.setLocationRelativeTo(null);
		kreFrame.setVisible(true);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        
	    JokoKudeatzailea.getEMA().addObserver(this);

	}
	
	public static KredituPantaila getEMA() {
		if (nireEMA == null) {
			nireEMA = new KredituPantaila();
		}
		return nireEMA;
	}
	
	public JPanel getKredituak() {
	    JPanel konI = new JPanel();
	    konI.setLayout(new BorderLayout());
	    konI.setBackground(Color.BLACK);

	    ImageIcon kredituak = new ImageIcon(getClass().getResource("kredituak.png"));
	    JLabel lblKredituak = new JLabel(kredituak);
	    lblKredituak.setHorizontalAlignment(JLabel.CENTER);

	    konI.add(lblKredituak, BorderLayout.CENTER);
	    return konI;
	}
	
	public void erakutsi() {
		kreFrame.setVisible(true);
	    this.requestFocusInWindow();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_C:
			kreFrame.setVisible(false);
			HasierakoPantaila.getEMA().erakutsi();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void update(Observable o, Object arg) {}
}
