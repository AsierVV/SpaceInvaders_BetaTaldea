package controller;

import java.util.Observable;
import java.util.Observer;

import modeloa.Gelaxka;
import modeloa.Tableroa;

public class JokoKudeatzailea extends Observable implements Observer{
	private static JokoKudeatzailea nireEma  = null;
	
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
    public void irekiJokoa() {
    	Tableroa.getTableroaEMA().hasiJokoa();
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
    
	// === TABLEROATIK JASOTAKO EVENTUAK BISTETARA BIRBIDALI ===
	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) return;
		if ("TABLEROA_SORTUTA".equals(arg)) {
			setChanged();
			notifyObservers("JOKOA_HASI");
		} else if ("IRABAZI".equals(arg)) {
			setChanged();
			notifyObservers("IRABAZI");
		} else if ("GALDU".equals(arg)) {
			setChanged();
			notifyObservers("GALDU");
		}
	}
}
