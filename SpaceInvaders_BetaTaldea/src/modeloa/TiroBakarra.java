package modeloa;

public class TiroBakarra implements TiroMota {
	@Override
	public void tiroEgin(int x, int y) {
		Tableroa.getTableroaEMA().getGelaxka(x, y).jarriTiroa();
	}

}
