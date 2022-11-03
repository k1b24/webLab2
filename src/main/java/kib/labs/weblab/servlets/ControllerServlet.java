//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kib.labs.weblab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/controller-servlet"})
public class ControllerServlet extends HttpServlet {
    public ControllerServlet() {
    }


    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (request.getMethod().equals("GET") && this.checkRequestForAreaCheckerRequest(request)) {
            request.getRequestDispatcher("area-check-servlet").forward(request, response);
        } else if (request.getMethod().equals("POST")) {
            request.getRequestDispatcher("area-check-servlet").forward(request, response);
        } else if (request.getMethod().equals("PUT")) {
            request.getRequestDispatcher("area-check-servlet").forward(request, response);
        }
    }

    private boolean checkRequestForAreaCheckerRequest(HttpServletRequest request) {
        return request.getParameter("x_value") != null && request.getParameter("y_value") != null && request.getParameter("r_value") != null;
    }
}
