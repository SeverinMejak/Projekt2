package projekt2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GlavniZacasni extends JFrame implements ActionListener{
	private JLabel label;
	private JMenuItem openItem;
	private JMenuItem exitItem;
	private JMenuItem predvajaj;
	private String datoteka;
	private JPanel zaslon;
	private JMenuItem prekini;
	
	public GlavniZacasni() {
	    setTitle("ImageViewer");
	    setSize(300, 400);
	    JMenuBar menu = new JMenuBar();
	    JMenu m = new JMenu("Datoteka");
	    openItem = new JMenuItem("Odpri");
	    openItem.addActionListener(this);
	    m.add(openItem);
	    predvajaj = new JMenuItem("Predvajaj");
	    predvajaj.addActionListener(this);
	    prekini = new JMenuItem("Prekini");
	    prekini.addActionListener(this);
	    JPanel zaslon = new JPanel();
	    zaslon.add(predvajaj);
	    m.add(predvajaj);
	    m.add(prekini);
	    exitItem = new JMenuItem("Exit");
	    exitItem.addActionListener(this);
	    m.add(exitItem);
	    menu.add(m);
	    setJMenuBar(menu);
	   
	    add(zaslon); 
	    //label = new JLabel();
	    //Container contentPane = getContentPane();
	    //contentPane.add(label, "Center");
	    //contentPane.add(zaslon);
	    
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		JFrame frame = new GlavniZacasni();
	    frame.setVisible(true);
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
					Image.slika(datoteka);
					//Predvajaj.zaigraj(name);
				} catch (IOException e1) {
				}
				
				
		    } else if (source == exitItem){
		      System.exit(0);
		  }

		}
		else if (source == predvajaj){
			try {
				Predvajaj.zaigraj(datoteka);
			} catch (InterruptedException e) {
			}
		}
		else if (source == prekini){
			Predvajaj.prekini();
		}
	}
	
}
		   

		
	
