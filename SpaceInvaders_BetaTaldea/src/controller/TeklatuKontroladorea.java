package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modeloa.Tableroa;

public class TeklatuKontroladorea implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int dx = 0;
		int dy = 0;
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				dx--;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				dx++;
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				dy--;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				dy++;
				break;
			case KeyEvent.VK_SPACE:
				Tableroa.getTableroaEMA().tiroaSortu();
				break;
		}
		if (dx!=0 || dy!=0) Tableroa.getTableroaEMA().mugituHegazkina(dx, dy);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
