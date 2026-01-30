import java.sql.*;

public class ShopDAO implements AutoCloseable{
    
	private Connection con = null;
	
    ShopDAO() throws ClassNotFoundException,SQLException{
		
			
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://127.0.0.1:3307/project_data";
        
		this.con = DriverManager.getConnection(url, "root", "zubair");
            
		
	}
		

public int signup(String username , String password)throws SQLException{
   	String q1 = "SELECT username FROM data WHERE username = ?";
            
		try(PreparedStatement p = con.prepareStatement(q1)){
            p.setString(1, username);
            try(ResultSet r = p.executeQuery()){

            if(r.next()){
			return 4;
			}
		}
        }
		
		String query = "INSERT INTO data (username, password, Usertype) VALUES (?, ?, ?)";

            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setString(1, username);
                ps.setString(2, password);
				ps.setString(3, "user");
  	
                int rs = ps.executeUpdate();
                
				return rs > 0 ? 1 : -1;        
		}
}

public ResultSet login(String username,String password)throws SQLException{
	    String query = "SELECT * FROM data WHERE username = ? AND password = ?";
       
	        PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
		    return ps.executeQuery();
			} 
	

public int Addproduct(String name,String quantity,String price,String  category,String description)throws SQLException{
	    
		String query = "INSERT INTO product(Productname, Quantity, Price, Category, Description) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement ps = con.prepareStatement(query)){

            ps.setString(1, name);
            ps.setString(2, quantity);
            ps.setString(3, price);
            ps.setString(4, category);
            ps.setString(5, description);

		int rs = ps.executeUpdate();
        
		return rs > 0 ? 1 : -1;	}	
	}

public int checkProduct(String name)throws SQLException{
    
	String query = "SELECT * FROM product WHERE Productname = ?";
            try(PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, name);
            try(ResultSet rs = ps.executeQuery()){
  
	return rs.next() ? 1 : -1 ; }
		}
	}

public int updateCategory(String name,String category)throws SQLException{
       
	String query = "UPDATE product SET Category = ? where Productname = ? ";	
	   
	   try(PreparedStatement ps = con.prepareStatement(query)){
	   
	   ps.setString(1,category);
	   ps.setString(2,name);
	   
	   int rs = ps.executeUpdate();
	   
	  return rs > 0 ? 1 : -1;
	  
	}
}

public int updateDescription(String name,String description)throws SQLException{
       
	String query = "UPDATE product SET Description = ? where Productname = ? ";	
	   
	   try(PreparedStatement ps = con.prepareStatement(query)){
	   
	   ps.setString(1,description);
	   ps.setString(2,name);
	   
	   int rs = ps.executeUpdate();
	
    return rs > 0 ? 1 : -1;
	   }
}


public int Deleteproduct(String name)throws SQLException{
		String query = "DELETE FROM product WHERE Productname = ?";
        try(PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, name);
            int rs = ps.executeUpdate();
		  
		  return rs > 0 ? 1 : -1 ;
		  
	}
}	

public ResultSet Getproduct()throws SQLException{
		
		String query = "SELECT * FROM product";    
            PreparedStatement ps = con.prepareStatement(query);

            return ps.executeQuery();
			
			}
	
public ResultSet Searchproduct(String name)throws SQLException{
	String query = "SELECT * from product  where Productname = ? ";	
	   
	   PreparedStatement ps = con.prepareStatement(query);
	   
	   ps.setString(1,name);
	   
	  return ps.executeQuery();
	 }
	
public ResultSet Getreport()throws SQLException{
	   String query = "SELECT * FROM cart";    
            PreparedStatement ps = con.prepareStatement(query);

            return ps.executeQuery();

			}

public int Addcart(String name,int quantity)throws SQLException{
    
	String query = "SELECT * FROM product WHERE Productname = ?";
            
			try(PreparedStatement ps = con.prepareStatement(query)){
            
			ps.setString(1, name);
            
			try(ResultSet rs = ps.executeQuery()){
            
			if(rs.next()){
				
				int available = rs.getInt("Quantity");
				
				if(available >= quantity){
				updatequantity(name, (available - quantity) + " " );
			    return 1;
			}
			
			else{
			return available;
			}
	  }
	
	else{
	  return -1;
	  }
	  
	}  
  }	
}

public int cart(String name)throws SQLException{
	String q = "SELECT ProcessID FROM cart where Username = ?";
    try(PreparedStatement ps = con.prepareStatement(q)){
	ps.setString(1,name);
	try(ResultSet rs = ps.executeQuery()){
	
	return rs.next() ? rs.getInt("ProcessID") : -1 ;
	
	}
  }
}
  
public int cart(String name , String product , int quantity , int processid , String date)throws SQLException{
	String query = "INSERT INTO cart (Username ,Product,Quantity,ProcessID,Delivery) VALUES(?,?,?,?,?)";
	
	try(PreparedStatement ps = con.prepareStatement(query)){
	
	ps.setString(1,name);
	ps.setString(2,product);
	ps.setInt(3,quantity);
	ps.setInt(4,processid);
	ps.setString(5,date);
		
    int rs = ps.executeUpdate();
	
	return rs > 0 ? 1 : -1;
	
  }
}
  
public int updatePrice(String name , String price )throws SQLException{
  String query = "UPDATE product SET Price = ? where Productname = ? ";	
	   
	   try(PreparedStatement ps = con.prepareStatement(query)){
	   
	   ps.setString(1,price);
	   ps.setString(2,name);
  		   
	   int rs = ps.executeUpdate();
   
    return rs > 0 ? 1 : -1;
	 
	}
}

public int updatequantity(String name,String quantity)throws SQLException{
	String query = "UPDATE product SET Quantity = ? where Productname = ? ";	
	   
	try(PreparedStatement ps = con.prepareStatement(query)){
	   
	 ps.setString(1,quantity);
	   ps.setString(2,name);
	   
	   int rs = ps.executeUpdate();
		   
	return rs > 0 ? 1 : -1;
	
  }
}

public void close()throws SQLException{

	if(this.con != null)con.close();

 } 

}  