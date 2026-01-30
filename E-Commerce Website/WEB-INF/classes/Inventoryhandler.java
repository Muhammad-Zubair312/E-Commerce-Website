import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Inventoryhandler extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        res.setContentType("text/html");
        
		HttpSession sess = req.getSession(false);
		
		PrintWriter out = res.getWriter();

        String name = req.getParameter("name");
        String updateOption = req.getParameter("updateOption");

        if( sess == null || !"admin".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not admin, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
       
        
		out.println("<html>");
        out.println("<head><title>Update " + updateOption + "</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");

        if ("price".equals(updateOption)) {
            out.println("<h1>Update Price for: " + name + "</h1>");
            out.println("<form method='post' action='Updateprice'>");
            out.println("<input type='hidden' name='name' value='" + name + "'>");
            out.println("<label for='price'>New Price:</label>");
            out.println("<input type='number' min = '1' name='price' placeholder='Enter updated price' required>");
            out.println("<br><br>");
            out.println("<input type='submit' value='Update Price'>");
            out.println("</form>");
        }
		
		else if ("quantity".equals(updateOption)) {
            out.println("<h1>Update Quantity for: " + name + "</h1>");
            out.println("<form method='post' action='Updatequantity'>");
            out.println("<input type='hidden' name='name' value='" + name + "'>");
            out.println("<label for='quantity'>New Quantity:</label>");
            out.println("<input type='number' min = '1' name='quantity' placeholder='Enter updated quantity' required>");
            out.println("<br><br>");
            out.println("<input type='submit' value='Update Quantity'>");
            out.println("</form>");
        }
		
		else {
            out.println("<p>Invalid option selected!</p><br><br>");
            out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
		}

        out.println("</body></html>");
       out.close();
	}
}
