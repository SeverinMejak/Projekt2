package projekt2;


import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NaloziSliko {
	public static int[] extractBytes (String ImageName) throws IOException {
		 // open image
		 File imgPath = new File(ImageName);
		 BufferedImage slika = ImageIO.read(imgPath);

		 System.out.println(slika.getWidth());
		 System.out.println(slika.getHeight());
		 
		 int[] sez = slika.getRGB(0, 0, slika.getWidth(), slika.getHeight(),null, 0, slika.getWidth());
		 
		
		 return(sez);
		}
}

