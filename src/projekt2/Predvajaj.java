package projekt2;

import java.io.IOException;

//import javax.sound.*;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

		public class Predvajaj {
		static int instrument = 19;
		static int note = 60;
		static int force = 45;
		static Synthesizer synth;
		static boolean prekinitev;
		static boolean pavza;
		static boolean play;
		static Thread vlakno;
		
		public static void zaigraj(String name, int n) throws InterruptedException{
		
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
	
		
			
		 
		 int[][] sez;
		 try {
			 sez = NaloziSliko.pretvori(name, n);
		
			 Synthesizer synth = null;
			 try {
				 synth = MidiSystem.getSynthesizer();
				 synth.open();
			 } catch (Exception e) {
	         System.out.println(e);
			 }
			 Soundbank soundbank = synth.getDefaultSoundbank();
			 Instrument[] instr = soundbank.getInstruments();
			 synth.loadInstrument(instr[instrument]);    //Changing this int (instrument) does nothing
			 MidiChannel[] mc = synth.getChannels();
	     
	     
			 MidiChannel[] mc2 = synth.getChannels();
			 MidiChannel[] mc3 = synth.getChannels();
	//      MidiChannel[] mc4 = synth.getChannels();
	//      MidiChannel[] mc5 = synth.getChannels();
	     
			 mc[4].programChange(0,34);
			 mc2[5].programChange(0, 1);
			 mc3[6].programChange(0,19);
	     
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
				 
				if (prekinitev){
					 break;
				 } else if (pavza){
					 mc[4].noteOff(j2 , force + 50);
					 mc2[5].noteOff(k2, force + 50);
					 mc3[6].noteOff(l2, force );
					 pavza = false;
					 while (!play){
						 Thread.sleep(200);
					 }
					 play = false;
					 
					 
				 }
				 
				 
				 
		   	 j1 =   (i[0]*100)/255 + 10;
		   	 k1 = (i[1]*100)/255 + 10;
		   	 l1 = (i[2]*100)/255 + 10;
		   	 
		   	 if (j1 == j2){
		   		 a1 = true;
		   		 
		   	 } else{
		   		 a1 = false;
		   		 mc[4].noteOff(j2 , force + 50);
		   		 
		   	 }
		   	 
		   	 if (k1 == k2){
		   		 a2 = true;
		   		 
		   	 } else {
		   		 a2 = false;
		   		 mc2[5].noteOff(k2, force + 50);
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
		   	 }
		   	 if (!a2){
		   		 mc2[5].noteOn(k1, force + 90);
		   	 }
		   	 if (!a3){
		   		 mc3[6].noteOn(l1, force + 45);
		   	 }
		   	 
		   	 Thread.sleep(200);
	   
			 }
	     
	     
	     synth.close();
	
		} catch (IOException e1) {
			e1.printStackTrace();
		}
}
public static void prekini() {
	prekinitev = true;
}

public static void pavza() {
	
	pavza = true;
	
}

public static void play() {
	play = true;
	
}


}