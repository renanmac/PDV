import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.*;

public class CtrlProducts extends JPanel{
  private class Model extends AbstractTableModel{
    private Product []products=null;

    public int getColumnCount(){
      return 5;
    }

    public String getColumnName(int columnIndex){
      switch(columnIndex){
        case 0: return "#";
        case 1: return "Código";
        case 2: return "Nome";
        case 3: return "Descrição";
        case 4: return "Preço";
        default: return "desconhecido";
      }
    }

    public int getRowCount(){
      return products==null?0:products.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex){
      Product p = products[rowIndex];
      switch(columnIndex){
        case 0: return rowIndex+1;
        case 1: return p.getCode();
        case 2: return p.getName();
        case 3: return p.getDescription();
        case 4: return p.getPrice();
        default: return "desconhecido";
      }
    }

    public Product getProduct(int row){
      return products[row];
    }

    public void setProducts(){
      Product product = new Product();
      java.util.List<Product> productslist = new LinkedList<Product>();
      for(Product p : product.getProducts())
        productslist.add(p);

      products = new Product[productslist.size()];
      products = productslist.toArray(products); 
      fireTableDataChanged();
    }

  }


    private JTable tbl;
    private Model  mdl;

    public CtrlProducts(){
      mdl = new Model();
      tbl = new  JTable();
      tbl.setModel(mdl);
      setLayout(new BorderLayout());         
      add(new JScrollPane(tbl), BorderLayout.CENTER);
    }

    public Product selectedProduct(){
      int i = tbl.getSelectedRow();
      if (i==-1)
        return null;
      else 
        return mdl.getProduct(i);
    }

  public void update(){
    mdl.setProducts();
  }
}