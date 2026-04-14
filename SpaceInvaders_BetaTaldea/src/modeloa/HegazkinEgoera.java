package modeloa;

public class HegazkinEgoera implements Egoera{
	private char hegazkinMota;
	
	public HegazkinEgoera(char pHegazkinMota) {
		hegazkinMota = pHegazkinMota;
	}
	
	@Override
	public char getMota() {
		return hegazkinMota;
	}

}
