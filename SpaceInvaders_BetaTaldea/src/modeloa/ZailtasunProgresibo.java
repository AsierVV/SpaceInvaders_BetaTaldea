package modeloa;

public class ZailtasunProgresibo implements ZailtasunPortaera{
	
	private int mailaProgresiboa;
	
	public ZailtasunProgresibo(int pMailaProgresiboa) {
		mailaProgresiboa = pMailaProgresiboa;
	}
	
	@Override
	public int etsaiKopLortu() {
		return 4 + (mailaProgresiboa - 1) * 2;
	}

	@Override
	public int etsaiAbiaduraLortu() {
		if (mailaProgresiboa == 1) return 200;
		else if (mailaProgresiboa == 2) return 160;
		else if (mailaProgresiboa == 3) return 120;
		else return 100;
	}

	@Override
	public int puntuazioBiderkatzaileaLortu() {
		return mailaProgresiboa;
	}

	@Override
	public boolean etsaiekTiroEginBeharDute() {
		return mailaProgresiboa >= 2;
	}

	@Override
	public boolean mugimenduAdimendunaErabili() {
		return mailaProgresiboa >= 3;
	}
}
