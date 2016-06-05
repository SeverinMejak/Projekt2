package projekt2;

import java.io.IOException;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

public class Predvajaj {
		static int instrument;
		static int force;
		Synthesizer synth;
		static boolean prekinitev;
		static boolean pavza;
		static boolean play;
		static Thread vlakno;
		protected static boolean sam;
		protected static int a;
		protected static int b;
		protected static int c;
		static int[][] sez;
		private static int sirina;

		
		private static int velocity;
		static int rdeca;
		static int zelena;
		static int modra;
		static int stevec;
		static int nastavljenaSlika;  
		static int nacin;
	
		
		
		
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
  
public static  void nastaviSliko(String name, int n) throws InterruptedException, IOException {
	if (nastavljenaSlika == 0 || !(n%2 + 1 == nastavljenaSlika)){
		sez = NaloziSliko.pretvori(name, n);
		if (!(n==1)){
			nastavljenaSlika = 1;
		} else {
			nastavljenaSlika = 2;
		}
	}
		
}
	
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
				 
				if (prekinitev){
					stevec = 0;
					 break;
				 } else if (pavza){
					 break;

					 
					 
					 
				 }else {
				 
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
			 a = -1;
			 b = -1;
			 c = -1;
			 while (sam){
				 Thread.sleep(10);
				 if (a== -1 || b == -1 || c==-1){
					 
				 } else {
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
public static  void prekini() {
	prekinitev = true;
	play = !pavza;
	sam = false;
	pavza = false;
}

public static void pavza() {
	pavza = true;
	play = false;
	sam = false;
	prekinitev = false;
}

public static  void play() throws InterruptedException, IOException {
	
	play = true;
	prekinitev = false;
	pavza = false;
	sam = false;
	zaigraj(nacin, sirina);
	
}

public static void samcat(){
	sam = !sam;
	prekinitev = !prekinitev;
	pavza = false;

}

public static void poklikaj(int x, int y) throws InterruptedException{
	
	if (!(nastavljenaSlika == 0)){
		int p = sirina*y + x;
		Platno.nastaviXY(x,y);
		int[] seznam = sez[p];
		a =   (seznam[0]*80)/255 + 20;
	  	b = (seznam[1]*80)/255 + 20;
	  	c = (seznam[2]*80)/255 + 20;
	  	Platno.nastaviBarve(a, b, c);
	}
}

public static void spremeniHitrost(int hitr) {
	velocity = hitr;
	
}

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