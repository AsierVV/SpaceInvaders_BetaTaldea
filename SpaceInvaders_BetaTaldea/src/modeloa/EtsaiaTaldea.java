package modeloa;

import java.util.ArrayList;
import java.util.List;

public abstract class EtsaiaTaldea extends Pixel{
	private List<Pixel> pixelak = new ArrayList<Pixel>();
	private int indizea;
	
	public EtsaiaTaldea(Koordenatua pPosizioa, int pInd) {
        super(pPosizioa);
        indizea = pInd;
        sortuForma();
    }
	
	public abstract char getMotaChar();
	
	protected abstract void sortuForma();
	
	public void addElementua(Pixel p) {
		pixelak.add(p);
	}
	
	public int getIndizea() {return indizea;}

	public Koordenatua etsaiaAusazkoMugimendua() {
		int mugimenduMota = (int) (Math.random() * 3);
		Koordenatua k = new Koordenatua(0,0);
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
	
	public Koordenatua mugimenduHegazkinAdimenduaEtsaia() {
		// Hegazkinaren koordenatuak hartu
		Koordenatua kordHegazkin = Tableroa.getTableroaEMA().getHegazkina().getPosizioa();
		int xH = kordHegazkin.getX();
		
		// Mugituko dena
		int dXE = 0;
		int dYE = 0;
		
		int mugimenduMota = (int) (Math.random() * 2);
		switch (mugimenduMota) {
        	case 0: // Etsaia hurbildu hegazkinera
        		if (xH < posizioa.getX()) dXE--;
        		else if (xH > posizioa.getX()) dXE++;
        		break;
        	case 1: // Behera
        		dYE++;
        		break;
		}
		
		// Koordenatu berriak bueltatu
		return new Koordenatua(dXE, dYE);
	}
	
	public Koordenatua mugimenduTiroAdimenduaEtsaia() {
		List<Koordenatua> tiroak = Tableroa.getTableroaEMA().getTiroenKoordenatuak();
		
		if (tiroak.isEmpty()) return etsaiaAusazkoMugimendua();

		Koordenatua tiroHurbilena = null;
		int distantziaTxikiena = Integer.MAX_VALUE;
		
		for (Koordenatua k : tiroak) {
			int dx = Math.abs(k.getX() - posizioa.getX());
			int dy = Math.abs(k.getY() - posizioa.getY());
			int distantzia = dx + dy;
			
			if (distantzia < distantziaTxikiena) {
				distantziaTxikiena = distantzia;
				tiroHurbilena = k;
			}
		}
		
		if (distantziaTxikiena > 10) return etsaiaAusazkoMugimendua();
		
		// Mugituko dena
		int dXE = 0;
		int dYE = 0;
		
		if (tiroHurbilena.getX() < posizioa.getX()) dXE++;
		else if (tiroHurbilena.getX() > posizioa.getX()) dXE--;
		else {
			if (Math.random() < 0.5) dXE++;
			else dXE--;
		}
		
		// Koordenatu berriak bueltatu
		return new Koordenatua(dXE, dYE);
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