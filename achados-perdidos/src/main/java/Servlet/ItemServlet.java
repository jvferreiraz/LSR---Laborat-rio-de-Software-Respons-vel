package Servlet;

import java.io.IOException;
import java.util.List;

import DAO.ItemDAO;
import Model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/itens")
public class ItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            ItemDAO dao = new ItemDAO();
            List<Item> lista = dao.listar();

            req.setAttribute("lista", lista);
            req.getRequestDispatcher("Listar.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar itens");
        }
    }
}
