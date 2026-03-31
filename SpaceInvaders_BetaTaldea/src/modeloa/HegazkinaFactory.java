package modeloa;

public class HegazkinaFactory {

	private static HegazkinaFactory nEMA = null;
	
	private HegazkinaFactory() {}
	public static HegazkinaFactory nireEMA() {
		if(nEMA == null) {nEMA = new HegazkinaFactory();}
		return nEMA;
	}
	public static Hegazkina sortuHegazkina(String mota, Koordenatua pPosizioa) {
		Hegazkina h = null;
        switch (mota) {
        case "GREEN": h= new HegazkinaBerdea(pPosizioa); break;
        case "BLUE": h= new HegazkinUrdina(pPosizioa); break;
        case "RED": h= new HegazkinGorria(pPosizioa); break;
        }
        return h;
	}
}
