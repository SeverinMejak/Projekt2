package projekt2;

import java.io.IOException;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

public class Predvajaj {
		static int instrument = 19;
		static int force = 45;
		static Synthesizer synth;
		static boolean prekinitev;
		static boolean pavza;
		static boolean play;
		static Thread vlakno;
		protected static boolean sam = false;
		protected static int a;
		protected static int b;
		protected static int c;
		static int[][] sez;
		private static int sirina;
		private static int visina;
		private static int velocity = 2000;
		static int rdeca = 34;
		static int zelena = 1;
		static int modra = 43;
		
		public static void zaigraj(String name, int n, int w, int h) throws InterruptedException{
		
			sirina = w;
			visina = h;
			
		if (vlakno != null) {
			prekinitev = true;
			vlakno.join();
		}
		vlakno = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					prekinitev = false;
					igraj(name, n);
					vlakno = null;
				
				
				}catch (Exception e) {
				}				
			}
		});
		vlakno.start();
		}
  
public static void igraj(String name, int n) throws InterruptedException { 
		 try {
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
			 
			 
			 sez = NaloziSliko.pretvori(name, n);
			 
			 if(!(n==2)){
				 int j1 = 0;
				 int k1 = 0;
				 int l1 = 0;
				 int j2 = 0;
				 int k2 = 0;
				 int l2 = 0;
				 boolean a1;
				 boolean a2;
				 boolean a3;
		     
				 for (int[] i : sez){
					 mc[4].programChange(0,rdeca);
					 mc2[5].programChange(0, zelena);
					 mc3[6].programChange(0,modra);
					 
					 Platno.nastaviBarve(i[0],i[1], i[2]);
					 Platno.nastaviXY(i[3], i[4]);
					 
					 mc4[3].allNotesOff();
					 
					if (prekinitev){
						 break;
					 } else if (pavza){
						 mc[4].noteOff(j2 , force + 90);
						 mc2[5].noteOff(k2, force + 90);
						 mc3[6].noteOff(l2, force +  60);

						 while (pavza){
							 Thread.sleep(200);
						 }
						 
						 
					 }
					 
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
				 }
			 } else {
				 a = -1;
				 b = -1;
				 c = -1;
				 while (sam){
					 if (a== -1 || b == -1 || c==-1){
						 
					 } else {
						 mc[4].allNotesOff();
						 mc2[5].allNotesOff();
						 mc3[6].allNotesOff();
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
		} catch (IOException e1) {
			e1.printStackTrace();
		}
}
public static void prekini() {
	prekinitev = true;
	play = false;
	sam = false;
	pavza = false;
}

public static void pavza() {
	pavza = true;
	play = false;
	sam = false;
	prekinitev = false;
}

public static void play() {
	play = true;
	prekinitev = false;
	pavza = false;
	sam = false;
}

public static void samcat(){
	sam = !sam;
}

public static void poklikaj(int x, int y) throws InterruptedException{
	if (!(sez == null)){
	
	int p = sirina*y + x;
	int[] seznam = sez[p];
	a =   (seznam[0]*80)/255 + 20;
  	b = (seznam[1]*80)/255 + 20;
  	c = (seznam[2]*80)/255 + 20;
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