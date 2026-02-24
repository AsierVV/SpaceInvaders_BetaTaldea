import java.util.Observable;

public abstract class Pixel extends Observable{
	protected Koordenatua pixelKoord;
	
    public Pixel() {
        this.pixelKoord = null;
    }
    
    public Koordenatua getPosizioa() {
        return pixelKoord;
    }
    
    public abstract void koordenatuakEguneratu();
}
