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
	private HegazkinaTaldea hegazkina;
	private List<EtsaiaTaldea> etsaiak;
	private List<TiroaTaldea> tiroak;
    private char motaHegazkina;
	
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
    private boolean etsaiaAzkenLerroraIritsi = false;
    
    // === ERAIKITZAILEA ===
    private Tableroa() {
        tableroMatrizea = new Gelaxka[zabalera][altuera];
        etsaiak = new ArrayList<>();
        tiroak = new ArrayList<>();
        gameOver = false;
        
        // --- MATRIZEA SORTU ---
        for (int i = 0; i < zabalera; i++) {
            for (int j = 0; j < altuera; j++) {
            	tableroMatrizea[i][j] = new Gelaxka(new Koordenatua(i,j), new HutsuneEgoera());
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
	
    public HegazkinaTaldea getHegazkina() {
    	return hegazkina;
	}
	 
    public List<EtsaiaTaldea> getEtsaiak() {
    	return etsaiak;
	}
	 
    public List<TiroaTaldea> getTiroak() {
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
    public void hasiJokoa(String mota) {
    	jokoHasita = true;
    	
        setChanged();
        notifyObservers("TABLEROA_SORTUTA");
        
    	sortuHegazkina(mota);
    	sortuEtsaiak();
    	
        if (!timer.isRunning()) timer.start();
    }

    public void stopJokoa() {
    	if (timer.isRunning()) timer.stop();
    }
	 
	// === HEGAZKINA SORTU ===
    public void sortuHegazkina(String mota) {
        hegazkina = HegazkinaFactory.sortuHegazkina(mota, new Koordenatua(50,55));
        if (mota.equals("GREEN")) motaHegazkina = 'g';
        else if (mota.equals("BLUE")) motaHegazkina = 'b';
        else if (mota.equals("RED")) motaHegazkina = 'r';
        margotuHegazkina();
        //tableroMatrizea[50][55].jarriHegazkina(motaHegazkina);
	}
    
	// === ETSAIAK SORTU ===
	private void sortuEtsaiak() {
		Random r = new Random();
	    int kopurua = 4 + r.nextInt(5); // 4-8 etsai: r.nextInt(5) --> 0 eta 5-1 arteko zenbaki bat ematen du
	    int ind = 1;
	    
	    while (etsaiak.size() < kopurua) {
	    	int zutabea = r.nextInt(zabalera);
	
	    	// Konprobatu ea hutsik dagoen gelaxka
	        if (etsaiaSortuDaiteke(zutabea, 5)) {
	        	EtsaiaTaldea e = new EtsaiaTaldea(new Koordenatua(zutabea, 5), ind);
	        	ind ++;
	            etsaiak.add(e);
	            margotuEtsaia(e);
	            //tableroMatrizea[zutabea][5].jarriEtsaia();
	        }
	    }
	}
	 
	// === TIROA SORTU ===
	public void tiroaSortu() {
		int x = hegazkina.getPosizioa().getX();
		int y = hegazkina.getPosizioa().getY() - 3;							//Hegazkinaren gainean sortzeko
	 	
		long tiroOrain = System.currentTimeMillis(); 						//Oraingo momentuko denbora hartzen dugu, 300ms pasatu ez badira ez da tiro bat sortuko		
	 	
		if (tiroOrain - azkenTiroa >= tiroKadentzia) {
	 		List<Koordenatua> koordenatuak = hegazkina.getTiroMota().sortuKoordenatuak(new Koordenatua(x,y));	// Tiroa sortuko du. "Strategy"-ren bidez, momenturo dagkion tiro mota egokia sortuko du.
			
	 		if (tiroaSortuDaiteke(koordenatuak)) {	// Tiro osoa sortu daitekeen konprobatu
		 		TiroaTaldea t = new TiroaTaldea(koordenatuak);					// TiroaTaldea sortzen du (bere forma egokiarekin)
				tiroak.add(t);													// Tiroa "tiroak" listan sartzen du
		 		margotuTiroa(t);												// Gelaxka eguneratzen du tableroan
		 		azkenTiroa = tiroOrain;
	 		}
	 	}
	 }
	
	// === TIROA ALDATU ===
	public void tiroaAldatu(int i) {
		switch (i) {
		case 1:
			hegazkina.setTiroMota(new TiroBakarra());
			break;
		case 2:
			if (motaHegazkina == 'b') hegazkina.setTiroMota(new TiroRonbo());
			else hegazkina.setTiroMota(new TiroTriple());
			break;
		case 3:
			if (motaHegazkina == 'r') {
			hegazkina.setTiroMota(new TiroRonbo());
			}
			break;
		}
	}
	 
	// === MUGIMENDU EGOKIA DEN ALA EZ EGIAZTATZEKO ===
	private boolean posizioBaliozkoa(int x, int y) {
		return x >= 0 && x < zabalera && y >= 0 && y < altuera;
	}
	 
	// === HEGAZKINAREN MUGIMENDUA ===
	public void mugituHegazkina(int dx, int dy) {
		if (hegazkinaMugituDaiteke(dx, dy)) {
	    	garbituHegazkina();
	    	hegazkina.mugitu(dx, dy);
	    	margotuHegazkina();
		}
	}
	 
	// === ETSAIEN MUGIMENDUA ===
	public void mugituEtsaiak() {
		if (etsaiaAzkenLerroraIritsi) partidaGaldu();
		
		Iterator<EtsaiaTaldea> it = etsaiak.iterator();
		while (it.hasNext()) {
			EtsaiaTaldea e = it.next();
			Koordenatua dif = e.etsaiaAusazkoMugimendua();
			
			int dx = dif.getX();
			int dy = dif.getY();
			
			if (etsaiaMugituDaiteke(dx, dy, e)) {
		    	garbituEtsaia(e);
		    	e.mugitu(dx, dy);
		    	margotuEtsaia(e);
			}
			if (etsaiaBeheraHelduDa(e)) etsaiaAzkenLerroraIritsi = true;
		}
	}
	 
	// === TIROAREN MUGIMENDUA ===
	public void mugituTiroak() {
		Iterator<TiroaTaldea> it = tiroak.iterator();
		while (it.hasNext()) {
			TiroaTaldea t = it.next();
			
			List<Koordenatua> hurrengoKoordenatuak = tiroarenHurrengoKoordenatuak(t,0,-1);
			
			// Konprobatu etsai bat kolpatu duen
			EtsaiaTaldea kolpatuta = null;
			for (Koordenatua k : hurrengoKoordenatuak) {
				kolpatuta = kolpatutakoEtsaia(k.getX(), k.getY());
				if (kolpatuta!=null) break;	// Tiroak etsai bat kolpatu du, beraz for-etik ateratzen gara
			}
			
			garbituTiroa(t);
			
			if (kolpatuta != null) {
				it.remove();                 			// Tiroa ezabatu
				etsaiaEzabatu(kolpatuta.getIndizea());	// EtsaiaTaldea ezabatu
				continue;	// Tiro honekin amaitu dugu, hurrengoarekin jarraitu
			}
			
			if (!koordenatuGuztiakBaliozkoak(hurrengoKoordenatuak)) {
				it.remove();	// Tiroaren posizioaren bat tablerotik atera bada, ezabatuko dugu
				continue;	// Tiro honekin amaitu dugu, hurrengoarekin jarraitu
			}
			
			t.mugitu(0,-1);
			margotuTiroa(t);
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
	
	// === ETSAIA EZABATU ===
	private void etsaiaEzabatu(int ind) {
		boolean eliminatuta = false;
		Iterator<EtsaiaTaldea> it = etsaiak.iterator();
		while (it.hasNext() && !eliminatuta) {
			EtsaiaTaldea e = it.next();
			if (e.getIndizea() == ind) {
				it.remove();
				garbituEtsaia(e);
				eliminatuta = true;
			}
		}
		etsaiakBizirik();	// Etsai guztiak hilda badaude, partida irabazten dugu.
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
	
	
	
	// === COMPOSITE PATROIERAKO METODOAK ===
	private boolean hegazkinarenKoordenatuaDa(int x, int y) {
	    for (Koordenatua k : hegazkina.getKoordenatuLista()) { // hegazkina.getKoordenatuak --> hegazkina pixel guztien koordenatuen lista bat itzultzen du
	        if (k.getX() == x && k.getY() == y) return true;
	    }
	    return false;
	}
	
	private boolean hegazkinaMugituDaiteke(int dx, int dy) {
	    for (Koordenatua k : hegazkina.getKoordenatuLista()) {
	        int xBerria = k.getX() + dx;
	        int yBerria = k.getY() + dy;

	        if (!posizioBaliozkoa(xBerria, yBerria)) return false;
		    char mota = tableroMatrizea[xBerria][yBerria].getMota();
		    if (mota == 'e') partidaGaldu();
		    if (mota != 'u' && !hegazkinarenKoordenatuaDa(xBerria, yBerria)) return false;
	    }
	    return true;
	}
	
	private boolean etsaiaMugituDaiteke(int dx, int dy, EtsaiaTaldea e) {
	    List<Koordenatua> kordBerriak = new ArrayList<>(); 
		
		for (Koordenatua k : e.getKoordenatuLista()) {
			kordBerriak.add(new Koordenatua(k.getX() + dx, k.getY() + dy));
		}
		
		return koordenatuakLibreDaude(kordBerriak, e.getIndizea());
	}
	
	private boolean etsaiaBeheraHelduDa(EtsaiaTaldea e) {
		for (Koordenatua k : e.getKoordenatuLista()) {
			if (k.getY() >= altuera-1) return true;
		}
		return false;
	}
	
	private boolean etsaiaSortuDaiteke(int x, int y) {
		return koordenatuakLibreDaude(EtsaiaTaldea.sortuKoordenatuak(new Koordenatua(x,y)), -1);
	}

	private void margotuHegazkina() {
		for (Koordenatua k : hegazkina.getKoordenatuLista()) {
            tableroMatrizea[k.getX()][k.getY()].jarriHegazkina(motaHegazkina);
		}
	}
	
	private void margotuEtsaia(EtsaiaTaldea e) {
		for (Koordenatua k : e.getKoordenatuLista()) {
	        tableroMatrizea[k.getX()][k.getY()].jarriEtsaia();
	    }
	}
	
	private void margotuTiroa(TiroaTaldea t) {
		for (Koordenatua k : t.getKoordenatuLista()) {
	        tableroMatrizea[k.getX()][k.getY()].jarriTiroa();
	    }
	}
	
    private void garbituHegazkina() {
    	for (Koordenatua k : hegazkina.getKoordenatuLista()) {
            tableroMatrizea[k.getX()][k.getY()].hutsikUtzi();
        }
    }
    
    private void garbituEtsaia(EtsaiaTaldea e) {
    	for (Koordenatua k : e.getKoordenatuLista()) {
            tableroMatrizea[k.getX()][k.getY()].hutsikUtzi();
        }
    }
    
    private void garbituTiroa(TiroaTaldea t) {
    	for (Koordenatua k : t.getKoordenatuLista()) {
	        tableroMatrizea[k.getX()][k.getY()].hutsikUtzi();
	    }
    }
    
    private boolean koordenatuakLibreDaude(List<Koordenatua> koordBerriak, int nireEtsaia) {
        for (Koordenatua kBerria : koordBerriak) {
            int x = kBerria.getX();
            int y = kBerria.getY();

            if (!posizioBaliozkoa(x, y)) return false;

            if (hegazkinarenKoordenatuaDa(x, y)) partidaGaldu();

            if (tableroMatrizea[x][y].getMota() == 't') return false;

            for (EtsaiaTaldea e : etsaiak) {
                if (e.getIndizea() != nireEtsaia) {
                    for (Koordenatua k : e.getKoordenatuLista()) {
                        if (k.getX() == x && k.getY() == y) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private EtsaiaTaldea kolpatutakoEtsaia(int x, int y) {
        for (EtsaiaTaldea e : etsaiak) {
            for (Koordenatua k : e.getKoordenatuLista()) {
                if (k.getX() == x && k.getY() == y) {
                    return e;
                }
            }
        }
        return null;
    }
    
    private List<Koordenatua> tiroarenHurrengoKoordenatuak(TiroaTaldea t, int dx, int dy) {
    	List<Koordenatua> koordenatuBerriak = new ArrayList<>();

    	for (Koordenatua k : t.getKoordenatuLista()) {
    		koordenatuBerriak.add(new Koordenatua(k.getX() + dx, k.getY() + dy));
    	}

    	return koordenatuBerriak;
    }

    private boolean koordenatuGuztiakBaliozkoak(List<Koordenatua> koordenatuak) {
    	for (Koordenatua k : koordenatuak) {
    		if (!posizioBaliozkoa(k.getX(), k.getY())) return false;
    	}
    	return true;
    }

    private boolean tiroaSortuDaiteke(List<Koordenatua> koordenatuak) {
    	for (Koordenatua k : koordenatuak) {
    		int x = k.getX();
    		int y = k.getY();

    		if (!posizioBaliozkoa(x, y)) return false;
    		if (hegazkinarenKoordenatuaDa(x, y)) return false;
    		if (tableroMatrizea[x][y].getMota() == 't') return false;
    	}
    	return true;
    }
}