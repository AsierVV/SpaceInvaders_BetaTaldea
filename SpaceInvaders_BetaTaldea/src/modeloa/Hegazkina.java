package modeloa;

import java.util.List;

public class Hegazkina extends Pixel{
	private TiroMota mota;
	
	protected Hegazkina(Koordenatua pPosizioa) {
		super(pPosizioa);
		this.mota= new TiroBakarra();
	}
	
	public TiroMota getMota() {
		return this.mota;
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