package modeloa;

public class MailaZaila implements MailaPortaera{
	@Override
	public int etsaiKopLortu() {
		return 12;
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
	public boolean mugimenduAdimendunaErabili() {
		return true;
	}
}
