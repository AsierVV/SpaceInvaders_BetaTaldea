package modeloa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;


public class Tableroa extends Observable {
	
	private static Tableroa nireEMA = null;
	private Gelaxka[][] tableroMatrizea;
	private Hegazkina hegazkina;
	private List<Etsaia> etsaiak;
	private List<Tiroa> tiroak;
	
	private Timer timer;
	private final int abiaduraTimer = 50;	//50ms
	
	private int mugituHegazkinaKont = 1;	//100ms, beraz 50ms * 2 --> Batean hasi parametroa, horrela 50ms-ko timerra deitzen den bigarren aldian 100ms pasatu dira.
	private int mugituEtsaiakKont = 1;		//200ms, beraz 100ms * 2 --> Batean hasi parametroa, horrela 100ms pasatzen diren bigarren aldian 200ms pasatu dira.
	private int tiroEginKont = 2;			//300ms, beraz 100ms * 3 --> Bian hasi parametroa, horrela 100ms pasatzen diren hirugarren aldian 300ms pasatu dira.
	
	private long azkenTiroa = 0;
    private final long tiroKadentzia= 300;	//300ms
	
	private final int zabalera = 100;
    private final int altuera = 60;
    
	private boolean gora;
	private boolean behera;
	private boolean ezkerrera;
	private boolean eskuinera;
	private boolean tiroEgin;

    private boolean gameOver;
    private boolean jokoHasita = false;
    private String HegazkinMota;
    
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
        		
        		// 50ms-ro mugitu tiroak eta hegazkina kontrola konprobatau
        		mugituTiroak();
        		
        		// Konprobatu 100ms pasatu diren
        		if (mugituHegazkinaKont <= 0) {
        			mugituHegazkinaControl();
        			mugituHegazkinaKont = 1;
        			
        			// Konprobatu 200ms pasatu diren
            		if (mugituEtsaiakKont <= 0) {
        				mugituEtsaiak();
        				mugituEtsaiakKont = 1;
            		} else mugituEtsaiakKont--;
            		
            		// Konprobatu 300ms pasatu diren
            		if (tiroEginKont <= 0) {
    					tiroakSortu();
    					tiroEginKont = 2;
        				
            		} else tiroEginKont--;
            		
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

    public boolean getHasiDaJokoa() {
    	return this.jokoHasita;
    }
    
    private void etsaiakBizirik() {
    	if (etsaiak.isEmpty()) {
    		partidaIrabazi();
		 }
    }
    
    // === JOKOA HASTEKO ETA GELDITZEKO METODOAK ===
    public void hasiJokoa() {
    	jokoHasita = true;
    	
        setChanged();
        notifyObservers("TABLEROA_SORTUTA");
        
    	sortuHegazkina();
    	sortuEtsaiak();
    	
        if (!timer.isRunning()) timer.start();
    }

    public void stopJokoa() {
    	if (timer.isRunning()) timer.stop();
    }
	 
	// === HEGAZKINA SORTU ===
    public void sortuHegazkina() {
        hegazkina = HegazkinaFactory.sortuHegazkina(HegazkinMota, new Koordenatua(50,55));
    	tableroMatrizea[50][55].setMota('h');
	}
	 
	// === ETSAIAK SORTU ===
	private void sortuEtsaiak() {
		Random r = new Random();
	    int kopurua = 4 + r.nextInt(5); // 4-8 etsai: r.nextInt(5) --> 0 eta 5-1 arteko zenbaki bat ematen du
	
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
		int y = hegazkina.getPosizioa().getY() - 2;		//Hegazkinaren gainean sortzeko
	 		
		long tiroOrain = System.currentTimeMillis(); 	//Oraingo momentuko denbora hartzen dugu, 300ms pasatu ez badira ez da tiro bat sortuko		
	 	if (tiroOrain - azkenTiroa >= tiroKadentzia && posizioBaliozkoa(x, y) && !(tableroMatrizea[x][y].getMota()=='t')) {
	 		Tiroa t = new Tiroa(new Koordenatua(x, y));	// Tiroa sortzen du
	 		tiroak.add(t);								// Tiroa "tiroak" listan sartzen du
	 		tableroMatrizea[x][y].setMota('t');			// Gelaxka eguneratzen du tableroan
	 		azkenTiroa = tiroOrain;
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
	    	 
	        // Etsai bat badago, galdu
	    	if (mota == 'e') {
	        	partidaGaldu();
	            return;
	        }
	        // Mugitu bakarrik gelaxka hutsik badago
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
			    
			    // Posizioa baliozkoa den eta etsai bat ez dagoen konprobatzen du
			    if (posizioBaliozkoa(xBerria, yBerria) && tableroMatrizea[xBerria][yBerria].getMota()!='e') {
			    	// Konprobatzen du ea hegazkina dagoen mugituko den posizioan, horrela bada, partida galtzen dugu.
			    	if (tableroMatrizea[xBerria][yBerria].getMota()=='h') {
			    		tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
			    		partidaGaldu();
			    		break;
			        // Konprobatzen du ea tiro bat dagoen mugituko den posizioan, horrela bada, etsaia eta tiroa ezabatzen dira.
			    	} else if (tableroMatrizea[xBerria][yBerria].getMota()=='t') {
			    		tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
			    		tableroMatrizea[xBerria][yBerria].hutsikUtzi();
			    		tiroaEzabatu(xBerria, yBerria);
			    		it.remove();
			    		etsaiakBizirik();
			            break;
			    	} else {
			    		tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
				        tableroMatrizea[xBerria][yBerria].setMota('e');
				        mugituta = true; 
			    	}
			    } else {
			    	e.getPosizioa().setX(xZaharra);
			    	e.getPosizioa().setY(yZaharra);
			    }
			    saiakerak--;
		    }
		    
		    if (yZaharra >= altuera - 1) {
		    	tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
		    	it.remove();
		    	partidaGaldu();
	            break;
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
					tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();	// Tiroa zegoen lekua hutsunea bihurtzen da
					tableroMatrizea[xBerria][yBerria].hutsikUtzi();		// Etsaia zegoen lekua hutsunea bihurtzen da
					etsaiaEliminatu(xBerria, yBerria);
					it.remove();
					continue;
				} else {
					tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
					tableroMatrizea[xBerria][yBerria].setMota('t'); 
				}
			} else {
				tableroMatrizea[xZaharra][yZaharra].hutsikUtzi();
				it.remove();
			}
		}
	}	
	 	 
	// === PARTIDA AMAITZEKO METODOAK ===
	public boolean isGameOver() {
		return gameOver;
	}
	private void partidaAmaitu() {
		gameOver = true;
		stopJokoa();
	}
	private void partidaIrabazi() {
		partidaAmaitu();
		JokoKudeatzailea.getEMA().setIrabazi(true);
		setChanged();
		notifyObservers();
	}
	private void partidaGaldu() {
		partidaAmaitu();
		JokoKudeatzailea.getEMA().setIrabazi(false);
		setChanged();
		notifyObservers();
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
		etsaiakBizirik();	// Etsai guztiak hilda badaude, partida irabazten dugu.
	}
	 
	// === TIROA EZABATU ===
	private void tiroaEzabatu(int x, int y) {
		boolean eliminatuta = false;
		Iterator<Tiroa> it = tiroak.iterator();
		while (it.hasNext() && !eliminatuta) {
			Tiroa t = it.next();
			if (t.getPosizioa().getX() == x && t.getPosizioa().getY() == y) {
				it.remove();
				eliminatuta = true;
			}
		}
	}

	// === TEKLATUAREN EKINTZAK EGIN ===
	private void mugituHegazkinaControl() {
		int dx = 0;
		int dy = 0;
		
		if (ezkerrera) dx--;
		if (eskuinera) dx++;
		if (gora) dy--;
		if (behera) dy++;		
		 
		if (dx!=0 || dy!=0) mugituHegazkina(dx, dy);
	}
	private void tiroakSortu() {
		if (tiroEgin) tiroaSortu();
	}
	 

	// === TEKLATUKO EKINTZEN METODO LAGUNGARRIAK ===
	public void ezkerraSakatu() {ezkerrera = true;}
	public void ezkerraAskatu() {ezkerrera = false;}
	
	public void eskuinaSakatu() {eskuinera = true;}
	public void eskuinaAskatu() {eskuinera = false;}
	
	public void goraSakatu() {gora = true;}
	public void goraAskatu() {gora = false;}
	
	public void beheraSakatu() {behera = true;}
	public void beheraAskatu() {behera = false;}
	
	public void tiroaSakatu() {
		if (!tiroEgin) {
			tiroaSortu();
			tiroEgin = true;
		}
	}
	public void tiroaAskatu() {tiroEgin = false;}

}