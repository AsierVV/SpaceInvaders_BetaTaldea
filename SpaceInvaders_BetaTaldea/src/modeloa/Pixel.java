package modeloa;
import java.util.Observable;

public abstract class Pixel extends Observable{
	protected Koordenatua pixelKoord;
	
    public Pixel(Koordenatua k) {
        this.pixelKoord = k;
    }
    
    public Koordenatua getPosizioa() {
        return pixelKoord;
    }
    
    public abstract void koordenatuakEguneratu();
}
