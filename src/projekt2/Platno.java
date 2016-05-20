package projekt2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
public class Platno extends JFrame implements ActionListener, MouseListener {
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
	private JRadioButtonMenuItem sam;
	private boolean aliJeSam;
	private static JButton barve;
	private static JButton xy;
	private int zacetnaVisina;
	private int zacetnaSirina;
	private JMenuItem openAction;
	private JMenuItem hitrost;
	
	public Platno() {
		super();
		zacetnaVisina = 450;
		zacetnaSirina = 400;
		this.setPreferredSize(new Dimension(zacetnaVisina, zacetnaSirina));
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
	    predvajaj.setVisible(false);
	    
	    //"Predvajaj po stolpcih"
	    predvajaj1 = new JButton();
	    predvajaj1.addActionListener(this);
	    zaslon.add(predvajaj1);
	    predvajaj1.setVisible(false);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/linesV.jpg"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        predvajaj1.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    
	    
	    
	    play = new JButton();
	    play.addActionListener(this);
	    zaslon.add(play);
	    play.setVisible(false);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/play.png"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        play.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    
	    prekini = new JButton();
	    prekini.addActionListener(this);
	    zaslon.add(prekini);
	    prekini.setVisible(false);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/stop.png"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        prekini.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    
	    pavza = new JButton();
	    pavza.addActionListener(this);
	    zaslon.add(pavza);
	    pavza.setVisible(false);
	 
	    try {
	        Image img = ImageIO.read(getClass().getResource("/pause.png"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        pavza.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    
	    barve = new JButton();
	    zaslon.add(barve);
	    barve.setVisible(false);
	    
	    xy = new JButton();
	    zaslon.add(xy);
	    xy.setVisible(false);
	    
	    
	    
	    
	    //ustvari menu
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu fileMenu = new JMenu("Datoteka");
        JMenu editMenu = new JMenu("Nastavitve");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        openAction = new JMenuItem("Open");
        openAction.addActionListener(this);
        JMenuItem exitAction = new JMenuItem("Exit");
        hitrost = new JMenuItem("Hitrost");
        hitrost.addActionListener(this);
        JMenuItem instr = new JMenuItem("Instrumenti");
	    
        fileMenu.add(openAction);
        fileMenu.add(exitAction);
        editMenu.add(hitrost);
        editMenu.add(instr);
        
        //JCheckBoxMenuItem checkAction = new JCheckBoxMenuItem("Check Action");
        sam = new JRadioButtonMenuItem(
                "Igraj sam");
        sam.addActionListener(this);
        sam.setVisible(false);
        
        editMenu.add(sam);
        
        
	    add(zaslon); 
	    
	    spremeniIkono("/disk.png");
	    aliJeSam = false;
	    
	    this.addMouseListener(this);
	}
	
	public static void nastaviBarve (int a, int b, int c){
		barve.setText("Rdeca:" + a + ", " + "Zelena:" +  b + ", " + "Modra:" + c);
	}
	
	public static void nastaviXY (int a, int b){
		xy.setText("X:" + a + ", " + "Y:" +  b);
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
	
	public void podajhitrost(){
		boolean inputAccepted = false;
		while(!inputAccepted) {
			
		  try {
			String beseda =   JOptionPane.showInputDialog("Doloèi èas med dvema zvokoma v milisekundah:");
			if (beseda.equalsIgnoreCase("")){
				inputAccepted = true;
			}
		    int hitr = Integer.parseInt(beseda);
		    System.out.println(hitr);
		    
		    if (hitr <= 0){

		    } else{
		    	inputAccepted = true;
			      Predvajaj.spremeniHitrost(hitr);
		    }
		  } catch(NumberFormatException e) {
			  
		  }
		
		  
	}
	}
	@Override
	public void actionPerformed(ActionEvent ex) {
		Object source = ex.getSource();
		if (source == openItem || source == openAction) {
			
		      JFileChooser chooser = new JFileChooser();
		      chooser.setCurrentDirectory(new File("."));
		      int r = chooser.showOpenDialog(this);
		      if (r == JFileChooser.APPROVE_OPTION) {
		        datoteka = chooser.getSelectedFile().getAbsolutePath();
		        System.out.print(datoteka);
				try {
					zaslon.setSlika(datoteka);
					setTitle(datoteka);
					zacetnaVisina = ImageIO.read(new File(datoteka)).getHeight();
					zacetnaSirina = ImageIO.read(new File(datoteka)).getWidth();
					setSize(zacetnaSirina, zacetnaVisina);
					spremeniIkono("/Open-icon.png");
				} catch (IOException e1) {
				}
				openItem.setVisible(false);
				predvajaj.setVisible(true);
				predvajaj1.setVisible(true);
				sam.setVisible(true);
				
				
		    } else if (source == exitItem){
		      System.exit(0);
		  }	
		      
		}
		else if (source == predvajaj){
			spremeniIkono("/play.png");
			
			try {
				Predvajaj.zaigraj(datoteka, 0, zacetnaSirina, zacetnaVisina);
			} catch (InterruptedException e) {
			}
			predvajaj.setVisible(false);
			predvajaj1.setVisible(false);
			play.setVisible(true);
			pavza.setVisible(true);
			prekini.setVisible(true);
			sam.setVisible(false);
			xy.setVisible(true);
			barve.setVisible(true);
		} else if (source == predvajaj1){
			spremeniIkono("/play.png");
			
			try {
				Predvajaj.zaigraj(datoteka, 1, zacetnaSirina, zacetnaVisina);
			} catch (InterruptedException e) {
			}
			predvajaj.setVisible(false);
			predvajaj1.setVisible(false);
			play.setVisible(true);
			pavza.setVisible(true);
			prekini.setVisible(true);
			sam.setVisible(false);
			xy.setVisible(true);
			barve.setVisible(true);
		}
		else if (source == prekini){
			spremeniIkono("/stop.png");
			Predvajaj.prekini();
			sam.setVisible(true);
			predvajaj.setVisible(true);
			predvajaj1.setVisible(true);
			play.setVisible(false);
			pavza.setVisible(false);
			prekini.setVisible(false);
			xy.setVisible(false);
			barve.setVisible(false);
			
		} else if (source == pavza){
			spremeniIkono("/pause.png");
			Predvajaj.pavza();
			sam.setVisible(true);
		} else if (source == play){
			spremeniIkono("/play.png");
			Predvajaj.play();
			sam.setVisible(false);
		} else if (source == sam){
			spremeniIkono("/play.png");
			Predvajaj.samcat();
			aliJeSam = !aliJeSam;
			try {
				Predvajaj.zaigraj(datoteka, 2, zacetnaSirina, zacetnaVisina);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			predvajaj.setVisible(!aliJeSam);
			predvajaj1.setVisible(!aliJeSam);
		} else if (source == hitrost) {
			podajhitrost();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(aliJeSam);
		
		if (aliJeSam){
			int u = e.getX();
			int v = e.getY();

			Rectangle r = this.getBounds();
			int h = r.height;
			int w = r.width;

			try {
				Predvajaj.poklikaj((u * zacetnaSirina)/w, (v * zacetnaVisina)/h);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
