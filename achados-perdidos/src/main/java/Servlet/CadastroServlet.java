package Servlet;

import java.io.IOException;
import java.util.List;

import DAO.CategoriaDAO;
import DAO.LocalEncontroDAO;
import Model.Categoria;
import Model.LocalEncontro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            LocalEncontroDAO localDAO = new LocalEncontroDAO();
            
            List<Categoria> categorias = categoriaDAO.listar();
            List<LocalEncontro> locais = localDAO.listar();

            req.setAttribute("categorias", categorias);
            req.setAttribute("locais", locais);
            
            req.getRequestDispatcher("cadastro.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar formulário");
        }
    }
}
