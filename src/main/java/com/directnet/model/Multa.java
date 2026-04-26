package com.directnet.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author Gleydson
 */
public class Multa {
    public double calcularMesesRestantes(Date inicioFidelidade) {
        LocalDate inicio = inicioFidelidade.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoje = LocalDate.now();
        LocalDate fimFidelidade = inicio.plusMonths(12);

        if (hoje.isAfter(fimFidelidade)) return 0;

        long diasRestantes = ChronoUnit.DAYS.between(hoje, fimFidelidade);
        return diasRestantes / 30.44; // Média de dias por mês para fracionamento
    }
}
