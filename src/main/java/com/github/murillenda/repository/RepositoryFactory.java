package com.github.murillenda.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryFactory implements AutoCloseable {

    private final Connection conexao;

    public RepositoryFactory() {
        try {
            this.conexao = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/comercial", "root", "admin");
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }

    public VendaRepository criarVendaRepository() {
        return new VendaRepository(conexao);
    }

    @Override
    public void close() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }
}
