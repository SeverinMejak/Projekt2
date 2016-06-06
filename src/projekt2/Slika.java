package projekt2;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Razred, ki nari�e sliko
@SuppressWarnings("serial")
public class Slika extends JPanel {
	BufferedImage slika;
	
	//Konstruktor
	public Slika() {
		super();
		this.slika = null;
	}
	
	//Ri�e
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.slika != null) {
			//�e imamo sliko, jo nari�emo
			g.drawImage(slika, 0, 0, getWidth(),getHeight(), null);
			if(Platno.aliJeSam || Platno.aliVrste || Platno.aliStolpci){
			g.setColor(Color.CYAN);
			g.fillOval(((Platno.x) * getWidth())/Platno.zacetnaSirina - 15, ((Platno.y) * getHeight())/Platno.zacetnaVisina -15, 30, 30);
			g.setColor(Color.MAGENTA);
			g.fillOval(((Platno.x) * getWidth())/Platno.zacetnaSirina - 5, ((Platno.y) * getHeight())/Platno.zacetnaVisina - 5, 10, 10);
			}
		}
		else {
			//�e slike ni, napi�emo, da je ni
			String napis = "(ni slike)";
			//Nastavimo font za izpis
			g.setFont(new Font("Helvetica", Font.PLAIN, 30));
			//Dobimo objekt fm, ki zna ra�unati vse v zvezi s fontom
			FontMetrics fm = g.getFontMetrics();
			//Objekt fm vpra�amo, kako velik bo na� napis, da ga znamo
			//Centrirati
			Rectangle2D r = fm.getStringBounds(napis, g);
			//Naredimo napis, centrirano
			g.drawString(napis, 
					(int)((getWidth() - r.getWidth())/2),
					(int)((getHeight() - r.getHeight())/2));
		}
	}
	
	//Prebere sliko iz datoteke
	public  void setSlika(String datoteka)  throws IOException {
        File file = new File(datoteka);
        slika = ImageIO.read(file);
        
        repaint();
    }
	

	
}
