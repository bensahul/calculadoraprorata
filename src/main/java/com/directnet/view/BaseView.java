package com.directnet.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Gleydson
 */
public abstract class BaseView extends JFrame {

    public BaseView(String subtitulo) {
        setTitle("DIRECTNET - " + subtitulo);
        setSize(500, 550);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Carrega o ícone uma única vez para todas as filhas
        carregarIcone();
        
        // Centraliza a janela
        setLocationRelativeTo(null);
    }

    private void carregarIcone() {
        try {
            java.net.URL url = getClass().getResource("/images/logo.png");
            if (url != null) {
                setIconImage(new ImageIcon(url).getImage());
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar ícone: " + e.getMessage());
        }
    }

    // Método utilitário para criar o cabeçalho padrão DIRECTNET
    protected JLabel criarHeader() {
        JLabel lblHeader = new JLabel("DIRECTNET", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeader.setForeground(new Color(204, 0, 0));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        return lblHeader;
    }
}
