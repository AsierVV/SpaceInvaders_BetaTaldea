package game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import modeloa.Tableroa;
import visual.JokoPanela;
import visual.HasierakoPantaila;

public class main {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JokoKudeatzailea().abiarazi();
        });
    }
	/*
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
    		
    		JFrame hasiFrame = new JFrame("Space Invaders - Hasiera");
            hasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            HasierakoPantaila hasiera = new HasierakoPantaila();
            hasiFrame.setContentPane(hasiera);		// Panela sartu JFrame-ean
            hasiFrame.pack();						// Tamaina ajustatu
            hasiFrame.setLocationRelativeTo(null);	// Lehioa pantailan zentratzen du
            hasiFrame.setVisible(true);				// Lehioa bistaratzen du
            
            // Al pulsar "HASI"
            hasiera.addHasiListener(e -> {
            	// Cierra la ventana de inicio
                hasiFrame.dispose();

                // Crea el modelo (esto ya crea nave + enemigos + timers)
                Tableroa.getTableroaEMA();

                // Abre el juego (tu JokoPanela crea su propio JFrame)
                new JokoPanela();
                
                Tableroa.getTableroaEMA().refreshAllGelaxkak();
                Tableroa.getTableroaEMA().hasiJokoa();
            });
    	});
    }
    */
}