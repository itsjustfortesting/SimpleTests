package main.java.pl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    public static final String VISIT_COUNTER = "visitCounter";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter response = resp.getWriter();

        response.write("<html><body>");

        HttpSession session = req.getSession();
        Enumeration<String> attrNames = session.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String attrName = attrNames.nextElement();
            response.write("<p>" + attrName + ": " + session.getAttribute(attrName));
        }

        response.write("</body></html>");
        increaseVisitCounter(session);
    }

    private void increaseVisitCounter(HttpSession session) {
        Object counter = session.getAttribute(VISIT_COUNTER);
        int numberOfVisits;
        if (counter != null) {
            numberOfVisits = (Integer) counter + 1;
        } else {
            numberOfVisits = 1;
        }
        session.setAttribute(VISIT_COUNTER, numberOfVisits);
    }
}
