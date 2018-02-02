package main.java.pl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/params")
public class ParamsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter respWriter = resp.getWriter();
        respWriter.write("<html><body>");
        for(Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
            respWriter.write("<p>" + entry.getKey() + ":" + Arrays.toString(entry.getValue()));
        }
        respWriter.write("</html></body>");

    }
}
