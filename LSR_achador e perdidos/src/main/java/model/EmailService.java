package model;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

    private String remetente;
    private String senha;
    private String host;
    private int porta;

    public EmailService() {
        // ✅ CONFIGURAÇÃO GMAIL
        this.host = "smtp.gmail.com";
        this.porta = 587;
        this.remetente = "escolateste596@gmail.com";  // ← ALTERE AQUI
        this.senha = "sqiiqwxfedpyuwxw";         // ← ALTERE AQUI
    }

    public boolean enviarEmailRecuperacao(String emailDestino, String token, String urlBase) {
        try {
            System.out.println("📧 Iniciando envio de email via Gmail...");
            System.out.println("   Remetente: " + remetente);
            System.out.println("   Destinatário: " + emailDestino);

            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", porta);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");
            // ← REMOVA: props.put("mail.smtp.socketFactory.port", porta);
            // ← REMOVA: props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            // ← REMOVA: props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remetente, senha);
                }
            });
            session.setDebug(false); // Mude para true se quiser ver logs detalhados

            System.out.println("✍️ Preparando mensagem...");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino));
            message.setSubject("FindGo - Link de Recuperação de Senha");

            String linkRecuperacao = urlBase + "/resetar-senha.jsp?token=" + token;

            String corpoEmail = "<html><body style=\"font-family: Arial, sans-serif; background-color: #f5f5f5;\">"
                    + "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; background-color: white; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);\">"
                    + "<div style=\"text-align: center; margin-bottom: 30px;\">"
                    + "<h2 style=\"color: #8B7355; margin: 0;\">🔐 Recuperação de Senha</h2>"
                    + "<p style=\"color: #666; margin: 5px 0 0 0;\">FindGo - Achador de Perdidos</p>"
                    + "</div>"
                    + "<p style=\"color: #333; font-size: 16px;\">Olá,</p>"
                    + "<p style=\"color: #555; line-height: 1.6;\">Recebemos uma solicitação para redefinir sua senha. Clique no botão abaixo para criar uma nova senha:</p>"
                    + "<div style=\"text-align: center; margin: 30px 0;\">"
                    + "<a href=\"" + linkRecuperacao + "\" style=\"background-color: #8B7355; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; display: inline-block; font-weight: bold; font-size: 16px;\">Redefinir Senha</a>"
                    + "</div>"
                    + "<p style=\"color: #666; font-size: 14px; word-break: break-all;\"><strong>Ou copie e cole este link:</strong><br/>" + linkRecuperacao + "</p>"
                    + "<div style=\"background-color: #f9f9f9; border-left: 4px solid #8B7355; padding: 15px; margin: 20px 0; border-radius: 3px;\">"
                    + "<p style=\"color: #666; margin: 0; font-size: 13px;\">⏰ <strong>Importante:</strong> Este link é válido por apenas <strong>1 hora</strong>.</p>"
                    + "<p style=\"color: #666; margin: 8px 0 0 0; font-size: 13px;\">Se você não solicitou esta recuperação, ignore este email.</p>"
                    + "</div>"
                    + "<hr style=\"border: none; border-top: 1px solid #ddd; margin: 30px 0;\">"
                    + "<p style=\"color: #999; font-size: 12px; text-align: center; margin: 0;\">© 2026 FindGo - Achador de Perdidos. Todos os direitos reservados.</p>"
                    + "</div></body></html>";

            message.setContent(corpoEmail, "text/html; charset=utf-8");

            System.out.println("📤 Enviando email...");
            Transport.send(message);

            System.out.println("✅ Email enviado com sucesso para: " + emailDestino);
            return true;

        } catch (AuthenticationFailedException e) {
            System.err.println("❌ ERRO: Email ou senha de app incorretos!");
            System.err.println("   Verifique: https://myaccount.google.com/apppasswords");
            e.printStackTrace();
            return false;

        } catch (MessagingException e) {
            System.err.println("❌ ERRO ao enviar: " + e.getMessage());
            e.printStackTrace();
            return false;

        } catch (Exception e) {
            System.err.println("❌ ERRO: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    // Adicione este método à classe EmailService existente:

    public boolean enviarEmailParaEscola(String nomeUsuario, String emailUsuario, String mensagem) {
        try {
            System.out.println("📧 Enviando email do usuário para a escola...");
            System.out.println("   Usuário: " + nomeUsuario);
            System.out.println("   Email do usuário: " + emailUsuario);

            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", porta);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remetente, senha);
                }
            });
            session.setDebug(false);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(remetente)); // Envia para o email da escola (remetente)
            message.setSubject("FindGo - Contato de Usuário: " + nomeUsuario);

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
                    + "<p style=\"color: #555; margin: 0; line-height: 1.6;\">" + (mensagem == null || mensagem.isEmpty() ? "Nenhuma mensagem adicional" : mensagem) + "</p>"
                    + "</div>"
                    + "<hr style=\"border: none; border-top: 1px solid #ddd; margin: 30px 0;\">"
                    + "<p style=\"color: #999; font-size: 12px; text-align: center; margin: 0;\">© 2026 FindGo - Achador de Perdidos. Email enviado automaticamente.</p>"
                    + "</div></body></html>";

            message.setContent(corpoEmail, "text/html; charset=utf-8");

            System.out.println("📤 Enviando email...");
            Transport.send(message);

            System.out.println("✅ Email enviado com sucesso para: " + remetente);
            return true;

        } catch (Exception e) {
            System.err.println("❌ ERRO ao enviar email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}