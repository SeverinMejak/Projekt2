package projekt2;

import java.awt.Color;
import java.io.IOException;

public class GlavniZacasni {
	public static void main(String[] args) throws IOException, InterruptedException {
		int[] sez = NaloziSliko.extractBytes(args[0]);
		
		int[][] sez1 = new int[sez.length][4];
		int n = 0;
		for (int i: sez) {
			Color j = new Color(i);
			
			System.out.println(j.getRed()+","+ j.getBlue()+","+j.getGreen()+","+ j.getAlpha());
			sez1[n][0] = j.getRed();
			sez1[n][1] = j.getBlue();
			sez1[n][2] = j.getGreen();
			n += 1;
			
		}
		Predvajaj.zaigraj(sez1);
		
	}
}
