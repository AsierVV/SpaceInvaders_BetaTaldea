package visual;

import modeloa.Tableroa;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	private boolean matrizeaSortuta = false;
	
	private JokoPanela() {
	    frame = new JFrame("Space Invaders");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    panel = new JPanel();
	    panel.setLayout(new GridLayout(Tableroa.getTableroaEMA().getAltuera(), Tableroa.getTableroaEMA().getZabalera(), 0, 0));
	    panel.setPreferredSize(new Dimension(1200, 720)); // tamaño total del panel
	    panel.addKeyListener(this);	// Panelak teklatua detektatzeko
	    panel.setFocusable(true);							// Panelak focus-a euki dezake
	    panel.requestFocusInWindow();						// Focus-a panelean jartzen dugu
	    
	    frame.getContentPane().add(panel);
	    frame.pack();                     // calcula tamaño real de la ventana
	    frame.setLocationRelativeTo(null); // ¡centrar después de pack!
	    frame.setVisible(false);

	    Tableroa.getTableroaEMA().addObserver(this);
	}
	
	public static JokoPanela getEMA() {
		if (nireEMA == null) {
			nireEMA = new JokoPanela();
		}
		return nireEMA;
	}
	
	private JLabel getLblNewLabel(int x, int y) {
		GelaxkaBista newLabel = new GelaxkaBista();
		Tableroa.getTableroaEMA().getGelaxka(x,y).addObserver(newLabel);
		return newLabel;
	}
	
	private void matrizeakSortu() {
		for(int y=0;y<60;y++) {
			for(int x=0;x<100;x++) {
				panel.add(getLblNewLabel(x,y));
			}
		}
	    frame.setVisible(true);
	    panel.requestFocusInWindow();

	}
	
		@Override
		public void update(Observable o, Object arg)  {		
			if (arg != null && arg.equals("TABLEROA_SORTUTA") && !matrizeaSortuta) {
				matrizeakSortu();
				matrizeaSortuta = true;
			}else if (arg != null && arg.equals("GALDU")) {
		    	frame.dispose();
	
		        JFrame frame = new JFrame("Game Over");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		        frame.setContentPane(new GalduPantaila());
		        frame.pack();
		        frame.setLocationRelativeTo(null);
		        frame.setVisible(true);
		    }else if (arg != null && arg.equals("IRABAZI")) {
		    	frame.dispose();
	
		        JFrame frame = new JFrame("Irabazi duzu!");
		        frame.setContentPane(new IrabaziPantaila());
		        frame.pack();
		        frame.setLocationRelativeTo(null);
		        frame.setVisible(true);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				Tableroa.getTableroaEMA().setEzk(true);
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				Tableroa.getTableroaEMA().setEsk(true);
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				Tableroa.getTableroaEMA().setGo(true);
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				Tableroa.getTableroaEMA().setBe(true);
				break;
			case KeyEvent.VK_SPACE:
				if (!Tableroa.getTableroaEMA().getTiroEgin()) {
					Tableroa.getTableroaEMA().tiroaSortu();
					Tableroa.getTableroaEMA().setTi(true);
				}
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				Tableroa.getTableroaEMA().setEzk(false);
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				Tableroa.getTableroaEMA().setEsk(false);
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				Tableroa.getTableroaEMA().setGo(false);
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				Tableroa.getTableroaEMA().setBe(false);
				break;
			case KeyEvent.VK_SPACE:
				Tableroa.getTableroaEMA().setTi(false);
				break;
			}	
		}
 }