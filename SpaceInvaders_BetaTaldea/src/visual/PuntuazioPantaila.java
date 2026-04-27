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
	
	// LABELAK
	private JLabel denboraLabel;
	private JLabel puntuakLabel;
	private JLabel etsaiLabel;
	private JLabel tiroMotaLabel;
	private JLabel munizioLabel;
	
	private int puntuazioa;
	private int etsaiKop;
	private String tiroMota;
	private int tiroKop;
	
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
	    panel.setLayout(new GridLayout(5, 1, 0, 10));
	    panel.setPreferredSize(new Dimension(100, 500));
	    
	    parametroakHasieratu();
	    
	    // Labelak gehitu
	    JPanel denboraPanel = new JPanel(new BorderLayout());
	    denboraPanel.setBackground(Color.BLACK);
	    denboraLabel = new JLabel("", JLabel.CENTER);
	    denboraLabel.setForeground(Color.WHITE);
	    denboraPanel.add(denboraLabel, BorderLayout.CENTER);
	    panel.add(denboraPanel);
	    
	    JPanel puntuakPanel = new JPanel(new BorderLayout());
	    puntuakPanel.setBackground(Color.BLACK);
	    puntuakLabel = new JLabel("", JLabel.CENTER);
	    puntuakLabel.setForeground(Color.WHITE);
	    puntuakPanel.add(puntuakLabel, BorderLayout.CENTER);
	    panel.add(puntuakPanel);
	    
	    JPanel etsaiPanel = new JPanel(new BorderLayout());
	    etsaiPanel.setBackground(Color.BLACK);
	    etsaiLabel = new JLabel("", JLabel.CENTER);
	    etsaiLabel.setForeground(Color.WHITE);
	    etsaiPanel.add(etsaiLabel, BorderLayout.CENTER);
	    panel.add(etsaiPanel);

	    JPanel tiroMotaPanel = new JPanel(new BorderLayout());
	    tiroMotaPanel.setBackground(Color.BLACK);
	    tiroMotaLabel = new JLabel("", JLabel.CENTER);
	    tiroMotaLabel.setForeground(Color.WHITE);
	    tiroMotaPanel.add(tiroMotaLabel, BorderLayout.CENTER);
	    panel.add(tiroMotaPanel);
	    
	    JPanel munizioPanel = new JPanel(new BorderLayout());
	    munizioPanel.setBackground(Color.BLACK);
	    munizioLabel = new JLabel("", JLabel.CENTER);
	    munizioLabel.setForeground(Color.WHITE);
	    munizioPanel.add(munizioLabel, BorderLayout.CENTER);
	    panel.add(munizioPanel);
	    
	    eguneratuDenbora();
	    eguneratuPuntuazioa();
	    
	    frame.getContentPane().add(panel);
	    frame.pack();
	    frame.setLocation(100, 100);
	    frame.setVisible(false);

	    JokoKudeatzailea.getEMA().addObserver(this);
	}
	
	private void parametroakHasieratu() {
		puntuazioa = 0;
		etsaiKop = JokoKudeatzailea.getEMA().getEtsaiKop();
		tiroMota = "Normala";
		//tiroKop = 100;
	}
	
	// === EGUNERATU LABELAK ===
	private void eguneratuDenbora() {
		int denbora = JokoKudeatzailea.getEMA().getDenboraSegunduak();
		int min = denbora/60;
		int seg = denbora%60;
		
	    denboraLabel.setText(String.format("Denbora: %02d:%02d", min, seg));
	    	// % --> balore bat sartzeko
	    	// 0 --> 0-ekin bete
	    	// 2 --> gutxienez 2 zifra
	    	// d --> zenbaki osoa
	}

	private void eguneratuPuntuazioa() {
		puntuakLabel.setText("Puntuazioa: " + puntuazioa);
	}
/*
	private void eguneratuEtsaiBizirik() {
	    lblAukera.setText("Hegazkina: " + motaHegazkina + " | Etsaia: " + motaEtsaia);
	}
	private void eguneratuTiroMota() {
	    lblAukera.setText("Hegazkina: " + motaHegazkina + " | Etsaia: " + motaEtsaia);
	}
	private void eguneratuTiroKop() {
	    lblAukera.setText("Hegazkina: " + motaHegazkina + " | Etsaia: " + motaEtsaia);
	}
	*/
	
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
		} else if ("DENBORA_EGUNERATU".equals(arg)) {
			eguneratuDenbora();
		}
	}
}