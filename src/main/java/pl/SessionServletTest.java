package main.java.pl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet("/sessiontest")
public class SessionServletTest extends HttpServlet {

    public static final String NUMBERS = "SessionSum";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter response = resp.getWriter();
        HttpSession session = req.getSession();

        response.write("<html><body>");

        Map<String, String[]> paramMap = req.getParameterMap();
        int requestSum = 0;

        for (Map.Entry<String, String[]> map : paramMap.entrySet()) {
            List<String> paramValues = Arrays.asList(map.getValue());
            for (String paramValue : paramValues) {
                int number = 0;
                try {
                    number = Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    // null
                }
                requestSum = requestSum + number;
            }
        }
        response.write("<p>requestSum: " + requestSum + "</p>");
        updateNumbersAttr(session, requestSum);

        response.write("<p>sessionSum: " + session.getAttribute(NUMBERS).toString() + "</p>");
        response.write("</body></html>");
    }

    private void updateNumbersAttr(HttpSession httpSession, int requestSum) {
        Object numbers = httpSession.getAttribute(NUMBERS);
        int newNumber = 0;
        if (numbers != null) {
            newNumber = (Integer)numbers + requestSum;
        } else {
            newNumber = requestSum;
        }
        httpSession.setAttribute(NUMBERS, newNumber);
    }
}
