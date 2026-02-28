package game;

import javax.swing.JFrame;
import modeloa.Tableroa;
import visual.JokoPanela;

public class main {

    public static void main(String[] args) {

        Tableroa tableroa = new Tableroa();

        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JokoPanela panel = new JokoPanela(tableroa);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}