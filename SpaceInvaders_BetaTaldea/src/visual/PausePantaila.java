package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modeloa.JokoKudeatzailea;

public class PausePantaila extends JPanel implements KeyListener {
	
	public PausePantaila(JFrame pauseFrame) {
		setBackground(new Color(0, 0, 0));
		add(getPause());
		
		pauseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pauseFrame.setContentPane(this);
		pauseFrame.pack();
		pauseFrame.setLocationRelativeTo(null);
		pauseFrame.setVisible(true);
        
		addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
	}
	
	public JPanel getPause() {
	    JPanel pause = new JPanel();
	    pause.setLayout(new BorderLayout());
	    pause.setBackground(Color.BLACK);
	    // HEMEN KODE KOMENTATU HAU ALDATU BEHAR DA ETA PANTAILA BERRIA EGIN PAUSE PANTAILARAKO
	    /*
	    ImageIcon hasiera = new ImageIcon(getClass().getResource("bukaeraIrabazita.png"));
	    JLabel lblAlien = new JLabel(hasiera);
	    lblAlien.setHorizontalAlignment(JLabel.CENTER);

	    pause.add(lblAlien, BorderLayout.CENTER);
	    */
	    return pause;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			JokoKudeatzailea.getEMA().startStopJokoa();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
}