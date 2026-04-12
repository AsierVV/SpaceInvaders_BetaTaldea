package modeloa;

public class HegazkinGorria extends HegazkinaTaldea {

	public HegazkinGorria(Koordenatua pPosizioa) {
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
			setTiroMota(new TiroRonbo());
			break;
		case 'r':
			setTiroMota(new TiroBakarra());
			break;
		}
	}
}
