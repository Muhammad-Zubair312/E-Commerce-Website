import java.io.*;
import java.time.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class Cart extends HttpServlet{
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException{ 
		
		res.setContentType("text/html");
		
		HttpSession sess = req.getSession(false);
		
		PrintWriter out = res.getWriter();
		
		if( sess == null || !"user".equals(sess.getAttribute("usertype"))){
			out.println("<h2> You are not the user, Go Back <h2>");
			out.println("<a href = 'login.html'> Go to Login </a>");
			return;
		}
        
		String name = (String) req.getAttribute("name");
		String product = (String) req.getAttribute("pname");
		Integer quantity = (Integer) req.getAttribute("quantity");
		
		LocalDate currentdate = LocalDate.now();
        LocalDate deliverydate = currentdate.plusDays(4);
		String date = deliverydate.toString();
		
	int processid = 0;
	out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");

    try(ShopDAO obj = new ShopDAO()){

	int value = obj.cart(name);
	
    if ( value != -1) {
    
	int receive = value;

    
	if (receive != 0) {
        processid = receive ;
        processid++; 
    } 
		
	else {
        processid = 4573; 
    }
	}
	
	
	int check = obj.cart(name,product,quantity,processid,date);
	
	
	if( check == 1 ){
	out.println("<b>Order Done:</b><br>");
    out.println("Customer Name: "+ name + "<br>");
    out.println("Product: "+ product + "<br>");
    out.println("Quantity: "+ quantity + "<br>");
    out.println("<b>ProcessID: "+ processid + "</b><br>");
    out.println("<b>Delivery Date: "+ deliverydate + "</b><br>");
 	out.println("<a href='userinterface.html'>User Interface</a><br><br>");
	 
	}
	
	else{
	 out.println("Error while Processing Order<br><br>");
	 out.println("<a href='userinterface.html'>User Interface</a><br><br>");
	
	 }
	
    }
	
	catch(Exception e){
	  out.println("Error: "+e.getMessage());}
	
	finally{
	    out.println("</body></html>");
        out.close();
		}
 }
}	
    