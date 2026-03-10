package game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import modeloa.JokoKudeatzailea;
import modeloa.Tableroa;
import visual.JokoPanela;
import visual.HasierakoPantaila;
	
public class main {
	private static JokoKudeatzailea JK;
	private static HasierakoPantaila HP;
	
	public static void main(String[] args) {
	    JK = new JokoKudeatzailea();
	    HP = new HasierakoPantaila();
	}
}

//JK instantzia eta HP instantzia ere, JK dena erabakiko du