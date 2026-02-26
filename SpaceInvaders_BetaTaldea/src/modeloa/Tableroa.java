package modeloa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tableroa {
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
	
	private void sortuHegazkina() {
        hegazkina = new Hegazkina();
        tableroMatrizea[50][55].setMota(hegazkina);
    }

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

}
