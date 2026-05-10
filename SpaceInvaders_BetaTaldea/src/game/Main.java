package game;

import visual.JokoPanela;
import visual.PuntuazioPantaila;
import modeloa.JokoKudeatzailea;
import visual.HasierakoPantaila;
import visual.JokalariErregistroa;
	
public class Main {
	
	public static void main(String[] args) {
		JokoKudeatzailea.getEMA();
		JokoPanela.getEMA();
		PuntuazioPantaila.getEMA();
		JokalariErregistroa.getEMA();
	}
}