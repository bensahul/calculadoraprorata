/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.directnet.controller;

import com.directnet.model.Prorata;
import com.directnet.view.ProrataView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gleydson
 */
public class ProrataController {
    private ProrataView view;
    private Prorata model;
    private DecimalFormat df = new DecimalFormat("0.00");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ProrataController() {
        this.view = new ProrataView();
        this.model = new Prorata();
        
        // Associar Botões
        view.getBtnCalcular().addActionListener(e -> calcular());
        view.getBtnLimpar().addActionListener(e -> view.limparTudo());
        view.getBtnVoltar().addActionListener(e -> voltar());
        view.getBtnCopiar().addActionListener(e -> view.copiarReferencia());

        view.setVisible(true);
    }

    private void calcular() {
        double valorPlano = view.getValorPlano();
        double descontoTotal = view.getValorDesconto();
        Date inicio = view.getDataIni();
        Date fim = view.getDataFim();

        // 1. Calcular Dias
        long dias = model.calcularDias(inicio, fim);
        
        // 2. Calcular Valores Proporcionais (Base 30 dias)
        double valorProp = (valorPlano / 30.0) * dias;
        double descProp = (descontoTotal / 30.0) * dias;
        double totalPagar = valorProp - descProp;

        // 3. Gerar Texto de Referência
        String ref = "Referente ao Acesso a Internet via Fibra de " 
                   + sdf.format(inicio) + " a " + sdf.format(fim);

        // 4. Atualizar View
        view.setResultados(
            String.valueOf(dias),
            df.format(valorProp),
            df.format(descProp),
            df.format(totalPagar),
            ref
        );
    }

    private void voltar() {
        view.dispose();
        // Aqui chamamos o Menu novamente
        new MenuController(); 
    }
}
