import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Getreport extends HttpServlet {
    
    public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException { 
        
        res.setContentType("text/html");
        
        PrintWriter out = res.getWriter();
        
        HttpSession sess = req.getSession(false);
		
		if( sess == null || !"admin".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not admin, Go Back <h2>");
			out.println("<a href = 'Login.html'> Go to Login </a>");
			return;
		}
       
		out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
		out.println("<h1>Sale Record</h1>");
        
		
        try ( ShopDAO obj = new ShopDAO(); ResultSet rs = obj.Getreport() ){
            
            if (rs.next()) {
                        
			do {    
			        out.println("<b>=> " + rs.getString("Username") + " bought " + rs.getString("Quantity") +" "+ rs.getString("Product"));
					out.println(" their ProcessID is " + rs.getString("ProcessID") + " and order Delivered on " + rs.getString("Delivery"));
                    out.println("<br><br>");
				} while (rs.next());
               out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
			   
			} 
			else{
                out.println("No products is sale.");
                out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
			   
			}
            
            } 
		catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }

         finally{ 
        out.println("</body></html>");
        out.close();
	}
		
	}
}
