package visual;

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

public class JokoPanela extends JPanel implements Observer {
	private JPanel panel;
	
	public JokoPanela() {
        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
        frame.setVisible(true);
		this.panel = new JPanel(); 
		matrizeakSortu();
	}
	
	private JLabel getLblNewLabel(int x, int y) { //sarrera parametroak
		JLabel newLabel = new JLabel("a"); //botoiaren testua
		getema.getgelaxka(x,y).addobserver;
		return newLabel;
	}
	
	private void matrizeakSortu() {
		for(int i=0;i<10;i++) {
			for(int z=0;z<10;z++) {
					panel.add(getLblNewLabel(i,z));
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
 }