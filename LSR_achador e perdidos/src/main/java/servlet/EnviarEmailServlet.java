package servlet;

import model.EmailService;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/enviar-email-escola")
public class EnviarEmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Recupera o usuário logado da sessão
        Usuario usuarioLogado = (Usuario) req.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // Dados do usuário
        String nomeUsuario = usuarioLogado.getNome();
        String emailUsuario = usuarioLogado.getEmail();

        // Recebe a mensagem (opcional)
        String mensagem = req.getParameter("mensagem");
        if (mensagem == null) {
            mensagem = "";
        }

        // Email da escola
        String emailEscola = "escolateste596@gmail.com";

        try {
            // Cria o serviço de email
            EmailService emailService = new EmailService();

            // Cria um método customizado para enviar email para a escola
            boolean emailEnviado = enviarEmailParaEscola(nomeUsuario, emailUsuario, emailEscola, mensagem);

            if (emailEnviado) {
                req.setAttribute("sucesso", "Email enviado com sucesso para a escola!");
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                req.setAttribute("erro", "Erro ao enviar email. Tente novamente mais tarde!");
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro interno do servidor!");
            try {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Método reutilizável que usa a configuração do EmailService
     * para enviar email do usuário para a escola
     */
    private boolean enviarEmailParaEscola(String nomeUsuario,
                                          String emailUsuario, String emailEscola, String mensagem) {
        try {
            System.out.println("📧 Enviando email do usuário para a escola...");
            System.out.println("   Remetente: " + emailUsuario);
            System.out.println("   Destinatário: " + emailEscola);

            // Configuração do servidor SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");

            // Cria a sessão com autenticação
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("escolateste596@gmail.com", "sqiiqwxfedpyuwxw");
                }
            });

            // Cria a mensagem
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("escolateste596@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailEscola));
            message.setSubject("FindGo - Contato de usuário: " + nomeUsuario);

            // Corpo do email em HTML
            String corpoEmail = "<html><body style=\"font-family: Arial, sans-serif; background-color: #f5f5f5;\">"
                    + "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; background-color: white; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);\">"
                    + "<div style=\"text-align: center; margin-bottom: 30px;\">"
                    + "<h2 style=\"color: #8B7355; margin: 0;\">📬 Novo Contato via FindGo</h2>"
                    + "</div>"
                    + "<p style=\"color: #333; font-size: 16px;\"><strong>Informações do usuário:</strong></p>"
                    + "<ul style=\"color: #555; font-size: 15px;\">"
                    + "<li><strong>Nome:</strong> " + nomeUsuario + "</li>"
                    + "<li><strong>Email:</strong> " + emailUsuario + "</li>"
                    + "</ul>"
                    + "<hr style=\"border: none; border-top: 1px solid #ddd; margin: 20px 0;\">"
                    + "<p style=\"color: #333; font-size: 16px;\"><strong>Mensagem:</strong></p>"
                    + "<div style=\"background-color: #f9f9f9; padding: 15px; border-radius: 8px; border-left: 4px solid #8B7355;\">"
                    + "<p style=\"color: #555; margin: 0; line-height: 1.6;\">"
                    + (mensagem.isEmpty() ? "<em>Nenhuma mensagem adicional</em>" : mensagem)
                    + "</p>"
                    + "</div>"
                    + "<hr style=\"border: none; border-top: 1px solid #ddd; margin: 30px 0;\">"
                    + "<p style=\"color: #999; font-size: 12px; text-align: center; margin: 0;\">© 2026 FindGo - Achador de Perdidos. Email enviado automaticamente.</p>"
                    + "</div></body></html>";

            message.setContent(corpoEmail, "text/html; charset=utf-8");

            System.out.println("📤 Enviando email...");
            Transport.send(message);

            System.out.println("✅ Email enviado com sucesso para: " + emailEscola);
            return true;

        } catch (Exception e) {
            System.err.println("❌ ERRO ao enviar email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}