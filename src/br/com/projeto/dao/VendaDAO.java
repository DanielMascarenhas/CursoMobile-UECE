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
import javax.swing.JOptionPane;
import br.com.projeto.dao.ClientesDAO;
import br.com.projeto.dao.ProdutosDAO;

import java.sql.ResultSet;

public class VendaDAO {

    private Connection connection;

    public VendaDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void Venda(Clientes cliente, Produtos produto, int quantidadeProduto) {
        try {
            //procurar produto selecionado
            String sql = "SELECT * FROM tb_produtos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreço(Double.parseDouble(rs.getString("preco")));
                produto.setQtd_estoque(Integer.parseInt(rs.getString("qtd_estoque")));
            } else {
                JOptionPane.showMessageDialog(null, "Produto não existe!");
            }

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO aki: " + ex);
        }

        //produto.setId(rs.getInt("id"));
        try {
            //procurar cliente selecionado
            String sql = "SELECT * FROM tb_clientes WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setCep(rs.getString("cep"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não existe!");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO procurando cliente: " + ex);

        }
         
        if (produto.getQtd_estoque() >= quantidadeProduto) {
            try {
                //int novoEstoque = produto.getQtd_estoque() - quantidadeProduto;
                String sql = "UPDATE tb_produtos SET descricao=?, preco=?, qtd_estoque=?"
                        + " where id=?";
                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, produto.getDescricao());
                stmt.setDouble(2, produto.getPreço());
                stmt.setInt(3, (produto.getQtd_estoque() - quantidadeProduto) );

                stmt.setInt(4, produto.getId());

                stmt.execute();
                stmt.close();

                JOptionPane.showMessageDialog(null, "Venda feita!");
                JOptionPane.showMessageDialog(null, "Preço total: " + String.format("%.2f", quantidadeProduto * produto.getPreço()));

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERRO: " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Quantidade requerida superior à quantidade em estoque!");
        }
         
    }

}
