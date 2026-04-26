package com.directnet.controller;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.directnet.view.*;


/**
 * @author Gleydson
 */
public class MenuController {
    private MenuView view;

    public MenuController() {
        view = new MenuView();
        view.getBtnProrata().addActionListener(e -> { new ProrataController(); view.dispose(); });
        view.getBtnMulta().addActionListener(e -> { /* new MultaController(); */ view.dispose(); });
        view.getBtnSair().addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(view, "Sair?", "Sair", JOptionPane.YES_NO_OPTION) == 0) System.exit(0);
        });
        view.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuController());
    }
}
