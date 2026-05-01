package modeloa;

public class MailaProgresibo implements MailaPortaera{
	
	private int nivelProgresiboa;
	
	public MailaProgresibo(int pMailaProgresiboa) {
		nivelProgresiboa = pMailaProgresiboa;
	}
	
	@Override
	public int etsaiKopLortu() {
		return 4 + (nivelProgresiboa - 1) * 2;
	}

	@Override
	public int etsaiAbiaduraLortu() {
		if (nivelProgresiboa == 1) return 200;
		else if (nivelProgresiboa == 2) return 160;
		else if (nivelProgresiboa == 3) return 120;
		else return 100;
	}

	@Override
	public int puntuazioBiderkatzaileaLortu() {
		return nivelProgresiboa;
	}

	@Override
	public boolean etsaiekTiroEginBeharDute() {
		return nivelProgresiboa >= 2;
	}

	@Override
	public boolean mugimenduAdimendunaErabili() {
		return nivelProgresiboa >= 3;
	}
}
