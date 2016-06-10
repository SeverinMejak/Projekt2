package projekt2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
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
	
	static Slika zaslon;
	
	JLabel label;
	
	Predvajaj predvajalnik;
	
	// Gumbi
	private JButton openItem;
	private JButton predvajaj;
	private JButton predvajaj1;
	private JButton prekini;
	private JButton pavza;
	private JButton play;
	private static JButton barve;
	private static JButton xy;
	private JButton predvajajSam;
	
	//Podatki o datoteki
	public  File datoteka;
	public String naslov;
	
	// Pull down menu
	JMenuBar menuBar;
	private JMenuItem openAction;
	private JMenuItem exitItem;
	private JMenuItem hitrost;
	private JMenuItem instr;
	private JMenuItem pomoc;

	
	private JRadioButtonMenuItem sam;
	private JRadioButtonMenuItem predvajajVrstice;
	private JRadioButtonMenuItem predvajajStolpce;
	
	//Koordinate miške
	static int x;
	static int y;
	
	//Velikost gumbov
	int VG = 25;

	private JMenuItem licenca;

	
	//Zaèetna višina in širina
	static int zacetnaVisina;
	static int zacetnaSirina;

	//Ali je sam vkljuèen
	static boolean aliJeSam;
	
	//Ali je igranje vrstic vkljuèeno
	public static boolean aliVrste;
	
	//Ali je igranje stolpcev vkljuèeno
	public static boolean aliStolpci;
	
	//Konstruktor
	public Platno() {
		super();
		zacetnaVisina = 450;
		zacetnaSirina = 400;
		this.setPreferredSize(new Dimension(zacetnaVisina, zacetnaSirina));
		this.setBackground(Color.white);
		setTitle("Muzika");
		
		setLayout(new GridLayout(1, 6));
		
		//Ustvarimo objekt slika
	    zaslon = new Slika();
	    
	    //Ustvarimo objekt predvajaj
	    predvajalnik = new Predvajaj();
	    
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
	    
	    //Napis z barvami
	    barve = new JButton();
	    zaslon.add(barve);
	    barve.setVisible(false);
	    
	    //Napis s koordinatami
	    xy = new JButton();
	    zaslon.add(xy);
	    xy.setVisible(false);
	    
	    //Ustvari menu
	    menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu fileMenu = new JMenu("Datoteka");
        JMenu editMenu = new JMenu("Nastavitve");
        
        JMenu pomagaj = new JMenu("Pomoc");
        
        /*pomoc1 = new JButton("Pomoc1");
        pomoc1.addActionListener(this);
        menuBar.add(pomoc1);*/
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(pomagaj);
        
        pomoc = new JMenuItem("Navodila");
        pomoc.addActionListener(this);
        pomagaj.add(pomoc);
        
        licenca = new JMenuItem("Licenca");
        licenca.addActionListener(this);
        pomagaj.add(licenca);
        
        openAction = new JMenuItem("Odpri");
        openAction.addActionListener(this);
        fileMenu.add(openAction);
        
        exitItem = new JMenuItem("Izhod");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        
        hitrost = new JMenuItem("Hitrost");
        hitrost.addActionListener(this);
        editMenu.add(hitrost);
        
        instr = new JMenuItem("Instrumenti");
        instr.addActionListener(this);
        editMenu.add(instr);
        
        sam = new JRadioButtonMenuItem("Igraj sam");
        sam.addActionListener(this);
        sam.setVisible(false);
        editMenu.add(sam);
        
        predvajajVrstice = new JRadioButtonMenuItem("Predvajaj po vrsticah");
        predvajajVrstice.addActionListener(this);
        predvajajVrstice.setVisible(false);
        editMenu.add(predvajajVrstice);
        
        predvajajStolpce = new JRadioButtonMenuItem("Predvajaj zvezno po stolpcih");
        predvajajStolpce.addActionListener(this);
        predvajajStolpce.setVisible(false);
        editMenu.add(predvajajStolpce);
        
        //Dodaj sliko
	    add(zaslon); 
	    
	    spremeniIkono("/disk.png");
	    
	    //Nastavi parametre za igranje
	    aliJeSam = false;
	    aliStolpci= false;
	    aliVrste = false;
	    
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
		//Ustvari nov JFrame
		JFrame frame = new JFrame();
		
		//Inicializiraj pojavno okno, preko katerega uporabnik izbere prvo glasbilo
		Object[] possibilities = {"klavir", "èelo", "kitara", "bass", "violina", "orgle", "saksofon"};
		String s = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Izberi glasbilo, ki predstavlja rdeèo barvo:",
		                    "Izberi glasbilo",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "klavir");
		
		String beseda2;
		if (s == "klavir"){
			beseda2 = "èelo";
		} else {
			beseda2 = "klavir";
		}
		
		//Inicializiraj pojavno okno, preko katerega uporabnik izbere drugo glasbilo
		String s1 = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Izberi glasbilo, ki predstavlja zeleno barvo:",
		                    "Izberi glasbilo",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    beseda2);
		
		String beseda3;
		if (s == "klavir" || s1 == "klavir"){
			if (s == "èelo" || s1 == "èelo"){
				beseda3 = "bass";
			} else {
				beseda3 = "èelo";
			}
		} else {
			beseda3 = "klavir";
		}
		
		//Inicializiraj pojavno okno, preko katerega uporabnik izbere tretje glasbilo
		String s2 = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Izberi glasbilo, ki predstavlja modro barvo:",
		                    "Izberi glasbilo",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    beseda3);
		
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
					String beseda =   JOptionPane.showInputDialog(null, "Doloèi èas med dvema zvokoma v milisekundah:", "Hitrost", JOptionPane.QUESTION_MESSAGE);
					if (beseda == null){
						break;
					}
				    int hitr = Integer.parseInt(beseda);
				    if (hitr > 0){
				    	inputAccepted = true;
					    Predvajaj.spremeniHitrost(hitr);
				    }
			  } catch(Exception e) {   
			  }
		}
	}
	
	//Implementacija actionlistenerja
	@Override
	public void actionPerformed(ActionEvent ex) {
		Object source = ex.getSource();
		
		//Èe smo izbrali opcijo odpiranja datoteke
		if (source == openItem || source == openAction) {
		      JFileChooser chooser = new JFileChooser();
		      chooser.setCurrentDirectory(new File("."));
		      int r = chooser.showOpenDialog(this);
		      if (r == JFileChooser.APPROVE_OPTION) {
		    	  datoteka = chooser.getSelectedFile();
		    	  try {
		    		  naslov = datoteka.getAbsolutePath();
		    		  zaslon.setSlika(naslov);
		    		  setTitle(naslov);
		    		  zacetnaVisina = ImageIO.read(datoteka).getHeight();
		    		  zacetnaSirina = ImageIO.read(datoteka).getWidth();
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
		
		else if (source == exitItem){
			System.exit(0);
		}
		
		//Èe smo izbrali opcijo predvajanja po vrsticah
		else if (source == predvajaj || source == predvajajVrstice){
			aliVrste = !aliVrste;
			if (aliVrste){
				spremeniIkono("/play.png");
				
				try {
					Predvajaj.nastaviSliko(datoteka, 0);
					Predvajaj.zaigraj(0, zacetnaSirina);
				} catch (InterruptedException e) {
				} catch (IOException e) {

					e.printStackTrace();
				}
				predvajaj.setVisible(false);
				predvajaj1.setVisible(false);
				play.setVisible(true);
				pavza.setVisible(true);
				prekini.setVisible(true);
				sam.setEnabled(false);
				xy.setVisible(true);
				barve.setVisible(true);
				predvajajSam.setVisible(false);
				predvajajStolpce.setEnabled(false);
				
				aliVrste = true;
				aliStolpci = false;
				aliJeSam = false;
			} else {
				prekini();
			}

			
		//Èe smo izbrali opcijo predvajanja po stolpcih
		} else if (source == predvajaj1 || source == predvajajStolpce){
			aliStolpci = !aliStolpci;
			if (aliStolpci){
				spremeniIkono("/play.png");
				
				try {
					Predvajaj.nastaviSliko(datoteka, 1);
					Predvajaj.zaigraj(1, zacetnaSirina);
				} catch (InterruptedException e) {
				} catch (IOException e) {
	
					e.printStackTrace();
				}
				predvajaj.setVisible(false);
				predvajaj1.setVisible(false);
				play.setVisible(true);
				pavza.setVisible(true);
				prekini.setVisible(true);
				sam.setEnabled(false);
				xy.setVisible(true);
				barve.setVisible(true);
				predvajajSam.setVisible(false);
				predvajajVrstice.setEnabled(false);
				
				aliStolpci = true;
				aliVrste = false;
				aliJeSam = false;
			} else {
				prekini();
			}
		}
		
		//Èe smo kliknili prekini
		else if (source == prekini){
			prekini();
		} 
		
		// Èe smo prekinili igranje
		else if (source == pavza){
			spremeniIkono("/pause.png");
			Predvajaj.pavza();
			sam.setVisible(true);
			nastaviXY(0, 0);
			nastaviBarve(0, 0, 0);
		} 
		
		// Èe smo izbrali možnost nadaljevanja igranja
		else if (source == play){
			spremeniIkono("/play.png");
			try {
				Predvajaj.play();
				nastaviXY(0, 0);
				nastaviBarve(0, 0, 0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			sam.setVisible(false);
		} 
		
		//Èe smo izbrali naèin, da igra uporabnik
		else if (source == sam || source == predvajajSam){
			
			Predvajaj.samcat();
			aliJeSam = !aliJeSam;
			try {
				Predvajaj.nastaviSliko(datoteka, 2);
				Predvajaj.zaigraj(2, zacetnaSirina);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
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
			        predvajajVrstice.setEnabled(false);
					predvajajStolpce.setEnabled(false);
			      } catch (IOException ex1) {
			      }
			} else {
				try {
					
					spremeniIkono("/stop.png");
				    Image img = ImageIO.read(getClass().getResource("/play.png"));
				    Image newimg = img.getScaledInstance( VG, VG,  java.awt.Image.SCALE_SMOOTH ) ;
				    predvajajSam.setIcon(new ImageIcon(newimg));
				    predvajajVrstice.setEnabled(true);
					predvajajStolpce.setEnabled(true);
					repaint();
				     } catch (IOException ex2) {
				     }
			}
		} 
		
		//Èe smo izbrali možnost nastavljanja hitrosti
		else if (source == hitrost) {
			podajhitrost();
		} 
		
		//Èe smo izbrali možnost nastavljanja instrumentov
		else if (source == instr){
			podajInstr();
		}
		
		else if (source == pomoc){
			JOptionPane.showMessageDialog(null,"Aplikacija za pretvarjanje slik v glasbo\n" + 
					"\n" + 
					"Uporabnik bodisi z gumbom bodisi z menujem sproži izbiro datoteke slike.  Ob izboru slike se ta na platnu pojavi,\n" + 
					"kjer je pred tem  napis, da slike ni. Ob tem se gumb za odpiranje nadomesti z gumbi z možnostmi za predvajanje.\n" + 
					"Èe je izbran kateri od gumbov, se slika najprej pretvori v seznam s podatki o RGB komponentah posameznih pixlov. \n" + 
					"Posamezni pixel se predvaja tako, da se intenziteta rdeèe, zelene in modre barve pretvori v visino tona glasbila,\n" + 
					"ki predstavlja posamezno barvo. Glasbila, ki predstavljajo barve, se lahko izbere v menuju \"Nastavitve\".\n" + 
					"\n" + 
					"Gumba s èrtami: sprožita predvajanje tako, da se zaporedoma predvajajo pixli. Hitrost predvajanja je na zaèetku \n" + 
					"nastavljena na 2000 mili sekund, uporabnik pa lahko hitrost ponastavi v menuju \"Nastavitve\". Predvajanje se \n" + 
					"zaèasno ustavi s klikom na gumb \"pause\", nadaljuje s \"play\" in prekine s \"stop\". \n" + 
					"\n" + 
					"Gumb z navpiènimi èrtami (ali možnost \"Predvajaj zvezno po stolpcih\" v menuju \"Nastavitve\") predvaja pixle od \n" + 
					"levega zgornjega kota do levega spodnjega kota, nato se pomakne za en pixel v desno in nadaljuje predvajanje \n" + 
					"navzgor. Tako predvaja do konca slike.\n" + 
					"\n" + 
					"Gumb z vodoravnimi èrtami (ali možnost \"Predvajaj po vrsticah\" v menuju \"Nastavitve\") predvaja pixle vsakiè od \n" + 
					"leve do desne. Prav tako zaène v levem zgornjem kotu.\n" + 
					"\n" + 
					"Gumb z oznako \"play\" (ali možnost \"Igraj sam\" v menuju \"Nastavitve\"): preklopi na igranje uporabnika. Uporabnik \n" + 
					"z miško klika po platnu, aplikacija pa predvaja pixel, ki ga je uporabnik izbral. Igranje uporabnika se prekine \n" + 
					"sam klikom na gumb \"stop\" ali pa v menuju \"Nastavitve\".\n" + 
					"\n" + 
					"Za vsak pixel se na zaslonu prikažeta krog, ki oznaèuje mesto pixla ter škatli s podatki o koordinatah pixla in\n" + 
					"intenzitetah barv pixla.\n" + 
					"\n" + 
					"V menuju \"Datoteka\" je možnost \"Izhod\" za terminiranje aplikacije.", "Navodila" , JOptionPane.PLAIN_MESSAGE);
		}
		
		else if (source == licenca){
			JOptionPane.showMessageDialog(null,"The MIT License (MIT)\n" + 
					"\n" + 
					"Copyright (c) 2016 Severin Mejak and Julija Tominc\n" + 
					"\n" + 
					"Permission is hereby granted, free of charge, to any person obtaining a copy\n" + 
					"of this software and associated documentation files (the \"Software\"), to deal\n" + 
					"in the Software without restriction, including without limitation the rights\n" + 
					"to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" + 
					"copies of the Software, and to permit persons to whom the Software is\n" + 
					"furnished to do so, subject to the following conditions:\n" + 
					"\n" + 
					"The above copyright notice and this permission notice shall be included in all\n" + 
					"copies or substantial portions of the Software.\n" + 
					"\n" + 
					"THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" + 
					"IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" + 
					"FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" + 
					"AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" + 
					"LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" + 
					"OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" + 
					"SOFTWARE.", "Licenca" , JOptionPane.PLAIN_MESSAGE);
		}
		
	}

	//Èe kliknemo miško
	@Override
	public void mouseClicked(MouseEvent e) {
		//Èe je vklopljen naèin igranja uporabnika nadajujemo, drugaèe ne
		if (aliJeSam){
			int h = zaslon.getHeight();
			int w = zaslon.getWidth();
			
			x = ((e.getX() - this.getInsets().left)* zacetnaSirina)/w;
			y = ((e.getY() - this.getInsets().top - this.menuBar.getHeight()) * zacetnaVisina)/h;
			
			try {
				Predvajaj.poklikaj(x, y);
				repaint();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	//Funkcija, ki na novo prebarva zaslon
	public void pobarvaj() {
		repaint();
	}

	//Funkcija, ki prekine vse dogajanje
	public void prekini(){
		spremeniIkono("/stop.png");
		Predvajaj.prekini();
		sam.setEnabled(true);
		predvajaj.setVisible(true);
		predvajaj1.setVisible(true);
		play.setVisible(false);
		pavza.setVisible(false);
		prekini.setVisible(false);
		xy.setVisible(false);
		barve.setVisible(false);
		predvajajSam.setVisible(true);
		predvajajStolpce.setEnabled(true);
		predvajajVrstice.setEnabled(true);
		predvajajStolpce.setSelected(false);
		predvajajVrstice.setSelected(false);
		aliVrste = false;
		aliStolpci = false;
		aliJeSam = false;
	}

}
