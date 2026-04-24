package modeloa;

public class EtsaiMorea extends EtsaiaTaldea{

	public EtsaiMorea(Koordenatua pPosizioa, int pInd) {
		super(pPosizioa, pInd);
	}
	
	@Override
	public char getMotaChar() {
		return 'm';
	}
	@Override
	protected void sortuForma() {
	    int x = posizioa.getX();
	    int y = posizioa.getY();

	    addElementua(new Etsaia(new Koordenatua(x, y)));
	}
}
