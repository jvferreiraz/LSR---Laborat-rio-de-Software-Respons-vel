package servlet;

import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Usuario usuario = new UsuarioDAO().autenticar(email, senha);

        if (usuario != null) {
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogado", usuario);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("erro", "Email ou senha inválidos!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}