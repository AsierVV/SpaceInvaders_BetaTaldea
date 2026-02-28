package visual;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import modeloa.Tableroa;
import modeloa.Pixel;
import modeloa.Hutsunea;
import modeloa.Hegazkina;
import modeloa.Etsaia;
import modeloa.Tiroa;

public class JokoPanela extends JPanel implements Observer {

    private Tableroa tablero;
    private final int TAM_CELDA = 10;

    public JokoPanela(Tableroa tableroa) {
        this.tablero = tableroa;
        this.tablero.addObserver(this);

        setPreferredSize(new Dimension(100 * TAM_CELDA, 60 * TAM_CELDA));
        setBackground(Color.BLACK);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 60; y++) {

                Pixel p = tablero.getGelaxka(x, y).getMota();

                if (p instanceof Hutsunea) {
                    g.setColor(Color.BLACK);
                } 
                else if (p instanceof Hegazkina) {
                    g.setColor(Color.GREEN);
                } 
                else if (p instanceof Etsaia) {
                    g.setColor(Color.RED);
                } 
                else if (p instanceof Tiroa) {
                    g.setColor(Color.YELLOW);
                }

                g.fillRect(x * TAM_CELDA, y * TAM_CELDA, TAM_CELDA, TAM_CELDA);
            }
        }
    }
}