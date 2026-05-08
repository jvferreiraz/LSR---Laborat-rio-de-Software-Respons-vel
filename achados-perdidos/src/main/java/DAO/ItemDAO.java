package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Model.Item;

public class ItemDAO {

    public void inserir(Item item) throws Exception {
        Connection conn = null;
        
        try {
            conn = Conexao.getConnection();
            
            String sql = "INSERT INTO item (descricao, data_encontro, observacao, status, " +
                        "categorias_id_categoria, local_encontro_id_local_encontro, status_item_id_status_item) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, item.getDescricao());
            stmt.setObject(2, item.getDataEncontro());
            stmt.setString(3, item.getObservacao());
            stmt.setString(4, item.getStatus());
            stmt.setInt(5, item.getCategoriaId());
            stmt.setInt(6, item.getLocalEncontroId());
            stmt.setInt(7, item.getStatusItemId());
            
            stmt.execute();
            stmt.close();
        } finally {
            if(conn != null) conn.close();
        }
    }

    public List<Item> listar() throws Exception {
        List<Item> lista = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = Conexao.getConnection();
            
            String sql = "SELECT i.id_item, i.descricao, i.data_encontro, i.observacao, i.status, " +
                        "c.nome as categoria_nome, l.nome as local_nome, s.nome as status_nome " +
                        "FROM item i " +
                        "LEFT JOIN categorias c ON i.categorias_id_categoria = c.id_categoria " +
                        "LEFT JOIN local_encontro l ON i.local_encontro_id_local_encontro = l.id_local_encontro " +
                        "LEFT JOIN status_item s ON i.status_item_id_status_item = s.id_status_item " +
                        "ORDER BY i.data_encontro DESC";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                Item item = new Item();
                
                item.setId(rs.getInt("id_item"));
                item.setDescricao(rs.getString("descricao"));
                
                java.sql.Date sqlDate = rs.getDate("data_encontro");
                if(sqlDate != null) {
                    item.setDataEncontro(sqlDate.toLocalDate());
                }
                
                item.setObservacao(rs.getString("observacao"));
                item.setStatus(rs.getString("status"));
                item.setCategoriaNome(rs.getString("categoria_nome"));
                item.setLocalEncontroNome(rs.getString("local_nome"));
                item.setStatusItemNome(rs.getString("status_nome"));
                
                lista.add(item);
            }
            
            rs.close();
            stmt.close();
        } finally {
            if(conn != null) conn.close();
        }
        
        return lista;
    }
    
    public Item buscarPorId(int id) throws Exception {
        Item item = null;
        Connection conn = null;
        
        try {
            conn = Conexao.getConnection();
            
            String sql = "SELECT i.id_item, i.descricao, i.data_encontro, i.observacao, i.status, " +
                        "i.categorias_id_categoria, i.local_encontro_id_local_encontro, i.status_item_id_status_item, " +
                        "c.nome as categoria_nome, l.nome as local_nome, s.nome as status_nome " +
                        "FROM item i " +
                        "LEFT JOIN categorias c ON i.categorias_id_categoria = c.id_categoria " +
                        "LEFT JOIN local_encontro l ON i.local_encontro_id_local_encontro = l.id_local_encontro " +
                        "LEFT JOIN status_item s ON i.status_item_id_status_item = s.id_status_item " +
                        "WHERE i.id_item = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                item = new Item();
                item.setId(rs.getInt("id_item"));
                item.setDescricao(rs.getString("descricao"));
                
                java.sql.Date sqlDate = rs.getDate("data_encontro");
                if(sqlDate != null) {
                    item.setDataEncontro(sqlDate.toLocalDate());
                }
                
                item.setObservacao(rs.getString("observacao"));
                item.setStatus(rs.getString("status"));
                item.setCategoriaId(rs.getInt("categorias_id_categoria"));
                item.setLocalEncontroId(rs.getInt("local_encontro_id_local_encontro"));
                item.setStatusItemId(rs.getInt("status_item_id_status_item"));
                item.setCategoriaNome(rs.getString("categoria_nome"));
                item.setLocalEncontroNome(rs.getString("local_nome"));
                item.setStatusItemNome(rs.getString("status_nome"));
            }
            
            rs.close();
            stmt.close();
        } finally {
            if(conn != null) conn.close();
        }
        
        return item;
    }
    
    public void atualizar(Item item) throws Exception {
        Connection conn = null;
        
        try {
            conn = Conexao.getConnection();
            
            String sql = "UPDATE item SET descricao = ?, data_encontro = ?, observacao = ?, status = ?, " +
                        "categorias_id_categoria = ?, local_encontro_id_local_encontro = ?, status_item_id_status_item = ? " +
                        "WHERE id_item = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, item.getDescricao());
            stmt.setObject(2, item.getDataEncontro());
            stmt.setString(3, item.getObservacao());
            stmt.setString(4, item.getStatus());
            stmt.setInt(5, item.getCategoriaId());
            stmt.setInt(6, item.getLocalEncontroId());
            stmt.setInt(7, item.getStatusItemId());
            stmt.setInt(8, item.getId());
            
            stmt.execute();
            stmt.close();
        } finally {
            if(conn != null) conn.close();
        }
    }
    
    public void deletar(int id) throws Exception {
        Connection conn = null;
        
        try {
            conn = Conexao.getConnection();
            
            String sql = "DELETE FROM item WHERE id_item = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } finally {
            if(conn != null) conn.close();
        }
    }
}
