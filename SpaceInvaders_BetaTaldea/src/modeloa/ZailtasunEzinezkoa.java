package modeloa;

public class ZailtasunEzinezkoa implements ZailtasunPortaera{
	@Override
	public int etsaiKopLortu() {
		return 10;
	}

	@Override
	public int etsaiAbiaduraLortu() {
		return 100;
	}

	@Override
	public int puntuazioBiderkatzaileaLortu() {
		return 4;
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
		return 30;
	}

	@Override
	public int erronboKopLortu() {
		return 20;
	}

	@Override
	public int barreraKopLortu() {
		return 8;
	}
}
