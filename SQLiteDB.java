import java.sql.*;
import java.io.*;
import java.util.*;

//1. carregar para a memoria a classe do driver JDBC org.sqlite.JDBC
//2. conectar com o banco de dados
//3 - criar ambiente de execução de consulta sql
//4 - criar e executar a consulta sql
	//4.1 - pegar os dados do resultset
        //4.2 - manipular o resultset
        //4.3 - fechar result set
//5 - fechar o ambiente de execução
//6 - fechar conexao com banco

public class SQLiteDB
{
  private static String _STR_CONEXAO_ = "jdbc:sqlite:PDV.db";
  /*public static void main(String []args){

    SQLiteDB db = new SQLiteDB();
    try{
      User u = new User();
      u.setName("User1");
      u.setBlock(0);
      u.setCPF("123");
      u.setUsername("user");
      u.setPassword("senha");
      u.setProfile(1);
      db.insertUser(u);

      User u2 = new User();
      u2.setName("UserJedi");
      u2.setBlock(0);
      u2.setCPF("321");
      u2.setUsername("jedi");
      u2.setPassword("tralala");
      u2.setProfile(2);
      db.insertUser(u2);

      Product p = new Product();
      p.setCode(1151);
      p.setName("Product1");
      p.setDescription("Descricao");
      p.setPrice(1.99f);
      p.setImagePath("tomate.jpeg");
      db.insertProduct(p);

      Product p2 = new Product();
      p2.setCode(26848);
      p2.setName("Products2");
      p2.setDescription("Descricao2");
      p2.setPrice(10f);
      db.insertProduct(p2);

      Sale sale = new Sale();
      sale.setUser(u);
      sale.setProduct(p2);
      sale.setProduct(p2);
      sale.setProduct(p2);
      db.insertSale(sale);
      db.insertSale(sale);
      db.insertSale(sale);

      Sale sale2 = new Sale();
      sale2.setUser(u2);
      sale2.setProduct(p);
      sale2.setProduct(p);
      sale2.setProduct(p);
      db.insertSale(sale2);

    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }
*/
  public SQLiteDB(){
    try{
      //1. carregar para a memoria a classe do driver JDBC org.sqlite.JDBC
      Class.forName("org.sqlite.JDBC");// passo 1 - carregar o driver do banco

      File f = new File("PDV.db");
      if (!f.exists())
        createDB();
    }catch(Exception e){
       e.printStackTrace();
       System.exit(0);
    }
  }

    // ************** Create DB ************** 

  public void createDB(){
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      Statement stmt = conn.createStatement();
      stmt.executeUpdate("create table Users("+
                "id integer not null primary key,"+
                "CPF varchar(11) not null,"+
                "Name varchar(30) not null,"+
                "Username varchar(30) not null,"+
                "Password varchar(64) not null,"+
                "Profile integer not null,"+
                "Block integer not null)");
      stmt.executeUpdate("create table Products( "+
                "Code integer not null primary key, "+
                "Name varchar(30) not null,"+
                "Description varchar(255) not null,"+
                "Price float not null,"+
                "Image blob)");
      stmt.executeUpdate("create table Sales("+
                "id integer not null primary key, "+
                "User_id references Users(code) not null,"+
                "Created_at varchar(10) not null, "+
                "Updated_at varchar(10) not null)");
      stmt.executeUpdate("create table SaleProduct( "+
                "id integer not null primary key, "+
                "Product_id references Products(code) not null,"+
                "Sale_id references Sales(id) not null)");
     
      stmt.close();
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  // ************** Inserts ************** 

  public void insertSale(Sale s){
    try{
        //2. conectar com o banco de dados
        Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
        //3 - criar ambiente de execução de consulta sql
        PreparedStatement stmt = conn.prepareStatement("insert into Sales(id, User_id, Created_at, Updated_at) values (?,?,datetime('now', 'localtime'), datetime('now', 'localtime'))");
        //4 - criar e executar a consulta sql
        stmt.setString(1,null);
        stmt.setString(2,s.getUser().getCPF());
        //stmt.setBytes(5,ImageToBytes(a.getFoto()));
        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        //keys.next();
        int key = keys.getInt("last_insert_rowid()");
        keys.close();
        //stmt.close();

        for(Product p : s.getProducts()){
          stmt = conn.prepareStatement("insert into SaleProduct(id, Sale_id, Product_id) values (?,?,?)");
          stmt.setString(1,null);
          stmt.setInt(2,key);
          stmt.setInt(3,p.getCode());
          stmt.executeUpdate();
        }

        //4.1 - pegar os dados do resultset
        //4.2 - manipular o resultset
        //4.3 - fechar result set
        //5 - fechar o ambiente de execução
        //stmt.close();
        //6 - fechar conexao com banco
        stmt.close();
        conn.close();
    }catch(Exception e){
        e.printStackTrace();
        System.exit(0);
    }
  }

  public void insertUser(User user){
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      PreparedStatement stmt = conn.prepareStatement("insert into Users(id, CPF, Name, Username, Password, Profile, Block ) values (?,?,?,?,?,?,?)");
      stmt.setString(1,null);
      stmt.setString(2, user.getCPF());
      stmt.setString(3, user.getName());
      stmt.setString(4, user.getUsername());
      stmt.setString(5, user.getPassword());
      stmt.setInt(6, user.getProfile());
      stmt.setInt(7, user.getBlock());

      stmt.executeUpdate();

      stmt.close();
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void insertProduct(Product product){
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      PreparedStatement stmt = conn.prepareStatement("insert into Products(Code, Name, Description, Price, Image) values (?,?,?,?,?)");
      stmt.setInt(1, product.getCode());
      stmt.setString(2, product.getName());
      stmt.setString(3, product.getDescription());
      stmt.setFloat(4, product.getPrice());
      stmt.setBytes(5, product.getImage());

      stmt.executeUpdate();

      stmt.close();
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  // ************** Updates **************  

  public void updateUser(String cpf, User u){
    try{
      //2. conectar com o banco de dados
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      //3 - criar ambiente de execução de consulta sql
      PreparedStatement stmt = conn.prepareStatement("update Users set CPF=?, Name=?, Password=?, Username=?, Profile=?, Block=? where CPF=?");
      //4 - criar e executar a consulta sql
      stmt.setString(1,u.getCPF());
      stmt.setString(2,u.getName());
      stmt.setString(3,u.getPassword());
      stmt.setString(4,u.getUsername());
      stmt.setInt(5,u.getProfile());
      stmt.setInt(6,u.getBlock());
      stmt.setString(7,cpf);
      //stmt.setBytes(5,ImageToBytes(a.getFoto()));
      stmt.executeUpdate();
      //5 - fechar o ambiente de execução
      stmt.close();
      //6 - fechar conexao com banco
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void updateProduct(int code, Product p){
    try{
      //2. conectar com o banco de dados
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      //3 - criar ambiente de execução de consulta sql
      PreparedStatement stmt = conn.prepareStatement("update Products set Name=?, Description=?, Price=?, Image=? where Code=?");
      //4 - criar e executar a consulta sql
      //stmt.setInt(1,p.getCode()); VERIFICAR SE É NECESSÁRIO ALTERAR !!!!
      stmt.setString(1,p.getName());
      stmt.setString(2,p.getDescription());
      stmt.setFloat(3,p.getPrice());
      stmt.setBytes(4,p.getImage());
      stmt.setInt(5,code);
      stmt.executeUpdate();
      //5 - fechar o ambiente de execução
      stmt.close();
      //6 - fechar conexao com banco
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void updateSale(int id, Sale s){
    try{
      //2. conectar com o banco de dados
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      //3 - criar ambiente de execução de consulta sql
      PreparedStatement stmt = conn.prepareStatement("update Sales set User_id=?, Updated_at=datetime('now', 'localtime') where id=?");
      //4 - criar e executar a consulta sql
      stmt.setString(1,s.getUser().getCPF());
      stmt.setInt(2,id);
      stmt.executeUpdate();
      for(Product p : s.getProducts()){
          stmt = conn.prepareStatement("update SaleProduct set Product_id=? where Sale_id=?");
          stmt.setInt(1,p.getCode());
          stmt.setInt(2,id);
          stmt.executeUpdate();
      }
      stmt.close();
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  // ************** Deletes **************

  public void deleteUser(String CPF){
    try{
      //2. conectar com o banco de dados
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      //3 - criar ambiente de execução de consulta sql
      PreparedStatement stmt = conn.prepareStatement("delete from Users where CPF=?");
      //4 - criar e executar a consulta sql
      stmt.setString(1,CPF);
   	  stmt.executeUpdate();
      //5 - fechar o ambiente de execução
      stmt.close();
 	    //6 - fechar conexao com banco
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void deleteProduct(int code){
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      PreparedStatement stmt = conn.prepareStatement("delete from Products where Code=?");
      stmt.setInt(1,code);
      stmt.executeUpdate();
      stmt.close();
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void deleteSale(int id){
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      PreparedStatement stmt = conn.prepareStatement("delete from Sales where id=?");
      stmt.setInt(1,id);
      stmt.executeUpdate();

      stmt = conn.prepareStatement("delete from SaleProduct where Sale_id=?");
      stmt.setInt(1,id);
      stmt.executeUpdate();

      stmt.close();
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  // ************** Gets ************** 

  public User getUser(String cpf){
    User u=null;
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      if (conn != null) {
        PreparedStatement stmt = conn.prepareStatement("select Name, CPF, Username, Password, Profile, Block from Users where CPF=?");
        stmt.setString(1,cpf);
        ResultSet result = stmt.executeQuery();
        System.out.println("Chego aqui !!!"+result.getString(1));
        if(result.next()){
          u = new User();
          u.setName(result.getString(1));
          u.setCPF(result.getString(2));
          u.setUsername(result.getString(3));
          u.setPasswordDB(result.getString(4));
          u.setProfile(result.getInt(5));
          u.setBlock(result.getInt(6));
        }
        stmt.close();
      }
      conn.close();
      return u;
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
      return null;
    }
  }

  public Product getProduct(int code){
    Product p=null;
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      if (conn != null) {
        PreparedStatement stmt = conn.prepareStatement("select Code, Name, Description, Price, Image from Products where Code=?");
        stmt.setInt(1,code);
        ResultSet result = stmt.executeQuery();
        if (result.next()){
          p = new Product();
          p.setCode(result.getInt(1));
          p.setName(result.getString(2));
          p.setDescription(result.getString(3));
          p.setPrice(result.getFloat(4));
          p.setImage(result.getBytes(5));
        }
        result.close();
        stmt.close();
        conn.close();
      }
      return p;
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
      return null;
    }
  }

  public Sale getSale(int id){
    Sale s=null;
    List<Product> products = new ArrayList<Product>(); 
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      if (conn != null) {
        PreparedStatement stmt = conn.prepareStatement("select User_id from Sales where id=?");
        stmt.setInt(1,id);
        ResultSet result = stmt.executeQuery();
        if (result.next()){
          s = new Sale();
          s.setUser(getUser(result.getString(1)));

          stmt = conn.prepareStatement("select Product_id from SaleProduct where Sale_id=?");
          stmt.setInt(1,id);
          result = stmt.executeQuery();
          while(result.next())
            products.add(getProduct(result.getInt(1)));
          
          s.setProducts(products);
        }
        result.close();
        stmt.close();
        conn.close();
      }

      return s;
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
      return null;
    }
  }
  
  public Collection<User> getUsers(){
    LinkedList<User> U = new LinkedList<User>();
    try{
      //2. conectar com o banco de dados
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      //3 - criar ambiente de execução de consulta sql
      PreparedStatement stmt = conn.prepareStatement("select Name, CPF, Password, Username, Profile, Block from Users order by Name");
      //4 - criar e executar a consulta sql
      ResultSet result = stmt.executeQuery();
      //4.1 - pegar os dados do resultset
      //4.2 - manipular o resultset
      while (result.next()){
        User u = new User();
        u.setName(result.getString(1));
        u.setCPF(result.getString(2));
        u.setPasswordDB(result.getString(3));
        u.setUsername(result.getString(4));
        u.setProfile(result.getInt(5));
        u.setBlock(result.getInt(6));
        U.add(u);
      }
      //4.3 - fechar result set
      result.close();
      //5 - fechar o ambiente de execução
      stmt.close();
      //6 - fechar conexao com banco
      conn.close();
      return U;
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
      return U;
    }
  }

  public Collection<Product> getProducts(){
    LinkedList<Product> P = new LinkedList<Product>();
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      PreparedStatement stmt = conn.prepareStatement("select Code, Name, Description, Price, Image from Products order by Name");
      ResultSet result = stmt.executeQuery();
      while (result.next()){
        Product p = new Product();
        p.setCode(result.getInt(1));
        p.setName(result.getString(2));
        p.setDescription(result.getString(3));
        p.setPrice(result.getFloat(4));
        p.setImage(result.getBytes(5));
        P.add(p);
      }
      result.close();
      stmt.close();
      conn.close();
      return P;
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
      return P;
    }
  }

  public Collection<Sale> getSales(){
    LinkedList<Sale> S = new LinkedList<Sale>();
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      PreparedStatement stmt = conn.prepareStatement("select id from Sales order by User_id");
      ResultSet result = stmt.executeQuery();
      while (result.next()){
        Sale s = new Sale();
        S.add(getSale(result.getInt(1)));
      }
      result.close();
      stmt.close();
      conn.close();
      return S;
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
      return S;
    }
  }

  public User getUserLike(String text){
    User u=null;
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      if (conn != null) {
        PreparedStatement stmt = conn.prepareStatement("select Name, CPF, Username, Password, Profile, Block from Users where (Name like ? or Username like ? or CPF like ?)");
        stmt.setString(1,"%" + text + "%");
        stmt.setString(2,"%" + text + "%");
        stmt.setString(3,"%" + text + "%");
        ResultSet result = stmt.executeQuery();
        if(result.next()){
          u = new User();
          u.setName(result.getString(1));
          u.setCPF(result.getString(2));
          u.setUsername(result.getString(3));
          u.setPasswordDB(result.getString(4));
          u.setProfile(result.getInt(5));
          u.setBlock(result.getInt(6));
        }
        stmt.close();
      }
      conn.close();
      return u;
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
      return null;
    }
  }  

  public Product getProductLike(String text){
    Product p=null;
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      if (conn != null) {
        PreparedStatement stmt = conn.prepareStatement("select Name, Code, Description, Price, Image from Products where (Name like ? or Description like ? or Code like ?)");
        stmt.setString(1,"%" + text + "%");
        stmt.setString(2,"%" + text + "%");
        stmt.setString(3,"%" + text + "%");
        ResultSet result = stmt.executeQuery();
        if(result.next()){
          p = new Product();
          p.setName(result.getString(1));
          p.setCode(result.getInt(2));
          p.setDescription(result.getString(3));
          p.setPrice(result.getFloat(4));
          p.setImage(result.getBytes(5));
        }
        stmt.close();
      }
      conn.close();
      return p;
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
      return null;
    }
  }

    // ************** Drop Table ************** 

    public void dropDB(String name){
    try{
      Connection conn = DriverManager.getConnection(_STR_CONEXAO_);
      PreparedStatement stmt = conn.prepareStatement("drop table "+name);
      stmt.executeUpdate();
      stmt.close();
      conn.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }
}
