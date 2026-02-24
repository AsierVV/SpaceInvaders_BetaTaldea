package modeloa;

public class Tableroa {
	private Pixel[][] tableroMatrizea;
	
	public Tableroa(int errenkadak, int zutabeak) {
        tableroMatrizea = new Pixel[errenkadak][zutabeak];

        for (int i = 0; i < errenkadak; i++) {
            for (int j = 0; j < zutabeak; j++) {
                Pixel p = new Hutsunea();
            	tableroMatrizea[i][j] = p;
            }
        }
    }
	
	 public Pixel getPixel(int errenkadak, int zutabeak) {
	        return tableroMatrizea[errenkadak][zutabeak];
	    }
}
