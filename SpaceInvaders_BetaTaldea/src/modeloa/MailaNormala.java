package modeloa;

public class MailaNormala implements MailaPortaera{
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
	public boolean mugimenduAdimendunaErabili() {
		return false;
	}
}
