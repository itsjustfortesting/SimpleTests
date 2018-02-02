package main.java.pl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter response = resp.getWriter();

        response.write("<html><body>");

        for(Cookie cookie : req.getCookies()) {
            response.write("<p>" + cookie.getName() + ": " + cookie.getValue() + "</p>");
        }

        Cookie cookie = new Cookie("moje_ciacho","no elo");
        cookie.setMaxAge(100);
        resp.addCookie(cookie);

        response.write("</body></html>");
    }
}
