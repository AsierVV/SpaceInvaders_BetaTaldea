
public class Tableroa {
	private Pixel[][] tableroMatrizea;
	
	public Tableroa(int errenkadak, int zutabeak) {
        tableroMatrizea = new Pixel[errenkadak][zutabeak];

        for (int i = 0; i < errenkadak; i++) {
            for (int j = 0; j < zutabeak; j++) {
                tableroMatrizea[i][j] = new Pixel();
            }
        }
    }
	
	 public Pixel getPixel(int errenkadak, int zutabeak) {
	        return tableroMatrizea[errenkadak][zutabeak];
	    }
}
