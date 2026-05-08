package Servlet;

import java.io.IOException;
import java.time.LocalDate;

import DAO.ItemDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Item;

@WebServlet("/cadastrar")
public class CadastrarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Item item = new Item();

            item.setDescricao(req.getParameter("descricao"));
            item.setObservacao(req.getParameter("observacao"));
            
            String dataStr = req.getParameter("data");
            if(dataStr != null && !dataStr.isEmpty()) {
                item.setDataEncontro(LocalDate.parse(dataStr));
            }
            
            int categoriaId = Integer.parseInt(req.getParameter("categoria"));
            item.setCategoriaId(categoriaId);
            
            int localId = Integer.parseInt(req.getParameter("local"));
            item.setLocalEncontroId(localId);
            
            item.setStatus("encontrado");
            item.setStatusItemId(2); // Status: Encontrado
            
            ItemDAO dao = new ItemDAO();
            dao.inserir(item);

            resp.sendRedirect("itens");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("cadastro.jsp?erro=true");
        }
    }
}
