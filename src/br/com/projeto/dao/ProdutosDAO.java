/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

/**
 *
 * @author Daniel Mascarenhas
 */

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Produtos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import java.sql.ResultSet;

public class ProdutosDAO {

    private Connection connection;

    public ProdutosDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void cadastrarProdutos(Produtos pro){
        try {
            String sql =
                    "INSERT INTO tb_produtos(id,descricao,preco,qtd_estoque)"
                    + " values(?,?,?,?) ";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            
            stmt.setInt(1, pro.getId());
            stmt.setString(2, pro.getDescricao());
            stmt.setDouble(3, pro.getPreço());
            stmt.setInt(4, pro.getQtd_estoque());
            
 
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado! "+ ex);
        }
                 
    }

    public void excluirProdutos(Produtos pro) {
        try {

            String sql = "DELETE FROM tb_produtos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pro.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!!!");

        } catch (SQLException ex) {

        }

    }
    
    public List<Produtos> listarProdutos() {
        try {
            List<Produtos> lista = new ArrayList<>();
            String sql = "SELECT * FROM tb_produtos";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos pro = new Produtos();

                pro.setId(rs.getInt("id"));
                pro.setDescricao(rs.getString("descricao"));
                pro.setPreço(Double.parseDouble(rs.getString("preco")));
                pro.setQtd_estoque(Integer.parseInt(rs.getString("qtd_estoque")));

                lista.add(pro);
            }
            return lista;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            return null;
        }
    }

    public void alterarProduto(Produtos produtos) {
        try {

            String sql = "UPDATE tb_produtos SET descricao=?, preco=?, qtd_estoque=?"
                    + " where id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, produtos.getDescricao());
            stmt.setDouble(2, produtos.getPreço());
            stmt.setInt(3, produtos.getQtd_estoque());
            
            stmt.setInt(4, produtos.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!!!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Produtos> consultaPorNome(String descricao) {
        try {
            List<Produtos> lista = new ArrayList<>();            
            String sql = "SELECT * FROM tb_produtos WHERE descricao like ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, descricao);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos produtos = new Produtos();
                
                produtos.setId(rs.getInt("id"));
                produtos.setDescricao(rs.getString("descricao"));
                produtos.setPreço(rs.getDouble("preco"));
                produtos.setQtd_estoque(rs.getInt("qtd_estoque"));

                lista.add(produtos);
            }
            return lista;         
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ ex);
            return null;
        }
    }
    
}
