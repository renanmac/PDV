import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener{
	private JButton btnCancel;
	private JButton btnLogin;
	private JTextField txbUsername;
	private JPasswordField txbPassword;

	public Login(){
		setTitle("Login");
    	setSize(350,110);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	JPanel fields = new JPanel();
    	txbUsername = createField(fields, "Usuário", 18);
    	txbPassword = createFieldPassword(fields, "Senha", 18);
		JPanel pnlButtons = new JPanel();
		btnLogin = createButton(pnlButtons, "Login");
		btnCancel = createButton(pnlButtons, "Cancelar");
		
    	getContentPane().add(fields, BorderLayout.CENTER);
    	getContentPane().add(pnlButtons, BorderLayout.SOUTH);

	}

	private JButton createButton(Container c, String text){
    	JButton btn = new JButton(text);
    	btn.setPreferredSize(new Dimension(130,25));
    	btn.addActionListener(this);
    	c.add(btn);
    	return btn;
    }

    private JTextField createField(Container c, String text, int Largura)
    {
       JPanel panel = new JPanel();
       panel.setPreferredSize(new Dimension(300,25));
       ((FlowLayout)panel.getLayout()).setAlignment(FlowLayout.LEFT);
       JLabel lbl = new JLabel(text);
       lbl.setPreferredSize(new Dimension(85,20));
       panel.add(lbl);
       JTextField tbx = new JTextField(Largura);
       panel.add(tbx);
       c.add(panel);
       return tbx;
    }    

    private JPasswordField createFieldPassword(Container c, String text, int Largura)
    {
       JPanel panel = new JPanel();
       panel.setPreferredSize(new Dimension(300,25));
       ((FlowLayout)panel.getLayout()).setAlignment(FlowLayout.LEFT);
       JLabel lbl = new JLabel(text);
       lbl.setPreferredSize(new Dimension(85,20));
       panel.add(lbl);
       JPasswordField tbx = new JPasswordField(Largura);
       panel.add(tbx);
       c.add(panel);
       return tbx;
    }

    public void actionPerformed(ActionEvent evt){
		JButton btn = (JButton)evt.getSource();
		if(btn==btnCancel){
			setVisible(false);
		}else if(btn==btnLogin){
			User u = new User();
			u = u.getUserLike(txbUsername.getText());
			if(u ==null){
				JOptionPane.showMessageDialog(null, "Usuário não existe");
			}else if(u.getPassword().equals(User.hashpass(txbPassword.getText()))){
				setVisible(false);
				if(u.getProfile()==0){
					AdmScreen adm = new AdmScreen();
					adm.setVisible(true);
				}else if(u.getProfile()==1){
					OperatorMenu op = new OperatorMenu();
					op.setVisible(true);
				}else{}
			}else{
				JOptionPane.showMessageDialog(null, "Usuário e/ou Senha incorretos");
			}
		}
	}
	private static Login login=null;
    

	public static void main(String args[]){
		Splash sp = new Splash();
		Login login = new Login();
		login.setVisible(true);
	}


}