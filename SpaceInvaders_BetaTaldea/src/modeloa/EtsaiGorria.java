package modeloa;

public class EtsaiGorria extends EtsaiaTaldea{

	public EtsaiGorria(Koordenatua pPosizioa, int pInd) {
		super(pPosizioa, pInd);
	}
	
	@Override
	public char getMotaChar() {
		return 'e';
	}
	
	@Override
	protected void sortuForma() {
	    int x = posizioa.getX();
	    int y = posizioa.getY();

	    addElementua(new Etsaia(new Koordenatua(x, y)));       // centro arriba
	    addElementua(new Etsaia(new Koordenatua(x-1, y+1)));
	    addElementua(new Etsaia(new Koordenatua(x+1, y+1)));
	    addElementua(new Etsaia(new Koordenatua(x-2, y+2)));
	    addElementua(new Etsaia(new Koordenatua(x+2, y+2)));
	}
}
