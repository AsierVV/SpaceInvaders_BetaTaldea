package modeloa;

public class Gelaxka {
	
	private Koordenatua gelaxkaPosizioa;
	private Pixel mota;
	
	/*
	private int indize; //0 --> hutsunea
						//1 --> hegazkina
						//2-10 --> etsaia
						//-1 --> tiroa
	*/
	
	public Gelaxka(Koordenatua pKoord) {
		this.gelaxkaPosizioa = pKoord;
		this.mota = new Hutsunea(pKoord);
		//this.indize = pIndize;
	}
	
	public void setMota(Pixel pMota) {
		this.mota = pMota;
	}
	
	public Pixel getMota() {
		return mota;
	}
	
	public void hutsikUtzi() {
	    this.mota = new Hutsunea(gelaxkaPosizioa);
	}
	
	public Koordenatua getGelaxkaKord() {
		return gelaxkaPosizioa;
	}
}