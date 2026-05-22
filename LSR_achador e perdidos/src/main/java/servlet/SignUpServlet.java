package servlet;

import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String confirmaSenha = req.getParameter("confirmaSenha");

        // Validações
        if (nome == null || nome.trim().isEmpty()) {
            req.setAttribute("erro", "Nome é obrigatório!");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("erro", "Email é obrigatório!");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            return;
        }

        if (senha == null || senha.trim().isEmpty() || senha.length() < 6) {
            req.setAttribute("erro", "Senha deve ter no mínimo 6 caracteres!");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            return;
        }

        if (!senha.equals(confirmaSenha)) {
            req.setAttribute("erro", "As senhas não correspondem!");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        boolean registrado = new UsuarioDAO().registrar(usuario);

        if (registrado) {
            req.setAttribute("sucesso", "Conta criada com sucesso! Faça login para continuar.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("erro", "Este email já está registrado!");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }
}