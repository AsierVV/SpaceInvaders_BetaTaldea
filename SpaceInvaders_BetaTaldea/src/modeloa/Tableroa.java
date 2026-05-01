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
	
	private String motaEtsaia;
	private String motaHegazkina;
	
	private Timer timer;
	private Timer timerEtsai;
	private final int abiaduraTimer = 50;	//50ms
	private int denboraSegundoak = 0;
	private long azkenDenboraEguneraketa = 0;
	private int puntuazioa = 0;
	
	private int etsaiKop = 4;					//4 etsai (default)
	private int etsaiAbiadura = 200;			//200ms (default)
	private int puntuazioBiderkatzailea = 1;	//x1 (default)
	
	private int mugituHegazkinaKont = 1;	//100ms, beraz 50ms * 2 --> Batean hasi parametroa, horrela 50ms-ko timerra deitzen den bigarren aldian 100ms pasatu dira.
	private int tiroEginKont = 2;			//300ms, beraz 100ms * 3 --> Bian hasi parametroa, horrela 100ms pasatzen diren hirugarren aldian 300ms pasatu dira.
	
	private long azkenTiroa = 0;
    private final long tiroKadentzia= 300;	//300ms
    
    private long azkenEtsaiTiroa = 0;
    private final long etsaiTiroDenbora = 800;
	
	private final int zabalera = 100;
    private final int altuera = 60;
    
	private boolean gora;
	private boolean behera;
	private boolean ezkerrera;
	private boolean eskuinera;
	private boolean tiroEgin;

    private boolean gameOver;
    private boolean jokoHasita = false;
    private boolean irabaziDuzu = false;
    
    private String zailtasunMota;
    private ZailtasunPortaera zailtasunPortaera;
    private boolean hurrengoMailaProgEskatuta = false;
    
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
        		
        		// HAU BAKARRIK ERABILIKO DA MAILA PROGRESIBOAN
        		// Hurrengo nivel progresiboa eskatuta badago, aurrera egin
        		// Hau hemen egiten dugu arazoak ekiditeko; izan ere, mugituTiroak metodoaren while barruan hurrengo mailara joatea eskatzen badugu,
        		// arazoak emango ditu iteradorea, tiroak.clear() egiten dugulako nivelez aldatzerakoan
        		if (hurrengoMailaProgEskatuta) {
        			hurrengoMailaProgEskatuta = false;
        			hurrengoMailaProgresiboa();
        			return;
        		}
        		
        		// Konprobatu 1s pasatu den
        		long orain = System.currentTimeMillis();
        		if (orain - azkenDenboraEguneraketa > 1000) {
        			denboraSegundoak++;
        			azkenDenboraEguneraketa = orain;
        			
        			setChanged();
        			notifyObservers("DENBORA_EGUNERATU");
        		}
        		
        		// Konprobatu 100ms pasatu diren
        		if (mugituHegazkinaKont <= 0) {
        			mugituHegazkinaControl();
        			mugituHegazkinaKont = 1;
        			
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
    		if (zailtasunMota.equals("Progresiboa") && JokoKudeatzailea.getEMA().getMailaProgresiboa() < 4) {
    			hurrengoMailaProgEskatuta = true;
    		} else {
        		irabaziDuzu = true;
        		partidaIrabazi();    			
    		}
		}
    }
    
    public int getDenboraSegundoak(){
    	return denboraSegundoak;
    }
    
    public int getTiroKopGezia() {
    	return hegazkina.getTiroKopGezia();
    }
    
    public int getTiroKopErronbo() {
    	return hegazkina.getTiroKopErronbo();
    }
    
    public char getHegazkinMota() {
    	return hegazkina.getMotaChar();
    }
    
    public char getTiroMota() {
    	return hegazkina.getTiroMota().motaChar();
    }
    
    public String getZailtasunMota() {
    	return this.zailtasunMota;
    }


    public int getPuntuazioa() {
    	return puntuazioa;
    }

	// === JOKOA HASTEKO METODOA ===
    public void hasiJokoa(String pMotaHegazkina, String pMotaEtsaia, String pZailtasunMota) {

    	motaHegazkina = pMotaHegazkina;
    	motaEtsaia = pMotaEtsaia;
    	zailtasunMota = pZailtasunMota;
    	
    	zailtasunPortaera = ZailtasunFactory.nireEMA().sortuZailtasuna(zailtasunMota);
    	zailtasunaAplikatu();
    	
    	// --- ETSAIEN TIMERRA ERAIKI ---
        timerEtsai = new Timer(etsaiAbiadura, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mugituEtsaiak();
			}
		});
    	
    	jokoHasita = true;
    	
        setChanged();
        notifyObservers("TABLEROA_SORTUTA");
        
    	sortuHegazkina(motaHegazkina);
    	sortuEtsaiak(motaEtsaia);
    	
    	setChanged();
        notifyObservers("PUNTUAZIO_PANTAILA_EGUNERATU");
        
        if (!timer.isRunning()) {
        	timer.start();
        }
        if (!timerEtsai.isRunning()) {
            timerEtsai.start();
        }
        
        azkenDenboraEguneraketa = System.currentTimeMillis();
        
        setChanged();
        notifyObservers("JOKOA_FOCUS_HARTU");
    }
    
    // === JOKOA RESETEATU ===
    public void jokoaReset() {
    	amaituJokoa();
    	nireEMA = null;
    	getTableroaEMA();
    	
    	setChanged();
    	notifyObservers("RESET");
    }
    
    // === START/STOP ===
    public void amaituJokoa() {
    	timer.stop();
    	timerEtsai.stop();
    }
    public void startStopJokoa() {
    	if (timer.isRunning()) {
    		timer.stop();
        	timerEtsai.stop();
    		setChanged();
        	notifyObservers("STOP");
    	} else if (!timer.isRunning()) {
    		setChanged();
        	notifyObservers("START");
        	timer.start();
        	timerEtsai.start();
    	}
    }
    
    private void zailtasunaAplikatu() {
    	etsaiKop = zailtasunPortaera.etsaiKopLortu();
    	etsaiAbiadura = zailtasunPortaera.etsaiAbiaduraLortu();
    	puntuazioBiderkatzailea = zailtasunPortaera.puntuazioBiderkatzaileaLortu();
    }
    
    // === ZAILTASUN MOTAK ===
    public boolean isErraza() {
    	return "Erraza".equals(zailtasunMota);
    }
    public boolean isNormala() {
    	return "Normala".equals(zailtasunMota);
    }
    public boolean isZaila() {
    	return "Zaila".equals(zailtasunMota);
    }
    public boolean isProgresibo() {
    	return "Progresiboa".equals(zailtasunMota);
    }
	

	// === HEGAZKINA SORTU ===
    private void sortuHegazkina(String mota) {
        hegazkina = HegazkinaFactory.nireEMA().sortuHegazkina(mota, new Koordenatua(50,55));
        margotuHegazkina();
	}
    
	// === ETSAIAK SORTU ===
	private void sortuEtsaiak(String mota) {
		Random r = new Random();
	    int kopurua = etsaiKop + r.nextInt(etsaiKop/2 + 1); // r.nextInt(5) --> 0 eta 4 arteko zenbaki bat ematen du
			/* ETSAI KOPURUAK:
			 *	- Erraza -> 4-6
			 *	- Normala -> 8-12
			 *	- Zaila -> 12-18
			 */
	    
	    // Etsaiak "PURPLE" direnean asko okupatzen dute eta errorea ematen du 20 baino gehiago sortu behar badira,
	    // izan ere ez dira sartzen pantailan --> Hau konpontzeko momentuz purple etsaiak 18-ra limitatu dira
	    if ("PURPLE".equals(mota)) {
	        kopurua = Math.min(kopurua, 18);	// kopurua eta 18 balioetatik txikiena hartzen du, beraz ez da inoiz 18 baino etsai purple gehiago egongo
	    }
	    
	    int ind = 1;
	    int saiakerak = 0;
	    int maxSaiakerak = 100;
	    
	    while (etsaiak.size() < kopurua && saiakerak < maxSaiakerak) {
	    	saiakerak++;
	    	
	    	int zutabea = r.nextInt(zabalera);
	
	    	// Konprobatu ea hutsik dagoen gelaxka
	        if (etsaiaSortuDaiteke(mota, zutabea, 5)) {
	        	EtsaiaTaldea e = EtsaiaFactory.nireEMA().sortuEtsaia(mota, new Koordenatua(zutabea, 5), ind);
	        	ind ++;
	            etsaiak.add(e);
	            margotuEtsaia(e);
	        }
	    }
	}
	 
	// === TIROA SORTU ===
	public void tiroaSortu() {
		int x = hegazkina.getPosizioa().getX();
		int y = hegazkina.getPosizioa().getY() - 3;							//Hegazkinaren gainean sortzeko. Oharra: tiroa (x, y-3) koordenatuan sortzen da (x, y-2)-an sortu beharrean,
	 																		//izan ere, bestela tiro handiekin kolisioa sortzen da hegazkinarekin
		long tiroOrain = System.currentTimeMillis(); 						//Oraingo momentuko denbora hartzen dugu, 300ms pasatu ez badira ez da tiro bat sortuko		
	 	
		if (tiroOrain - azkenTiroa >= tiroKadentzia && timer.isRunning()) {
	 		List<Koordenatua> koordenatuak = hegazkina.getTiroMota().sortuKoordenatuak(new Koordenatua(x,y));	// Tiroa sortuko du. "Strategy"-ren bidez, momenturo dagkion tiro mota egokia sortuko du.
			
	 		if (hegazkina.tiroaEginDaiteke()) {	// Konprobatzen du ea mota horretako tirorik geratzen diren
		 		if (tiroaSortuDaiteke(koordenatuak)) {	// Tiro osoa sortu daitekeen konprobatu
			 		TiroaTaldea t = new TiroaTaldea(koordenatuak, -1);					// TiroaTaldea sortzen du (bere forma egokiarekin)
					tiroak.add(t);													// Tiroa "tiroak" listan sartzen du
			 		margotuTiroa(t);												// Gelaxka eguneratzen du tableroan
			 		hegazkina.tiroaKontsumitu();
			 		azkenTiroa = tiroOrain;
			 		setChanged();
			 		notifyObservers("TIRO_KOP_EGUNERATU");
		 		}
			}
		}
	 }
	public void etsaiekTiroEgin() {
	    long orain = System.currentTimeMillis();

	    if (orain - azkenEtsaiTiroa < etsaiTiroDenbora) return;

	    azkenEtsaiTiroa = orain;
		Random r = new Random();
		for (EtsaiaTaldea e : etsaiak) {
	        //Probabilidad de disparo (40%)
	        if (r.nextInt(100) < 40) {
	        
	        int x = e.getPosizioa().getX();
	        int y = e.getPosizioa().getY() + 2; // debajo del enemigo
	        
	        List<Koordenatua> koordenatuak = List.of(new Koordenatua(x, y));
	        
	        if (tiroaSortuDaiteke(koordenatuak)) {
	            TiroaTaldea t = new TiroaTaldea(koordenatuak, 1);
	            tiroak.add(t);
	            margotuTiroa(t);
	        }
	     }
		}
	}
	       
	// === TIROA ALDATU ===
	// Metodo honekin tiroa aldatzen da tekla bakarra erabiliz --> 'r' tekla
	public void tiroaAldatu() {
		hegazkina.tiroMotaAldatu();
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
		if (gameOver && !irabaziDuzu) partidaGaldu();
		
		if (zailtasunPortaera.etsaiekTiroEginBeharDute()) {
		    etsaiekTiroEgin();
		}
		
		Iterator<EtsaiaTaldea> it = etsaiak.iterator();
		while (it.hasNext()) {			
			EtsaiaTaldea e = it.next();
			
			Koordenatua dif;

			if (zailtasunPortaera.mugimenduAdimendunaErabili()) {
			    dif = e.mugimenduAdimenduaEtsaia();
			} else {
			    dif = e.etsaiaAusazkoMugimendua();
			}
				
			int dx = dif.getX();
			int dy = dif.getY();
			
			if (etsaiaMugituDaiteke(dx, dy, e)) {
		    	garbituEtsaia(e);
		    	e.mugitu(dx, dy);
		    	margotuEtsaia(e);
			}
			if (etsaiaBeheraHelduDa(e)) gameOver = true;
		}
	}
	 
	// === TIROEN MUGIMENDUA ===
	public void mugituTiroak() {
	    List<TiroaTaldea> ezabatzekoTiroak = new ArrayList<>();	// Beste tiro batekin talka egiten duten tiroak hemen gordeko dira, gero ezabatzeko
	    
	    Iterator<TiroaTaldea> it = tiroak.iterator();

	    while (it.hasNext()) {
	        TiroaTaldea t = it.next();

	        // Uneko tiro hau aurreko iterazioren batean ezabatzeko markatu bada, tiroa ez dugu gehiago mugitu behar, bakarrik borratu
	        if (ezabatzekoTiroak.contains(t)) {
	            garbituTiroa(t);
	            it.remove();
	            continue;	// Hurrengo tiroarekin jarraitu
	        }

	        List<Koordenatua> hurrengoKoordenatuak = tiroarenHurrengoKoordenatuak(t.getKoordenatuLista(), 0, t.getDy());

	        // Hurrengo posizioa tablerotik kanpo badoa, tiroa ezabatzen dugu
	        if (!koordenatuGuztiakBaliozkoak(hurrengoKoordenatuak)) {
	            garbituTiroa(t);
	            it.remove();
	            continue;	// Hurrengo tiroarekin jarraitu
	        }

	        // Tiroak beste tiro bat jo duen konprobatzen dugu, horrela bada, biaka ezabatzen ditugu
	        if (tiroakBesteTiroBatJoDu(t, hurrengoKoordenatuak, ezabatzekoTiroak)) {
	            garbituTiroa(t);
	            it.remove();
	            continue;	// Hurrengo tiroarekin jarraitu
	        }

	        // Tiroak zerbait jo duen konprobatzen dugu --> hegazkinaren tiro batek etsai bat jo edo etsai baten tiro batek hegazkina jo
	        if (tiroakZerbaitJoDu(t, hurrengoKoordenatuak)) {
	            garbituTiroa(t);
	            it.remove();
	            continue;	// Hurrengo tiroarekin jarraitu
	        }

	        // Ez badu ezer jo eta tablero barruan badago, tiroa normal mugitzen dugu
	        garbituTiroa(t);
	        t.mugitu(0, t.getDy());
	        margotuTiroa(t);
	    }

	    // Tiro guztiak iteratu eta gero, ezabatzeko markatutako tiroak tablerotik garbitzen ditugu
	    for (TiroaTaldea t : ezabatzekoTiroak) {
	        garbituTiroa(t);
	    }
	    
	    // Eta amaitzeko, ezabatzeko markatutako tiroak zerrendatik kentzen ditugu
	    tiroak.removeAll(ezabatzekoTiroak);
	}
	
	// === TIROEN MUGIMENDURAKO METODO LAGUNGARRIAK ===
	private boolean tiroakZerbaitJoDu(TiroaTaldea t, List<Koordenatua> hurrengoKoordenatuak) {
		// Tiroa hegazkinarena bada, etsaia jo duen konprobatu
		if (t.hegazkinarenaDa()) {
	        return tiroakEtsaiaJoDu(hurrengoKoordenatuak);
	    }
		// Tiroa etsaiarena bada, hegazkina jo duen konprobatu
	    if (t.etsaiarenaDa()) {
	        return tiroakHegazkinaJoDu(hurrengoKoordenatuak);
	    }
	    return false;
	}
	
	private boolean tiroakEtsaiaJoDu(List<Koordenatua> hurrengoKoordenatuak) {
	    for (Koordenatua k : hurrengoKoordenatuak) {
	        EtsaiaTaldea e = kolpatutakoEtsaia(k.getX(), k.getY());

	        if (e != null) {	// Etsairen bat aurkitu bada, ezabatu egiten da
	            etsaiaEzabatu(e.getIndizea());
	            return true;
	        }
	    }
	    return false;	// Ez badu etsairik jo
	}
	
	private boolean tiroakHegazkinaJoDu(List<Koordenatua> hurrengoKoordenatuak) {
	    for (Koordenatua k : hurrengoKoordenatuak) {
	        if (kolpatutakoHegazkina(k.getX(), k.getY())) {	// Koordenatu horretan hegazkina badago, partida galtzen da
	            partidaGaldu();
	            return true;
	        }
	    }
	    return false;	// Ez badu hegazkina jo
	}
	
	private boolean tiroakBesteTiroBatJoDu(TiroaTaldea t, List<Koordenatua> hurrengoKoordenatuak, List<TiroaTaldea> ezabatzekoTiroak) {
	    for (Koordenatua k : hurrengoKoordenatuak) {
	        TiroaTaldea besteTiroa = kolpatutakoTiroa(k.getX(), k.getY(), t);
	        
	        // Koordenatu horretan beste tiro bat badago eta kontrako noranzkoan badoa, talka egin dute
	        if (besteTiroa != null && besteTiroa.getDy() != t.getDy()) { 
	        	ezabatzekoTiroak.add(besteTiroa);	// Iteratzen ez gauden tiroa ezabatzeko markatzen dugu
	            return true;						// Iteratzen gauden tiroa ezabatu beharko dugu
	        }
	    }
	    return false;	// Ez du beste tirorik jo
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
    
    private TiroaTaldea kolpatutakoTiroa(int x, int y, TiroaTaldea nireTiroa) {
	    for (TiroaTaldea t : tiroak) {
	        if (t != nireTiroa) {
	            for (Koordenatua k : t.getKoordenatuLista()) {
	                if (k.getX() == x && k.getY() == y) {
	                    return t;
	                }
	            }
	        }
	    }
	    return null;
	}
    
	private boolean kolpatutakoHegazkina(int x, int y) {
	    for (Koordenatua k : hegazkina.getKoordenatuLista()) {
	        if (k.getX() == x && k.getY() == y) {
	            return true;
	        }
	    }
	    return false;
	}
		 
	// === PARTIDA AMAITZEKO METODOAK ===
	private void partidaAmaitu() {
		gameOver = true;
		amaituJokoa();
	}
	private void partidaIrabazi() {
		partidaAmaitu();
		JokoKudeatzailea.getEMA().setIrabazi(true);
		setChanged();
		notifyObservers("PARTIDA_AMAITUTA");
	}
	private void partidaGaldu() {
		partidaAmaitu();
		JokoKudeatzailea.getEMA().setIrabazi(false);
		setChanged();
		notifyObservers("PARTIDA_AMAITUTA");
	}
	private boolean etsaiaBeheraHelduDa(EtsaiaTaldea e) {
		for (Koordenatua k : e.getKoordenatuLista()) {
			if (k.getY() >= altuera-1) return true;
		}
		return false;
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
				puntuazioa = puntuazioa + 20*puntuazioBiderkatzailea;
				setChanged();
				notifyObservers("ETSAIAK_KOP_EGUNERATU");
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
	
	// === PIXELAK MARGOTZEKO ETA GARBITZEKO METODOAK ===
	private void margotuHegazkina() {
		hegazkina.getKoordenatuLista().stream().forEach(k->tableroMatrizea[k.getX()][k.getY()].jarriHegazkina(hegazkina.getMotaChar()));
	}
	private void garbituHegazkina() {
		hegazkina.getKoordenatuLista().stream().forEach(k->tableroMatrizea[k.getX()][k.getY()].hutsikUtzi());
    }
	private void margotuEtsaia(EtsaiaTaldea e) {
		e.getKoordenatuLista().stream().forEach(k->tableroMatrizea[k.getX()][k.getY()].jarriEtsaia(e.getMotaChar()));
	}
	private void garbituEtsaia(EtsaiaTaldea e) {
		e.getKoordenatuLista().stream().forEach(k->tableroMatrizea[k.getX()][k.getY()].hutsikUtzi());
    }
	private void margotuTiroa(TiroaTaldea t) {
		t.getKoordenatuLista().stream().forEach(k->tableroMatrizea[k.getX()][k.getY()].jarriTiroa());
	}
    private void garbituTiroa(TiroaTaldea t) {
		t.getKoordenatuLista().stream().forEach(k->tableroMatrizea[k.getX()][k.getY()].hutsikUtzi());
    }
	
	// === COMPOSITE PATROIERAKO METODOAK ===
	private boolean hegazkinarenKoordenatuaDa(int x, int y) {
	    
		//return hegazkina.getKoordenatuLista().stream()
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
		    if (mota == 'e' || mota == 'm' || mota == 'c') {
		    	gameOver = true;
		    	return false;
		    }
		    if (mota != 'u' && !hegazkinarenKoordenatuaDa(xBerria, yBerria)) return false;
	    }
	    return true;
	}
	
	private boolean etsaiaMugituDaiteke(int dx, int dy, EtsaiaTaldea e) {
	    // Kordenatu berrien lista bat sortzen dugu, eta gero lista horren kordenatu guztiak libre dauden konprobatzen dugu.
		List<Koordenatua> kordBerriak = new ArrayList<>(); 
		
		e.getKoordenatuLista().stream().forEach(k->kordBerriak.add(new Koordenatua(k.getX() + dx, k.getY() + dy)));
		
		return koordenatuakLibreDaudeEtsaia(kordBerriak, e.getIndizea());
	}
	
	private boolean etsaiaSortuDaiteke(String mota, int x, int y) {
		return koordenatuakLibreDaudeEtsaia(EtsaiaFactory.nireEMA().sortuKoordenatuak(mota, new Koordenatua(x,y)), -1);
	}
	
    private boolean koordenatuakLibreDaudeEtsaia(List<Koordenatua> koordBerriak, int nireEtsaia) {
        for (Koordenatua kBerria : koordBerriak) {
            int x = kBerria.getX();
            int y = kBerria.getY();

            if (!posizioBaliozkoa(x, y)) return false;

            if (hegazkinarenKoordenatuaDa(x, y)) {
            	gameOver = true;
            	return false;
            }

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
    
    
    private List<Koordenatua> tiroarenHurrengoKoordenatuak(List<Koordenatua> koordenatuak, int dx, int dy) {
    	List<Koordenatua> koordenatuBerriak = new ArrayList<>();

    	for (Koordenatua k : koordenatuak) {
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
    
    // === PROGRESIBO MODUA ===
    private void hurrengoMailaProgresiboa() {
    	JokoKudeatzailea.getEMA().hurrengoMailaProgresiboa();
    	zailtasunPortaera = ZailtasunFactory.nireEMA().sortuZailtasuna(zailtasunMota);
    	zailtasunaAplikatu();
    	
    	// Zailtasun berria aplikatu eta gero, etsaien timerra eguneratu behar dugu
    	if (timerEtsai != null) {
            timerEtsai.setDelay(etsaiAbiadura);
        }
    	
    	tableroaGarbitu();	// Tableroa guztiz garbitu berriro hasteko
    	
    	tiroak.clear();		// Tiro zerrenda garbitu
    	etsaiak.clear();	// Etsai zerrenda garbitu (ez luke beharrezkoa izango)
    	
    	sortuEtsaiak(motaEtsaia);
    	sortuHegazkina(motaHegazkina);
    	
    	setChanged();
    	notifyObservers("ETSAIAK_KOP_EGUNERATU");
    }
    
    private void tableroaGarbitu() {
    	for (int i = 0; i < zabalera; i++) {
            for (int j = 0; j < altuera; j++) {
            	tableroMatrizea[i][j].hutsikUtzi();
            }
        }
    }
}