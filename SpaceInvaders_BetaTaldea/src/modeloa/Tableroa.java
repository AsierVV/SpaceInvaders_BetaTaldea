package modeloa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import controller.TeklatuKontroladorea;


public class Tableroa extends Observable {

	private static Tableroa nireEMA = null;
	private Gelaxka[][] tableroMatrizea;
	private Hegazkina hegazkina;
	private List<Etsaia> etsaiak;
	private List<Tiroa> tiroak;
	
	private Timer timer;
	private final int abiaduraTimer = 50;	//50ms
	
	private int mugituHegazkinaKont = 1;	//100ms, beraz 50ms * 2 --> Batean hasi parametroa, horrela 50ms-ko timerra deitzen den bigarren aldian 100ms pasatu dira.
	private int mugituEtsaiakKont = 1;		//200ms, beraz 100ms * 2
	private int tiroEginKont = 1;			//400ms, beraz 200ms * 2
	
	private final int zabalera = 100;
    private final int altuera = 60;

    private boolean gameOver;
    
    // === ERAIKITZAILEA ===
    private Tableroa() {
        tableroMatrizea = new Gelaxka[zabalera][altuera];
        etsaiak = new ArrayList<>();
        tiroak = new ArrayList<>();
        gameOver = false;
        
        // --- MATRIZEA SORTU ---
        for (int i = 0; i < zabalera; i++) {
            for (int j = 0; j < altuera; j++) {
            	tableroMatrizea[i][j] = new Gelaxka(new Koordenatua(i,j),'u');
            }
        }
        
        // --- TIMERRRA ERAIKI ---
        timer = new Timer(abiaduraTimer, new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		
        		// 50ms-ro mugitu tiroak 
        		mugituTiroak();
        		
        		// Konprobatu 100ms pasatu diren
        		if (mugituHegazkinaKont <= 0) {
        			mugituHegazkinaControl();
        			mugituHegazkinaKont = 1;
        			
        			// Konprobaty 200ms pasatu diren
        			if (mugituEtsaiakKont <= 0) {
        				mugituEtsaiak();
        				mugituEtsaiakKont = 1;
        				
        				// Konprobatu 400ms pasatu diren
        				if (tiroEginKont <= 0) {
        					tiroakSortu();
        					tiroEginKont = 1;
        				} else tiroEginKont--;
        				
        			} else mugituEtsaiakKont--;
        			
        		} else mugituHegazkinaKont--;
            }
        });
    }
    
    // === GET EMA ===
    public static Tableroa getTableroaEMA() {
    	if(nireEMA == null) {
    		nireEMA = new Tableroa();
    	}
    	return nireEMA;
    }
    
    // === METODO LAGUNGARRIAK ===
    public Gelaxka getGelaxka(int x, int y) {
    	return tableroMatrizea[x][y];
    }
	
    public Hegazkina getHegazkina() {
    	return hegazkina;
	}
	 
    public List<Etsaia> getEtsaiak() {
    	return etsaiak;
	}
	 
    public List<Tiroa> getTiroak() {
    	return tiroak;
	}
    
    public int getZabalera() {
    	return this.zabalera;
    }
    
    public int getAltuera() {
    	return this.altuera;
    }
    
    // === JOKOA HASTEKO ETA GELDITZEKO METODOAK ===
    public void hasiJokoa() {
    	sortuHegazkina();
    	sortuEtsaiak();
        //if (!timerEtsaiak.isRunning()) timerEtsaiak.start();
        if (!timer.isRunning()) timer.start();
    }

    public void startStopJokoa() {
    	if (timer.isRunning()) timer.stop();
    	else timer.start();
        /*
    	if (timerEtsaiak.isRunning()) timerEtsaiak.stop();
        else timerEtsaiak.start();
        if (timerTiroak.isRunning()) timerTiroak.stop();
        else timerTiroak.start();
        */
    }
	 
	// === HEGAZKINA SORTU ===
    public void sortuHegazkina() {
    	hegazkina = new Hegazkina(new Koordenatua(50,55));
    	tableroMatrizea[50][55].setMota('h');
	}
	 
	// === ETSAIAK SORTU ===
	private void sortuEtsaiak() {
		Random r = new Random();
	    int kopurua = 4 + r.nextInt(5); // 4-8 etsai
	
	    while (etsaiak.size() < kopurua) {
	    	int zutabea = r.nextInt(zabalera);
	
	    	// Konprobatu ea hutsik dagoen gelaxka
	        if (tableroMatrizea[zutabea][5].getMota()=='u') {
	        	Etsaia e = new Etsaia(new Koordenatua(zutabea, 5));
	            etsaiak.add(e);
	            tableroMatrizea[zutabea][5].setMota('e');
	        }
	    }
	}    
	 
	// === TIRO BAT SORTU ===
	public void tiroaSortu() {
		int x = hegazkina.getPosizioa().getX();
		int y = hegazkina.getPosizioa().getY() - 2; //Hegazkinaren gainean sortzeko
	 		
	 	if (posizioBaliozkoa(x, y) && !(tableroMatrizea[x][y].getMota()=='t')) {
	 		Tiroa t = new Tiroa(new Koordenatua(x, y)); // Tiroa sortzen du
	 		tiroak.add(t);	// Tiroa "tiroak" listan sartzen du
	 		tableroMatrizea[x][y].setMota('t');	// Gelaxka eguneratzen du tableroan
	 	}
	 }
	 
	 // === MUGIMENDU EGOKIA DEN ALA EZ EGIAZTATZEKO ===
	 private boolean posizioBaliozkoa(int x, int y) {
		 return x >= 0 && x < zabalera && y >= 0 && y < altuera;
	 }
	 
	 // === HEGAZKINAREN MUGIMENDUA ===
	 public void mugituHegazkina(int dx, int dy) {
		 int xZaharra = hegazkina.getPosizioa().getX();
	     int yZaharra = hegazkina.getPosizioa().getY();
	
	     int xBerria = xZaharra + dx;
	     int yBerria = yZaharra + dy;
	     
	     if (posizioBaliozkoa(xBerria, yBerria)) {
	    	 char mota = tableroMatrizea[xBerria][yBerria].getMota();
	    	 
	         //si hay enemigo pierdes
	         if (mota == 'e') {
	             partidaGaldu();
	             return;
	         }
	         // mover solo si está vacío
	         if (mota == 'u') {
	    	 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
	    	 hegazkina.getPosizioa().setX(xBerria);
	    	 hegazkina.getPosizioa().setY(yBerria);
	    	 tableroMatrizea[xBerria][yBerria].setMota('h');
	     
	         }
	     }
	 }
	 
	 // === ETSAIEN MUGIMENDUA ===
	 public void mugituEtsaiak() {
		 Iterator<Etsaia> it = etsaiak.iterator();
		 while (it.hasNext()) {
			 Etsaia e = it.next();
			 
			 int xZaharra = e.getPosizioa().getX();
		     int yZaharra = e.getPosizioa().getY();
		     
		     int xBerria = xZaharra;
		     int yBerria = yZaharra;
	
		     boolean mugituta = false;
		     int saiakerak = 3;
		     
		     while (!mugituta && saiakerak>0) {
		    	 e.mugituEtsaia();
			     xBerria = e.getPosizioa().getX();
			     yBerria = e.getPosizioa().getY();
		    	 
			     if (yBerria >= altuera) {
			    	 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
			    	 it.remove();      	// Etsaia eliminatu
			    	 partidaAmaitu();   // Partida galdu
		             break;          	// Amaitu
			     }
			     
			     if (posizioBaliozkoa(xBerria, yBerria) && tableroMatrizea[xBerria][yBerria].getMota()=='u') { //Comprueba que no haya nada en la Gelaxka a la que se va a mover

			         // COMPROBAR SI HA LLEGADO ABAJO
			         if (yBerria >= altuera - 1) {
			        	 partidaGaldu();
			             return;
			         }
			    	 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
			         tableroMatrizea[xBerria][yBerria].setMota('e');
			         mugituta = true;

			    	 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
			    	 tableroMatrizea[xBerria][yBerria].setMota('e');
			    	 mugituta = true; 
			     } else {
			    	 e.getPosizioa().setX(xZaharra);
			    	 e.getPosizioa().setY(yZaharra);
			     }
			     saiakerak--;
		     }
		 }
	 }
	 
	 // === TIROAREN MUGIMENDUA ===
	 public void mugituTiroak() {
		 Iterator<Tiroa> it = tiroak.iterator();
		 while (it.hasNext()) {
			 Tiroa t = it.next();
			 
			 int xZaharra = t.getPosizioa().getX();
			 int yZaharra = t.getPosizioa().getY();
			 
			 t.mugitu();
			 
			 int xBerria = t.getPosizioa().getX();
			 int yBerria = t.getPosizioa().getY();
			 
			 if (posizioBaliozkoa(xBerria, yBerria)) {
				 if (tableroMatrizea[xBerria][yBerria].getMota()=='e') {
					 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
					 tableroMatrizea[xBerria][yBerria].hutsikUtzi();
					 etsaiaEliminatu(xBerria, yBerria);
					 it.remove();
					 continue;
				 }
				 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
				 tableroMatrizea[xBerria][yBerria].setMota('t');
			 } else {
				 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
				 it.remove();
			 }
		 }
		 setChanged();
		 notifyObservers();
	 }	 
	 	 
	 // === PARTIDA AMAITZEKO METODOAK ===
	 public boolean isGameOver() {
		 return gameOver;
	 }
	 private void partidaAmaitu() {
		 gameOver = true;
		 startStopJokoa();
	 }
	 
	 // === ETSAIA ELIMINATU ===
	 private void etsaiaEliminatu(int x, int y) {
		 boolean eliminatuta = false;
		 Iterator<Etsaia> it = etsaiak.iterator();
		 while (it.hasNext() && !eliminatuta) {
			 Etsaia e = it.next();
			 if (e.getPosizioa().getX() == x && e.getPosizioa().getY() == y) {
				 it.remove();
				 eliminatuta = true;
			 }
		 }
		    // comprobar si ya no quedan enemigos
		    if (etsaiak.isEmpty()) {
		        partidaIrabazi();
		    }

	 }
	 
	 // === TEKLATUAREN EKINTZAK EGIN ===
	 private void mugituHegazkinaControl() {
		 int dx = 0;
		 int dy = 0;
		 
		 if (TeklatuKontroladorea.getTeklatuEMA().getEzk()) dx--;
		 if (TeklatuKontroladorea.getTeklatuEMA().getEsk()) dx++;
		 if (TeklatuKontroladorea.getTeklatuEMA().getGo()) dy--;
		 if (TeklatuKontroladorea.getTeklatuEMA().getBe()) dy++;		
	
		 if (dx!=0 || dy!=0) mugituHegazkina(dx, dy);
	 }
	 private void tiroakSortu() {
		 if (TeklatuKontroladorea.getTeklatuEMA().getTi()) tiroaSortu();
	 }

	// === PARTIDA IRABAZI ===
	private void partidaIrabazi() {
		partidaAmaitu();
		setChanged();
		notifyObservers("IRABAZI");
	}
	 
	// === PARTIDA AMAITU ===
	private void partidaGaldu() {
		partidaAmaitu();
		setChanged();
		notifyObservers("GALDU");
	}
}