import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.*;

public class CtrlUsers extends JPanel{
  private class Model extends AbstractTableModel{
    private User []users=null;

    public int getColumnCount(){
      return 7;
    }

    public String getColumnName(int columnIndex){
      switch(columnIndex){
        case 0: return "#";
        case 1: return "CPF";
        case 2: return "Nome";
        case 3: return "Nome de Usuário";
        case 4: return "Senha";
        case 5: return "Perfil";
        case 6: return "Bloqueado";
        default: return "desconhecido";
      }
    }

    public int getRowCount(){
      return users==null?0:users.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex){
      User u = users[rowIndex];
      switch(columnIndex){
        case 0: return rowIndex+1;
        case 1: return u.getCPF();
        case 2: return u.getName();
        case 3: return u.getUsername();
        case 4: return u.getPassword();
        case 5: return u.getProfile();
        case 6: return u.getBlock();
        default: return "desconhecido";
      }
    }

    public User getUser(int row){
      return users[row];
    }

    public void setUsers(){
      User user = new User();
      java.util.List<User> userslist = new LinkedList<User>();
      for(User u : user.getUsers())
        userslist.add(u);

      users = new User[userslist.size()];
      users = userslist.toArray(users); 
      fireTableDataChanged();
    }

  }


    private JTable tbl;
    private Model  mdl;

    public CtrlUsers(){
      mdl = new Model();
      tbl = new  JTable();
      tbl.setModel(mdl);
      setLayout(new BorderLayout());         
      add(new JScrollPane(tbl), BorderLayout.CENTER);
    }

    public User selectedUser(){
      int i = tbl.getSelectedRow();
      if (i==-1)
        return null;
      else 
        return mdl.getUser(i);
    }

  public void update(){
    mdl.setUsers();
  }
}