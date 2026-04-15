package modeloa;

import java.util.Observable;
import java.util.Observer;

public class JokoKudeatzailea extends Observable implements Observer{
	private static JokoKudeatzailea nireEma  = null;
	private boolean partidaIrabazita;
	
	private JokoKudeatzailea() {
		Tableroa.getTableroaEMA().addObserver(this);
	}
	
	public static JokoKudeatzailea getEMA() {
		if(nireEma == null) {
			nireEma = new JokoKudeatzailea();
		}
		return nireEma;
	}

	// === JOKOA HASTEKO ===
    public void irekiJokoa(String motaHegazkina, String motaEtsaia) {
    	Tableroa.getTableroaEMA().hasiJokoa(motaHegazkina, motaEtsaia);
    }
    
    // === TABLEROAREN DATUAK BISTARAKO ===
    public int getZabalera() {
		return Tableroa.getTableroaEMA().getZabalera();
	}

	public int getAltuera() {
		return Tableroa.getTableroaEMA().getAltuera();
	}

	public Gelaxka getGelaxka(int x, int y) {
		return Tableroa.getTableroaEMA().getGelaxka(x, y);
	}
	
	public void setIrabazi(boolean pEgoera) {
		partidaIrabazita = pEgoera;
	}
	
	// === TEKLATUKO EKINTZAK ===
	public void ezkerraSakatu() {Tableroa.getTableroaEMA().ezkerraSakatu();}
	public void ezkerraAskatu() {Tableroa.getTableroaEMA().ezkerraAskatu();}
	
	public void eskuinaSakatu() {Tableroa.getTableroaEMA().eskuinaSakatu();}
	public void eskuinaAskatu() {Tableroa.getTableroaEMA().eskuinaAskatu();}
	
	public void goraSakatu() {Tableroa.getTableroaEMA().goraSakatu();}
	public void goraAskatu() {Tableroa.getTableroaEMA().goraAskatu();}
	
	public void beheraSakatu() {Tableroa.getTableroaEMA().beheraSakatu();}
	public void beheraAskatu() {Tableroa.getTableroaEMA().beheraAskatu();}
	
	public void tiroaSakatu() {Tableroa.getTableroaEMA().tiroaSakatu();}
	public void tiroaAskatu() {Tableroa.getTableroaEMA().tiroaAskatu();}
	
	public void tiroaAldatu() {Tableroa.getTableroaEMA().tiroaAldatu();}
    
	// === TABLEROATIK JASOTAKO EVENTUAK BISTETARA BIRBIDALI ===
	@Override
	public void update(Observable o, Object arg) {
		if ("TABLEROA_SORTUTA".equals(arg)) {
			setChanged();
			notifyObservers("JOKOA_HASI");
		} else if (partidaIrabazita) {
			setChanged();
			notifyObservers("IRABAZI");
		} else if (!partidaIrabazita) {
			setChanged();
			notifyObservers("GALDU");
		}
	}
}
