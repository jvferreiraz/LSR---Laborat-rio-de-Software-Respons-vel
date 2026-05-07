package servlet;

import java.io.IOException;

import dao.ItemDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item;

@WebServlet("/cadastrar")
public class CadastrarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            Item item = new Item();

            item.setDescricao(req.getParameter("descricao"));
            item.setCategoria(req.getParameter("categoria"));
            item.setLocalEncontro(req.getParameter("local"));
            item.setDataEncontro(req.getParameter("data"));
            item.setObservacao(req.getParameter("observacao"));

            ItemDAO dao = new ItemDAO();
            dao.inserir(item);

            resp.sendRedirect("itens");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}