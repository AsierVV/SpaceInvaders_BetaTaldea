package modeloa;

public class EtsaiEgoera implements Egoera{
	
	public EtsaiEgoera() {}
	
	@Override
	public char getMota() {
		return 'e';
	}

	@Override
	public void jarriHegazkina(Gelaxka g, char pHegazkinMota) {
		g.egoeraAldatu(new HegazkinEgoera(pHegazkinMota));
	}

	@Override
	public void jarriEtsaia(Gelaxka g) {
		// Etsaia bera da, ez da ezer egin behar
	}

	@Override
	public void jarriTiroa(Gelaxka g) {
		g.egoeraAldatu(new TiroEgoera());
	}

	@Override
	public void hutsikUtzi(Gelaxka g) {
		g.egoeraAldatu(new HutsuneEgoera());
	}
}
