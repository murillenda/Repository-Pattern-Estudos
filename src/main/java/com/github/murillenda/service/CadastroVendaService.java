package com.github.murillenda.service;

import com.github.murillenda.entity.Venda;
import com.github.murillenda.repository.VendaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CadastroVendaService {

    private final VendaRepository vendaRepository;

    public CadastroVendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public Venda cadastrar(String nomeCliente, BigDecimal valorTotal, LocalDate dataPagamento) {
        if (valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegocioException("Valor total deve ser maior que 0");
        }
        if (dataPagamento.isAfter(LocalDate.now())) {
            throw new NegocioException("Data do pagamento não pode ser uma data futura");
        }

        return vendaRepository.adicionar(new Venda(nomeCliente, valorTotal, dataPagamento));
    }

}
