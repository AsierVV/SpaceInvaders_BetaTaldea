package visual;

import javax.swing.JPanel;

import modeloa.JokoKudeatzailea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class PuntuazioPantaila extends JPanel implements Observer{

	private static PuntuazioPantaila nireEMA = null;
	
	private JFrame frame;
	private JPanel panel;
	
	/* COSAS PARA AÑADIR EN ESTE PANEL
		- Marcador
		- Tiempo de juego
		- Enemigos restantes
		- Tipo de disparo actual
		- Munición restante		
	 */
	
	
	public PuntuazioPantaila() {
		frame = new JFrame("Puntuazioa");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    panel = new JPanel();
	    panel.setLayout(new GridLayout(5, 5, 0, 0));
	    panel.setPreferredSize(new Dimension(100, 500));
		
		frame.getContentPane().add(panel);
	    frame.pack();
	    //frame.setLocationRelativeTo(null);
	    frame.setLocation(100, 100);
	    frame.setVisible(false);
	    
	    JokoKudeatzailea.getEMA().addObserver(this);
	}
	
	public static PuntuazioPantaila getEMA() {
		if (nireEMA == null) {
			nireEMA = new PuntuazioPantaila();
		}
		return nireEMA;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) return;
		if ("JOKOA_HASI".equals(arg)) {
		    frame.setVisible(true);
		}
	}
}