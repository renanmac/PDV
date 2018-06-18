import java.util.*;

public class Sale{
 	private User user;
 	private List<Product> products = new ArrayList<Product>();
 	private SQLiteDB db;

	public Sale(){
		db = new SQLiteDB();
	}


	public User 		 getUser()	  { return user;}
	public List<Product> getProducts(){ return products;}

	public void setUser(User u){
		user = u;
	}

	public void setProducts(List<Product> p){
		products = p;
	}

	public void setProduct(Product p){
		products.add(p);
	}


	public void insertsale(Sale s){
		db.insertSale(s);
	}

	public void updatesale(int id, Sale s){
		db.updateSale(id,s);
	}

	public void removesale(int id){
		db.deleteSale(id);
	}

	public Iterable<Sale> getSales(){
		return db.getSales();
	}

	public Sale getSale(int id){
		return db.getSale(id);
	}

	public void dropDB(){
		db.dropDB("Sales");
		db.dropDB("SaleProduct");
	}

}