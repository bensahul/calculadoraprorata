package com.directnet.controller;

import com.directnet.model.Multa;
import com.directnet.view.MultaView;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author Gleydson
 */
public class MultaController {
    private MultaView view;
    private Multa model;
    private DecimalFormat df = new DecimalFormat("0.00");
    private DecimalFormat dfMeses = new DecimalFormat("0.0");

    public MultaController() {
        this.view = new MultaView();
        this.model = new Multa();

        // Ações dos botões
        view.getBtnCalcular().addActionListener(e -> calcular());
        view.getBtnLimpar().addActionListener(e -> view.limparTudo());
        view.getBtnVoltar().addActionListener(e -> voltar());

        // Faz a janela aparecer
        view.setVisible(true);
    }

    private void calcular() {
        Date dataInicio = view.getDataIni();
        
        // 1. Calcula meses restantes fracionados (ex: 3.8 meses)
        double mesesRestantes = model.calcularMesesRestantes(dataInicio);
        
        // 2. Define a base do cálculo conforme o Radio selecionado na View
        double valorBaseMensal;
        if (view.isPlano()) {
            // Regra: 50% do valor do plano mensal
            valorBaseMensal = view.getValPlano() * 0.50;
        } else {
            // Regra: Valor fixo da multa dividido por 12 meses
            valorBaseMensal = view.getValMulta() / 12.0;
        }

        // 3. Calcula o total final proporcional ao tempo restante
        double totalMulta = valorBaseMensal * mesesRestantes;

        // 4. Atualiza os visores da View com os resultados formatados
        view.setRes(
            dfMeses.format(mesesRestantes), 
            df.format(valorBaseMensal), 
            df.format(totalMulta)
        );
    }

    private void voltar() {
        view.dispose();
        new MenuController(); // Reabre o menu principal
    }
}
