package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.LocalEncontro;

public class LocalEncontroDAO {

    public List<LocalEncontro> listar() throws Exception {
        List<LocalEncontro> lista = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = Conexao.getConnection();
            String sql = "SELECT id_local_encontro, nome, endereco FROM local_encontro";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                LocalEncontro local = new LocalEncontro();
                local.setId(rs.getInt("id_local_encontro"));
                local.setNome(rs.getString("nome"));
                local.setEndereco(rs.getString("endereco"));
                lista.add(local);
            }
            
            rs.close();
            stmt.close();
        } finally {
            if(conn != null) conn.close();
        }
        
        return lista;
    }
}
