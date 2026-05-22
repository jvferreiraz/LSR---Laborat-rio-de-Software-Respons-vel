package dao;

import br.com.util.ConnectionFactory;
import model.Usuario;
import java.sql.*;

public class UsuarioDAO {

    public boolean registrar(Usuario usuario) {
        String sqlVerificar = "SELECT * FROM usuarios WHERE email = ?";
        String sqlInserir = "INSERT INTO usuarios (nome, email, senha) VALUES (?,?,?)";

        try (Connection con = ConnectionFactory.getConnection()) {
            // Verifica se o email já existe
            try (PreparedStatement psVerificar = con.prepareStatement(sqlVerificar)) {
                psVerificar.setString(1, usuario.getEmail());
                try (ResultSet rs = psVerificar.executeQuery()) {
                    if (rs.next()) {
                        return false; // Email já existe
                    }
                }
            }

            // Insere o novo usuário
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
}