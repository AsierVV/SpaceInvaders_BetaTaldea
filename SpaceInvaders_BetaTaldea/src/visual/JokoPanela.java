package visual;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modeloa.JokoKudeatzailea;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

public class JokoPanela extends JPanel implements Observer, KeyListener{
	
	private static JokoPanela nireEMA = null;
	private JPanel panel;
	private JFrame frame;
    private JFrame framePause = new JFrame("PAUSE");
	
	private boolean matrizeaSortuta = false;
	
	private JokoPanela() {
	    frame = new JFrame("Space Invaders");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    panel = new JPanel();
	    panel.setLayout(new GridLayout(JokoKudeatzailea.getEMA().getAltuera(), JokoKudeatzailea.getEMA().getZabalera(), 0, 0));
	    panel.setPreferredSize(new Dimension(1200, 720));	// Panelaren tamaiña totala
	    panel.addKeyListener(this);							// Panelak teklatua detektatzeko
	    panel.setFocusable(true);							// Panelak focus-a euki dezake
	    panel.requestFocusInWindow();						// Focus-a panelean jartzen dugu
	    
	    frame.getContentPane().add(panel);
	    frame.pack();
	    //frame.setLocationRelativeTo(null);
	    frame.setLocation(250, 50);
	    frame.setVisible(false);

	    JokoKudeatzailea.getEMA().addObserver(this);
	}
	
	public static JokoPanela getEMA() {
		if (nireEMA == null) {
			nireEMA = new JokoPanela();
		}
		return nireEMA;
	}
	
	private JLabel getLblNewLabel(int x, int y) {
		GelaxkaBista newLabel = new GelaxkaBista();
		JokoKudeatzailea.getEMA().getGelaxka(x,y).addObserver(newLabel);
		return newLabel;
	}
	
	private void matrizeakSortu() {
		for(int y=0;y<JokoKudeatzailea.getEMA().getAltuera(); y++) {
			for(int x=0;x<JokoKudeatzailea.getEMA().getZabalera();x++) {
				panel.add(getLblNewLabel(x,y));
			}
		}
	    frame.setVisible(true);
	    panel.requestFocusInWindow();

	}
	
	@Override
	public void update(Observable o, Object arg)  {		
		if (arg == null) return;
		if ("JOKOA_HASI".equals(arg) && !matrizeaSortuta) {
			matrizeakSortu();
			matrizeaSortuta = true;
		} else if ("GALDU".equals(arg)) {
		    frame.dispose();
		    JFrame frame = new JFrame("Game Over!");
		    frame.setContentPane(new GalduPantaila(frame));
		} else if ("IRABAZI".equals(arg)) {
			frame.dispose();
		    JFrame frame = new JFrame("Irabazi duzu!");
		    frame.setContentPane(new IrabaziPantaila(frame));
		} else if ("STOP".equals(arg)) {	    	
		    framePause.setContentPane(new PausePantaila(framePause));
		} else if ("START".equals(arg)) {
			framePause.dispose();
		    panel.requestFocusInWindow();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			JokoKudeatzailea.getEMA().ezkerraSakatu();
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			JokoKudeatzailea.getEMA().eskuinaSakatu();
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			JokoKudeatzailea.getEMA().goraSakatu();
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			JokoKudeatzailea.getEMA().beheraSakatu();
			break;
		case KeyEvent.VK_SPACE:
			JokoKudeatzailea.getEMA().tiroaSakatu();
			break;
		case KeyEvent.VK_R:
			JokoKudeatzailea.getEMA().tiroaAldatu();
			break;
		case KeyEvent.VK_ESCAPE:
			JokoKudeatzailea.getEMA().startStopJokoa();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			JokoKudeatzailea.getEMA().ezkerraAskatu();
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			JokoKudeatzailea.getEMA().eskuinaAskatu();
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			JokoKudeatzailea.getEMA().goraAskatu();
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			JokoKudeatzailea.getEMA().beheraAskatu();
			break;
		case KeyEvent.VK_SPACE:
			JokoKudeatzailea.getEMA().tiroaAskatu();
			break;
		}	
	}
 }