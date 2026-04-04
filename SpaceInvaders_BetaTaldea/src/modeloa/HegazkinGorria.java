package modeloa;

public class HegazkinGorria extends HegazkinaTaldea {

	public HegazkinGorria(Koordenatua pPosizioa) {
		super(pPosizioa);
		setTiroMota(new TiroBakarra());	// Defektuz tiro bakarra
	}
}
