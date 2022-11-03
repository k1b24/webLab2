package kib.labs.weblab.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(value = "/area-check-servlet")
public class AreaCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (request.getDispatcherType().equals(DispatcherType.REQUEST)) {
            PrintWriter writer = res.getWriter();
            writer.println("Direct access to AreaCheck forbidden!");
            res.setStatus(403);
            writer.close();
            return;
        }
        chain.doFilter(request, response);
    }
}
