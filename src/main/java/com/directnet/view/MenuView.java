package com.directnet.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Gleydson
 */
public class MenuView extends BaseView {
    private JButton btnProrata = new JButton("Pro rata");
    private JButton btnMulta = new JButton("Multa Rescisória");
    private JButton btnSair = new JButton("Sair");

    public MenuView() {
        super("Menu"); // Configura título, ícone, tamanho, fundo e centralização automaticamente

        setLayout(new BorderLayout());
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título maior específico para o Menu
        JLabel lblTitulo = criarHeader();
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel central para os botões
        JPanel painelBotoes = new JPanel(new GridBagLayout());
        painelBotoes.setBackground(Color.WHITE);
        
        JPanel inner = new JPanel(new GridLayout(3, 1, 10, 25));
        inner.setBackground(Color.WHITE);
        
        estilizarBotao(btnProrata);
        estilizarBotao(btnMulta);
        estilizarBotao(btnSair);

        inner.add(btnProrata);
        inner.add(btnMulta);
        inner.add(btnSair);
        
        painelBotoes.add(inner);
        add(painelBotoes, BorderLayout.CENTER);
    }

    private void estilizarBotao(JButton btn) {
        btn.setPreferredSize(new Dimension(280, 60));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(204, 0, 0));
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setBorder(BorderFactory.createLineBorder(new Color(204, 0, 0), 2));
    }

    public JButton getBtnProrata() { return btnProrata; }
    public JButton getBtnMulta() { return btnMulta; }
    public JButton getBtnSair() { return btnSair; }
}
