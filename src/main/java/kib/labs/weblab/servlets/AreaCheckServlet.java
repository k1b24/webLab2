//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kib.labs.weblab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import kib.labs.weblab.entities.ResultTableRow;
import kib.labs.weblab.maths.AreaChecker;
import kib.labs.weblab.validation.RequestValidator;

@WebServlet({"/area-check-servlet"})
public class AreaCheckServlet extends HttpServlet {
    private static final RequestValidator requestValidator = new RequestValidator();
    private static final AreaChecker areaChecker = new AreaChecker();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mapper.findAndRegisterModules();
    }

    public AreaCheckServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultTableRow responseInfo = this.processGetRequest(request);
        if (request.getSession().getId() != null && this.getServletContext().getAttribute(request.getSession().getId()) != null) {
            ((List)this.getServletContext().getAttribute(request.getSession().getId())).add(responseInfo);
        }
        String responseBody = mapper.writeValueAsString(responseInfo);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseBody);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List resultTableRowList;
        if (this.getServletContext().getAttribute(request.getSession().getId()) != null) {
            resultTableRowList = (List)this.getServletContext().getAttribute(request.getSession().getId());
        } else {
            resultTableRowList = null;
            this.getServletContext().setAttribute(request.getSession().getId(), new ArrayList());
        }
        String responseBody = mapper.writeValueAsString(resultTableRowList);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseBody);
        out.flush();
        out.close();
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        this.getServletContext().setAttribute(request.getSession().getId(), new ArrayList());
    }

    private ResultTableRow processGetRequest(HttpServletRequest request) {
        String x = request.getParameter("x_value");
        String y = request.getParameter("y_value");
        String r = request.getParameter("r_value");
        String timezoneShift = request.getParameter("timezone");
        if (requestValidator.validateRequest(x, y, r, timezoneShift)) {
            float xValue = Float.parseFloat(x);
            float yValue = Float.parseFloat(y);
            float rValue = Float.parseFloat(r);
            int timezoneShiftValue = timezoneShift == null ? 0 : Integer.parseInt(timezoneShift);
            ResultTableRow resultTableRow = new ResultTableRow();
            resultTableRow.setX(xValue);
            resultTableRow.setY(yValue);
            resultTableRow.setR(rValue);
            resultTableRow.setDate(Instant.now().minusSeconds((long)timezoneShiftValue * 60L));
            resultTableRow.setResult(areaChecker.checkIfPointInArea(xValue, yValue, rValue));
            return resultTableRow;
        } else {
            return null;
        }
    }

    private void saveResultTableRowToContext(ResultTableRow resultTableRow, String sessionId) {
    }

    public void destroy() {
    }
}
