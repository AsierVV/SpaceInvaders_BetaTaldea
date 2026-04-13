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

	@Override
	public void hutsikUtzi(Gelaxka pG) {
		pG.setEgoera(new HutsuneEgoera());
	}

	@Override
	public void jarriHegazkina(Gelaxka pG, char pHegazkinMota) {	
		//Ez de ezer egiten jadanik hegazkina dagoelako
	}

	@Override
	public void jarriEtsaia(Gelaxka pG) {		
		pG.setEgoera(new EtsaiEgoera());
	}

	@Override
	public void jarriTiroa(Gelaxka pG) {		
		pG.setEgoera(new TiroEgoera());
	}

}
