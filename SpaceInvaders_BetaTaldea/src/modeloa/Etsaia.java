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
	public void koordenatuakEguneratu() {
        int xOrain = this.pixelKoord.getKordX();
        int yOrain = this.pixelKoord.getKordY();
        
        // Generar movimiento aleatorio (0, 1, o 2)
        int mugimenduMota = (int) (Math.random() * 3);
        
        int xBerria = xOrain;
        int yBerria = yOrain + 1;  // Siempre baja 1 posición
        
        switch (mugimenduMota) {
            case 0: // Solo hacia abajo
                xBerria = xOrain;
                break;
            case 1: // Abajo-izquierda
                xBerria = xOrain - 1;
                break;
            case 2: // Abajo-derecha
                xBerria = xOrain + 1;
                break;
        }
        
        // Actualizar coordenadas
        this.pixelKoord.setX(xBerria);
        this.pixelKoord.setY(yBerria);
        
        setChanged();
        notifyObservers("mugitu");
	}
}
