package com.github.murillenda.repository;


import com.github.murillenda.repository.memoria.MemoriaRepositoryFactory;
import com.github.murillenda.repository.mysql.MySQLRepositoryFactory;

import java.io.IOException;
import java.util.Properties;

public interface RepositoryFactory extends AutoCloseable {


    static RepositoryFactory obterInstancia() {
        // Classe que representa as propriedades
        var properties = new Properties();
        try (var is = RepositoryFactory.class
                .getResourceAsStream("/persistencia.properties")) {

            // Lê tudo dentro do inputStream e carrega para o properties
            properties.load(is);
        } catch (IOException e) {
            throw new PersistenciaException("Erro carregando configurarações", e);
        }

        if ("mysql".equals(properties.getProperty("repositorio"))) {
            return new MySQLRepositoryFactory(properties);
        } else if ("memoria".equals(properties.getProperty("repositorio"))) {
            return new MemoriaRepositoryFactory();
        }

        throw new PersistenciaException("Implementação de repositório não existe");
    }

    VendaRepository criarVendaRepository();

    @Override
    void close();
}
