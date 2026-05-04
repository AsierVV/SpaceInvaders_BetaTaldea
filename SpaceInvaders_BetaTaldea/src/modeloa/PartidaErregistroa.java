package modeloa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PartidaErregistroa {

	private static final String fitxategia = "partiden_erregistroa.txt";
	
	// Metodo static erabiltzen dugu edozein lekutik deitzeko eta eraikitzailea ez erabiltzeko.
	// Izan ere, ez dugu nahi partida bakoitzean klase honetako objektu berri bat sortzea.
	public static void gordePartida(String pJokalari, String pEmaitza, int pPuntuazioa, int pDenbora,
									String pZailtasuna, String pHegazkinMota, String pEtsaiMota) {
		// Denbora segundutik --> mm:ss -ra pasatu
		int min = pDenbora/60;
		int seg = pDenbora%60;
		String dataString = String.format("%02d:%02d", min, seg);	// Partida denbora beti izateko mm:ss formatoa

		
		String pData = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
			// LocalDateTime.now() --> Momentuko data eta ordua hartzen dugu
			// .format() --> Nahi dugun formatoa ezartzeko
			// DateTimeFormatter.ofPattern(...) --> Nahi dugun formatoa sortzeko
		
		
		// try-catch erabiltzen dugu fitxategiekin lan egitean erroreak gerta daitezkeelako.
		// Java-k try-catch erabiltzera behartzen gaitu, IOException bat gerta daitekeelako.
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fitxategia, true));

			writer.println(pJokalari + "#" +  pData + "#" + pEmaitza + "#" + pZailtasuna + "#" + pPuntuazioa + "#" + dataString + "#" + pHegazkinMota + "#" + pEtsaiMota);

			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}