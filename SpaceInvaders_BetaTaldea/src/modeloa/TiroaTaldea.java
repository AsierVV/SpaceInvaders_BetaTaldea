package modeloa;

import java.util.ArrayList;
import java.util.List;

public class TiroaTaldea extends Pixel{
	private List<Pixel> pixelak = new ArrayList<>();
	private int dy;
	
	public TiroaTaldea(List<Koordenatua> pKoordenatuak, int dy) {
		// Super hau egiterakoan koordenatuaren kopia berria sortu behar dugu, bestela arazoak ematen ditu koordenatuak pasatzerakoan
	    super(new Koordenatua(pKoordenatuak.get(0).getX(), pKoordenatuak.get(0).getY()));
	    this.dy = dy;
	    sortuTiroaTaldea(pKoordenatuak);
    }
	
	private void sortuTiroaTaldea(List<Koordenatua> pKoordenatuak) {
        pKoordenatuak.stream()
        	.map(k -> new Tiroa(new Koordenatua(k.getX(), k.getY())))	// Tiro taldearen pixel berri bakoitza sortu
        	.forEach(this::addElementua);;	// Pixel berri bakoitza sartu "pixelak" listan
    }
	public int getDy() {
	    return dy;
	}
	
	public void addElementua(Pixel p) {
		pixelak.add(p);
	}
	
	// Tiroa hegazkinarena den jakiteko --> dy = -1 bada, tiroa gora doa, beraz hegazkinarena da
	public boolean hegazkinarenaDa() {
	    return dy == -1;
	}

	// Tiroa etsaiarena den jakiteko --> dy = 1 bada, tiroa behera doa, beraz etsaiarena da
	public boolean etsaiarenaDa() {
	    return dy == 1;
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