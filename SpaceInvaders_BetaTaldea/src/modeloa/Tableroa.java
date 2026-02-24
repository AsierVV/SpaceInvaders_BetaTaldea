package modeloa;

public class Tableroa {
	private Gelaxka[][] tableroMatrizea;
	
	public Tableroa() {
        tableroMatrizea = new Gelaxka[100][60];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 60; j++) {
            	Koordenatua k = new Koordenatua(i,j);
                Gelaxka g = new Gelaxka(k);
            	tableroMatrizea[i][j] = g;
            }
        }
    }
	
	 public Gelaxka getPixel(int errenkadak, int zutabeak) {
	        return tableroMatrizea[errenkadak][zutabeak];
	 }
	 
	 public void setHegazkina() {
		 Hegazkina h = new Hegazkina();
		 tableroMatrizea[50][55].setMota(h); 
	 }
}
