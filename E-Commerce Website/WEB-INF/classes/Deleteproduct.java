import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Deleteproduct extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
		HttpSession sess = req.getSession(false);
		
		if( sess == null || !"admin".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not the admin, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
       
		
		String name = req.getParameter("name");

        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");

        try (ShopDAO obj = new ShopDAO()){
			
			int check = obj.Deleteproduct(name);
		
         
            if ( check  == 1) {
                out.println("<h1>Product " + name + " Deleted</h1><br><br>");
			   out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
			   
                }
				
			else {
                out.println("<b>The product you enter is not present in stock.</b><br><br>");
			    out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
			   
            }

            }
			catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    
	finally{
	    out.println("</body></html>");
        out.close();
	} 
	}
}
