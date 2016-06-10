package projekt2;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

//Razred, ki predvaja glasbo
public class Predvajaj {
	//Nastavitve in�tumentov
	//In�trument
	public static int instrument;
	//Glasnost
	public static int force;
	public Synthesizer synth;
	//Hitrost igranja
	private static int velocity;
	
	//Nastavitve igranja
	public static boolean prekinitev;
	public static boolean pavza;
	public static boolean play;
	public static Thread vlakno;
	protected static boolean sam;
	
	/*Parametri za na�in igranja uporabnika. �e so enaki -1, pomeni, da uporabnik ni naredil novega klika.
	�e so med pozitivni, nam dolo�ajo parametre klika.*/
	protected static int a;
	protected static int b;
	protected static int c;
	
	//Seznam z podatki o barvah slike
	public static int[][] sez;
	private static int sirina;

	
	public static int rdeca;
	public static int zelena;
	public static int modra;
	public static int stevec;
	public static int nastavljenaSlika;  
	public static int nacin;

	public Predvajaj() {
		super();
		instrument = 19;
		force = 45;
		prekinitev = false;
		pavza = false;
		play = false;
		sam = false;
		velocity = 2000;
		rdeca = 34;
		zelena = 1;
		modra = 43;
		stevec = 0;
		nastavljenaSlika = 0; 
	}
	
	
	//Metoda, ki ustvari vlakno in poklice funkcijo spilaj s parametri, ki dolo�ajo na�ine predvajanja
	public static  void zaigraj(int n, int w) throws InterruptedException{
		nacin = n;
		sirina = w;

		if (vlakno != null) {
			prekinitev = true;
			vlakno.join();
		}
		vlakno = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					prekinitev = false;

					spilaj(n);
					vlakno = null;


				}catch (Exception e) {
				}				
			}
		});
		vlakno.start();
	}

	
	//Metoda, ki nastavi seznam s podatki o sliki
	public static  void nastaviSliko(File name, int n) throws InterruptedException, IOException {
		/*Sliko nastavimo glede na na�in predvajanja. �e predvajamo po vrsticah ali z igranjem
		uporabnika, seznama ni potrebno spreminjati. �e pa je izbran na�in igranja po stolpcih, se mora slika ponovno nalo�iti.*/ 
		if (nastavljenaSlika == 0 || !(n%2 + 1 == nastavljenaSlika)){
			sez = NaloziSliko.pretvori(name, n);
			if (!(n==1)){
				nastavljenaSlika = 1;
			} else {
				nastavljenaSlika = 2;
			}
		}
	}

	
	//Metoda, ki inicilizira sintisizer in glede na na�in predvajanja predvaja sliko
	public static  void spilaj(int m) throws InterruptedException, IOException{	
		Synthesizer synth = null;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
		} catch (Exception e) {
			System.out.println(e);
		}
		Soundbank soundbank = synth.getDefaultSoundbank();
		Instrument[] instr = soundbank.getInstruments();
		synth.loadInstrument(instr[instrument]);
		MidiChannel[] mc = synth.getChannels();
		MidiChannel[] mc2 = synth.getChannels();
		MidiChannel[] mc3 = synth.getChannels();
		MidiChannel[] mc4 = synth.getChannels();

		mc[4].programChange(0,rdeca);
		mc2[5].programChange(0, zelena);
		mc3[6].programChange(0,modra);
		mc4[3].programChange(0, 113);

		if(!(m==2)){
			//�e ni vklu�en na�in igranja uporabnika, gremo z zanko po sliki
			int j1 = 0;
			int k1 = 0;
			int l1 = 0;
			int j2 = 0;
			int k2 = 0;
			int l2 = 0;
			boolean a1;
			boolean a2;
			boolean a3;

			for (int iteriraj = stevec; iteriraj< sez.length; iteriraj += 1){
				stevec += 1;
				int[] i = sez[iteriraj];
				mc[4].programChange(0,rdeca);
				mc2[5].programChange(0, zelena);
				mc3[6].programChange(0,modra);

				mc4[3].allNotesOff();

				//�e moramo prekiniti ali za�asno ustaviti predvajanje
				if (prekinitev){
					stevec = 0;
					break;
				} else if (pavza){
					break;
				}else {

					//Zaigramo ustrezne tone. �e naj bi instrument ponovno zaigral isti ton, ga zgolj podalj�a, zraven pa se zaigra triangel. 
					j1 =   (i[0]*80)/255 + 20;
					k1 = (i[1]*80)/255 + 20;
					l1 = (i[2]*80)/255 + 20;

					if (j1 == j2){
						a1 = true;
					} else{
						a1 = false;
						mc[4].noteOff(j2 , force + 90);
					}

					if (k1 == k2){
						a2 = true;

					} else {
						a2 = false;
						mc2[5].noteOff(k2, force + 90);
					}

					if (l1 == l2){
						a3 = true;

					} else {
						a3 = false;
						mc3[6].noteOff(l2, force );
					}

					j2 =  j1;
					k2 = k1;
					l2 = l1;

					if (!a1){
						mc[4].noteOn(j1, force + 90);
					} else {
						mc4[3].noteOn(j1%12 + 72, force + 80);
					}
					if (!a2){
						mc2[5].noteOn(k1, force + 90);

					} else {
						mc4[3].noteOn(k1%12 + 72, force + 80);
					}
					if (!a3){
						mc3[6].noteOn(l1, force + 60);

					} else {
						mc4[3].noteOn(l1%12 + 72, force + 80);
					}

					Thread.sleep(velocity);
					Platno.nastaviBarve(i[0],i[1], i[2]);
					Platno.x = i[3];
					Platno.y= i[4];
					Platno.zaslon.repaint();
					Platno.nastaviXY(Platno.x,Platno.y);
				}
			}

		} else {
			//Vklju�ena je mo�nost igranja uporabnika.
			a = -1;
			b = -1;
			c = -1;
			while (sam){
				//�akamo na informacije o kliku uporabnika
				Thread.sleep(10);
				if (a== -1 || b == -1 || c==-1){
					//Ni bilo klika	
				} else {
					//Informacije o kliku, posredovane prek metode "poklikaj"
					mc[4].allNotesOff();
					mc2[5].allNotesOff();
					mc3[6].allNotesOff();
					mc[4].programChange(0,rdeca);
					mc2[5].programChange(0, zelena);
					mc3[6].programChange(0,modra);
					mc[4].noteOn( a, force + 90);
					mc2[5].noteOn(b, force + 90);
					mc3[6].noteOn(c, force + 60);

					a = -1;
					b = -1;
					c = -1;
				}
			}
		}
		synth.close();
	}

	//Metoda, ki prekine igranje
	public static  void prekini() {
		prekinitev = true;
		play = !pavza;
		sam = false;
		pavza = false;
	}

	//Metoda, ki za�asno prekine igranje
	public static void pavza() {
		pavza = true;
		play = false;
		sam = false;
		prekinitev = false;
	}

	//Metoda, ki nadaljuje igranje
	public static  void play() throws InterruptedException, IOException {
		play = true;
		prekinitev = false;
		pavza = false;
		sam = false;
		zaigraj(nacin, sirina);
	}

	//Metoda, ki izklju�i ali vklju�i mo�nost igranja uporabnika
	public static void samcat(){
		sam = !sam;
		prekinitev = !prekinitev;
		pavza = false;
	}

	/*Metoda, ki metodi spilaj podaja informacijo o kliku uporanika, ter Platnu
	 *  sporo�a nastavitve  podatkov o  koordinatah in barvah,�e je vklju�ena mo�nost igranja uporabnika
	 */
	public static void poklikaj(int x, int y) throws InterruptedException{
		if (!(nastavljenaSlika == 0)){
			int p = sirina*y + x;
			//Zaradi varnosti Platno.nastaviXY pokli�emo od tukaj, da prepre�imo mo�nost, da slika �e ni pravilnonastavljena
			Platno.nastaviXY(x,y);
			int[] seznam = sez[p];
			a =   (seznam[0]*80)/255 + 20;
			b = (seznam[1]*80)/255 + 20;
			c = (seznam[2]*80)/255 + 20;
			Platno.nastaviBarve(a, b, c);
		}
	}
	
	//Metoda, ki spremeni hitrost
	public static void spremeniHitrost(int hitr) {
		velocity = hitr;
	}

	//Metoda, ki zamenja instrumente
	public static void zamenjaj(int i, int k) {
		if (k == 0){
			rdeca = i;
		} else if (k == 1){
			zelena = i;
		} else if (k== 2){
			modra = i;
		}
	}
}