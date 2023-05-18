package com.github.murillenda.repository.memoria;

import com.github.murillenda.entity.Venda;
import com.github.murillenda.repository.VendaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoriaVendaRepository implements VendaRepository {

    private static long proximoId = 1L;
    private static final List<Venda> vendas = new ArrayList<>();

    @Override
    public Venda adicionar(Venda venda) {
        var novaVenda = new Venda(proximoId++, venda.getNomeCliente(), venda.getValorTotal(), venda.getDataPagamento());
        vendas.add(novaVenda);
        return novaVenda;
    }

    @Override
    public List<Venda> consultar() {
        return Collections.unmodifiableList(vendas);
    }
}
