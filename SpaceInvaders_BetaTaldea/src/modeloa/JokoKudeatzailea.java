package modeloa;

import java.util.Observable;

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

public class JokoKudeatzailea extends Observable{
	private static JokoKudeatzailea nireEma  = null;
	//atributo bat jakiteko ea zer gertatzen ari den momentuan (0 ezer, 1 jolasten, 2 amaitu...)
	
	private JokoKudeatzailea() {}
	
	public static JokoKudeatzailea getEMA() {
		if(nireEma == null) {
			nireEma = new JokoKudeatzailea();
		}
		return nireEma;
		
	}

    public void irekiJokoa() {
        Tableroa.getTableroaEMA();

        setChanged();
        notifyObservers();
        //bidali zenbaki bat jakiteko zer gertatu den

        Tableroa.getTableroaEMA().hasiJokoa();
    }
}
