package modeloa;

public class HegazkinUrdina extends HegazkinaTaldea{

	public HegazkinUrdina(Koordenatua pPosizioa) {
		super(pPosizioa);
		setTiroMota(new TiroBakarra());	// Defektuz tiro bakarra
	}
}
