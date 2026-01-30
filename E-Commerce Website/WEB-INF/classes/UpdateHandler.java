import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class UpdateHandler extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession sess = req.getSession(false);
		
		if( sess == null || !"admin".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not admin, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
       
        String name = req.getParameter("name");
        String updateOption = req.getParameter("updateOption");
        
        out.println("<html>");
        out.println("<head><title>Update " + updateOption + "</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");

         if ("category".equals(updateOption)) {
            out.println("<h1>Update Category for: " + name + "</h1>");
            out.println("<form method='post' action='Updatecategory'>");
            out.println("<input type='hidden' name='name' value='" + name + "'>");
            out.println("<label for='category'>New Category:</label>");
            out.println("<input type='text' name='category' placeholder='Enter updated category' required>");
            out.println("<br><br>");
            out.println("<input type='submit' value='Update Category'>");
            out.println("</form>");
        }
		
		else if ("description".equals(updateOption)) {
            out.println("<h1>Update Description for: " + name + "</h1>");
            out.println("<form method='post' action='Updatedescription'>");
            out.println("<input type='hidden' name='name' value='" + name + "'>");
            out.println("<label for='description'>New Description:</label>");
			out.println("<textarea type='text' name='description' placeholder='Enter updated description' rows = '10' cols = '50' required></textarea>");
            out.println("<br><br>"); 
            out.println("<input type='submit' value='Update Description'>");
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
