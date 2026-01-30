import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Login extends HttpServlet {
    
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        
        res.setContentType("text/html");
        
		HttpSession sess = req.getSession(true);
        PrintWriter out = res.getWriter();
        
        String Username = req.getParameter("username");
        String Password = req.getParameter("password");
        
        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        
        try ( ShopDAO obj = new ShopDAO() ){
 
			ResultSet rs = obj.login(Username,Password);
			
            if (rs.next()) {    
				out.println("<h1>Successfully LogIn</h1><br><br>");
				String user = rs.getString("Usertype");
				sess.setAttribute("usertype", user);
			
			if(user.equals("admin")){
		       res.sendRedirect("admininterface.html");
		       }
			
			if( user.equals("user")){
		       res.sendRedirect("userinterface.html");
		       return;}
			   
			}
				
			else{
                out.println("<h1>Unauthorized User: Login Failed</h1><br><br>");
                out.println("<a href='Login.html'>Login</a><br><br>");
			                
			}

        } 
		catch (Exception e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        }
        
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
