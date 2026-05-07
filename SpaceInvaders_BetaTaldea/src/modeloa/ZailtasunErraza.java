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
		return false;
	}

	@Override
	public int geziKopLortu() {
		return 10;
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
