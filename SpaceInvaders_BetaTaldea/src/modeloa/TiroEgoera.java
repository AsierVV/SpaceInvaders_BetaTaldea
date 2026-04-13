package modeloa;

public class TiroEgoera implements Egoera{
	
	public TiroEgoera() {}
	
	@Override
	public char getMota() {
		return 't';
	}

	@Override
	public void jarriHegazkina(Gelaxka g, char pHegazkinMota) {
		g.egoeraAldatu(new HegazkinEgoera(pHegazkinMota));
	}

	@Override
	public void jarriEtsaia(Gelaxka g) {
		g.egoeraAldatu(new EtsaiEgoera());
	}

	@Override
	public void jarriTiroa(Gelaxka g) {
		// Ez da ezer egin behar, dagoeneko tiroa dago
	}

	@Override
	public void hutsikUtzi(Gelaxka g) {
		g.egoeraAldatu(new HutsuneEgoera());
	}

}
