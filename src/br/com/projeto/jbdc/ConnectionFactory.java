/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jbdc;

/**
 *
 * @author Daniel Mascarenhas
 */


import java.sql.Connection; // para poder importar apenas com atalho no getConnection() baixar driver do mysql, ou fazer importação na mão
import java.sql.DriverManager;

public class ConnectionFactory {
    public Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BDVENDAS", "ProjetoDeAula", "123");

        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public Connection getConnnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
