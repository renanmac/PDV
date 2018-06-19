import java.io.*;

public class ProdTXT{
  
  private String Name;  
  
  public  ProdTXT (String name){
    Name = name;
  }

  public void exportar(){
    try{
      FileOutputStream st = new FileOutputStream(Name);
      OutputStreamWriter sw = new OutputStreamWriter(st);
      BufferedWriter bw = new BufferedWriter(sw);
      Product product = new Product();
      for(Product p : product.getProducts()){
        bw.write(p.getName()+ ";"+p.getPrice()+";"+p.getCode()+";"+p.getDescription());          
        bw.newLine();
      }

    bw.close();
    sw.close();
    st.close();
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void importar(){
    try{
      File f = new File(Name);
      if (f.exists()){          
        FileInputStream st = new FileInputStream(Name);
        InputStreamReader sr = new InputStreamReader(st);
        BufferedReader br = new BufferedReader(sr);
        String linha;
        Product product = new Product();
        while(( linha=br.readLine()) != null)
          if(linha.trim().length()>0){
            String []v = linha.split(";");
            product.setName(v[0]);
            product.setPrice(Float.parseFloat(v[1])); 
            product.setCode(Integer.parseInt(v[2]));
            product.setDescription(v[3]);
            product.insertProduct(product); 
          }
        br.close();
        sr.close();
        st.close();
      }
    }catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }
}