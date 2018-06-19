import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperatorMenu extends JFrame implements ActionListener{
	private JButton btnConsultar;
	private JButton btnVenda;

	public OperatorMenu(){
		setTitle("Módulo Operador");
    	setSize(500,300);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);

		JPanel pnlButtons = new JPanel();
		btnConsultar = createButton(pnlButtons, "Consultar");
		btnVenda = createButton(pnlButtons, "Venda");
		btnConsultar.setPreferredSize(new Dimension(300,100));
		btnVenda.setPreferredSize(new Dimension(300,100));
    	getContentPane().add(pnlButtons, BorderLayout.CENTER);

	}

	private JButton createButton(Container c, String text){
    	JButton btn = new JButton(text);
    	btn.setPreferredSize(new Dimension(130,25));
    	btn.addActionListener(this);
    	c.add(btn);
    	return btn;
    }

    public void actionPerformed(ActionEvent evt){
		JButton btn = (JButton)evt.getSource();
		if(btn==btnConsultar){
			String text = JOptionPane.showInputDialog("Consultar produto:");
			Product p = new Product();
			p = p.getProductLike(text);
			if(p==null){
				JOptionPane.showMessageDialog(null,"Nenhum registro encontrado");
			}else{
				FrmProduct.execute(p);
			}
		}else if(btn==btnVenda){

		}
	}

	public static void main(String args[]){
		OperatorMenu op = new OperatorMenu();
		op.setVisible(true);
	}


}