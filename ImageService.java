import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class ImageService{
	
	public static byte[] imagetobytes(String path){
		byte[] b = null;
		try{
			File f = new File(path);
			InputStream input = new FileInputStream(f);
			int size = (int)f.length();
			b = new byte[size];
			input.read(b);
			return b;
		}catch(Exception e){
			e.printStackTrace();
			return b;
		}	
	}

	public static Image bytestoImage(byte[] b){
		try{
			InputStream input = new ByteArrayInputStream(b);
			Image img = ImageIO.read(input);
			return img;
		}catch(Exception e){
			return null;
		}
	}

	public static BufferedImage imagefromPath(String path){
		try{
			FileInputStream input = new FileInputStream(path);
			BufferedImage img = ImageIO.read(input);
			return img;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		} 
	}

	/*TESTE DA CLASSE
	public static void main(String s[]){
		byte[] b = ImageService.imagetobytes("tomate.jpeg");

		JLabel label = new JLabel(ImageService.bytestoimage(b));
        JOptionPane.showMessageDialog(null, label);
	}*/

}