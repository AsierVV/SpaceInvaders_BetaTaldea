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
	    egoera.hutsikUtzi(this);
	}
	
	public void jarriHegazkina(char pHegazkinMota) {
		egoera.jarriHegazkina(this, pHegazkinMota);
	}
	
	public void jarriEtsaia() {
		egoera.jarriEtsaia(this);
	}
	
	public void jarriTiroa() {
		egoera.jarriTiroa(this);
	}
	
	public Koordenatua getGelaxkaKord() {
		return gelaxkaPosizioa;
	}

}