package modeloa;

public class Hegazkina extends Pixel{
	
	public Hegazkina(Koordenatua pPosizioa) {
		super(pPosizioa);
	}
	
	public void mugituEzkerrera() {
		posizioa.setX(posizioa.getX() - 1);
	}
	
	public void mugituEskuinera() {
		posizioa.setX(posizioa.getX() + 1);
	}
	
	public void mugituGora() {
		posizioa.setY(posizioa.getY() - 1);
	}
	
	public void mugituBehera() {
		posizioa.setY(posizioa.getY() + 1);
	}
}