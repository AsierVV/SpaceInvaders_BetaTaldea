package modeloa;

import java.util.List;

public class Tiroa extends Pixel{

	public Tiroa(Koordenatua pPosizioa) {
		super(pPosizioa);
	}

	@Override
	public List<Koordenatua> getKoordenatuLista() {
        return List.of(posizioa); // Lista bat sortzen du posizio honekin: hau behar dugu, beti List bat bueltatu behar dugulako
	}

	@Override
	public void mugitu(int dx, int dy) {
		posizioa.setX(posizioa.getX()+dx);
    	posizioa.setY(posizioa.getY()+dy);
	}
}
