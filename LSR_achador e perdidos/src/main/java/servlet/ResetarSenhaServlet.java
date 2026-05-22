package servlet;

import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResetarSenhaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String token = req.getParameter("token");
        String novaSenha = req.getParameter("novaSenha");
        String confirmaSenha = req.getParameter("confirmaSenha");

        // Validações
        if (token == null || token.trim().isEmpty()) {
            req.setAttribute("erro", "Token inválido!");
            req.getRequestDispatcher("/resetar-senha.jsp?token=" + token).forward(req, resp);
            return;
        }

        if (novaSenha == null || novaSenha.trim().isEmpty() || novaSenha.length() < 6) {
            req.setAttribute("erro", "Senha deve ter no mínimo 6 caracteres!");
            req.getRequestDispatcher("/resetar-senha.jsp?token=" + token).forward(req, resp);
            return;
        }

        if (!novaSenha.equals(confirmaSenha)) {
            req.setAttribute("erro", "As senhas não correspondem!");
            req.getRequestDispatcher("/resetar-senha.jsp?token=" + token).forward(req, resp);
            return;
        }

        // Verifica se token é válido
        Usuario usuario = new UsuarioDAO().verificarTokenReset(token);

        if (usuario == null) {
            req.setAttribute("erro", "Link de recuperação expirado ou inválido! Solicite um novo.");
            req.getRequestDispatcher("/esqueceu-senha.jsp").forward(req, resp);
            return;
        }

        // Atualiza a senha
        boolean atualizado = new UsuarioDAO().atualizarSenha(token, novaSenha);

        if (atualizado) {
            req.setAttribute("sucesso", "Senha alterada com sucesso! Faça login com sua nova senha.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("erro", "Erro ao atualizar senha. Tente novamente!");
            req.getRequestDispatcher("/resetar-senha.jsp?token=" + token).forward(req, resp);
        }
    }
}