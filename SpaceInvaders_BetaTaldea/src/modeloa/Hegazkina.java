package modeloa;

public class Hegazkina extends Pixel{
	private TiroMota mota;
	
	protected Hegazkina(Koordenatua pPosizioa) {
		super(pPosizioa);
		this.mota= new TiroBakarra();
	}
	
	public TiroMota getMota() {
		return this.mota;
	}
}