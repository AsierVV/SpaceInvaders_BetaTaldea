package modeloa;

public class EtsaiEgoera implements Egoera{
	
	public EtsaiEgoera() {}
	
	@Override
	public char getMota() {
		// TODO Auto-generated method stub
		return 'e';
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
		//Ez du ezer egiten Etsaia jadanik dagoelako
	}

	@Override
	public void jarriTiroa(Gelaxka pG) {
		pG.setEgoera(new TiroEgoera());
	}


}
