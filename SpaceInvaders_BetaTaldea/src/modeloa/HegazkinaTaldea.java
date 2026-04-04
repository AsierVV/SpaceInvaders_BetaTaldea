package modeloa;

import java.util.ArrayList;
import java.util.List;

public class HegazkinaTaldea extends Pixel{
	private List<Pixel> pixelak = new ArrayList<Pixel>();
	private TiroMota mota;
	
	// HEMEN JAR DEZAKEGU ERAIKITZAILEAK char BAT HARTZEA FACTORY-an AUKERATUTAKO HEGAZKINA KONTUAN
	// HARTZEKO; ETA HORRELA HEGAZKIN DESBERDINAK EGIN DITZAKEGU (EZ DAKIT HORI ERABILPEN EGOKIA DEN ALA EZ)
	public HegazkinaTaldea(Koordenatua pPosizioa) {
        super(pPosizioa);
        this.mota = new TiroBakarra();
        sortuHegazkinaTaldea();
    }
	
	private void sortuHegazkinaTaldea() {
        int x = this.posizioa.getX();
        int y = this.posizioa.getY();

        // Hegazkinaren forma
        addElementua(new Hegazkina(new Koordenatua(x, y)));
        addElementua(new Hegazkina(new Koordenatua(x-1, y)));
        addElementua(new Hegazkina(new Koordenatua(x+1, y)));
        addElementua(new Hegazkina(new Koordenatua(x, y+1)));
        addElementua(new Hegazkina(new Koordenatua(x-1, y+1)));
        addElementua(new Hegazkina(new Koordenatua(x+1, y+1)));
        addElementua(new Hegazkina(new Koordenatua(x-1, y-1)));
        addElementua(new Hegazkina(new Koordenatua(x+1, y-1)));
        addElementua(new Hegazkina(new Koordenatua(x-2, y+1)));
        addElementua(new Hegazkina(new Koordenatua(x+2, y+1)));
    }
	
	public void addElementua(Pixel p) {
		pixelak.add(p);
	}
	
	public TiroMota getMota() {
		return this.mota;
	}
	
	public void setTiroMota(TiroMota pMota) {
		this.mota = pMota;
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