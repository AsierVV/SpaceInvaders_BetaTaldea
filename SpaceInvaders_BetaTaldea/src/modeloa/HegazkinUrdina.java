package modeloa;

public class HegazkinUrdina extends HegazkinaTaldea{

	public HegazkinUrdina(Koordenatua pPosizioa) {
		super(pPosizioa);
		setTiroMota(new TiroBakarra());	// Defektuz tiro bakarra
	}
	
	@Override
	public void tiroMotaAldatu() {
		switch (getTiroMota().motaChar()) {
		case 'b':
			setTiroMota(new TiroRonbo());
			break;
		case 'r':
			setTiroMota(new TiroBakarra());
			break;
		}
	}
}
