package modeloa;

import java.util.Observable;

public class Gelaxka extends Observable{
	
	private Koordenatua gelaxkaPosizioa;
	private char mota; 	//u --> hutsunea
						//h --> hegazkina
						//e --> etsaia
						//t --> tiroa
	
	public Gelaxka(Koordenatua pKoord, char pMota) {
		this.gelaxkaPosizioa = pKoord;
		this.mota = pMota;
		setChanged();
		notifyObservers();
	}
	
	public void setMota(char pMota) {
		this.mota = pMota;
		setChanged();
		notifyObservers();
	}
	
	public char getMota() {
		return mota;
	}
	
	public void hutsikUtzi() {
	    this.mota = 'u';
	    setChanged();
		notifyObservers();
	}
	
	public Koordenatua getGelaxkaKord() {
		return gelaxkaPosizioa;
	}
}