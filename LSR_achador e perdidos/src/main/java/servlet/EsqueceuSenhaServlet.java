package servlet;

import dao.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EsqueceuSenhaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("erro", "Email é obrigatório!");
            req.getRequestDispatcher("/esqueceu-senha.jsp").forward(req, resp);
            return;
        }

        boolean emailExiste = new UsuarioDAO().verificarEmailExistente(email);

        if (!emailExiste) {
            req.setAttribute("erro", "Este email não está registrado!");
            req.getRequestDispatcher("/esqueceu-senha.jsp").forward(req, resp);
            return;
        }

        boolean tokenGerado = new UsuarioDAO().gerarTokenReset(email);

        if (tokenGerado) {
            // AQUI você enviaria um email para o usuário
            // Por enquanto, vamos simular armazenando o email em sessão
            req.getSession().setAttribute("emailReset", email);
            req.setAttribute("sucesso", "Um link de recuperação foi enviado para seu email! Verifique sua caixa de entrada.");
            req.getRequestDispatcher("/esqueceu-senha.jsp").forward(req, resp);
        } else {
            req.setAttribute("erro", "Erro ao gerar token de reset. Tente novamente!");
            req.getRequestDispatcher("/esqueceu-senha.jsp").forward(req, resp);
        }
    }
}