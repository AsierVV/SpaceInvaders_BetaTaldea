package modeloa;

public class Tableroa {
	private Gelaxka[][] tableroMatrizea;
	
	public Tableroa(int errenkadak, int zutabeak) {
        tableroMatrizea = new Gelaxka[errenkadak][zutabeak];

        for (int i = 0; i < errenkadak; i++) {
            for (int j = 0; j < zutabeak; j++) {
                Gelaxka p = new Gelaxka();
            	tableroMatrizea[i][j] = p;
            }
        }
    }
	
	 public Gelaxka getPixel(int errenkadak, int zutabeak) {
	        return tableroMatrizea[errenkadak][zutabeak];
	    }
}
