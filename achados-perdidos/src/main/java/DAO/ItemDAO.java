package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemDAO {

    public void inserir(Item item) throws Exception {

        Connection conn = Conexao.getConnection();

        String sql = "INSERT INTO item (descricao, categoria, local_encontro, data_encontro, status, observacao) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, item.getDescricao());
        stmt.setString(2, item.getCategoria());
        stmt.setString(3, item.getLocalEncontro());
        stmt.setString(4, item.getDataEncontro());
        stmt.setString(5, "ACHADO");
        stmt.setString(6, item.getObservacao());

        stmt.execute();
    }

    public List<Item> listar() throws Exception {

        List<Item> lista = new ArrayList<>();

        Connection conn = Conexao.getConnection();

        String sql = "SELECT * FROM item";

        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {

            Item item = new Item();

            item.setId(rs.getInt("id"));
            item.setDescricao(rs.getString("descricao"));
            item.setCategoria(rs.getString("categoria"));
            item.setStatus(rs.getString("status"));

            lista.add(item);
        }

        return lista;
    }
}