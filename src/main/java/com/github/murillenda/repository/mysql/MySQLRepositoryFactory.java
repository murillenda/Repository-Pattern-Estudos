package com.github.murillenda.repository.mysql;

import com.github.murillenda.repository.PersistenciaException;
import com.github.murillenda.repository.RepositoryFactory;
import com.github.murillenda.repository.VendaRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLRepositoryFactory implements RepositoryFactory {

    private final Connection conexao;

    public MySQLRepositoryFactory(Properties properties) {
        try {
            this.conexao = DriverManager.getConnection(
                    properties.getProperty("conexao.url"),
                    properties.getProperty("conexao.user"),
                    properties.getProperty("conexao.password"));
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }

    @Override
    public VendaRepository criarVendaRepository() {
        return new MySQLVendaRepository(conexao);
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
