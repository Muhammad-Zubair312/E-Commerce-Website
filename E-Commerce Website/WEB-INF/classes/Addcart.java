import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Addcart extends HttpServlet{
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException{ 
		
		res.setContentType("text/html");
		
		HttpSession sess = req.getSession(false);
		
		PrintWriter out = res.getWriter();
		
		if( sess == null || !"user".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not the user, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
        
		String name = req.getParameter("name");
		String product = req.getParameter("pname");
		int Quantity = Integer.parseInt(req.getParameter("quantity"));
		
	out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");


    try (ShopDAO obj = new ShopDAO()){

	int check = obj.Addcart(name,Quantity);
		
	if(check == 1){
	req.setAttribute("name", name);
	req.setAttribute("pname", product);
	req.setAttribute("quantity", Quantity);
	 RequestDispatcher dispatch = req.getRequestDispatcher("Cart");
	dispatch.forward(req , res);
	 
	}
	
	else if(check == -1){
	 out.println("The Product you want to buy is Out Of Stock<br><br>");
	 out.println("<a href='userinterface.html'>User Interface</a><br><br>");
	 }
	
	else{
	out.println("Insufficient Stock! we have only "+check+ " quantity available<br><br>");
	out.println("<a href = 'userinterface.html'>User Interface</a><br><br>");
	}
	
	}
	catch(Exception e){
	out.println("Error: " + e.getMessage());}
   
   finally{
   out.println("</body></html>");
   out.close();
    }
 }
}