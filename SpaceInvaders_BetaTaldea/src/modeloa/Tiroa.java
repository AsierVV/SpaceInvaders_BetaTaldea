package modeloa;

public class Tiroa extends Pixel{

	public Tiroa(Koordenatua pPosizioa) {
		super(pPosizioa);
	}

	// Tiroa bakarrik mugituko da gora lerro zuzenean
	public void mugitu() {
		posizioa.setY(posizioa.getY() - 1);
	}
}
