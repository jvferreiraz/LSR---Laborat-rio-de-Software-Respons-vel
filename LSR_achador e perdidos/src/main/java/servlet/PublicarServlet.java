package servlet;

import dao.ItemDAO;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class PublicarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Item item = new Item();

        item.setTitulo(req.getParameter("titulo"));
        item.setTipo(req.getParameter("tipo"));
        item.setLocalizacao(req.getParameter("localizacao"));
        item.setDescricao(req.getParameter("descricao"));

        new ItemDAO().inserir(item);

        resp.sendRedirect(req.getContextPath() + "/index.jsp#itens");
    }
}