package modeloa;

import javax.swing.JFrame;

import controller.TeklatuKontroladorea;
import visual.HasierakoPantaila;
import visual.JokoPanela;

/**
 * Jokoaren abiaraztea kudeatzeko klase sinplea.
 * - Hasierako pantaila irekitzen du
 * - "JOKOA HASI" sakatzean, hasierako JFrame-a ixten du eta jokoaren leihoa irekitzen du
 * - Orduan bakarrik hasten ditu timer-ak (etsaien/tiroen mugimendua)
 */

	// ESTA CLASE TIENE QUE IR EN EL MODELO
	// Main hasten denean martxan jarri joko kudeatzailea eta joko kudeatazailea lotu hasierako pantaila
	// listener bitartez jaso espacioa, eta honekin observer bidez hasieratu dena (tableroa hasieratu, modeloko hasieraketa amaituta dagoenena abisatu matrize visualari )

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
    	
    	
    	// DESDE EL TABLERO SE LE LLAMA AL JOKO KUDEATZAILEA CUANDO SE PUEDE HABER PERDIDO O GANADO Y EL JOKO KUDEATZAILEA DECIDE SI SE HA GANADO O PERDIDO
    	// HEMEN ERABAKI ZER GERTATU DEN ETA OBSERVER BIDEZ DEITU JOKO PANELA ETA BIDALI ATRIBUTU BAT JAKITEKO ZER GERTATU DEN
    }
    
    public boolean hasiDaJokoa() {
    	return this.jokoaHasita;
    }
}
