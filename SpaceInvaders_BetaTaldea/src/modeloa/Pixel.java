package modeloa;

import java.util.List;

public abstract class Pixel {
	
	protected Koordenatua posizioa;
	
    public Pixel(Koordenatua pPosizioa) {
        this.posizioa = pPosizioa;
    }
    
    public Koordenatua getPosizioa() { // Metodoa hartzeko pixel konkretu baten posizioa (agian kenduko dugu gero ez delako beharrezkoa multipixelarekin)
        return posizioa;
    }
    
    public void setPosizioa(Koordenatua pPosizioa) {
    	this.posizioa = pPosizioa;
    }
    
    public abstract List<Koordenatua> getKoordenatuLista();	// Multipixel guztien koordenatuak
    
    public abstract void mugitu(int dx, int dy);
}
