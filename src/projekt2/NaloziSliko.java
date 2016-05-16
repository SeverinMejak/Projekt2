package projekt2;


import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NaloziSliko {
	
	public static int[][] pretvori (String ImageName, int m) throws IOException {
		
		
		 
		File imgPath = new File(ImageName);
		BufferedImage slika = ImageIO.read(imgPath);
		System.out.println(ImageName);
	     
		int w = slika.getWidth();
		int h = slika.getHeight();
		
		
		
		int[][] sez1 = new int[w * h][4];
		
		int[] sez = slika.getRGB(0, 0, slika.getWidth(), slika.getHeight(),null, 0, slika.getWidth());
		
		int n = 0;
		for (int i: sez) {
			Color j = new Color(i);
			
			//System.out.println(j.getRed()+","+ j.getBlue()+","+j.getGreen()+","+ j.getAlpha());
			sez1[n][0] = j.getRed();
			sez1[n][1] = j.getBlue();
			sez1[n][2] = j.getGreen();
			n += 1; 
		}
		
		if (m == 0){
			return sez1;
		} else if (m == 1){
			
			
			
			int[][] sez2 = new int[w * h][4];
			
			int j = 0;
			int k = 0;
			int l = 0;
			int u = 1;
			
			
			
			while (k < h * w - 1){
				
				sez2[k] = sez1[l];
				//System.out.print(j + "," + l + "," + k + "," + s + "," + u + ";    ");
				//System.out.print(j + ",");
				if (j == 1){
					j = 2;
					l += 1;
					
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
			return sez1;
		}
		
		
	}
}
