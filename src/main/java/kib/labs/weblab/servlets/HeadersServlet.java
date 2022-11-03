package kib.labs.weblab.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/headers")
public class HeadersServlet extends HttpServlet {

    private static final ObjectMapper mapper = new ObjectMapper();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(mapper.writeValueAsString(getServletContext().getAttribute("headers")));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
