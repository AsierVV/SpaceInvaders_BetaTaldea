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
	private String jokalariIzena;
	
	private Timer timer;
	private Timer timerEtsai;
	private Timer timerHegazkina;
	private final int abiaduraTimer = 50;			//50ms
	private final int abiaduraHegazkina = 70;		//70ms
	private int denboraSegundoak = 0;
	private long azkenDenboraEguneraketa = 0;
	private int puntuazioa = 0;
	
	private int etsaiKop = 4;						//4 etsai (default)
	private int etsaiAbiadura = 200;				//200ms (default)
	private int puntuazioBiderkatzailea = 1;		//x1 (default)
	
	private int tiroEginKont = 5;					//300ms, beraz 50ms * 6 --> Bostean hasi parametroa, horrela 50ms pasatzen diren seigarren aldian 300ms pasatu dira.
	private int etsaiekTiroEginKont = 19;			//1000ms, beraz 50ms * 20 --> Hemeretzian hasi parametroa, horrela 50ms pasatzen diren hogeigarren aldian 1000ms pasatu dira.
	
	private long azkenTiroa = 0;
    private final long tiroKadentzia= 300;			//300ms
	
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
    private boolean partidaGordeta = false;
    
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
        
        // --- TIMERRRAK ERAIKI ---
        timerHegazkina = new Timer(abiaduraHegazkina, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mugituHegazkinaControl();			
			}
		});
        
        timer = new Timer(abiaduraTimer, new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		
        		// 50ms
        		mugituTiroak();
    			barreraEtsaiJoDu();
        		
        		// HAU BAKARRIK ERABILIKO DA MAILA PROGRESIBOAN
        		// Hurrengo nivel progresiboa eskatuta badago, aurrera egin
        		// Hau hemen egiten dugu arazoak ekiditeko; izan ere, mugituTiroak metodoaren while barruan hurrengo mailara joatea eskatzen badugu,
        		// arazoak emango ditu iteradorea, tiroak.clear() egiten dugulako nivelez aldatzerakoan
        		if (hurrengoMailaProgEskatuta) {
        			hurrengoMailaProgEskatuta = false;
        			hurrengoMailaProgresiboa();
        			setChanged();
        			notifyObservers("HURRENGO_MAILA");
        			return;
        		}
    			
    			// Konprobatu 300ms pasatu diren
        		if (tiroEginKont <= 0) {
					tiroakSortu();
					tiroEginKont = 5;
					
        		} else tiroEginKont--;
        		
        		// Konprobatu 1000ms pasatu diren
        		if (etsaiekTiroEginKont <= 0) {
        			if (zailtasunPortaera.etsaiekTiroEginBeharDute()) {
					    etsaiekTiroEgin();
					}
        			etsaiekTiroEginKont = 19;
        			
        		} else etsaiekTiroEginKont--;

        		// Konprobatu 1s pasatu den
        		long orain = System.currentTimeMillis();
        		if (orain - azkenDenboraEguneraketa > 1000) {
        			denboraSegundoak++;
        			azkenDenboraEguneraketa = orain;
        			
        			setChanged();
        			notifyObservers("DENBORA_EGUNERATU");
        		}	
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
    
    public List<Koordenatua> getTiroenKoordenatuak() {
    	return tiroak.stream().map(t->t.getPosizioa()).toList();
    }
    
    public String getJokalariIzena() {
    	return jokalariIzena;
    }
    
    public int getBarreraKop() {
    	return hegazkina.getBarreraKop();
    }

	// === JOKOA HASTEKO METODOA ===
    public void hasiJokoa(String pMotaHegazkina, String pMotaEtsaia, String pZailtasunMota, String pJokalariIzena) {

    	motaHegazkina = pMotaHegazkina;
    	motaEtsaia = pMotaEtsaia;
    	zailtasunMota = pZailtasunMota;
    	jokalariIzena = pJokalariIzena;
    	
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
        
        if (!timer.isRunning()) timer.start();
        if (!timerEtsai.isRunning()) timerEtsai.start();
        if (!timerHegazkina.isRunning()) timerHegazkina.start();
        
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
    	timerHegazkina.stop();
    }
    public void startStopJokoa() {
    	if (timer.isRunning()) {
    		timer.stop();
        	timerEtsai.stop();
        	timerHegazkina.stop();
    		setChanged();
        	notifyObservers("STOP");
    	} else if (!timer.isRunning()) {
    		setChanged();
        	notifyObservers("START");
        	timer.start();
        	timerEtsai.start();
        	timerHegazkina.start();
    	}
    }
    
    private void zailtasunaAplikatu() {
    	etsaiKop = zailtasunPortaera.etsaiKopLortu();
    	etsaiAbiadura = zailtasunPortaera.etsaiAbiaduraLortu();
    	puntuazioBiderkatzailea = zailtasunPortaera.puntuazioBiderkatzaileaLortu();
    }
    
    private void hegazkinarenBaliabideakEzarri() {
    	hegazkina.munizioaEzarri(
    		zailtasunPortaera.geziKopLortu(),
    		zailtasunPortaera.erronboKopLortu(),
    		zailtasunPortaera.barreraKopLortu()
    		);
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
        hegazkinarenBaliabideakEzarri();
        margotuHegazkina();
	}
    
	// === ETSAIAK SORTU ===
	private void sortuEtsaiak(String mota) {
		Random r = new Random();
	    int kopurua = etsaiKop + r.nextInt(etsaiKop/2 + 1); // r.nextInt(5) --> 0 eta 4 arteko zenbaki bat ematen du
			/* ETSAI KOPURUAK:			Azalpena: etsaiKop/2 + 1 erbiltzen dugu etsai kopuruaren erdia gehitu ahal izateko: 4/2 + 1 = 3 -> r.nextInt(3) -> 0-2 arteko etsai gehiketa
			 *	- Erraza -> 4-6
			 *	- Normala -> 8-12
			 *	- Zaila -> 8-12
			 *	- Ezinezkoa -> 10-15
			 *	- Progresiboa:
			 *		1. Maila -> 4-6
			 *		2. Maila -> 6-9
			 *		3. Maila -> 8-12
			 *		4. Maila -> 10-15
			 */
	    
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
	public void hegazkinaTiroaSortu() {
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
		if (!(timer.isRunning() && timerEtsai.isRunning())) return;

		etsaiak.stream()
			.filter(e -> Math.random() < 0.3)	// Tiro egiteko probabilitatea -> %30
			.map(e -> {
				int x = e.getPosizioa().getX();
				int y = e.getPosizioa().getY() + 2;	// Etsaiaren azpian
				return List.of(new Koordenatua(x, y));
			})
			.filter(koordenatuak -> tiroaSortuDaiteke(koordenatuak))
			.map(koordenatuak -> new TiroaTaldea(koordenatuak, 1))
			.forEach(t -> {
				tiroak.add(t);
				margotuTiroa(t);
			});
	}
	
	// === BARRERA SORTU ===
		public void barreraEgin() {
			if(hegazkina.barrerakDaude() && barreraSortuDaiteke()) {
				hegazkina.barreraAktibatu();
				hegazkina.barreraKontsumitu();
				margotuBarrera();
				setChanged();
				notifyObservers("BARRERAK_KOP_EGUNERATU");
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
	    	if (hegazkina.barreraAktiboDago()) {
		    	garbituHegazkina();
	    		garbituBarrera();
	    		hegazkina.mugitu(dx, dy);
	    		margotuBarrera();
	    		margotuHegazkina();
	    	} else {
		    	garbituHegazkina();
		    	hegazkina.mugitu(dx, dy);
		    	margotuHegazkina();
	    	}
		}
	}
	 
	// === ETSAIEN MUGIMENDUA ===
	public void mugituEtsaiak() {
		if (gameOver && !irabaziDuzu) partidaGaldu();
		
		Iterator<EtsaiaTaldea> it = etsaiak.iterator();
		while (it.hasNext()) {			
			EtsaiaTaldea e = it.next();
			
			Koordenatua dif;

			if (zailtasunPortaera.mugimenduHegazkinAdimendunaErabili()) {
			    dif = e.mugimenduHegazkinAdimenduaEtsaia();
			} else if (zailtasunPortaera.mugimenduTiroAdimendunaErabili()) {
				dif = e.mugimenduTiroAdimenduaEtsaia();
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
	    ezabatzekoTiroak.stream().forEach(t -> garbituTiroa(t));
	    
	    // Eta amaitzeko, ezabatzeko markatutako tiroak zerrendatik kentzen ditugu
	    tiroak.removeAll(ezabatzekoTiroak);
	}
	
	// === BARRERA ETSAIEN BAT JO ===
	private void barreraEtsaiJoDu() {
		if (!hegazkina.barreraAktiboDago()) return;
		
		EtsaiaTaldea etsaia = hegazkina.getBarrera().getKoordenatuLista().stream()
			.map(k -> kolpatutakoEtsaia(k.getX(), k.getY()))	// Barreraren koordenatu bakoitzeko posizio horretan dagoen etsaian bihurtzen du, etsairik ez badago null itzultzen du
			.filter(e -> e != null)	// Null direnak ez ditu hartzen
			.findFirst()	// Aurkitutako lehenengo etsaia hartzen du (normalean bakarrik aurkituko du etsai bat)
			.orElse(null);	// Ezer ez badu aurkitzen null itzultzen du
		
		if (etsaia != null) {
			garbituBarrera();
			etsaiaEzabatu(etsaia.getIndizea());
			hegazkina.barreraDesaktibatu();
		}
	}
	
	// === TIROEN MUGIMENDURAKO METODO LAGUNGARRIAK ===
	private boolean tiroakZerbaitJoDu(TiroaTaldea t, List<Koordenatua> hurrengoKoordenatuak) {
		// Tiroa hegazkinarena bada, etsaia jo duen konprobatu
		if (t.hegazkinarenaDa()) {
	        return tiroakEtsaiaJoDu(hurrengoKoordenatuak);
	    }
		// Tiroa etsaiarena bada, hegazkina jo duen konprobatu
	    if (t.etsaiarenaDa()) {
	    	return tiroakHegazkinaJoDu(hurrengoKoordenatuak) || tiroakBarreraJoDu(hurrengoKoordenatuak);
	    }
	    return false;
	}
	
	private boolean tiroakEtsaiaJoDu(List<Koordenatua> hurrengoKoordenatuak) {
	    EtsaiaTaldea etsaia = hurrengoKoordenatuak.stream()
	    		.map(k -> kolpatutakoEtsaia(k.getX(), k.getY()))	// Koordenatua bakoitzeko kolpatutako etsaiak bilatzen dira
	    		.filter(e -> e != null)	// Null direnak ez dira hartzen
	    		.findFirst()	// Aurkitutako lehenengo etsaia hartzen da
	    		.orElse(null);	// Ez bada ezer aurkitzen null itzultzen da
	    
	    if (etsaia != null) {	// Etsairen bat aurkitu bada, ezabatu egiten da
            etsaiaEzabatu(etsaia.getIndizea());
            return true;
	    }
	    return false;	// Ez badu etsairik jo
	}
	
	private boolean tiroakHegazkinaJoDu(List<Koordenatua> hurrengoKoordenatuak) {
		// anyMatch -> Koordenaturen bat baldintza betetzen badu, joDu true izango da
	    boolean joDu = hurrengoKoordenatuak.stream().anyMatch(k -> kolpatutakoHegazkina(k.getX(), k.getY()));
		
	    if (joDu) {
	    	partidaGaldu();
	    	return true;
	    }
	    return false;	// Ez badu hegazkina jo
	}
	
	private boolean tiroakBarreraJoDu(List<Koordenatua> hurrengoKoordenatuak) {
		if (!hegazkina.barreraAktiboDago()) return false;
		
		boolean joDu = hurrengoKoordenatuak.stream().anyMatch(k -> kolpatutakoBarrera(k.getX(), k.getY()));
		
		if (joDu) {
			garbituBarrera();
        	hegazkina.barreraDesaktibatu();
            return true;
		}
	    return false;
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
        return etsaiak.stream()
        		.filter(e -> e.getKoordenatuLista().stream()			// Filtro bat egiten dugu baldintza betetzen dutenekin geratzeko
        				.anyMatch(k -> k.getX() == x && k.getY() == y))	// Baldintza: etsaiaren kordenaturen bat x eta y-rekin kointziditzea
        		.findFirst()	// Aurkitutako lehenengo etsaia hartzen dugu (normalean etsai baten bat aurkitzen bada, bakarra  izango da)
        		.orElse(null);	// Etsairik ez bada aurkitzen null bueltatu
    }
    
    private TiroaTaldea kolpatutakoTiroa(int x, int y, TiroaTaldea nireTiroa) {
	    return tiroak.stream()
	    		.filter(t -> t != nireTiroa)	// Tiroa ez bada sartutako tiroa filtroa pasatzen du
	    		.filter(t -> t.getKoordenatuLista().stream()		// Filtro bat egiten dugu baldintza betetzen dutenekin geratzeko
	    			.anyMatch(k -> k.getX() == x && k.getY() == y))	// Baldintza: tiroaren kordenaturen bat x eta y-rekin kointziditzea
	    		.findFirst()	// Aurkitutako lehenengo tiroa hartzen dugu
	    		.orElse(null);	// Tirorik ez bada aurkitzen null itzultzen da
	}
    
	private boolean kolpatutakoHegazkina(int x, int y) {
	    return hegazkina.getKoordenatuLista().stream()
	    		.anyMatch(k -> k.getX() == x && k.getY() == y);
	}
	
	private boolean kolpatutakoBarrera(int x, int y) {
		return hegazkina.getBarrera().getKoordenatuLista().stream()
				.anyMatch(k -> k.getX() == x && k.getY() == y);
	}
		 
	// === PARTIDA AMAITZEKO METODOAK ===
	private void partidaAmaitu() {
		gameOver = true;
		amaituJokoa();
		puntuazioFinalaLortu();
	}
	
	private void partidaIrabazi() {
		partidaAmaitu();
		
		if (!partidaGordeta) {
			PartidaErregistroa.gordePartida(
					jokalariIzena,
					"IRABAZI",
					puntuazioa,
					denboraSegundoak,
					zailtasunMota,
					motaHegazkina,
					motaEtsaia
					);
			partidaGordeta = true;
		}
		
		JokoKudeatzailea.getEMA().setIrabazi(true);
		setChanged();
		notifyObservers("PARTIDA_AMAITUTA");
	}
	
	private void partidaGaldu() {
		partidaAmaitu();
		
		if (!partidaGordeta) {
			PartidaErregistroa.gordePartida(
					jokalariIzena,
					"GALDU",
					puntuazioa,
					denboraSegundoak,
					zailtasunMota,
					motaHegazkina,
					motaEtsaia
					);
			partidaGordeta = true;
		}
		
		
		JokoKudeatzailea.getEMA().setIrabazi(false);
		setChanged();
		notifyObservers("PARTIDA_AMAITUTA");
	}
	
	private boolean etsaiaBeheraHelduDa(EtsaiaTaldea e) {
		return e.getKoordenatuLista().stream()
				.anyMatch(k -> k.getY() >= altuera - 1);
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
	
	// === PUNTUAZIO FINALA KALKULATU ===
	private void puntuazioFinalaLortu() {
		if (!irabaziDuzu) return;
		
		// Denboragatik bonifikazioa
		switch(zailtasunMota) {
			case "Erraza":
				if (denboraSegundoak <= 20) puntuazioa = puntuazioa*50;
				else if (denboraSegundoak <= 40) puntuazioa = puntuazioa*30;
				else if (denboraSegundoak <= 60) puntuazioa = puntuazioa*10;
				break;
			case "Normala":
				if (denboraSegundoak <= 20) puntuazioa = puntuazioa*60;
				else if (denboraSegundoak <= 40) puntuazioa = puntuazioa*40;
				else if (denboraSegundoak <= 60) puntuazioa = puntuazioa*10;
				break;
			case "Zaila":
				if (denboraSegundoak <= 20) puntuazioa = puntuazioa*80;
				else if (denboraSegundoak <= 40) puntuazioa = puntuazioa*40;
				else if (denboraSegundoak <= 60) puntuazioa = puntuazioa*10;
				break;
			case "Ezinezkoa":
				if (denboraSegundoak <= 20) puntuazioa = puntuazioa*100;
				else if (denboraSegundoak <= 40) puntuazioa = puntuazioa*50;
				else if (denboraSegundoak <= 60) puntuazioa = puntuazioa*20;
				break;
			case "Progresiboa":
				if (denboraSegundoak <= 60) puntuazioa = puntuazioa*100;
				else if (denboraSegundoak <= 80) puntuazioa = puntuazioa*50;
				else if (denboraSegundoak <= 100) puntuazioa = puntuazioa*20;
				break;
		}
		
		// Soberan utzi diren tiroengatik bonifikazioa
		int tiroErronboSoberanPuntuazioa = hegazkina.getTiroKopErronbo() * 30;
		int tiroGeziSoberanPuntuazioa = hegazkina.getTiroKopGezia() * 10;

		switch(hegazkina.getMotaChar()) {
			case 'g':
				puntuazioa = puntuazioa + tiroGeziSoberanPuntuazioa;
				break;
			case 'b':
				puntuazioa = puntuazioa + tiroErronboSoberanPuntuazioa;
				break;
			case 'r':
				puntuazioa = puntuazioa + tiroGeziSoberanPuntuazioa;
				puntuazioa = puntuazioa + tiroErronboSoberanPuntuazioa;
				break;
		}
	}
	
	// === ETSAIA EZABATU ===
	private void etsaiaEzabatu(int ind) {
		EtsaiaTaldea ezabatzekoEtsaia = etsaiak.stream()
				.filter(e -> e.getIndizea() == ind)	// Filter baten bidez nahi dugun etsaiarekin bakarrik geratzen gara
				.findFirst()	// Badakigu etsai bakarra egongo dela ind horrekin, baina FindFirst erabili behar dugu filter komandotik objetu bakarrarekin geratzeko
				.orElse(null);
		
		if (ezabatzekoEtsaia != null) {
			etsaiak.remove(ezabatzekoEtsaia);
			garbituEtsaia(ezabatzekoEtsaia);
			puntuazioa = puntuazioa + 20*puntuazioBiderkatzailea;
			setChanged();
			notifyObservers("ETSAIAK_KOP_EGUNERATU");
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
		if (tiroEgin) hegazkinaTiroaSortu();
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
			hegazkinaTiroaSortu();
			tiroEgin = true;
		}
	}
	public void tiroaAskatu() {tiroEgin = false;}
	
	public void barreraSakatu() {
		if(!hegazkina.barreraAktiboDago()) {
			barreraEgin();
		}
	}
	
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
    private void margotuBarrera() {
    	hegazkina.getBarrera().getKoordenatuLista().stream().forEach(k->tableroMatrizea[k.getX()][k.getY()].jarriBarrera());
    }
    private void garbituBarrera() {
    	hegazkina.getBarrera().getKoordenatuLista().stream().forEach(k->tableroMatrizea[k.getX()][k.getY()].hutsikUtzi());
    }
	
	// === COMPOSITE PATROIERAKO METODOAK ===
	private boolean hegazkinarenKoordenatuaDa(int x, int y) {
		return hegazkina.getKoordenatuLista().stream()
				.anyMatch(k -> k.getX() == x && k.getY() == y);
	}
	
	private boolean hegazkinaMugituDaiteke(int dx, int dy) {
		if (hegazkina.barreraAktiboDago() && !barreraMugituDaiteke(dx, dy)) {
	    	return false;
	    }
		
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
	
	private boolean barreraMugituDaiteke(int dx, int dy) {
		return hegazkina.getBarrera().getKoordenatuLista().stream()
				.allMatch(k -> posizioBaliozkoa(k.getX() + dx, k.getY() + dy));	// allMatch erabiliz, koordenatu guztiak baldintza bete behar dute (baliozko koordenatua izatea)
	}
	
	private boolean etsaiaMugituDaiteke(int dx, int dy, EtsaiaTaldea e) {
	    // Kordenatu berrien lista bat sortzen dugu, eta gero lista horren kordenatu guztiak libre dauden konprobatzen dugu.
		List<Koordenatua> kordBerriak = e.getKoordenatuLista().stream()
				.map(k -> new Koordenatua(k.getX() + dx, k.getY() + dy))
				.toList();
		
		// Goiko .stream guztia sartu daiteke ere beheko metodo honetan "kordBerriak" sartu den lekuan, eta horrela lerroa bakarrean egin guztia,
		// baina askoz ulergarriagoa da horrela
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
    	return koordenatuak.stream()
    			.map(k -> new Koordenatua(k.getX() + dx, k.getY() + dy))
    			.toList();
    }

    private boolean koordenatuGuztiakBaliozkoak(List<Koordenatua> koordenatuak) {
    	return koordenatuak.stream()
    			.allMatch(k -> posizioBaliozkoa(k.getX(), k.getY()));
    }

    private boolean tiroaSortuDaiteke(List<Koordenatua> koordenatuak) {
    	return koordenatuak.stream()
    			.allMatch(k -> 	// allMatch honekin lortu nahi dugu konprobatzea listaren koordenatu guztiak hurrengo baldintza guztiak betetzea
    					posizioBaliozkoa(k.getX(), k.getY()) &&
    					!hegazkinarenKoordenatuaDa(k.getX(), k.getY()) &&
    					tableroMatrizea[k.getX()][k.getY()].getMota() != 't' &&
    					tableroMatrizea[k.getX()][k.getY()].getMota() != 'p');
    }
    
    private boolean barreraSortuDaiteke() {
    	return hegazkina.getBarrera().getKoordenatuLista().stream()
    			.allMatch(k -> posizioBaliozkoa(k.getX(), k.getY()));
    }
    
    // === PROGRESIBO MODUA ===
    private void hurrengoMailaProgresiboa() {
    	JokoKudeatzailea.getEMA().hurrengoMailaProgresiboa();
    	zailtasunPortaera = ZailtasunFactory.nireEMA().sortuZailtasuna(zailtasunMota);
    	zailtasunaAplikatu();
    	
    	// Zailtasun berria aplikatu eta gero, etsaien timerra eguneratu behar dugu
    	timerEtsai.setDelay(etsaiAbiadura);
    	
    	tableroaGarbitu();	// Tableroa guztiz garbitu berriro hasteko
    	
    	tiroak.clear();		// Tiro zerrenda garbitu
    	etsaiak.clear();	// Etsai zerrenda garbitu (ez luke beharrezkoa izango)
    	
    	if (hegazkina.barreraAktiboDago()) hegazkina.barreraDesaktibatu();
    	
    	sortuEtsaiak(motaEtsaia);
    	
    	hegazkina.defaultTiroMotaEzarri();
    	hegazkinaHasierakoPosizioraEraman();
    	margotuHegazkina();	// Hegazkina ez dugu berriro sortuko, bakarrik margotuko dugu berriz hasierako koordenatuaetan
    	
    	setChanged();
    	notifyObservers("ETSAIAK_KOP_EGUNERATU");
    }
    
    private void hegazkinaHasierakoPosizioraEraman() {
    	int dx = 50 - hegazkina.getPosizioa().getX();	// X koordenatuan hasierako posiziora heltzeko mugitu behar den X koordenatuko pixel kantitatea lortzen dugu
    	int dy = 55 - hegazkina.getPosizioa().getY();	// Y koordenatuan hasierako posiziora heltzeko mugitu behar den Y koordenatuko pixel kantitatea lortzen dugu

    	hegazkina.mugitu(dx, dy);	// Hegazkina mugitzeko aldi batean hasierako posiziora
    }
    
    private void tableroaGarbitu() {
    	for (int i = 0; i < zabalera; i++) {
            for (int j = 0; j < altuera; j++) {
            	tableroMatrizea[i][j].hutsikUtzi();
            }
        }
    }
}