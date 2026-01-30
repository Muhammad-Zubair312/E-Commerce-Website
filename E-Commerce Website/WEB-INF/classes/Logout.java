import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Logout extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session == null) {
            session.invalidate();
        }
		else {
            out.println("<h1>No active session found!</h1>");
        }
    response.sendRedirect("Login.html"); 
 }
}