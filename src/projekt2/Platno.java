package projekt2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Platno extends JFrame implements ActionListener{
	JLabel label;
	private JButton openItem;
	private JButton exitItem;
	private JButton predvajaj;
	private JButton predvajaj1;
	public  String datoteka;
	Slika zaslon;
	private JButton prekini;
	private JButton pavza;
	private JButton play;
	
	public Platno() {
		super();
		this.setPreferredSize(new Dimension(450, 400));
		this.setBackground(Color.white);
		setTitle("Muzika");
		
		setLayout(new GridLayout(1, 6));
	    
		//Velikost gumba
		int VG = 25;
	    zaslon = new Slika();
	    
	    openItem = new JButton();
	    openItem.addActionListener(this);
	    
	    try {
	        Image img = ImageIO.read(getClass().getResource("/Open-icon.png"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        openItem.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    zaslon.add(openItem);
	    
	    //"Predvajaj po vrsticah"
	    predvajaj = new JButton();
	    predvajaj.addActionListener(this);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/lines.jpg"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        predvajaj.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    zaslon.add(predvajaj);
	    
	    //"Predvajaj po stolpcih"
	    predvajaj1 = new JButton();
	    predvajaj1.addActionListener(this);
	    zaslon.add(predvajaj1);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/linesV.jpg"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        predvajaj1.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    
	    play = new JButton();
	    play.addActionListener(this);
	    zaslon.add(play);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/play.png"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        play.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    
	    prekini = new JButton();
	    prekini.addActionListener(this);
	    zaslon.add(prekini);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/stop.png"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        prekini.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    
	    pavza = new JButton();
	    pavza.addActionListener(this);
	    zaslon.add(pavza);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/pause.png"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        pavza.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    
	    add(zaslon); 
	    
	    spremeniIkono("/disk.png");
	}
	

	public void spremeniIkono(String datoteka){
		//Spremeni ikono okvira
		try {
	    	Image slikica = ImageIO.read(getClass().getResource(datoteka));
	        setIconImage(slikica.getScaledInstance( 60, 60,  java.awt.Image.SCALE_SMOOTH ));
	    }
	    catch (IOException exc) {
	        exc.printStackTrace();
	    }
	}
	@Override
	public void actionPerformed(ActionEvent ex) {
		Object source = ex.getSource();
		if (source == openItem) {
		      JFileChooser chooser = new JFileChooser();
		      chooser.setCurrentDirectory(new File("."));
		      int r = chooser.showOpenDialog(this);
		      if (r == JFileChooser.APPROVE_OPTION) {
		        datoteka = chooser.getSelectedFile().getAbsolutePath();
		        System.out.print(datoteka);
				try {
					zaslon.setSlika(datoteka);
					setSize(ImageIO.read(new File(datoteka)).getWidth(), ImageIO.read(new File(datoteka)).getHeight());
					spremeniIkono("/Open-icon.png");
				} catch (IOException e1) {
				}
				
				
		    } else if (source == exitItem){
		      System.exit(0);
		  }

		}
		else if (source == predvajaj){
			spremeniIkono("/play.png");
			try {
				Predvajaj.zaigraj(datoteka, 0);
			} catch (InterruptedException e) {
			}
		} else if (source == predvajaj1){
			spremeniIkono("/play.png");
			try {
				Predvajaj.zaigraj(datoteka, 1);
			} catch (InterruptedException e) {
			}
		}
		else if (source == prekini){
			spremeniIkono("/stop.png");
			Predvajaj.prekini();
		} else if (source == pavza){
			spremeniIkono("/pause.png");
			Predvajaj.pavza();
		} else if (source == play){
			spremeniIkono("/play.png");
			Predvajaj.play();
		}
	}

}
