package projekt2;

import java.io.IOException;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class GlavniZacasni extends JFrame {
	public static void main(String[] args) throws IOException, InterruptedException {
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame frame = new VecjePlatno();
		frame.pack();
	    frame.setVisible(true);
	}
}
		   

		
	
