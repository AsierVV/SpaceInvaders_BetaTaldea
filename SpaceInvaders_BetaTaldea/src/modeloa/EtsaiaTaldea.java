package modeloa;

import java.util.ArrayList;
import java.util.List;

public class EtsaiaTaldea extends Pixel{
	private List<Pixel> pixelak = new ArrayList<Pixel>();
	private int indizea;
	
	public EtsaiaTaldea(Koordenatua pPosizioa, int pInd) {
        super(pPosizioa);
        indizea = pInd;
        sortuEtsaiaTaldea();
    }
	
	private void sortuEtsaiaTaldea() {
        int x = this.posizioa.getX();
        int y = this.posizioa.getY();

        // Etsaien forma
        addElementua(new Etsaia(new Koordenatua(x, y)));
        addElementua(new Etsaia(new Koordenatua(x-1, y)));
        addElementua(new Etsaia(new Koordenatua(x+1, y)));
        addElementua(new Etsaia(new Koordenatua(x, y+1)));
    }
	
	public void addElementua(Pixel p) {
		pixelak.add(p);
	}
	
	public int getIndizea() {return indizea;}

	public Koordenatua etsaiaAusazkoMugimendua() {
		int mugimenduMota = (int) (Math.random() * 3);
		Koordenatua k = new Koordenatua(0,0);;
		switch (mugimenduMota) {
        	case 0: // Behera
        		k = new Koordenatua(0,1);
        		break;
        	case 1: // Ezkerrera
        		k = new Koordenatua(-1,0);
        		break;
        	case 2: // Eskuinera
        		k = new Koordenatua(1,0);
        		break;
		}
		return k;
	}
	
	public void mugituEtsaia() {
        // Ausazko mugimendua sortu (0, 1, edo 2)
        int mugimenduMota = (int) (Math.random() * 3);
        
        int xBerria = posizioa.getX();;
        int yBerria = posizioa.getY();
        
        switch (mugimenduMota) {
            case 0: // Behera
            	yBerria++;
                break;
            case 1: // Ezkerrera
                xBerria--;
                break;
            case 2: // Eskuinera
                xBerria++;
                break;
        }
        
        // Koordenatuak eguneratu
        posizioa.setX(xBerria);
        posizioa.setY(yBerria);
	}
	
	@Override
	public List<Koordenatua> getKoordenatuLista() {
		List<Koordenatua> koordenatuak = new ArrayList<>();
        for (Pixel p : pixelak) {
            koordenatuak.addAll(p.getKoordenatuLista());
        }
        return koordenatuak;
	}

	@Override
	public void mugitu(int dx, int dy) {
		posizioa.setX(posizioa.getX()+dx);
    	posizioa.setY(posizioa.getY()+dy);
	    for (Pixel p : pixelak) {
	    	p.mugitu(dx, dy);
	    }
	}
	
	public static List<Koordenatua> sortuKoordenatuak(Koordenatua pos) {
	    List<Koordenatua> lista = new ArrayList<>();
	    int x = pos.getX();
	    int y = pos.getY();

	    lista.add(new Koordenatua(x, y));
	    lista.add(new Koordenatua(x-1, y));
	    lista.add(new Koordenatua(x+1, y));
	    lista.add(new Koordenatua(x, y+1));

	    return lista;
	}
}