package main.java.pl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/head")
public class HeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter response = resp.getWriter();

        response.write("<html><body>");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> values = req.getHeaders(headerName);
            while (values.hasMoreElements()) {
                String value = values.nextElement();
                response.write("<p>" + headerName + ": " + value + "</p>");
            }
        }

        resp.addHeader("my-custom-header", "val1");
        resp.addHeader("my-custom-header", "val2");
        resp.setIntHeader("my-custom-int-head", 123);

        response.write("</body></html>");
    }

}
