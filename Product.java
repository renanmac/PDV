public class Product{

	private SQLiteDB db = new SQLiteDB();
	private int Code;
	private String Name, Description;
	private float Price;
	private byte[] Image;

	public int    getCode()			{return Code;}
	public String getName()			{return Name;}
	public String getDescription()	{return Description;}
	public float  getPrice()		{return Price;}
	public byte[]  getImage()		{return Image;}

	public void setCode(int c){
		if(c>0)
			Code = c;
		else  
			throw new Error("O código deve ser um número inteiro maior que zero!");
	}
	public void setName(String n){ Name = n;}
	public void setDescription(String d){ Description = d;}
	public void setImage(byte[] b){ Image = b;}
	public void setImagePath(String path){ Image = ImageService.imagetobytes(path);}
	public void setPrice(float f){
		if(f >= 0)
			Price = f;
		else  
			throw new Error("O preço deve ser um número maior ou igual a zero!");
	}

	public void insertProduct(Product p){
		db.insertProduct(p);
	}

	public void removeProduct(int code){
		db.deleteProduct(code);
	}

	public void updateProduct(int code, Product p){
		db.updateProduct(code, p);
	}

	public Iterable<Product> getProducts(){
		return db.getProducts();
	}

	public Product getProduct(int code){
		return db.getProduct(code);
	}

	public void dropDB(){
		db.dropDB("Products");
	}

}