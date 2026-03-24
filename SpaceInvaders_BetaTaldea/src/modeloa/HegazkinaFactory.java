package modeloa;

public class HegazkinaFactory {

	private static HegazkinaFactory nEMA = null;
	
	private HegazkinaFactory() {}
	public static HegazkinaFactory nireEMA() {
		if(nEMA == null) {nEMA = new HegazkinaFactory();}
		return nEMA;
	}
	public Hegazkina sortuHegazkina(String mota, Koordenatua pPosizioa) {
        switch (mota) {
        case "GREEN": return new HegazkinaBerdea(pPosizioa);
        case "BLUE": return new HegazkinUrdina(pPosizioa);
        case "RED": return new HegazkinGorria(pPosizioa);
        }
	}
}
