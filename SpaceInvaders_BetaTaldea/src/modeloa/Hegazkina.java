package modeloa;

public class Hegazkina extends Pixel{

    private int max_X = 99;
    private int max_Y = 59;

    public Hegazkina(Koordenatua k) {
    	super(k);
    }

    public Koordenatua getPosizioa() {
        return pos;
    }

    public void mugituGora() {
        if (pos.getKordY() > 0) {
            pos.setY(pos.getKordY() - 1);
        }
    }

    public void mugituBehera() {
        if (pos.getKordY() < max_Y) {
            pos.setY(pos.getKordY() + 1);
        }
    }

    public void mugituEzkerra() {
        if (pos.getKordX() > 0) {
            pos.setX(pos.getKordX() - 1);
        }
    }

    public void mugituEskuina() {
        if (pos.getKordX() < max_X) {
            pos.setX(pos.getKordX() + 1);
        }
    }

	@Override
	public void koordenatuakEguneratu() {}
}