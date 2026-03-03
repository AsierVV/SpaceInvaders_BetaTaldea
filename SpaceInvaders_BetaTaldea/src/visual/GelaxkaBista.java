package visual;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class GelaxkaBista extends JLabel implements Observer{
	
	public GelaxkaBista() {
		JLabel j = new JLabel();
		j.setText("aaaa");
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
