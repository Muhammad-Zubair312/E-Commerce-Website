import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Updatedescription extends HttpServlet{
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException{ 
		
		res.setContentType("text/html");
		
		
		HttpSession sess = req.getSession(false);
		
		PrintWriter out = res.getWriter();
		
		if( sess == null || !"admin".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not admin, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
       
		
	    String name = req.getParameter("name");	
	    String description =  req.getParameter("description");
		
	out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
    
	try (ShopDAO obj = new ShopDAO()){
	
	int check = obj.updateDescription(name,description);
			
	   if( check == 1 ){
	     out.println("Description Updated!<br><br>");
		out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
		   }

	    else{
		out.println("Description not updated!<br><br>");
		out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
		   }
    
	}
		catch(Exception e){

      out.println(e);
	  
    }

	   finally{
   out.println("</body></html>");
   out.close();
   }
}
}