package modeloa;

import java.util.ArrayList;
import java.util.List;

public class TiroBakarra implements TiroMota {

	@Override
	public List<Koordenatua> sortuKoordenatuak(Koordenatua jatorria) {
		
		int x = jatorria.getX();
		int y = jatorria.getY();
		
		List<Koordenatua> tiroForma = new ArrayList<>();
		
		tiroForma.add(new Koordenatua(x,y));
		
		return tiroForma;
	}

	@Override
	public char mota() {
		return 'b';
	}

}
