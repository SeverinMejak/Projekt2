package projekt2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VecjePlatno extends JFrame implements ActionListener{
	JLabel label;
	private JButton openItem;
	private JButton exitItem;
	Slika zaslon;
	
	VecjePlatno(){
		super();
		this.setPreferredSize(new Dimension(450, 250));
		this.setBackground(Color.white);
		setTitle("MUZIKA");
		
		JPanel slika = new JPanel(); 
		
		openItem = new JButton();
	    openItem.addActionListener(this);
	    
	    try {
	        Image img = ImageIO.read(getClass().getResource("/add.png"));
	        Image newimg = img.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
	        openItem.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    slika.add(openItem);
	    
	    exitItem = new JButton();
	    exitItem.addActionListener(this);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/exit.png"));
	        Image newimg = img.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
	        exitItem.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    slika.add(exitItem);
	    
	    try {
	    	Image slikica = ImageIO.read(getClass().getResource("/disk.png"));
	        setIconImage(slikica.getScaledInstance( 60, 60,  java.awt.Image.SCALE_SMOOTH ));
	    }
	    catch (IOException exc) {
	        exc.printStackTrace();
	    }
	    
	    add(slika);
	}
	@Override
	public void actionPerformed(ActionEvent ex) {
		Object source = ex.getSource();
		if (source == openItem) {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JFrame frame = new Platno();
			frame.pack();
		    frame.setVisible(true);
		}
		else if (source == exitItem){
			System.exit(0);
		}
	}
}