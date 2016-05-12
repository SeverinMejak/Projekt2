package projekt2;


import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NaloziSliko {
	public static int[][] extractBytes (String ImageName) throws IOException {
		 // open image
		
		File imgPath = new File(ImageName);
		BufferedImage slika = ImageIO.read(imgPath);
		//System.out.println(slika.getWidth());
		System.out.println(ImageName);
	     
		int[] sez = slika.getRGB(0, 0, slika.getWidth(), slika.getHeight(),null, 0, slika.getWidth());
		int[][] sez1 = new int[sez.length][4];
		int n = 0;
		for (int i: sez) {
			Color j = new Color(i);
			
		//System.out.println(j.getRed()+","+ j.getBlue()+","+j.getGreen()+","+ j.getAlpha());
		sez1[n][0] = j.getRed();
		sez1[n][1] = j.getBlue();
		sez1[n][2] = j.getGreen();
		n += 1; 
		}
		return(sez1);
	}
}
