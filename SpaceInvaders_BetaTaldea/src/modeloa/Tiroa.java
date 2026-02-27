package modeloa;

public class Tiroa extends Pixel{

	private boolean aktibo;
	
	public Tiroa(Koordenatua pPosizioa) {
		super(pPosizioa);
		this.aktibo = true;
	}
	
	// Tiroa bakarrik mugituko da gora lerro zuzenean
	public void mugitu() {
		posizioa.setY(posizioa.getY() - 1);
	}
	
	public boolean aktiboDago() {
		return aktibo;
	}
	
	public void setAktibo(boolean a) {
		this.aktibo = a;
	}
}
