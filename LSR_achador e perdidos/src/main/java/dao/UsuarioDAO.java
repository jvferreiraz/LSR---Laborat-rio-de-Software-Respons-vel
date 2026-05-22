package dao;

import br.com.util.ConnectionFactory;
import model.Usuario;
import java.sql.*;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UsuarioDAO {

    public boolean registrar(Usuario usuario) {
        String sqlVerificar = "SELECT * FROM usuarios WHERE email = ?";
        String sqlInserir = "INSERT INTO usuarios (nome, email, senha) VALUES (?,?,?)";

        try (Connection con = ConnectionFactory.getConnection()) {
            try (PreparedStatement psVerificar = con.prepareStatement(sqlVerificar)) {
                psVerificar.setString(1, usuario.getEmail());
                try (ResultSet rs = psVerificar.executeQuery()) {
                    if (rs.next()) {
                        return false;
                    }
                }
            }

            try (PreparedStatement psInserir = con.prepareStatement(sqlInserir)) {
                psInserir.setString(1, usuario.getNome());
                psInserir.setString(2, usuario.getEmail());
                psInserir.setString(3, usuario.getSenha());
                psInserir.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setDataCriacao(rs.getTimestamp("data_criacao"));
                    return usuario;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean verificarEmailExistente(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String gerarTokenReset(String email) {
        String sqlVerificar = "SELECT id FROM usuarios WHERE email = ?";
        String sqlAtualizar = "UPDATE usuarios SET token_reset = ?, data_expiracao_token = DATE_ADD(NOW(), INTERVAL 1 HOUR) WHERE email = ?";

        try (Connection con = ConnectionFactory.getConnection()) {
            // Verifica se email existe
            try (PreparedStatement psVerificar = con.prepareStatement(sqlVerificar)) {
                psVerificar.setString(1, email);
                try (ResultSet rs = psVerificar.executeQuery()) {
                    if (!rs.next()) {
                        return null; // Email não existe
                    }
                }
            }

            // Gera token e atualiza
            String token = UUID.randomUUID().toString();
            try (PreparedStatement psAtualizar = con.prepareStatement(sqlAtualizar)) {
                psAtualizar.setString(1, token);
                psAtualizar.setString(2, email);
                psAtualizar.executeUpdate();
                return token; // Retorna o token gerado
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario verificarTokenReset(String token) {
        String sql = "SELECT * FROM usuarios WHERE token_reset = ? AND data_expiracao_token > NOW()";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTokenReset(rs.getString("token_reset"));
                    return usuario;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean atualizarSenha(String token, String novaSenha) {
        String sqlVerificar = "SELECT id FROM usuarios WHERE token_reset = ? AND data_expiracao_token > NOW()";
        String sqlAtualizar = "UPDATE usuarios SET senha = ?, token_reset = NULL, data_expiracao_token = NULL WHERE token_reset = ?";

        try (Connection con = ConnectionFactory.getConnection()) {
            // Verifica se token é válido
            try (PreparedStatement psVerificar = con.prepareStatement(sqlVerificar)) {
                psVerificar.setString(1, token);
                try (ResultSet rs = psVerificar.executeQuery()) {
                    if (!rs.next()) {
                        return false; // Token inválido ou expirado
                    }
                }
            }

            // Atualiza senha
            try (PreparedStatement psAtualizar = con.prepareStatement(sqlAtualizar)) {
                psAtualizar.setString(1, novaSenha);
                psAtualizar.setString(2, token);
                psAtualizar.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}