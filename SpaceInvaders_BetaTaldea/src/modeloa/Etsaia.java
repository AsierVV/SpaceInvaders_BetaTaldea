package modeloa;

public class Etsaia extends Pixel{
	private boolean bizirik;
	private int mugimenduAbiadura;
	
	public Etsaia(Koordenatua k) {
		super(k);
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
	
	@Override
	public void koordenatuakEguneratu() {}
}
