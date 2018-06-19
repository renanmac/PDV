import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdmScreen extends JFrame implements ActionListener{
	private JButton btnInserir;
    private JButton btnRemover;
    private JButton btnAlterar;
    private JButton btnBuscar;
    private JButton btnSair;
    private CtrlProducts ctrlProducts;
    private CtrlUsers ctrlUsers;
    private int escolhido;
    private User user;
    private Product product;

    private JButton createButton(Container c, String text){
       JButton btn = new JButton(text);
       btn.setPreferredSize(new Dimension(130,25));
       btn.addActionListener(this);
       c.add(btn);
       return btn;
    }

    public AdmScreen(){
    	setTitle("Cadastro de Usuários");
    	setSize(500,300);

    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	JPanel pnlButtons = new JPanel();
    	getContentPane().add(pnlButtons, BorderLayout.NORTH);

    	btnInserir = createButton(pnlButtons, "Inserir");
    	btnRemover = createButton(pnlButtons, "Remover");
    	btnAlterar = createButton(pnlButtons, "Alterar");
    	btnBuscar = createButton(pnlButtons, "Buscar");
    	btnSair = createButton(pnlButtons, "Sair");

    	ctrlProducts = new CtrlProducts();
    	getContentPane().add(ctrlProducts, BorderLayout.CENTER);
    	ctrlProducts.update();

    	/*ctrlUsers = new CtrlUsers();
    	getContentPane().add(ctrlUsers, BorderLayout.CENTER);
    	ctrlUsers.update();*/

    }

	public void actionPerformed(ActionEvent evt){
		JButton btn = (JButton)evt.getSource();
		if (btn==btnInserir){
			User u = new User();
			if (FrmUser.execute(u)){
				u.insertUser(u);
				ctrlUsers.update();
			}
		}else if (btn==btnRemover){
			User u = ctrlUsers.selectedUser();
			if (u==null)
				JOptionPane.showMessageDialog(null,"Nenhum User selecionado!");
			else if (JOptionPane.showConfirmDialog( null,"Deseja realmente remover o Usuário "+u.getCPF()+ " - "+u.getName()+"?",
					"Remover",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				u.deleteUser(u.getCPF());
				ctrlUsers.update();	
			} 
		}else if (btn==btnAlterar){
			User u = ctrlUsers.selectedUser();
			if (u==null)
				JOptionPane.showMessageDialog(null,"Nenhum Usuário selecionado!");
			else{
				String cpf = u.getCPF();
				if (FrmUser.execute(u)){ 
					u.updateUser(cpf, u);
					ctrlUsers.update();	 
				}
			} 
		}
		else if (btn==btnBuscar)
		{
		}
		else if (btn==btnSair)
		{
			setVisible(false);
		}
	}

	public static void main(String s[]){
        AdmScreen frm = new AdmScreen();
		frm.setVisible(true);
    }  


}