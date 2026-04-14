package modeloa;

import java.util.Observable;

public class Gelaxka extends Observable{
	
	private Koordenatua gelaxkaPosizioa;
	private Egoera egoera;
	
	public Gelaxka(Koordenatua pKoord, Egoera pEgoera) {
		this.gelaxkaPosizioa = pKoord;
		this.egoera = pEgoera;
	}

	public void setEgoera(Egoera pEgoera) {
		this.egoera= pEgoera	;
		setChanged();
		notifyObservers();
	}
	
	public char getMota() {
		return egoera.getMota();
	}
	
	public void hutsikUtzi() {
	    setEgoera(new HutsuneEgoera());
	}
	
	public void jarriHegazkina(char pHegazkinMota) {
		setEgoera(new HegazkinEgoera(pHegazkinMota));
	}
	
	public void jarriEtsaia() {
		setEgoera(new EtsaiEgoera());
	}
	
	public void jarriTiroa() {
		setEgoera(new TiroEgoera());
	}
	
	public Koordenatua getGelaxkaKord() {
		return gelaxkaPosizioa;
	}

}