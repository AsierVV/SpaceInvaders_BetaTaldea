package modeloa;

public class EtsaiEgoera implements Egoera{
	private char etsaiMota;
	
	public EtsaiEgoera(char pEtsaiMota) {
		this.etsaiMota = pEtsaiMota;
	}
	
	@Override
	public char getMota() {
		return etsaiMota;
	}
}
