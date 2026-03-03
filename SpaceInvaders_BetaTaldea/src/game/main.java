package game;

import javax.swing.JFrame;
import modeloa.Tableroa;
import visual.JokoPanela;

public class main {

    public static void main(String[] args) {
    	javax.swing.SwingUtilities.invokeLater(() -> {
    	    JokoPanela panel = new JokoPanela(); // crea vista y registros de observadores
    	    Tableroa tablero = Tableroa.getTableroaEMA(); // inicializa modelo y nave
    	    Tableroa.getTableroaEMA().sortuHegazkina();
    	});
    }
}