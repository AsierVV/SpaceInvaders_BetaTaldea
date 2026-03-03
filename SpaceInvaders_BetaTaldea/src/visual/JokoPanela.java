package visual;

import modeloa.Tableroa;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

public class JokoPanela extends JPanel implements Observer{
	private JPanel panel;
	
	public JokoPanela() {
        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new GridLayout(60, 100, 0, 0));
        frame.setVisible(true);
		this.panel = new JPanel(); 
		matrizeakSortu();
	}
	
	private JLabel getLblNewLabel(int x, int y) { //sarrera parametroak
		GelaxkaBista newLabel = new GelaxkaBista(); //botoiaren testua
		Tableroa.getTableroaEMA().getGelaxka(x,y).addObserver(newLabel);
		return newLabel;
	}
	
	private void matrizeakSortu() {
		for(int x=0;x<100;x++) {
			for(int y=0;y<60;y++) {
					panel.add(getLblNewLabel(x,y));
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
 }