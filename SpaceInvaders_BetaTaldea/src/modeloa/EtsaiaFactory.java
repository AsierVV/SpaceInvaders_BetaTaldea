package modeloa;

public class EtsaiaFactory {

	private static EtsaiaFactory nEMA = null;
	
	private  EtsaiaFactory() {}
	public static EtsaiaFactory nireEMA() {
		if(nEMA == null) {nEMA = new EtsaiaFactory();}
		return nEMA;
	}
	public EtsaiaTaldea sortuEtsaia(String mota, Koordenatua pPosizioa, int pInd) {
		EtsaiaTaldea e = null;
        switch (mota) {
        case "BROWN": e = new EtsaiCyan(pPosizioa, pInd); break;
        case "PURPLE": e = new EtsaiMorea(pPosizioa, pInd); break;
        case "RED": e = new EtsaiGorria(pPosizioa, pInd); break;
        }
        return e;
	}
	
}

