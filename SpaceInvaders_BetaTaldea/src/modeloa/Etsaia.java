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
	
    /* El override este lo he quitado de pixel, creo que no tenia mucho sentido
	@Override
	public void koordenatuakEguneratu() {
	*/
    public void mugituEtsaia() {
        int xOrain = this.posizioa.getX();
        int yOrain = this.posizioa.getY();
        
        // Ausazko mugimendua sortu (0, 1, edo 2)
        int mugimenduMota = (int) (Math.random() * 3);
        
        int xBerria = xOrain;
        // Esto es asi? Yo entiendo en el enunciado que el etsaia puede no bajar y solo ir hacia un lado, se le pregunta el lunes
        int yBerria = yOrain + 1;  // Beti jeisten du posizio 1
        
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
        
        // Koordenatuak eguneratu
        posizioa.setX(xBerria);
        posizioa.setY(yBerria);
        
        /* Con el cambio que hecho esto solo deberia de ir en tableroa, nose si esta del todo bien
        setChanged();
        notifyObservers("mugitu");
        */
	}
}
