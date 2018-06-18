public class Adm extends User{

	private SQLiteBD bd = new SQLiteBD();

	public void insertUser(User u){
		bd.insertUser(u);
	}

	public void removeUser(String cpf){
		bd.removeUser(cpf);
	}
	public void updateUser(String cpf, User u){
		bd.updateUser(cpf, u);
	}

	public void updateUserPass(User u, String pass){
		u.setPassword(pass);
		bd.updateUser(u.cpf, u);
	}
	
	public void blockUser(User u, int b){
		u.setBlock(b);
		bd.updateUser(u.cpf, u);
	}

	public Iterable<User> listUsers(){
		return bd.getUsers();
	}

	public User getUsers(){
		return bd.getUser();
	}
}