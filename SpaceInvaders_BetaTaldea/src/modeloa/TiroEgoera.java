package modeloa;

public class TiroEgoera implements Egoera{
	
	public TiroEgoera() {}
	
	@Override
	public char getMota() {
		return 't';
	}

	@Override
	public void hutsikUtzi(Gelaxka pG) {
		pG.setEgoera(new HutsuneEgoera());
	}

	@Override
	public void jarriHegazkina(Gelaxka pG, char pHegazkinMota) {
		pG.setEgoera(new HegazkinEgoera(pHegazkinMota));
	}

	@Override
	public void jarriEtsaia(Gelaxka pG) {
		pG.setEgoera(new EtsaiEgoera());
	}

	@Override
	public void jarriTiroa(Gelaxka pG) {
		//Ez da ezer egiten jadanik tiroa dagoelako
	}

}
