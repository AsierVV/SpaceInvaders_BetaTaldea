package modeloa;

import java.util.Observable;

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
        Tableroa.getTableroaEMA().hasiJokoa();
    }
    
    public void partidaAmaitu(boolean irabazi) {
    	if (irabazi) {
    		setChanged();
    		notifyObservers("irabazi");
    	} else {
    		setChanged();
    		notifyObservers("galdu");
    	}
    }
}
