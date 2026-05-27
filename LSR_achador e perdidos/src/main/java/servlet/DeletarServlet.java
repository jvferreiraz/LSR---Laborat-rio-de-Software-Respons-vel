package servlet;

import dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            new ItemDAO().deletar(id);
            resp.sendRedirect(req.getContextPath() + "/index.jsp#itens");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        }
    }
}