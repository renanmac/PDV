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
    private JFileChooser chooseImage;
    private boolean Resp;

    private JButton createButton(Container c, String text)
    {
       JButton btn = new JButton(text);
       btn.setPreferredSize(new Dimension(130,25));
       btn.addActionListener(this);
       c.add(btn);
       return btn;
    }

    private JFileChooser createFileChooser(Container c){
      JFileChooser chooser = new JFileChooser();
      chooser.setPreferredSize(new Dimension(550,350));
      javax.swing.filechooser.FileNameExtensionFilter filter = new javax.swing.filechooser.FileNameExtensionFilter(
        "JPEG, JPG & GIF Images", "jpeg", "jpg", "gif");
      chooser.setFileFilter(filter);
      c.add(chooser);
      int returnVal = chooser.showOpenDialog(null);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
       return chooser;
      }
      return null;
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
   
    private FrmProduct()
    {
       setTitle("Produto");
       setSize(400,250);
       setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       setLocationRelativeTo(null);
       setModal(true);
       
       JPanel pnlButtons = new JPanel();
       getContentPane().add(pnlButtons, BorderLayout.SOUTH);
       btnOk = createButton(pnlButtons, "Ok");
       btnCancel = createButton(pnlButtons, "Cancelar");

       JPanel panelField = new JPanel();
       getContentPane().add(panelField, BorderLayout.CENTER);
       chooseImage = createFileChooser(panelField);
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
       //txbImage.setText(""+p.getNota2());
    }

    private void updateObject(Product p)
    {
       p.setName(txbName.getText());
       p.setCode(Integer.parseInt(txbCode.getText()));
       p.setDescription(txbDescription.getText());
       p.setPrice(Float.parseFloat(txbPrice.getText()));
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