package com.directnet.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author Gleydson
 */
public class Prorata {
    public long calcularDias(Date inicio, Date fim) {
        LocalDate d1 = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate d2 = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(d1, d2);
    }

    public double calcularValorProporcional(double valor, long dias) {
        return (valor / 30.0) * dias;
    }
}
