package modeloa;

import java.util.List;

public class Tiroa extends Pixel{

	public Tiroa(Koordenatua pPosizioa) {
		super(pPosizioa);
	}

	// Tiroa bakarrik mugituko da gora lerro zuzenean
	public void mugitu() {
		posizioa.setY(posizioa.getY() - 1);
	}

	@Override
	public List<Koordenatua> getKoordenatuLista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mugitu(int dx, int dy) {
		// TODO Auto-generated method stub
		
	}
}
