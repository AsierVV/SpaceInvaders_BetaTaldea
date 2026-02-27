package modeloa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Observable;


public class Tableroa extends Observable {
	
	/* CADA VEZ QUE ALGO CAMBIE:
	setChanged();
	notifyObservers();
	*/
	
	private Gelaxka[][] tableroMatrizea;
	private Hegazkina hegazkina;
	private List<Etsaia> etsaiak;
	private List<Tiroa> tiroak;
	
	private final int zabalera = 100;
    private final int altuera = 60;
	
    public Tableroa() {
		
        tableroMatrizea = new Gelaxka[zabalera][altuera];
        etsaiak = new ArrayList<>();
        tiroak = new ArrayList<>();
        
        for (int i = 0; i < zabalera; i++) {
            for (int j = 0; j < altuera; j++) {
            	tableroMatrizea[i][j] = new Gelaxka(new Koordenatua(i,j));
            }
        }
        
        sortuHegazkina();
        sortuEtsaiak();
    }
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
	 
	// === HEGAZKINA SORTU ===
    private void sortuHegazkina() {
    	hegazkina = new Hegazkina(new Koordenatua(50,55));
    	tableroMatrizea[50][55].setMota(hegazkina);
	}
	 
	// === ETSAIAK SORTU ===
	private void sortuEtsaiak() {
		Random r = new Random();
	    int kopurua = 4 + r.nextInt(5); // 4-8 etsai
	
	    while (etsaiak.size() < kopurua) {
	    	int zutabea = r.nextInt(zabalera);
	
	    	// Konprobatu ea hutsik dagoen gelaxka
	        if (tableroMatrizea[zutabea][5].getMota() instanceof Hutsunea) {
	        	Etsaia e = new Etsaia(new Koordenatua(zutabea, 5));
	            etsaiak.add(e);
	            tableroMatrizea[zutabea][5].setMota(e);
	        }
	    }
	}    
	 
	// === TIRO BAT SORTU ===
	public void tiroaSortu() {
		int x = hegazkina.getPosizioa().getX();
		int y = hegazkina.getPosizioa().getY() - 1; //Hegazkinaren gainean sortzeko
	 		
	 	if (posizioBaliozkoa(x, y)) {
	 		Tiroa t = new Tiroa(new Koordenatua(x, y)); // Tiroa sortzen du
	 		tiroak.add(t);	// Tiroa "tiroak" listan sartzen du
	 		tableroMatrizea[x][y].setMota(t);	// Gelaxka eguneratzen du tableroan
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
	    	 
	    	 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
	    	 hegazkina.getPosizioa().setX(xBerria);
	    	 hegazkina.getPosizioa().setY(yBerria);
	    	 tableroMatrizea[xBerria][yBerria].setMota(hegazkina);
	    	 
	    	 setChanged();
			 notifyObservers();
	     }
	 }
	 
	 // === ETSAIEN MUGIMENDUA ===
	 public void mugituEtsaiak() {
		 Iterator<Etsaia> it = etsaiak.iterator();
		 while (it.hasNext()) {
			 Etsaia e = it.next();
			 
			 int xZaharra = e.getPosizioa().getX();
		     int yZaharra = e.getPosizioa().getY();
	
		     e.mugituEtsaia();
	
		     int xBerria = e.getPosizioa().getX();
		     int yBerria = e.getPosizioa().getY();
	
		     if (posizioBaliozkoa(xBerria, yBerria) && tableroMatrizea[xBerria][yBerria].getMota() instanceof Hutsunea) { //Comprueba que no haya nada en la Gelaxka a la que se va a mover
		         tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
		         tableroMatrizea[xBerria][yBerria].setMota(e);
		     } else {
		    	 e.getPosizioa().setX(xZaharra);
		    	 e.getPosizioa().setY(yZaharra);
		     }
		 }
		 setChanged();
		 notifyObservers();
	 }
	 
	 // === TIROAREN MUGIMENDUA ===
	 public void mugituTiroa() {
		 Iterator<Tiroa> it = tiroak.iterator();
		 while (it.hasNext()) {
			 Tiroa t = it.next();
			 
			 int xZaharra = t.getPosizioa().getX();
			 int yZaharra = t.getPosizioa().getY();
			 
			 t.mugitu();
			 
			 int xBerria = t.getPosizioa().getX();
			 int yBerria = t.getPosizioa().getY();
			 
			 if (posizioBaliozkoa(xBerria, yBerria)) {
				 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
				 tableroMatrizea[xBerria][yBerria].setMota(t);
			 } else {
				 tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
				 it.remove();
			 }
		 }
		 setChanged();
		 notifyObservers();
	 }	 
}