package modeloa;

public class HutsuneEgoera implements Egoera{
	
	public HutsuneEgoera() {}
	
	@Override
	public char getMota() {
		return 'u';
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
		g.egoeraAldatu(new TiroEgoera());
	}

	@Override
	public void hutsikUtzi(Gelaxka g) {
		// Ez da ezer egin behar, dagoeneko hutsunea dago
	}

}