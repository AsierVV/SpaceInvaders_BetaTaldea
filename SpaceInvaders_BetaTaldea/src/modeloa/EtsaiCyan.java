package modeloa;

public class EtsaiCyan extends EtsaiaTaldea {

	public EtsaiCyan(Koordenatua pPosizioa, int pInd) {
		super(pPosizioa, pInd);
	}
	
	@Override
	public char getMotaChar() {
		return 'c';
	}
	@Override
	protected void sortuForma() {
	    int x = posizioa.getX();
	    int y = posizioa.getY();

	    addElementua(new Etsaia(new Koordenatua(x, y)));
	    addElementua(new Etsaia(new Koordenatua(x-1, y)));
	    addElementua(new Etsaia(new Koordenatua(x+1, y)));
	    addElementua(new Etsaia(new Koordenatua(x, y-1)));
	    addElementua(new Etsaia(new Koordenatua(x, y+1)));
	}
}
