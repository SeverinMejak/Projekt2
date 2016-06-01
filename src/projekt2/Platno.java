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

//Grafièni vmesnik programa
@SuppressWarnings("serial")
public class Platno extends JFrame implements ActionListener, MouseListener {
	
	Slika zaslon;
	
	JLabel label;
	
	// Gumbi
	private JButton openItem;
	private JButton exitItem;
	private JButton predvajaj;
	private JButton predvajaj1;
	public  String datoteka;
	private JButton prekini;
	private JButton pavza;
	private JButton play;
	private static JButton barve;
	private static JButton xy;
	private JButton predvajajSam;
	
	// Pull down menu
	static JMenuBar menuBar;
	private JMenuItem openAction;
	private JMenuItem hitrost;
	private JMenuItem instr;
	
	private JRadioButtonMenuItem sam;
	private JRadioButtonMenuItem predvajajVrstice;
	private JRadioButtonMenuItem predvajajStolpce;
	
	//Koordinate miške
	static int x;
	static int y;
	
	//Velikost gumbov
	int VG = 25;
	
	//Zaèetna višina in širina
	static int zacetnaVisina;
	static int zacetnaSirina;
	
	//Ali je sam vkljuèen
	private boolean aliJeSam;
	
	//Konstruktor
	public Platno() {
		super();
		zacetnaVisina = 450;
		zacetnaSirina = 400;
		this.setPreferredSize(new Dimension(zacetnaVisina, zacetnaSirina));
		this.setBackground(Color.white);
		setTitle("Muzika");
		
		setLayout(new GridLayout(1, 6));
		
	    zaslon = new Slika();
	    
	    //Dodajanje gumbov 
	    //Gumb "Odpri"
	    openItem = new JButton();
	    openItem.addActionListener(this);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/Open-icon.png"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        openItem.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    zaslon.add(openItem);
	    
	    //Gumb "Predvajaj po vrsticah"
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
	    
	    //Gumb "Predvajaj po stolpcih"
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
	    
	    //Gumb "Igraj sam"
	    predvajajSam = new JButton();
	    predvajajSam.addActionListener(this);
	    try {
	        Image img = ImageIO.read(getClass().getResource("/play.png"));
	        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
	        predvajajSam.setIcon(new ImageIcon(newimg));
	      } catch (IOException ex) {
	      }
	    zaslon.add(predvajajSam);
	    predvajajSam.setVisible(false);
	    
	    //Gumb "Play"
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
	    
	    //Gumb "Stop"
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
	    
	    //Gumb "Pavza"
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
	    
	    //Napisa
	    barve = new JButton();
	    zaslon.add(barve);
	    barve.setVisible(false);
	    
	    xy = new JButton();
	    zaslon.add(xy);
	    xy.setVisible(false);
	    
	    
	    
	    
	    //Ustvari menu
	    menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu fileMenu = new JMenu("Datoteka");
        JMenu editMenu = new JMenu("Nastavitve");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        openAction = new JMenuItem("Odpri");
        openAction.addActionListener(this);
        JMenuItem exitAction = new JMenuItem("Izhod");
        hitrost = new JMenuItem("Hitrost");
        hitrost.addActionListener(this);
        instr = new JMenuItem("Instrumenti");
        instr.addActionListener(this);
	    
        fileMenu.add(openAction);
        fileMenu.add(exitAction);
        editMenu.add(hitrost);
        editMenu.add(instr);
        
        sam = new JRadioButtonMenuItem(
                "Igraj sam");
        sam.addActionListener(this);
        sam.setVisible(false);
        editMenu.add(sam);
        
        predvajajVrstice = new JRadioButtonMenuItem(
                "Predvajaj po vrsticah");
        predvajajVrstice.addActionListener(this);
        predvajajVrstice.setVisible(false);
        editMenu.add(predvajajVrstice);
        
        predvajajStolpce = new JRadioButtonMenuItem(
                "Predvajaj zvezno po stolpcih");
        predvajajStolpce.addActionListener(this);
        predvajajStolpce.setVisible(false);
        editMenu.add(predvajajStolpce);
        
        //Dodaj sliko
	    add(zaslon); 
	    
	    spremeniIkono("/disk.png");
	    aliJeSam = false;
	    
	    this.addMouseListener(this);
	    pack();
	}
	
	//Nastavi gumb barve
	public static void nastaviBarve (int a, int b, int c){
		barve.setText("Rdeca:" + a + ", " + "Zelena:" +  b + ", " + "Modra:" + c);
	}
	
	//Nastavi gumb XY
	public static void nastaviXY (int a, int b){
		xy.setText("X:" + a + ", " + "Y:" +  b);
	}
	
	//Spremeni ikono okvirja
	public void spremeniIkono(String datoteka){
		try {
	    	Image slikica = ImageIO.read(getClass().getResource(datoteka));
	        setIconImage(slikica.getScaledInstance( 60, 60,  java.awt.Image.SCALE_SMOOTH ));
	    }
	    catch (IOException exc) {
	        exc.printStackTrace();
	    }
	}
	
	//Spreminjanje instrumenta
	public void podajInstr(){
		
		JFrame frame = new JFrame();
	
		Object[] possibilities = {"klavir", "èelo", "kitara", "bass", "violina", "orgle", "saksofon"};
		String s = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Izberi glasbilo, ki predstavlja rdeèo barvo:",
		                    "Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "klavir");
		
		String s1 = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Izberi glasbilo, ki predstavlja zeleno barvo:",
		                    "Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "klavir");
		
		String s2 = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Izberi glasbilo, ki predstavlja modro barvo:",
		                    "Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "klavir");
		
		String[] sez = {s, s1, s2};
		
		int k = 0;
		for(String i : sez){
			if (!(i== null)){
				if (i.equals("klavir")){
					Predvajaj.zamenjaj(1, k);
				} else if(i.equals("bass")){
					Predvajaj.zamenjaj(34, k);
				} else if (i.equals("violina")){
					Predvajaj.zamenjaj(41, k);
				} else if (i.equals("èelo")){
					Predvajaj.zamenjaj(43, k);
				} else if (i.equals("orgle")){
					Predvajaj.zamenjaj(19, k);
				} else if (i.equals("saksofon")){
					Predvajaj.zamenjaj(67, k);
				} else if (i.equals("kitara")){
					Predvajaj.zamenjaj(27, k);
				}
			}
			k +=1;
		}
	}
	
	//Spreminjanje hitrosti
	public void podajhitrost(){
		boolean inputAccepted = false;
		while(!inputAccepted) {
			
			  try {
					String beseda =   JOptionPane.showInputDialog("Doloèi èas med dvema zvokoma v milisekundah:");
					if (beseda.equalsIgnoreCase("")){
						inputAccepted = true;
					}
				    int hitr = Integer.parseInt(beseda);
				 
				    if (hitr <= 0){
		
				    } else{
				    	inputAccepted = true;
					      Predvajaj.spremeniHitrost(hitr);
				    }
			  } catch(NumberFormatException e) {
				  
			  }
		}
	}
	
	//Implementacija actionlistenerja
	@Override
	public void actionPerformed(ActionEvent ex) {
		Object source = ex.getSource();
		if (source == openItem || source == openAction) {
			
				
		      JFileChooser chooser = new JFileChooser();
		      chooser.setCurrentDirectory(new File("."));
		      int r = chooser.showOpenDialog(this);
		      if (r == JFileChooser.APPROVE_OPTION) {
		    	  	datoteka = chooser.getSelectedFile().getAbsolutePath();
		        
					try {
						zaslon.setSlika(datoteka);
						setTitle(datoteka);
						zacetnaVisina = ImageIO.read(new File(datoteka)).getHeight();
						zacetnaSirina = ImageIO.read(new File(datoteka)).getWidth();
						setSize(zacetnaSirina, zacetnaVisina+(menuBar.getHeight()));
						spremeniIkono("/Open-icon.png");
						Predvajaj.nastavljenaSlika = 0;
					} catch (IOException e1) {
					}
					
					openItem.setVisible(false);
					predvajaj.setVisible(true);
					predvajaj1.setVisible(true);
					sam.setVisible(true);
					predvajajSam.setVisible(true);
					predvajajVrstice.setVisible(true);
					predvajajStolpce.setVisible(true);
				
			    } else if (source == exitItem){
			      System.exit(0);
			    }	
		      
		}
		else if (source == predvajaj || source == predvajajVrstice){
			spremeniIkono("/play.png");
			
			try {
				Predvajaj.nastaviSliko(datoteka, 0);
				Predvajaj.zaigraj(0, zacetnaSirina, this);
			} catch (InterruptedException e) {
			} catch (IOException e) {

				e.printStackTrace();
			}
			predvajaj.setVisible(false);
			predvajaj1.setVisible(false);
			play.setVisible(true);
			pavza.setVisible(true);
			prekini.setVisible(true);
			sam.setVisible(false);
			xy.setVisible(true);
			barve.setVisible(true);
			predvajajSam.setVisible(false);
			predvajajStolpce.setVisible(false);
			
			
		} else if (source == predvajaj1 || source == predvajajStolpce){
			spremeniIkono("/play.png");
			
			try {
				Predvajaj.nastaviSliko(datoteka, 1);
				Predvajaj.zaigraj(1, zacetnaSirina, this);
			} catch (InterruptedException e) {
			} catch (IOException e) {

				e.printStackTrace();
			}
			predvajaj.setVisible(false);
			predvajaj1.setVisible(false);
			play.setVisible(true);
			pavza.setVisible(true);
			prekini.setVisible(true);
			sam.setVisible(false);
			xy.setVisible(true);
			barve.setVisible(true);
			predvajajSam.setVisible(false);
			predvajajVrstice.setVisible(false);
			
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
			predvajajSam.setVisible(true);
			predvajajStolpce.setVisible(true);
			predvajajVrstice.setVisible(true);
			predvajajStolpce.setSelected(false);
			predvajajVrstice.setSelected(false);
			
		} else if (source == pavza){
			spremeniIkono("/pause.png");
			Predvajaj.pavza();
			sam.setVisible(true);
			nastaviXY(0, 0);
			nastaviBarve(0, 0, 0);
		} else if (source == play){
			spremeniIkono("/play.png");
			try {
				Predvajaj.play();
				nastaviXY(0, 0);
				nastaviBarve(0, 0, 0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			sam.setVisible(false);
			
		} else if (source == sam || source == predvajajSam){
			
			Predvajaj.samcat();
			aliJeSam = !aliJeSam;
			try {
				Predvajaj.nastaviSliko(datoteka, 2);
				Predvajaj.zaigraj(2, zacetnaSirina, this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			predvajaj.setVisible(!aliJeSam);
			predvajaj1.setVisible(!aliJeSam);
			sam.setSelected(aliJeSam);
			barve.setVisible(aliJeSam);
			xy.setVisible(aliJeSam);
			play.setVisible(false);
			pavza.setVisible(false);
			prekini.setVisible(false);
			nastaviXY(0, 0);
			nastaviBarve(0, 0, 0);
			if (aliJeSam){
				try {
					spremeniIkono("/play.png");
			        Image img = ImageIO.read(getClass().getResource("/stop.png"));
			        Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
			        predvajajSam.setIcon(new ImageIcon(newimg));
			        predvajajVrstice.setVisible(false);
					predvajajStolpce.setVisible(false);
			      } catch (IOException ex1) {
			      }
			} else {
				try {
					
					spremeniIkono("/stop.png");
				    Image img = ImageIO.read(getClass().getResource("/play.png"));
				    Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
				    predvajajSam.setIcon(new ImageIcon(newimg));
				    predvajajVrstice.setVisible(true);
					predvajajStolpce.setVisible(true);
				     } catch (IOException ex2) {
				     }
			}
			
		} else if (source == hitrost) {
			podajhitrost();
		} else if (source == instr){
			podajInstr();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		if (aliJeSam){
			x = e.getX();
			y = e.getY();
			
			
			Rectangle r = this.getBounds();
			int h = r.height;
			int w = r.width;
			
			try {
				Predvajaj.poklikaj((x * zacetnaSirina)/w,(y * zacetnaVisina)/h);
				x = (x * zacetnaSirina)/w;
				y = (y * zacetnaVisina)/h;
				repaint();
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

	public void pobarvaj() {
		repaint();
		
	}

	

}
