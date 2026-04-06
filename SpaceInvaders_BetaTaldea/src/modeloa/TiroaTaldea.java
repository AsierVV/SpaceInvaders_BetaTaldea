package modeloa;

import java.util.ArrayList;
import java.util.List;

public class TiroaTaldea extends Pixel{
	private List<Pixel> pixelak = new ArrayList<>();
	
	public TiroaTaldea(List<Koordenatua> pKoordenatuak) {
		// Super hau egiterakoan koordenatuaren kopia berria sortu behar dugu, bestela arazoak ematen ditu koordenatuak pasatzerakoan
        super(new Koordenatua(pKoordenatuak.get(0).getX(),pKoordenatuak.get(0).getY()));	// Listaren lehenengo koordenatua hartzen du, eta pixel ama klaseak posizioa bezala gordetzen du
        sortuTiroaTaldea(pKoordenatuak);
    }
	
	private void sortuTiroaTaldea(List<Koordenatua> pKoordenatuak) {
        for (Koordenatua k : pKoordenatuak) {
        	addElementua(new Tiroa(new Koordenatua(k.getX(),k.getY())));	// Elementu berria gehitzerakoan koordenatuaren kopia berria sortu behar dugu, bestela arazoak ematen ditu
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