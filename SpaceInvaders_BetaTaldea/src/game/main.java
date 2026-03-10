package game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.TeklatuKontroladorea;
import modeloa.JokoKudeatzailea;
import modeloa.Tableroa;
import visual.JokoPanela;
import visual.HasierakoPantaila;
	
public class main {
	private static JokoKudeatzailea JK;
	private static HasierakoPantaila HP;
	private static JokoPanela JP;
	
	public static void main(String[] args) {
	    JK = JokoKudeatzailea.getEMA();
	    HP = HasierakoPantaila.getEMA();
	    JP = new JokoPanela();
	}
}

//JK instantzia eta HP instantzia ere, JK dena erabakiko du