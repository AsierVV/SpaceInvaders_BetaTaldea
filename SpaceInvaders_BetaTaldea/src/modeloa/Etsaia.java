package modeloa;

public class Etsaia extends Pixel{
	
	private boolean bizirik;
	private int mugimenduAbiadura;
	
	public Etsaia(Koordenatua pPosizioa) {
		super(pPosizioa);
		this.bizirik = true;
		this.mugimenduAbiadura = 200;
	}
    public boolean isBizirik() {
        return bizirik;
    }
    
    public void setBizirik(boolean bizirik) {
        this.bizirik = bizirik;
    }
    public int getMugimenduAbiadura() {
        return mugimenduAbiadura;
    }
    
    public void setMugimenduAbiadura(int mugimenduAbiadura) {
        this.mugimenduAbiadura = mugimenduAbiadura;
    }
	
    public void mugituEtsaia() {
        int xOrain = this.posizioa.getX();
        int yOrain = this.posizioa.getY();
        
        // Ausazko mugimendua sortu (0, 1, edo 2)
        int mugimenduMota = (int) (Math.random() * 3);
        
        int xBerria = xOrain;
        int yBerria = yOrain;
        
        switch (mugimenduMota) {
            case 0: // Behera
            	yBerria = yOrain + 1;
                break;
            case 1: // Ezkerrera
                xBerria = xOrain - 1;
                break;
            case 2: // Eskuinera
                xBerria = xOrain + 1;
                break;
        }
        
        // Koordenatuak eguneratu
        posizioa.setX(xBerria);
        posizioa.setY(yBerria);
	}
}
