package modeloa;

import java.util.ArrayList;
import java.util.List;

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

	    //addElementua(new Etsaia(new Koordenatua(x, y)));
	    // Gezi forma behera
	    addElementua(new Etsaia(new Koordenatua(x, y)));
	    addElementua(new Etsaia(new Koordenatua(x, y-1)));
	    addElementua(new Etsaia(new Koordenatua(x-1, y-1)));
	    addElementua(new Etsaia(new Koordenatua(x+1, y-1)));
	    addElementua(new Etsaia(new Koordenatua(x-2, y-2)));
	    addElementua(new Etsaia(new Koordenatua(x+2, y-2)));
	}
	
	public static List<Koordenatua> sortuKoordenatuak(Koordenatua pos) {
		List<Koordenatua> lista = new ArrayList<>();
	    int x = pos.getX();
	    int y = pos.getY();

	    lista.add(new Koordenatua(x, y));
	    
	    return lista;
	}
}
