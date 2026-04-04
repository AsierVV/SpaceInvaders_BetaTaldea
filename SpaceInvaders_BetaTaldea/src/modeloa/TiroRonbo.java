package modeloa;

import java.util.ArrayList;
import java.util.List;

public class TiroRonbo implements TiroMota {
	
	@Override
	public List<Koordenatua> sortuKoordenatuak(Koordenatua jatorria) {
		
		int x = jatorria.getX();
		int y = jatorria.getY();
		
		List<Koordenatua> tiroForma = new ArrayList<>();
		
		tiroForma.add(new Koordenatua(x, y));
		tiroForma.add(new Koordenatua(x, y+1));
		tiroForma.add(new Koordenatua(x, y+2));
		tiroForma.add(new Koordenatua(x, y-1));
		tiroForma.add(new Koordenatua(x, y-2));
		tiroForma.add(new Koordenatua(x+1, y));
		tiroForma.add(new Koordenatua(x+2, y));
		tiroForma.add(new Koordenatua(x-1, y));
		tiroForma.add(new Koordenatua(x-2, y));
		tiroForma.add(new Koordenatua(x+1, y-1));
		tiroForma.add(new Koordenatua(x-1, y-1));
		tiroForma.add(new Koordenatua(x+1, y+1));
		tiroForma.add(new Koordenatua(x-1, y+1));
		
		return tiroForma;
	}

	@Override
	public char mota() {
		return 'r';
	}
}
