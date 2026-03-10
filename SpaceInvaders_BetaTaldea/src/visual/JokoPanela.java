package visual;

import modeloa.Gelaxka;
import modeloa.Tableroa;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.TeklatuKontroladorea;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

public class JokoPanela extends JPanel implements Observer{
	private JPanel panel;
	private JFrame frame;
	
	public JokoPanela() {
	    this.frame = new JFrame("Space Invaders");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    Tableroa.getTableroaEMA().addObserver(this);
	    
	    this.panel = new JPanel();
	    panel.setLayout(new GridLayout(Tableroa.getTableroaEMA().getAltuera(),
	                                   Tableroa.getTableroaEMA().getZabalera(), 0, 0));
	    panel.setPreferredSize(new Dimension(1200, 720)); // tamaño total del panel
	    
	    panel.addKeyListener(TeklatuKontroladorea.getTeklatuEMA());	// Panelak teklatua detektatzeko
	    panel.setFocusable(true);							// Panelak focus-a euki dezake
	    panel.requestFocusInWindow();						// Focus-a panelean jartzen dugu

	    frame.getContentPane().add(panel);
	    frame.pack();                     // calcula tamaño real de la ventana
	    frame.setLocationRelativeTo(null); // ¡centrar después de pack!
	}
	
	private JLabel getLblNewLabel(int x, int y) { //sarrera parametroak
		GelaxkaBista newLabel = new GelaxkaBista(); //botoiaren testua
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
	}
	
	@Override
	public void update(Observable o, Object arg)  {		
		if (!Tableroa.getTableroaEMA().getHasiDaJokoa()) {
			matrizeakSortu();
		}else {
		    if (arg != null && arg.equals("GALDU")) {
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
	}
 }