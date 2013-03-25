package fr.sewatech.message.web;

import fr.sewatech.message.Main;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/*")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        Main main = new Main(outputStream);

        outputStream.println("<!DOCTYPE html><html><head><title>Hello</title></head>" +
                "<body style=\"font-size: xx-large\">");
        if  (req.getRequestURI().contains("log")) {
            main.hello(true);
        } else {
            main.hello(false);
        }
        outputStream.println("</body></html>");
    }
}
