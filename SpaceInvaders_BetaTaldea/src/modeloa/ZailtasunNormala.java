package modeloa;

public class ZailtasunNormala implements ZailtasunPortaera{
	@Override
	public int etsaiKopLortu() {
		return 8;
	}

	@Override
	public int etsaiAbiaduraLortu() {
		return 160;
	}

	@Override
	public int puntuazioBiderkatzaileaLortu() {
		return 2;
	}

	@Override
	public boolean etsaiekTiroEginBeharDute() {
		return true;
	}

	@Override
	public boolean mugimenduHegazkinAdimendunaErabili() {
		return true;
	}

	@Override
	public boolean mugimenduTiroAdimendunaErabili() {
		return false;
	}

	@Override
	public int geziKopLortu() {
		return 20;
	}

	@Override
	public int erronboKopLortu() {
		return 5;
	}

	@Override
	public int barreraKopLortu() {
		return 3;
	}
}
