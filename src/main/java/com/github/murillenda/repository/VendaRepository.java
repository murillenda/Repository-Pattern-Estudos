package com.github.murillenda.repository;

import com.github.murillenda.entity.Venda;

import java.util.List;

public interface VendaRepository {
    Venda adicionar(Venda venda);

    List<Venda> consultar();
}
