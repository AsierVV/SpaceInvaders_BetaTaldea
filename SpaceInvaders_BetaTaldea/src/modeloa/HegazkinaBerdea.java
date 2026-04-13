package modeloa;

public class HegazkinaBerdea extends HegazkinaTaldea{

	public HegazkinaBerdea(Koordenatua pPosizioa) {
		super(pPosizioa);
		setTiroMota(new TiroBakarra());	// Defektuz tiro bakarra
	}
	
	@Override
	public void tiroMotaAldatu() {
		switch (getTiroMota().motaChar()) {
		case 'b':
			setTiroMota(new TiroTriple());
			break;
		case 't':
			setTiroMota(new TiroBakarra());
			break;
		}
	}

	@Override
	public char getMotaChar() {
		return 'g';
	}
}
