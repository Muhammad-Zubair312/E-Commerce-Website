import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Signup extends HttpServlet {
    
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        
        res.setContentType("text/html");
        
		PrintWriter out = res.getWriter();
        
        String Username = req.getParameter("username");
        
		String Password = req.getParameter("password");
        
		HttpSession sess = req.getSession(true);
		
		String currentUserType = (String) sess.getAttribute("usertype");
		
		if(currentUserType != null && currentUserType.equals("admin")){
		res.sendRedirect("Login.html");
		return;
		}
		
		sess.setAttribute("usertype", "user");
		
        out.println("<html>");
        out.println("<head><title>Response</title></head>");								
        out.println("<body bgcolor=\"#ffffff\">");
            
     try (ShopDAO obj = new ShopDAO()) {
     	
            	int check = obj.signup(Username, Password);
			
			if(check == 4){
                out.println("<h1>This User Already Has an Account!</h1><br><br>");
                out.println("<a href='Login.html'>Login</a>");
                return;}
			
			else if(check == 1){
                    out.println("<h1>Account Successfully Created!</h1><br><br>");
                    out.println("<a href='Login.html'>Login Your Account</a>");
                }
				
		   else {
                    out.println("<h1>Error While Creating Account.</h1><br><br>");
					out.println("<a href = 'Signup.html'>Signup</a>");
                }
			}
			catch(Exception e) {
                out.println("<h1>Error: " + e.getMessage() + "</h1>");
            } 
        
        out.println("</body></html>");
        out.close();
    }
}
