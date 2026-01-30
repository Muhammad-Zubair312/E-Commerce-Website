import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Getproduct extends HttpServlet {
    
    public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException { 
        
        res.setContentType("text/html");
        
        PrintWriter out = res.getWriter();
        
		HttpSession sess = req.getSession(false);
	    
		String user = (String) sess.getAttribute("usertype");
		
        if( sess == null ){
			out.println("<h2> You are not login, Go Back <h2><br><br>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
       
		out.println("<html>");
        out.println("<head><title>All Product</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        
		
           
		    try ( ShopDAO obj = new ShopDAO(); ResultSet rs = obj.Getproduct() ){
            
			
            if (rs.next()) {
			do {
                    out.println("<b>Product Name:</b> " + rs.getString("Productname") + "<br>");
					out.println("<b>Product Quantity:</b> " + rs.getString("Quantity") + "<br>");
					out.println("<b>Product Category:</b> " + rs.getString("Category") + "<br>");
					out.println("<b>Product Description:</b> " + rs.getString("Description") + "<br>");
                    out.println("<br><br>");
				} while (rs.next());
            
			} 
			
			else {
                out.println("No products available.<br><br>");
            			
			}
            
        			if( user != null && user.equals("admin")){
		       out.println("<a href='admininterface.html'>Admin Interface</a>");
			   return;}
			
			if( user != null && user.equals("user")){
		     out.println("<a href='userinterface.html'>User Interface</a>");
			 return;}
    
		   }																 
		
		catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    finally{
		out.println("</body></html>");
        out.close();
		}
    
		
	}
}
