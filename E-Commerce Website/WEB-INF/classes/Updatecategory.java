import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Updatecategory extends HttpServlet{
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException{ 
		
		res.setContentType("text/html");
	    PrintWriter out = res.getWriter();
		HttpSession sess = req.getSession(false);
		if( sess == null || !"admin".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not admin, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
       
	    String name = req.getParameter("name");	
	    String category =  req.getParameter("category");
		
		
	out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");
    
	try (ShopDAO obj = new ShopDAO()){

			int check = obj.updateCategory(name,category);
		
	   if( check == 1 ){
	           out.println("Category Updated!<br><br>");
		       out.println("<a href='admininterface.html'>Admin Interface</a><br><br>");
	 }

	    else{
		out.println("Category not updated!<br><br>");
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