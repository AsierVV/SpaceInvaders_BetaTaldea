package game;

import javax.swing.JFrame;

import controller.TeklatuKontroladorea;
import modeloa.Tableroa;
import visual.HasierakoPantaila;
import visual.JokoPanela;

/**
 * Jokoaren abiaraztea kudeatzeko klase sinplea.
 * - Hasierako pantaila irekitzen du
 * - "JOKOA HASI" sakatzean, hasierako JFrame-a ixten du eta jokoaren leihoa irekitzen du
 * - Orduan bakarrik hasten ditu timer-ak (etsaien/tiroen mugimendua)
 */

/*
ESTA CLASE SOLO VA A INICIAR Y CERRAR EL JUEGO
El modelo tiene que hacer que se inicie la vista, y no el main.
*/

public class JokoKudeatzailea {
	private boolean jokoaHasita = false;
	private JFrame hasiFrame = new JFrame("Space Invaders - Hasiera");
	
    public void abiarazi() {
    	TeklatuKontroladorea.getTeklatuEMA().setJK(this);
    	irekiHasieraPantaila();
        
    }

    private void irekiHasieraPantaila() {
        hasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HasierakoPantaila hasiera = new HasierakoPantaila();
        hasiFrame.setContentPane(hasiera);
        hasiFrame.pack();
        hasiFrame.setLocationRelativeTo(null);
        hasiFrame.setVisible(true);
        hasiFrame.setFocusable(true);
        hasiera.requestFocusInWindow();
        hasiera.addKeyListener(TeklatuKontroladorea.getTeklatuEMA());

    }

    public void irekiJokoa() {
        // 1) Modeloaren instantzia sortu (timerrak EZ dira constructorrean hasten)
        Tableroa.getTableroaEMA();

        // 2) Vista sortu (bere constructorrean GelaxkaBista guztiak sortzen dira eta
        //    hasierako egoera margotzen da observer-ak gehitu bezain laster)
        new JokoPanela();

        // 3) Orain bai: jokoa hasi (timerrak martxan)
        Tableroa.getTableroaEMA().hasiJokoa();
        
    	// 4) Jokoa hasi dela finkatu eta hasierako pantaila itxi
    	jokoaHasita = true;
    	if (hasiFrame != null) {
            hasiFrame.dispose(); 
        }
    }
    
    public boolean hasiDaJokoa() {
    	return this.jokoaHasita;
    }
}
