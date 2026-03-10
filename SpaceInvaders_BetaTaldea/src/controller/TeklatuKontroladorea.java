package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modeloa.Tableroa;
import game.JokoKudeatzailea;

public class TeklatuKontroladorea implements KeyListener{
	
	private static TeklatuKontroladorea nireEMA = null;
	private JokoKudeatzailea JK;
	
	private boolean gora;
	private boolean behera;
	private boolean ezkerrera;
	private boolean eskuinera;
	private boolean tiroEgin;
	
	public static TeklatuKontroladorea getTeklatuEMA() {
    	if(nireEMA == null) {
    		nireEMA = new TeklatuKontroladorea();
    	}
    	return nireEMA;
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			ezkerrera = true;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			eskuinera = true;
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			gora = true;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			behera = true;
			break;
		case KeyEvent.VK_SPACE:
			if (JK.hasiDaJokoa()) {	
				if (!tiroEgin) {
					Tableroa.getTableroaEMA().tiroaSortu();
					tiroEgin = true;	
				}
			}else {
				JK.irekiJokoa();
			}
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			ezkerrera = false;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			eskuinera = false;
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			gora = false;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			behera = false;
			break;
		case KeyEvent.VK_SPACE:
			tiroEgin = false;
			break;
		}	
	}
	
	public boolean getEzk() {
		return ezkerrera;
	}
	public boolean getEsk() {
		return eskuinera;
	}
	public boolean getGo() {
		return gora;
	}
	public boolean getBe() {
		return behera;
	}
	public boolean getTi() {
		return tiroEgin;
	}
	
	// Añade este método dentro de TeklatuKontroladorea
	public void setJK(JokoKudeatzailea pJK) {
	    this.JK = pJK;
	}
}
