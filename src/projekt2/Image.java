package projekt2;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Image{
	static String datoteka;
	public static void slika(String ime)  throws IOException {
		datoteka = ime;
        File file = new File(datoteka);
        BufferedImage image = ImageIO.read(file);
        
        JButton klik = new JButton("Predvajaj");
        
        
        JLabel label = new JLabel(new ImageIcon(image));
        
        JFrame f = new JFrame(datoteka);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        f.add(panel);
        
        //panel.add(klik);
        panel.add(label);
  
        f.pack();
        f.setLocation(200,200);
        f.setVisible(true);
    }
	
}
