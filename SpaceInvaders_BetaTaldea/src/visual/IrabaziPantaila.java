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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class IrabaziPantaila extends JPanel implements Observer, KeyListener{
	
	private JFrame bukFrame;
	
	public IrabaziPantaila(JFrame pBukFrame) {
		
		bukFrame = pBukFrame;
		
		setBackground(new Color(0, 0, 0));
		add(getBukaera());
		
        bukFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bukFrame.setContentPane(this);
        bukFrame.pack();
        bukFrame.setLocationRelativeTo(null);
        bukFrame.setVisible(true);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        
	    JokoKudeatzailea.getEMA().addObserver(this);
	}
	
	public JPanel getBukaera() {
	    JPanel bukI = new JPanel();
	    bukI.setLayout(new BorderLayout());
	    bukI.setBackground(Color.BLACK);

	    ImageIcon hasiera = new ImageIcon(getClass().getResource("bukaeraIrabazita.png"));
	    JLabel lblAlien = new JLabel(hasiera);
	    lblAlien.setHorizontalAlignment(JLabel.CENTER);

	    bukI.add(lblAlien, BorderLayout.CENTER);
	    return bukI;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			JokoKudeatzailea.getEMA().jokoaReset();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if ("RESET".equals(arg)) {
			bukFrame.dispose();
		}
	}
}