package game;

import javax.swing.JFrame;

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

    public void abiarazi() {
        irekiHasieraPantaila();
    }

    private void irekiHasieraPantaila() {
        JFrame hasiFrame = new JFrame("Space Invaders - Hasiera");
        hasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HasierakoPantaila hasiera = new HasierakoPantaila();
        hasiFrame.setContentPane(hasiera);
        hasiFrame.pack();
        hasiFrame.setLocationRelativeTo(null);
        hasiFrame.setVisible(true);

        hasiera.addHasiListener(e -> {
            hasiFrame.dispose();
            irekiJokoa();
        });
    }

    private void irekiJokoa() {
        // 1) Modeloaren instantzia sortu (timerrak EZ dira constructorrean hasten)
        Tableroa.getTableroaEMA();

        // 2) Vista sortu (bere constructorrean GelaxkaBista guztiak sortzen dira eta
        //    hasierako egoera margotzen da observer-ak gehitu bezain laster)
        new JokoPanela();

        // 3) Orain bai: jokoa hasi (timerrak martxan)
        Tableroa.getTableroaEMA().hasiJokoa();
    }
}
