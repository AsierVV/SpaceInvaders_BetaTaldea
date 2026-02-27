package modeloa;

public abstract class Pixel{
	
	protected Koordenatua posizioa;
	
    public Pixel(Koordenatua pPosizioa) {
        this.posizioa = pPosizioa;
    }
    
    public Koordenatua getPosizioa() {
        return posizioa;
    }
    
    public void setPosizioa(Koordenatua pPosizioa) {
    	this.posizioa = pPosizioa;
    }
    
}
