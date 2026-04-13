	package modeloa;
	
	public class HutsuneEgoera implements Egoera{
		
		public HutsuneEgoera() {}
		
		@Override
		public char getMota() {
			// TODO Auto-generated method stub
			return 'u';
		}
	
		@Override
		public void hutsikUtzi(Gelaxka pG) {
			//Ez de ezer egiten jadanik hutsunea dagoelako
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
			pG.setEgoera(new TiroEgoera());
		}
	
	}
