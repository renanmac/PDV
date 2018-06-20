import java.security.*;

public class User{
	private String Name;
	private String CPF;
	private String Password;
	private String Username;
	private int Profile;
	private int Block; 
	private SQLiteDB db = new SQLiteDB();

	public String  getName()	 { return Name;}
	public String  getCPF()		 { return CPF;}
	public String  getPassword() { return Password;}
	public String  getUsername() { return Username;}
	public int 	   getProfile()	 { return Profile;}
	public int 	   getBlock()    { return Block;}

	public void setName(String name)     	{ Name = name;}
	public void setBlock(int block)      	{ Block = block;}
	public void setCPF(String cpf)       	{ CPF = cpf;}
	public void setUsername(String user) 	{ Username = user;}
	public void setPassword(String password){ Password = hashpass(password);} 
	public void setPasswordDB(String password){ Password = password;} 
	public void setProfile(int profile)  	{ 
		if(profile>=0 && profile<=2)
			Profile = profile;
		else
			throw new Error("O Perfil deve estar no intervalo [0,2]");
	}

	public static String hashpass(String password){
		try{ 
			MessageDigest hash = MessageDigest.getInstance("SHA-256");
			byte digestPass[] = hash.digest(password.getBytes("UTF-8"));
			StringBuilder hexStringpass = new StringBuilder();
		    for (byte b : digestPass) {
		    	hexStringpass.append(String.format("%02X", 0xFF & b));
		    }
		    return hexStringpass.toString();
		}catch(Exception e){
			return "Senha inválida!";
		}
	}

	public void insertUser(User u){
		db.insertUser(u);
	}

	public void deleteUser(String cpf){
		db.deleteUser(cpf);
	}

	public void updateUser(String cpf, User u){
		db.updateUser(cpf, u);
	}

	public Iterable<User> getUsers(){
		return db.getUsers();
	}

	public User getUser(String cpf){
		return db.getUser(cpf);
	}

	public User getUserLike(String text){
		return db.getUserLike(text);
	}

	public void dropDB(){
		db.dropDB("Users");
	}

	/*public static void main(String args[]){
		User u = new User();
		u = u.getUserLike("jedi");
		System.out.println(u.getName());
	}
*/
}
