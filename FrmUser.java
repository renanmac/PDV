import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Checkbox;

public class FrmUser extends JDialog implements ActionListener
{
    private JButton btnOk;
    private JButton btnCancel;
    private JTextField txbCPF;
    private JTextField txbName;
    private JTextField txbUsername;
    private JTextField txbPassword;
    private JTextField txbProfile;
    private Checkbox chkBlock;
    private boolean Resp;

    private JButton createButton(Container c, String text){
       JButton btn = new JButton(text);
       btn.setPreferredSize(new Dimension(130,25));
       btn.addActionListener(this);
       c.add(btn);
       return btn;
    }
     
    private JTextField createField(Container c, String text, int Largura){
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

    private Checkbox createCheck(Container c, String text, String checktext, boolean state){
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(390,35));
      if(text != null){
        JLabel lbl = new JLabel(text);
        lbl.setPreferredSize(new Dimension(85,20));
        panel.add(lbl);
      }
      ((FlowLayout)panel.getLayout()).setAlignment(FlowLayout.LEFT);
      Checkbox chk = new Checkbox(checktext, state);
      panel.add(chk);
      c.add(panel);
      return chk;
    }
   
    private FrmUser()
    {
       setTitle("User");
       setSize(400,250);
       setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);     //fechar a janela
       setLocationRelativeTo(null); 			   //centraliza a janela
       setModal(true);
       
       JPanel pnlButtons = new JPanel();
       getContentPane().add(pnlButtons, BorderLayout.SOUTH);
       btnOk = createButton(pnlButtons, "Ok");
       btnCancel = createButton(pnlButtons, "Cancelar");

       JPanel panelField = new JPanel();
       getContentPane().add(panelField, BorderLayout.CENTER);
       txbCPF = createField(panelField, "CPF", 10);
       txbName = createField(panelField, "Nome", 25);
       txbUsername = createField(panelField, "Usuário", 25);
       txbPassword = createField(panelField, "Senha", 25);
       txbProfile = createField(panelField, "Perfil", 10);
       chkBlock = createCheck(panelField, null, "Marque para Bloquear o Usuário", false);
    }

    public void actionPerformed(ActionEvent evt)
    {
      Resp = evt.getSource()==btnOk;
 	    setVisible(false);       
    }

    private void updateFrame(User u){
       txbName.setText(u.getName());
       txbCPF.setText(u.getCPF());
       txbProfile.setText(""+u.getProfile());
       txbUsername.setText(u.getUsername());
       txbPassword.setText(u.getPassword());
       chkBlock.setState(u.getBlock()==1?true:false);
       //txbImage.setText(""+p.getNota2());
    }

    private void updateObject(User u){
       u.setName(txbName.getText());
       u.setCPF(txbCPF.getText());
       u.setUsername(txbUsername.getText());
       u.setPassword(txbPassword.getText());
       u.setProfile(Integer.parseInt(txbProfile.getText()));
       u.setBlock((chkBlock.getState()==true?1:0));
    }

    private static FrmUser frame=null;
    
    public static boolean execute(User u){
       if (frame==null)
          frame = new FrmUser();
       frame.updateFrame(u);
       frame.setVisible(true);
       if (frame.Resp)
           frame.updateObject(u);
       return frame.Resp;
    }

    public static void main(String s[])
    {
       User a = new User();
       a.setName("Usuario");
       a.setCPF("123");
       a.setUsername("user");
       a.setPassword("123");
       a.setProfile(1);
       a.setBlock(0);
 
       System.out.println(FrmUser.execute(a));
       System.out.println("Nome: "+a.getName());
       System.out.println("CPF: "+a.getCPF());
       System.out.println("Usuario: "+a.getUsername());
       System.out.println("Senha: "+a.getPassword());
       System.out.println("Bloqueado? "+a.getBlock());
       System.exit(0);
    }
}