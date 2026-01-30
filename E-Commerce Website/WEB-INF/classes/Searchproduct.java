import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Searchproduct extends HttpServlet{
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException{ 
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		
		HttpSession sess = req.getSession(false);
		
	    String name = req.getParameter("pname");	
		
		if( sess == null || !"user".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not user, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
       
	    
	out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
    
		
	  try (ShopDAO obj = new ShopDAO(); ResultSet rs = obj.Searchproduct(name)) {
		
	   if(rs.next()){
	    out.println("Product Available !Heres product detail<br>");
		out.println("<b>Product Name:</b> " + rs.getString("Productname") + "<br>");
		out.println("<b>Product Quantity:</b> " + rs.getString("Quantity") + "<br>");
		out.println("<b>Product Category:</b> " + rs.getString("Category") + "<br>");
		out.println("<b>Product Description:</b> " + rs.getString("Description") + "<br><br>");
        out.println("<a href='userinterface.html'>User Interface</a><br><br>");
		}
	    
		else{
		out.println("Product not Available!");
	    out.println("<a href='userinterface.html'>User Interface</a><br><br>");
		
		}
    
		}
		catch(Exception e){

      out.println(e.getMessage());
	  
    }
	
	finally{
		out.println("</body></html>");
        out.close();
	}

}
}