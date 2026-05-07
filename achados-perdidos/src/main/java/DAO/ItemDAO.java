package DAO;

import java.sql.*;
import java.util.*;

public class ItemDAO {

    public void inserir(Item item) throws Exception {
        Connection conn = Conexao.getConnection();

        String sql = "INSERT INTO item (descricao, categoria, local_encontro, data_encontro, status) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, item.getDescricao());
        stmt.setString(2, item.getCategoria());
        stmt.setString(3, item.getLocalEncontro());
        stmt.setString(4, item.getDataEncontro());
        stmt.setString(5, "ACHADO");

        stmt.execute();
    }

    public List<Item> listar() throws Exception {
        List<Item> lista = new ArrayList<>();

        Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM item");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setDescricao(rs.getString("descricao"));
            lista.add(item);
        }

        return lista;
    }
}