package game;

import modeloa.JokoKudeatzailea;
import modeloa.Tableroa;
import visual.JokoPanela;
import visual.HasierakoPantaila;
	
public class Main {
	
	public static void main(String[] args) {
		Tableroa.getTableroaEMA();
		JokoKudeatzailea.getEMA();
		JokoPanela.getEMA();
	    HasierakoPantaila.getEMA();
	}
}