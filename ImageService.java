import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

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

	public static ImageIcon bytestoimage(byte[] b){
		ImageIcon img = new ImageIcon(b);
		return img;
	}

	public static Image imagefromPath(String path){
		try{
			FileInputStream input = new FileInputStream(path);
			Image img = ImageIO.read(input);
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