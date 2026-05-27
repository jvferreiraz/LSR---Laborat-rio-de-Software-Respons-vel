package servlet;

import dao.ItemDAO;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Item item = new Item();
            item.setId(id);
            item.setTitulo(req.getParameter("titulo"));
            item.setTipo(req.getParameter("tipo"));
            item.setLocalizacao(req.getParameter("localizacao"));
            item.setDescricao(req.getParameter("descricao"));

            new ItemDAO().atualizar(item);

            resp.sendRedirect(req.getContextPath() + "/index.jsp#itens");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        }
    }
}