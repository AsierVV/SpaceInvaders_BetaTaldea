package modeloa;

public class Koordenatua {
	private int kordX;
	private int kordY;
	
	public Koordenatua(int pX, int pY){
		this.kordX = pX;
		this.kordY = pY;
	}
	
	public int getX() {
		return this.kordX;
	}
	public int getY() {
		return this.kordY;
	}
	public void setX(int pX) {
		this.kordX = pX;
	}
	public void setY(int pY) {
		this.kordY = pY;
	}
}
