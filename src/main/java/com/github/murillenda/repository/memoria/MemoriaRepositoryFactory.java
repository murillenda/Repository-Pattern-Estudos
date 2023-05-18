package com.github.murillenda.repository.memoria;

import com.github.murillenda.repository.RepositoryFactory;
import com.github.murillenda.repository.VendaRepository;

public class MemoriaRepositoryFactory implements RepositoryFactory {

    @Override
    public VendaRepository criarVendaRepository() {
        return new MemoriaVendaRepository();
    }

    @Override
    public void close() {}

}
