package modeloa;

public class ZailtasunErraza implements ZailtasunPortaera{
	@Override
	public int etsaiKopLortu() {
		return 4;
	}

	@Override
	public int etsaiAbiaduraLortu() {
		return 200;
	}

	@Override
	public int puntuazioBiderkatzaileaLortu() {
		return 1;
	}

	@Override
	public boolean etsaiekTiroEginBeharDute() {
		return false;
	}

	@Override
	public boolean mugimenduHegazkinAdimendunaErabili() {
		return false;
	}

	@Override
	public boolean mugimenduTiroAdimendunaErabili() {
		return true;
	}
}
