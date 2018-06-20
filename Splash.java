import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;


public class Splash extends JWindow{
	
	
		JProgressBar barradeprogresso;
			
			public Splash()/* throws InterruptedException*/{
				
					GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
					int w = gd.getDisplayMode().getWidth();
					int h = gd.getDisplayMode().getHeight();								
					 int z =  2;
					 int x = (w- 521)/z;
					 int y = (h - 335)/z;
					
					JLabel img = new JLabel(new ImageIcon("telasplash2.jpg"));
						img.setLocation(new Point(0,0));
							img.setSize(521,315);
							
						this.setLayout(null);
						this.add(img);
						this.setLocation(new Point(x,y));
							this.setSize(521,335);
							this.setVisible(true);
							
							barradeprogresso = new JProgressBar();
							barradeprogresso.setBackground(new Color(0,102,52));
							barradeprogresso.setBounds(0,315,521,20);
							barradeprogresso.setStringPainted(true);
							this.add(barradeprogresso);
							
							
							
							new Thread() {
								public void run() {
										for(int progress = 0;progress < 101; progress++){
											try{
													barradeprogresso.setValue(progress);
													sleep(80);
													
											} catch (InterruptedException e1){
												e1.printStackTrace();
											}
										}
							}
					}.start();
					try{
						Thread.sleep(9500);
						this.setVisible(false);
					}catch(Exception e){}		
	
			}
	
	public static void main (String[]a){
	try{
		new Splash();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}

}

									
							
							
							