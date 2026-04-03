package modeloa;

import java.util.ArrayList;
import java.util.List;

public class TiroaTaldea extends Pixel{
	private List<Pixel> pixelak = new ArrayList<Pixel>();
	
	public TiroaTaldea(Koordenatua pPosizioa) {
        super(pPosizioa);
        sortuTiroaTaldea();
    }
	
	private void sortuTiroaTaldea() {
        int x = this.posizioa.getX();
        int y = this.posizioa.getY();

        // Tiroen forma
        addElementua(new Etsaia(new Koordenatua(x, y)));
        addElementua(new Etsaia(new Koordenatua(x-1, y+1)));
        addElementua(new Etsaia(new Koordenatua(x+1, y+1)));
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