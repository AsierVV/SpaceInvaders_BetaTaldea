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
	private JLabel bakarraLabel;
	private JLabel geziLabel;
	private JLabel erronboLabel;
	
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
	    panel.setLayout(new GridLayout(5, 1, 0, 5));
	    panel.setPreferredSize(new Dimension(150, 500));
	    
	    frame.getContentPane().add(panel);
	    frame.pack();
	    frame.setLocation(50, 100);
	    //frame.setFocusableWindowState(false);
	    frame.setVisible(false);

	    JokoKudeatzailea.getEMA().addObserver(this);
	}
	
	// === HASIERATU LABELAK ===
	private void hasieratuLabelak() {
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
	    
	    JPanel munizioPanel;
	    switch(JokoKudeatzailea.getEMA().getHegazkinaMota()) {
		    case 'g':
		    	munizioPanel = new JPanel(new GridLayout(2,1));
	    	    munizioPanel.setBackground(Color.BLACK);
	    	    bakarraLabel = new JLabel("", JLabel.CENTER);
	    	    bakarraLabel.setForeground(Color.WHITE);
	    	    geziLabel = new JLabel("", JLabel.CENTER);
	    	    geziLabel.setForeground(Color.WHITE);
	    	    munizioPanel.add(bakarraLabel, BorderLayout.CENTER);
	    	    munizioPanel.add(geziLabel, BorderLayout.CENTER);
	    	    panel.add(munizioPanel);
	    	    break;
	    	case 'b':
	    		munizioPanel = new JPanel(new GridLayout(2,1));
	    	    munizioPanel.setBackground(Color.BLACK);
	    	    bakarraLabel = new JLabel("", JLabel.CENTER);
	    	    bakarraLabel.setForeground(Color.WHITE);
	    	    erronboLabel = new JLabel("", JLabel.CENTER);
	    	    erronboLabel.setForeground(Color.WHITE);
	    	    munizioPanel.add(bakarraLabel, BorderLayout.CENTER);
	    	    munizioPanel.add(erronboLabel, BorderLayout.CENTER);
	    	    panel.add(munizioPanel);
	    	    break;
	    	case 'r':
	    		munizioPanel = new JPanel(new GridLayout(3,1));
	    	    munizioPanel.setBackground(Color.BLACK);
	    	    bakarraLabel = new JLabel("", JLabel.CENTER);
	    	    bakarraLabel.setForeground(Color.WHITE);
	    	    geziLabel = new JLabel("", JLabel.CENTER);
	    	    geziLabel.setForeground(Color.WHITE);
	    	    erronboLabel = new JLabel("", JLabel.CENTER);
	    	    erronboLabel.setForeground(Color.WHITE);
	    	    munizioPanel.add(bakarraLabel, BorderLayout.CENTER);
	    	    munizioPanel.add(geziLabel, BorderLayout.CENTER);
	    	    munizioPanel.add(erronboLabel, BorderLayout.CENTER);
	    	    panel.add(munizioPanel);
	    	    break;
	    }
	    
	    eguneratuDenbora();
	    eguneratuPuntuazioa();
	    eguneratuEtsaiBizirik();
	    eguneratuTiroMota();
	    eguneratuTiroKop();
	    
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
		puntuakLabel.setText("Puntuazioa: ");
	}

	private void eguneratuEtsaiBizirik() {
		etsaiLabel.setText("Etsai bizirik: " + JokoKudeatzailea.getEMA().getEtsaiKop());
	}
	
	private void eguneratuTiroMota() {
		String mota;
		switch(JokoKudeatzailea.getEMA().getTiroMota()) {
			case 'b': mota = "BAKARRA"; break;
			case 't': mota = "GEZIA"; break;
			case 'r': mota = "ERRONBOA"; break;
			default: mota = "BAKARRA"; break;
		}
	    tiroMotaLabel.setText("Tiro mota: " + mota);
	}
	
	private void eguneratuTiroKop() {
	    switch(JokoKudeatzailea.getEMA().getHegazkinaMota()) {
	    case 'g':
	    	bakarraLabel.setText("Tiro bakarra kop: ∞");
			geziLabel.setText("Gezi tiro kop: " + JokoKudeatzailea.getEMA().getTiroKopGezia());
			break;
	    case 'b':
	    	bakarraLabel.setText("Tiro bakarra kop: ∞");
			erronboLabel.setText("Erronbo tiro kop: " + JokoKudeatzailea.getEMA().getTiroKopErronbo());
			break;
	    case 'r':
	    	bakarraLabel.setText("Tiro bakarra kop: ∞");
			geziLabel.setText("Gezi tiro kop: " + JokoKudeatzailea.getEMA().getTiroKopGezia());
			erronboLabel.setText("Erronbo tiro kop: " + JokoKudeatzailea.getEMA().getTiroKopErronbo());
			break;
		}
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
		if ("PUNTUAZIO_PANTAILA_EGUNERATU".equals(arg)) {
		    frame.setVisible(true);
		    hasieratuLabelak();
		} else if ("DENBORA_EGUNERATU".equals(arg)) {
			eguneratuDenbora();
		} else if ("TIRO_KOP_EGUNERATU".equals(arg)) {
			eguneratuTiroKop();
		} else if ("ETSAIAK_KOP_EGUNERATU".equals(arg)) {
			eguneratuEtsaiBizirik();
		} else if ("TIRO_MOTA_EGUNERATU".equals(arg)) {
			eguneratuTiroMota();
		}
	}
}