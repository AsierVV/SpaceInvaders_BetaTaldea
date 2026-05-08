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

        // Barreraren forma
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
		return pixelak.stream()
			// Pixel bakoitzak koordenatuen lista bat bueltatzen duenez, flatMap erabiltzen dugu lista txiki guztiak lista bakar batean elkartzeko.
			.flatMap(p -> p.getKoordenatuLista().stream())
			.toList();
	}

	@Override
	public void mugitu(int dx, int dy) {
		posizioa.setX(posizioa.getX()+dx);
    	posizioa.setY(posizioa.getY()+dy);
		
    	pixelak.stream().forEach(p -> p.mugitu(dx, dy));
	}
	

}
