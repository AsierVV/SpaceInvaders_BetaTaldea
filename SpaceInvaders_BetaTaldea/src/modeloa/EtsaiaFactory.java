package modeloa;

public class EtsaiaFactory {

	private static EtsaiaFactory nEMA = null;
	
	private  EtsaiaFactory() {}
	public static EtsaiaFactory nireEMA() {
		if(nEMA == null) {nEMA = new EtsaiaFactory();}
		return nEMA;
	}
	public static Etsaia sortuEtsaia(String mota, Koordenatua pPosizioa) {
		Etsaia e = null;
        switch (mota) {
        case "BROWN": e= new EtsaiMarroia(pPosizioa); break;
        case "PURPLE": e= new EtsaiMorea(pPosizioa); break;
        case "RED": e= new EtsaiGorria(pPosizioa); break;
        }
        return e;
	}
}

