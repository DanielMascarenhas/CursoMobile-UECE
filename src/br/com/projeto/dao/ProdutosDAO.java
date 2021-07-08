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
                    "INSERT INTO tb_produtos(id,descricao,preco,qtd_estoque,for_id)"
                    + " values(?,?,?,?,?) ";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            
            //stmt.setInt(1, pro.getId());
            stmt.setString(2, pro.getDescricao());
            stmt.setInt(3, pro.getPreço());
            stmt.setInt(4, pro.getQtd_estoque());
            stmt.setInt(5, CodigoCliente);
            
 
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado! "+ ex);
        }
                 
    }
}
