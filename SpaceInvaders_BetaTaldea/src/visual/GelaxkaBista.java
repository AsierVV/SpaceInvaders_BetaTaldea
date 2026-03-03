package visual;

import modeloa.Gelaxka;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class GelaxkaBista extends JLabel implements Observer{
	
	public GelaxkaBista() {
		this.setOpaque(true);
		this.setBackground(Color.black); 
		this.setSize(1,1);
	}

	@Override
	public void update(Observable o, Object arg) {
        Gelaxka g = (Gelaxka) o;

        switch (g.getMota()) {
            case 'u':
                setBackground(Color.BLACK);
                break;
            case 'h':
                setBackground(Color.GREEN);
                break;
            case 'e':
                setBackground(Color.RED);
                break;
            case 't':
                setBackground(Color.WHITE);
                break;
        }
	}

}
