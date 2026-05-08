package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Categoria;

public class CategoriaDAO {

    public List<Categoria> listar() throws Exception {
        List<Categoria> lista = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = Conexao.getConnection();
            String sql = "SELECT id_categoria, nome FROM categorias";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome"));
                lista.add(categoria);
            }
            
            rs.close();
            stmt.close();
        } finally {
            if(conn != null) conn.close();
        }
        
        return lista;
    }
}
