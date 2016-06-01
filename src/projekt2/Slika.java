package projekt2;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Slika extends JPanel {
	BufferedImage slika;
	
	// konstruktor
	public Slika() {
		super();
		this.slika = null;
	}
	
	// riše
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.slika != null) {
			// èe imamo sliko, jo narišemo
			g.drawImage(slika, 0, 0, getWidth(),getHeight(), null);
			g.drawOval(((Platno.x-15) * getWidth())/Platno.zacetnaSirina, ((Platno.y-15) * getHeight())/Platno.zacetnaVisina, 30, 30);
			
		}
		else {
			// èe slike ni, napišemo, da je ni
			String napis = "(ni slike)";
			// nastavimo font za izpis
			g.setFont(new Font("Helvetica", Font.PLAIN, 30));
			// dobimo objekt fm, ki zna raèunati vse v zvezi s fontom
			FontMetrics fm = g.getFontMetrics();
			// objekt fm vprašamo, kako velik bo naš napis, da ga znamo
			// centrirati
			Rectangle2D r = fm.getStringBounds(napis, g);
			// naredimo nais, centrirano
			g.drawString(napis, 
					(int)((getWidth() - r.getWidth())/2),
					(int)((getHeight() - r.getHeight())/2));
		}
	}
	
	// prebere sliko iz datoteko
	public  void setSlika(String datoteka)  throws IOException {
        File file = new File(datoteka);
        slika = ImageIO.read(file);
        
        repaint();
    }
	

	
}
