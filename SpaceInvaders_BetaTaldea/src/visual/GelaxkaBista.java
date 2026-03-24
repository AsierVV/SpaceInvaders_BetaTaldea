package visual;

import modeloa.Gelaxka;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class GelaxkaBista extends JLabel implements Observer{
	
	public GelaxkaBista() {
		setForeground(new Color(0, 0, 0));
		this.setOpaque(true);
		this.setBackground(Color.BLACK); 
		this.setSize(1,1);
	}

	private void eguneratuKolorea(char mota) {
		switch (mota) {
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
	
	@Override
	public void update(Observable o, Object arg) {
        eguneratuKolorea(((Gelaxka) o).getMota());
	}

}
