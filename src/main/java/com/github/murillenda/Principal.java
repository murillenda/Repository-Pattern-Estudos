package com.github.murillenda;

import com.github.murillenda.entity.Venda;
import com.github.murillenda.repository.VendaRepository;
import com.github.murillenda.service.CadastroVendaService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Principal {
    public static void main(String[] args) throws SQLException {
        try (Connection conexao = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/comercial", "root", "admin")) {

            var vendaRepository = new VendaRepository(conexao);
            var cadastroVendaServico = new CadastroVendaService(vendaRepository);

            Venda vendaCadastrada = cadastroVendaServico.cadastrar("José da Silva",
                    new BigDecimal("12300.87"), LocalDate.parse("2023-04-19"));

            System.out.println("Venda cadastrada: " + vendaCadastrada);

            System.out.println("Listando todas as vendas:");
            var todasVendas = vendaRepository.consultar();

            todasVendas.forEach(System.out::println);
        }
    }
}
