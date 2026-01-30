import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Updateinventory extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        HttpSession sess = req.getSession(false);
		
		if( sess == null || !"admin".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not admin, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
       
        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");

        try (ShopDAO obj = new ShopDAO()){
			
			int check = obj.checkProduct(name);
	
			
            if ( check == 1) {
                out.println("<h1>Product Found: " + name + "</h1>");
                out.println("<form method='post' action='Inventoryhandler'>");
                out.println("<input type='hidden' name='name' value='" + name + "'>");
                out.println("Select what to update:<br>");
                out.println("<input type='radio' name='updateOption' value='price' >Update Price<br>");
                out.println("<input type='radio' name='updateOption' value='quantity'>Update Quantity<br>");
                out.println("<br>");
                out.println("<input type='submit' value='Proceed'>");
                out.println("</form>");
            	} 
			
			else {
                out.println("<p>The product you want to update is not available in stock.</p><br><br>");
                out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");   
	  
			}

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

    finally{
   out.println("</body></html>");
   out.close();
   }
	}
}
