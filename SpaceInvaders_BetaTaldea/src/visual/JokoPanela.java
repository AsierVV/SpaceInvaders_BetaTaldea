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
	
	public JokoPanela() {

	    JFrame frame = new JFrame("Space Invaders");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    this.panel = new JPanel();
	    panel.setLayout(new GridLayout(Tableroa.getTableroaEMA().getAltuera(),
	                                   Tableroa.getTableroaEMA().getZabalera(), 0, 0));
	    panel.setPreferredSize(new Dimension(1200, 720)); // tamaño total del panel

	    matrizeakSortu(); // agrega todos los labels y conectarlos con observadores
	    
	    panel.addKeyListener(TeklatuKontroladorea.getTeklatuEMA());	// Panelak teklatua detektatzeko
	    panel.setFocusable(true);							// Panelak focus-a euki dezake
	    panel.requestFocusInWindow();						// Focus-a panelean jartzen dugu

	    frame.getContentPane().add(panel);
	    frame.pack();                     // calcula tamaño real de la ventana
	    frame.setLocationRelativeTo(null); // ¡centrar después de pack!
	    frame.setVisible(true);
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
	}
/*
	private void actualizarTableroCompleto() {
	    Tableroa tab = Tableroa.getTableroaEMA();
	    for (int x = 0; x < tab.getZabalera(); x++) {
	        for (int y = 0; y < tab.getAltuera(); y++) {
	            Gelaxka g = tab.getGelaxka(x, y);
	            g.setMota(g.getMota()); // dispara notifyObservers()
	        }
	    }
	}
*/
	@Override
	public void update(Observable o, Object arg)  {
		// TODO Auto-generated method stub
		
	}
 }