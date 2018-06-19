import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageBox extends JPanel
{
    private Image img;
    String resource;
    private boolean showDialog; 

    public ImageBox(){
    	img = null;
    	showDialog = true;
    	addMouseListener (  new MouseAdapter () {
            public void mouseClicked(MouseEvent e){
				if(showDialog)
                   showSelectImage();
	     	}
         });
    }

    public void setResource(String path){
    	resource = path;
    }

    public String getResource(){
    	return resource;
    }

    public void setShowDialog(boolean v){
       showDialog = v;
    }

    public boolean getShowDialog(){
       return showDialog;
    } 

    public void setImage(Image img){
    	if(img == null){
    		setImage("Sem_Foto.png");
    	}else{
       		this.img = img;
       		updateUI();
       	}
    }

    public void setImage(String path){
    	try{
        	java.io.FileInputStream fs = new java.io.FileInputStream(path);
        	img = javax.imageio.ImageIO.read(fs);
        	updateUI();
       	}catch(Exception e){
        	img = null;
        	System.out.println(e.getMessage());
       	}
    }

 

    public void showSelectImage(){
    	JFileChooser dlg = new JFileChooser();
        dlg.setFileFilter( new javax.swing.filechooser.FileFilter() {
               public boolean accept(java.io.File f){
                     return f.isDirectory() || 
                            f.getName().toLowerCase().endsWith(".jpg") || 
                            f.getName().toLowerCase().endsWith(".jpeg") || 
                            f.getName().toLowerCase().endsWith(".png");
	       		}
	       		public String getDescription(){
		     		return "JPEG, JPG & PNG";
	       		}
        });
		if(dlg.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			setResource(dlg.getSelectedFile().getAbsolutePath());
        	setImage(getResource());
		}
    }
    

    public void paint(Graphics g){
    	g.clearRect(0,0,getWidth(), getHeight());
    	if (img!=null){
    		int x0,y0,larg,alt;
	  		float dx,dy,escala;
          	dx = (float)getWidth()/img.getWidth(null);
          	dy = (float)getHeight()/img.getHeight(null);
 	  		escala = dx<dy?dx:dy;
          	larg = (int)(img.getWidth(null)*escala);
          	alt = (int)(img.getHeight(null)*escala);
  	  		x0 = (getWidth()-larg)/2;
	  		y0 = (getHeight()-alt)/2;	  
          	g.drawImage(img, x0,y0,larg,alt, null);
       	}
       g.setColor(Color.BLACK);
       g.drawRect(0,0, getWidth()-1, getHeight()-1);
    }

    public static void main(String args[]){
       JFrame frm = new JFrame();
       frm.setSize(800,600);
       frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frm.setLocationRelativeTo(null);
       frm.setLayout(new FlowLayout());
       ImageBox box = new ImageBox();
       box.setPreferredSize(new Dimension(380,430));
       if (args.length>0)
         box.setImage(args[0]);
       else
         box.setImage("tomate.jpeg");
       frm.add(box);
       frm.setVisible(true);
    }
}