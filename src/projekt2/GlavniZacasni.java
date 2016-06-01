package projekt2;

import java.io.IOException;
import javax.swing.JFrame;


@SuppressWarnings("serial")

//Glavni program
public class GlavniZacasni extends JFrame {
	public static void main(String[] args) throws IOException, InterruptedException {
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame frame = new Platno();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	    frame.setVisible(true);
	}
}
		   

		
	
