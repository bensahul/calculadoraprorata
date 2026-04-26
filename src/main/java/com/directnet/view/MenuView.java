package com.directnet.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Gleydson
 */
public class MenuView extends JFrame {
    private JButton btnProrata = new JButton("Pro rata");
    private JButton btnMulta = new JButton("Multa Rescisória");
    private JButton btnSair = new JButton("Sair");

    public MenuView() {
        carregarIcone();
        setTitle("DIRECTNET - Menu");
        setSize(500, 550);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        // Margem externa para o menu não ficar colado nas bordas
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("DIRECTNET", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitulo.setForeground(new Color(204, 0, 0));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(lblTitulo, BorderLayout.NORTH);

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

        setLocationRelativeTo(null);
    }

    private void carregarIcone() {
        try {
            java.net.URL url = getClass().getResource("/images/logo.png");
            if (url != null) {
                Image icone = Toolkit.getDefaultToolkit().getImage(url);
                this.setIconImage(icone);
            }
        } catch (Exception e) {
            System.out.println("Não foi possível carregar o ícone.");
        }
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
