package modeloa;

import java.util.List;

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
        case "CYAN": e = new EtsaiCyan(pPosizioa, pInd); break;
        case "PURPLE": e = new EtsaiMorea(pPosizioa, pInd); break;
        case "RED": e = new EtsaiGorria(pPosizioa, pInd); break;
        }
        return e;
	}
	
	public List<Koordenatua> sortuKoordenatuak(String mota, Koordenatua pos){
		switch (mota) {
        case "CYAN":
        	return EtsaiCyan.sortuKoordenatuak(pos);
        case "PURPLE":
        	return EtsaiMorea.sortuKoordenatuak(pos);
        case "RED":
        	return EtsaiGorria.sortuKoordenatuak(pos);
        default:
        	return EtsaiGorria.sortuKoordenatuak(pos);
        }
	}
	
}

