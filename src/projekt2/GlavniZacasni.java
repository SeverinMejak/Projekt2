package projekt2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GlavniZacasni extends JFrame implements ActionListener{
	private JLabel label;
	private JButton openItem;
	private JMenuItem exitItem;
	private JButton predvajaj;
	private JButton predvajaj1;
	public  String datoteka;
	private JPanel zaslon;
	private JButton prekini;
	private JButton pavza;
	private JButton play;
	
	public GlavniZacasni() {
		setTitle("Muzika");
	    setSize(300, 400);
	  
	    JPanel zaslon = new JPanel();
	    
	    openItem = new JButton("Odpri");
	    openItem.addActionListener(this);
	    zaslon.add(openItem);
	    
	    predvajaj = new JButton("Predvajaj po vrsticah");
	    predvajaj.addActionListener(this);
	    zaslon.add(predvajaj);
	    
		predvajaj1 = new JButton("Predvajaj zvezno po stolpcih");
	    predvajaj1.addActionListener(this);
	    zaslon.add(predvajaj1);
		
	    prekini = new JButton("Prekini");
	    prekini.addActionListener(this);
	    zaslon.add(prekini);
	    
	    pavza = new JButton("Premor");
	    pavza.addActionListener(this);
	    zaslon.add(pavza);
	    
	    play = new JButton("Nadaljuj");
	    play.addActionListener(this);
	    zaslon.add(play);
	    
	    add(zaslon); 
	    
	    pack();
	    
	    
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
					Slika.slika(datoteka);
					//Predvajaj.zaigraj(name);
				} catch (IOException e1) {
				}
				
				
		    } else if (source == exitItem){
		      System.exit(0);
		  }

		}
		else if (source == predvajaj){
			try {
				Predvajaj.zaigraj(datoteka, 0);
			} catch (InterruptedException e) {
			}
		} else if (source == predvajaj1){
			try {
				Predvajaj.zaigraj(datoteka, 1);
			} catch (InterruptedException e) {
			}
		}
		else if (source == prekini){
			Predvajaj.prekini();
		} else if (source == pavza){
			Predvajaj.pavza();
		} else if (source == play){
			Predvajaj.play();
		}
	}
	
}
		   

		
	
