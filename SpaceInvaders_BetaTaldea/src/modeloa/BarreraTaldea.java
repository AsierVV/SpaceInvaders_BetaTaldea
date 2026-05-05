package modeloa;

import java.util.ArrayList;
import java.util.List;

public class BarreraTaldea extends Pixel {
	private List<Pixel> pixelak = new ArrayList<Pixel>();
	private boolean aktibo = false;
	
	public BarreraTaldea(Koordenatua pKoord) {
		super(pKoord);
		sortuBarreraTaldea();
	}
	
	private void sortuBarreraTaldea() {
		
        int x = this.posizioa.getX();
        int y = this.posizioa.getY() - 3;

        // Hegazkinaren forma
        addElementua(new Barrera(new Koordenatua(x, y)));
        addElementua(new Barrera(new Koordenatua(x-1, y)));
        addElementua(new Barrera(new Koordenatua(x+1, y)));
        addElementua(new Barrera(new Koordenatua(x-2, y)));
        addElementua(new Barrera(new Koordenatua(x+2, y)));
        
    }
	
	public void addElementua(Pixel p) {
		pixelak.add(p);
	}
	
	public boolean aktiboDago() {
		return aktibo;
	}
	
	public void setAktibo(boolean b) {
		aktibo = b;
	}
	
	@Override
	public List<Koordenatua> getKoordenatuLista() {
		List<Koordenatua> koordenatuak = new ArrayList<>();
        for (Pixel p : pixelak) {
            koordenatuak.addAll(p.getKoordenatuLista()); // addAll erabiltzen da "getKoordenatuak" lista bat itzultzen duelako
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
