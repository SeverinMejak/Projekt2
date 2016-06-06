package projekt2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Razred, ki sliko pretvori v niz nizov z intenzitetami barv
public class NaloziSliko {
	
	//Metoda, ki sliko pretvori v niz nizov z intenzitetami barv
	public static int[][] pretvori (File fajl, int m) throws IOException {
		
		BufferedImage slika = ImageIO.read(fajl);

		int w = slika.getWidth();
		int h = slika.getHeight();
		
		//Ustvari nov seznam seznamov
		int[][] sez1 = new int[w * h][5];
		
		//Pridobi seznam z RGB podatki slike
		int[] sez = slika.getRGB(0, 0, slika.getWidth(), slika.getHeight(),null, 0, slika.getWidth());
		
		int n = 0;
		//V sez1 shrani podatke o barvi slike po barvah
		for (int i: sez) {
			Color j = new Color(i);
			
			sez1[n][0] = j.getRed();
			sez1[n][1] = j.getBlue();
			sez1[n][2] = j.getGreen();
			sez1[n][3] = n%w;
			sez1[n][4] = n/w;
			n += 1; 
		}
		
		//Kaj naredimo s sliko?
		if (m == 0){
			//Èe gremo po vrsticah (vsakiè od leve proti desni), pustimo tako kot je
			return sez1;
		} else if (m == 1){
			//Èe gremo zvezno po stolpcih, moramo "premešati seznam"
			//Ustvarimo nov seznam
			int[][] sez2 = new int[w * h][5];
			
			int j = 0;
			int k = 0;
			int l = 0;
			int u = 1;
			int z = 0;
			
			//While zanka, ki premeša seznam
			while (k < h * w - 1){
				sez2[k] = sez1[l];
				sez2[k][3] = z;
				sez2[k][4] = u-1;
				if (j == 1){
					j = 2;
					l += 1;
					z += 1;
				} else if (j == 0){
					l += w ;
					if (u >= h-1){
						j = 1;
						u = 1;
					} else{
					u += 1;
					}
				} else if (j == 2){
					l -= w;
					if (u >= h-1){
						j = 0;
						u = 1;
					} else{
					u += 1;
					}
				}
				k += 1;
			}
			return sez2;
		} else {
			//Èe imamo vkljuèeno možnost, da igra uporabnik, nam ni treba premešati seznama
			return sez1;
		}
	}
}
