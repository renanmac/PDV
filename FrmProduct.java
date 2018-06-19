import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.*;

public class FrmProduct extends JDialog implements ActionListener
{
    private JButton btnOk;
    private JButton btnCancel;
    private JTextField txbCode;
    private JTextField txbName;
    private JTextField txbDescription;
    private JTextField txbPrice;
    private ImageBox imageBox;
    private boolean Resp;

    private JButton createButton(Container c, String text)
    {
       JButton btn = new JButton(text);
       btn.setPreferredSize(new Dimension(130,25));
       btn.addActionListener(this);
       c.add(btn);
       return btn;
    }
     
    private JTextField createField(Container c, String text, int Largura)
    {
       JPanel panel = new JPanel();
       panel.setPreferredSize(new Dimension(390,25));
       ((FlowLayout)panel.getLayout()).setAlignment(FlowLayout.LEFT);
       JLabel lbl = new JLabel(text);
       lbl.setPreferredSize(new Dimension(85,20));
       panel.add(lbl);
       JTextField tbx = new JTextField(Largura);
       panel.add(tbx);
       c.add(panel);
       return tbx;
    }

    private ImageBox createImageBox(Container c){
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(285, 335));
      ((FlowLayout)panel.getLayout()).setAlignment(FlowLayout.CENTER);
      imageBox = new ImageBox();
      imageBox.setPreferredSize(new Dimension(280,330));
      panel.add(imageBox);
      c.add(panel);
      return imageBox;
    }
   
    private FrmProduct()
    {
       setTitle("Produto");
       setSize(400,550);
       setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       setLocationRelativeTo(null);
       setModal(true);
       
       JPanel pnlButtons = new JPanel();
       getContentPane().add(pnlButtons, BorderLayout.SOUTH);
       btnOk = createButton(pnlButtons, "Ok");
       btnCancel = createButton(pnlButtons, "Cancelar");

       JPanel pnlImage = new JPanel();
       getContentPane().add(pnlImage, BorderLayout.NORTH);
       imageBox = createImageBox(pnlImage);
       
       JPanel panelField = new JPanel();
       getContentPane().add(panelField, BorderLayout.CENTER);
       txbCode = createField(panelField, "Código", 10);
       txbName = createField(panelField, "Nome", 25);
       txbDescription = createField(panelField, "Descrição", 25);
       txbPrice = createField(panelField, "Preço", 10);
    }

    public void actionPerformed(ActionEvent evt)
    {
      Resp = evt.getSource()==btnOk;
 	    setVisible(false);       
    }

    private void updateFrame(Product p)
    {
       txbName.setText(p.getName());
       txbCode.setText(""+p.getCode());
       txbPrice.setText(""+p.getPrice());
       txbDescription.setText(p.getDescription());
       imageBox.setImage(ImageService.bytestoImage(p.getImage()));
    }

    private void updateObject(Product p)
    {
       p.setName(txbName.getText());
       p.setCode(Integer.parseInt(txbCode.getText()));
       p.setDescription(txbDescription.getText());
       p.setPrice(Float.parseFloat(txbPrice.getText()));
       p.setImage(ImageService.imagetobytes(imageBox.getResource()));
    }

    private static FrmProduct frame=null;
    
    public static boolean execute(Product p)
    {
       if (frame==null)
          frame = new FrmProduct();
       frame.updateFrame(p);
       frame.setVisible(true);
       if (frame.Resp)
           frame.updateObject(p);
       return frame.Resp;
    }

    public static void main(String s[])
    {
       Product a = new Product();
       a.setName("Produto");
       a.setCode(1);
       a.setDescription("Produto teste");
       a.setPrice(5.99f);
 
       System.out.println(FrmProduct.execute(a));
       System.out.println("Nome: "+a.getName());
       System.out.println("Código: "+a.getCode());
       System.out.println("Descrição: "+a.getDescription());
       System.out.println("Preço: "+a.getPrice());
       System.exit(0);
    }
}