package modeloa;

public class ZailtasunZaila implements ZailtasunPortaera{
	@Override
	public int etsaiKopLortu() {
		return 8;
	}

	@Override
	public int etsaiAbiaduraLortu() {
		return 120;
	}

	@Override
	public int puntuazioBiderkatzaileaLortu() {
		return 3;
	}

	@Override
	public boolean etsaiekTiroEginBeharDute() {
		return true;
	}

	@Override
	public boolean mugimenduHegazkinAdimendunaErabili() {
		return false;
	}

	@Override
	public boolean mugimenduTiroAdimendunaErabili() {
		return true;
	}

	@Override
	public int geziKopLortu() {
		return 20;
	}

	@Override
	public int erronboKopLortu() {
		return 10;
	}

	@Override
	public int barreraKopLortu() {
		return 5;
	}
}
