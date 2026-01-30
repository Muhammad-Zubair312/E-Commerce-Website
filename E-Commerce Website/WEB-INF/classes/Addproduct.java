import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Addproduct extends HttpServlet {
    
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        
		HttpSession sess = req.getSession(false);
        
		PrintWriter out = res.getWriter();
        
		res.setContentType("text/html");
		
		if(sess == null || !"admin".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not the admin, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
        
		
        String name = req.getParameter("name");
        String quantity = req.getParameter("quantity");
        String price = req.getParameter("price");
        String category = req.getParameter("category");
        String description = req.getParameter("description");
        
        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");

        
        try (ShopDAO obj = new ShopDAO()){
            
			int check = obj.Addproduct(name,quantity,price,category,description);
		
            if (check == 1) {
                out.println("<h1>Product Added Successfully!</h1><br><br>");
                out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
			  
			} 
			
			else {
                out.println("<h1>Error while Adding Product!</h1>");
				out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
			   
			}
			} 
			
			catch (Exception e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        } 

        finally{
        out.println("</body></html>");
        out.close();
	    	}
	}
}
