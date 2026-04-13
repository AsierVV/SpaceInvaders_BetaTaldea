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
	public void jarriHegazkina(Gelaxka g, char pHegazkinMota) {
		// Ez da ezer egin behar, dagoeneko hegazkina dago		
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
		g.egoeraAldatu(new HutsuneEgoera());
	}

}
