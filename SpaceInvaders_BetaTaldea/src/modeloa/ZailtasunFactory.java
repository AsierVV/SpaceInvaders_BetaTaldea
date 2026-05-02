package modeloa;

public class ZailtasunFactory {

	private static ZailtasunFactory nEMA = null;
	
	private ZailtasunFactory() {}
	
	public static ZailtasunFactory nireEMA() {
		if(nEMA == null) {nEMA = new ZailtasunFactory();}
		return nEMA;
	}
	
	public ZailtasunPortaera sortuZailtasuna(String pZailtasunMota) {
		// Estrategia desberdina aukeratu zailtasun motaren arabera
		switch (pZailtasunMota) {
		case "Erraza":
			return new ZailtasunErraza();
		case "Normala":
			return new ZailtasunNormala();
		case "Zaila":
			return new ZailtasunZaila();
		case "Ezinezkoa":
			return new ZailtasunEzinezkoa();
		case "Progresiboa": 
			return  new ZailtasunProgresibo(JokoKudeatzailea.getEMA().getMailaProgresiboa());
		default:
			return  new ZailtasunErraza();
		}
	}
}