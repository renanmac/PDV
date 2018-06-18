import javax.swing.*;

public class GUI{

	public static void main(String s[]){
		SQLiteDB db = new SQLiteDB();
		String opc="0";
            	opc = JOptionPane.showInputDialog(null, "\tGUI Tosca\n1-Inserir\n2-Remover\n3-Alterar\n4-Listar\n5-Sair\nEntre com a sua opcao:");
         		switch(opc){
         			case "1":
         				User u = new User();
         				u.setName(JOptionPane.showInputDialog(null, "Nome: "));
         				u.setCPF(JOptionPane.showInputDialog(null, "CPF: "));
         				u.setUsername(JOptionPane.showInputDialog(null, "Username: "));
         				u.setPassword(JOptionPane.showInputDialog(null, "Senha: "));
         				u.setProfile(Integer.parseInt(JOptionPane.showInputDialog(null, "Perfil: ")));
         				u.setBlock(Integer.parseInt(JOptionPane.showInputDialog(null, "Block: ")));
         				db.insertUser(u);
         		}


    } 
 }
