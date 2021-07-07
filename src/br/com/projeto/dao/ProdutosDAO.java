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
import br.com.projeto.model.Produtos;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    private Connection connection;

    public ProdutosDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void cadastrarProdutos(Produtos pro, int CodigoCliente){
        try {
            String sql =
                    "INSERT INTO tb_produtos(id,descricao,preço,qtd_estoque,CodigoCliente)"
                    + " values(?,?,?,?,?) ";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            
            stmt.setInt(0, pro.getId());
            stmt.setString(1, pro.getDescricao());
            stmt.setInt(2, pro.getPreço());
            stmt.setInt(3, pro.getQtd_estoque());
            stmt.setInt(4, CodigoCliente);
            
 
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado! "+ ex);
        }
                 
    }
}
