package modeloa;

import java.util.ArrayList;
import java.util.List;

public class TiroaTaldea extends Pixel{
	private List<Pixel> pixelak = new ArrayList<Pixel>();
	private TiroMota mota;
	
	public TiroaTaldea(Koordenatua pPosizioa, TiroMota pMota) {
        super(pPosizioa);
        this.mota = pMota;
        sortuTiroaTaldea();
    }
	
	private void sortuTiroaTaldea() {
        int x = this.posizioa.getX();
        int y = this.posizioa.getY();
        char m = this.mota.tiroEgin();

        // Tiroen forma
        if (m == 'b') {addElementua(new Tiroa(new Koordenatua(x, y)));}
        if (m == 't') {
        	addElementua(new Tiroa(new Koordenatua(x, y)));
        	addElementua(new Tiroa(new Koordenatua(x-1, y+1)));
        	addElementua(new Tiroa(new Koordenatua(x+1, y+1)));
        }
        if (m == 'r') {
        	addElementua(new Tiroa(new Koordenatua(x, y)));
        	addElementua(new Tiroa(new Koordenatua(x, y+1)));
        	addElementua(new Tiroa(new Koordenatua(x, y+2)));
        	//addElementua(new Tiroa(new Koordenatua(x, y-1)));
        	//addElementua(new Tiroa(new Koordenatua(x, y-2)));
        	addElementua(new Tiroa(new Koordenatua(x+1, y)));
        	addElementua(new Tiroa(new Koordenatua(x+2, y)));
        	addElementua(new Tiroa(new Koordenatua(x-1, y)));
        	addElementua(new Tiroa(new Koordenatua(x-2, y)));
        	//addElementua(new Tiroa(new Koordenatua(x+1, y-1)));
        	//addElementua(new Tiroa(new Koordenatua(x-1, y-1)));
        	addElementua(new Tiroa(new Koordenatua(x+1, y+1)));
        	addElementua(new Tiroa(new Koordenatua(x-1, y+1)));

        }
        
    }
	
	public void addElementua(Pixel p) {
		pixelak.add(p);
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
}