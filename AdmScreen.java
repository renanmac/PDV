import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdmScreen extends JFrame implements ActionListener{
	private JButton btnInserir;
    private JButton btnRemover;
    private JButton btnAlterar;
    private JButton btnBuscar;
    private JButton btnSair;
    private JButton btnInseriru;
    private JButton btnRemoveru;
    private JButton btnAlteraru;
    private JButton btnBuscaru;
    private JButton btnSairu;
    private JButton btnUsuarios;
    private JButton btnProdutos;
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

    private JPanel usersScreen(){
    	JPanel userpnl = new JPanel(new BorderLayout());
    	JPanel pnlButtons = new JPanel();
    	userpnl.add(pnlButtons, BorderLayout.NORTH);
    	btnInseriru = createButton(pnlButtons, "Inserir");
    	btnRemoveru = createButton(pnlButtons, "Remover");
    	btnAlteraru = createButton(pnlButtons, "Alterar");
    	btnBuscaru = createButton(pnlButtons, "Buscar");
    	btnSairu = createButton(pnlButtons, "Sair");

    	ctrlUsers = new CtrlUsers();
    	userpnl.add(ctrlUsers, BorderLayout.CENTER);
    	ctrlUsers.update();

    	return userpnl;
    }

    private JPanel productsScreen(){
    	JPanel productpnl = new JPanel(new BorderLayout());
    	JPanel pnlButtons = new JPanel();
    	productpnl.add(pnlButtons, BorderLayout.NORTH);

    	btnInserir = createButton(pnlButtons, "Inserir");
    	btnRemover = createButton(pnlButtons, "Remover");
    	btnAlterar = createButton(pnlButtons, "Alterar");
    	btnBuscar = createButton(pnlButtons, "Buscar");
    	btnSair = createButton(pnlButtons, "Sair");

    	ctrlProducts = new CtrlProducts();
    	productpnl.add(ctrlProducts, BorderLayout.CENTER);
    	ctrlProducts.update();

    	return productpnl;
    }

    public AdmScreen(){
    	setTitle("Módulo Admin");
    	setSize(500,300);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);

    	JTabbedPane m = new JTabbedPane();
    	m.setPreferredSize(new Dimension(495, 295));
    	m.add(usersScreen(), "Usuários");
    	m.add(productsScreen(), "Produtos");
    	//JPanel m = new JPanel();
    	//m.add(u);
    	//m.add(p);*/
    	getContentPane().add(m, BorderLayout.CENTER);
    }

	public void actionPerformed(ActionEvent evt){
		JButton btn = (JButton)evt.getSource();
		if (btn==btnInserir){
			Product p = new Product();
			if (FrmProduct.execute(p)){
				p.insertProduct(p);
				ctrlProducts.update();
			}
		}else if (btn==btnRemover){
			Product p = ctrlProducts.selectedProduct();
			if (p==null)
				JOptionPane.showMessageDialog(null,"Nenhum Produto selecionado!");
			else if (JOptionPane.showConfirmDialog( null,"Deseja realmente remover o Produto "+p.getCode()+ " - "+p.getName()+"?",
					"Remover",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				p.deleteProduct(p.getCode());
				ctrlProducts.update();	
			} 
		}else if (btn==btnAlterar){
			Product p = ctrlProducts.selectedProduct();
			if (p==null)
				JOptionPane.showMessageDialog(null,"Nenhum Produto selecionado!");
			else{
				int code = p.getCode();
				if (FrmProduct.execute(p)){ 
					p.updateProduct(code, p);
					ctrlProducts.update();	 
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
		else if (btn==btnInseriru){
			User u = new User();
			if (FrmUser.execute(u)){
				u.insertUser(u);
				ctrlUsers.update();
			}
		}else if (btn==btnRemoveru){
			User u = ctrlUsers.selectedUser();
			if (u==null)
				JOptionPane.showMessageDialog(null,"Nenhum User selecionado!");
			else if (JOptionPane.showConfirmDialog( null,"Deseja realmente remover o Usuário "+u.getCPF()+ " - "+u.getName()+"?",
					"Remover",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				u.deleteUser(u.getCPF());
				ctrlUsers.update();	
			} 
		}else if (btn==btnAlteraru){
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
		else if (btn==btnBuscaru)
		{
		}
		else if (btn==btnSairu)
		{
			setVisible(false);
		}else if (btn==btnUsuarios){
			usersScreen();
		}else if (btn==btnProdutos){
			productsScreen();
		}
	}

	public static void main(String s[]){
        AdmScreen frm = new AdmScreen();
		frm.setVisible(true);
    }  


}