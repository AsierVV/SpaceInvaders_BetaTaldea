package modeloa;

public class HegazkinaBerdea extends HegazkinaTaldea{

	public HegazkinaBerdea(Koordenatua pPosizioa) {
		super(pPosizioa);
		setTiroMota(new TiroBakarra());	// Defektuz tiro bakarra
	}
}
