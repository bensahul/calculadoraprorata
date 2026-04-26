package com.directnet.view;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Date;

/**
 * @author Gleydson
 */
public class MultaView extends JFrame {
    private JRadioButton rbMulta = new JRadioButton("Multa Fixa"), rbPlano = new JRadioButton("50% Plano");
    private JFormattedTextField txtMulta, txtPlano;
    private JDateChooser dataInicio = new JDateChooser();
    private JTextField txtMeses = criarVisor(), txtBase = criarVisor(), txtTotal = criarVisor();
    private JButton btnCalcular = new JButton("Calcular"), btnLimpar = new JButton("Limpar"), btnVoltar = new JButton("Voltar");

    public MultaView() {
        carregarIcone();
        setTitle("DIRECTNET - Multa");
        setSize(500, 600);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblHeader = new JLabel("DIRECTNET", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeader.setForeground(new Color(204, 0, 0));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(lblHeader, BorderLayout.NORTH);

        JPanel central = new JPanel();
        central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));
        central.setBackground(Color.WHITE);

        ButtonGroup bg = new ButtonGroup(); bg.add(rbMulta); bg.add(rbPlano);
        rbPlano.setSelected(true);

        txtMulta = criarMonetario(); txtPlano = criarMonetario();
        Dimension dimC = new Dimension(160, 30);
        txtMulta.setPreferredSize(dimC);
        txtPlano.setPreferredSize(dimC);
        dataInicio.setPreferredSize(dimC);

        central.add(criarLinhaRadio(rbPlano, txtPlano));
        central.add(criarLinhaRadio(rbMulta, txtMulta));
        central.add(criarLinha("Início Fidelidade:", dataInicio));

        central.add(Box.createVerticalStrut(20));

        JPanel res = new JPanel(new GridLayout(3, 1, 5, 12));
        res.setBackground(Color.WHITE);
        res.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "Rescisão", 0, 0, null, new Color(204, 0, 0)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        res.add(criarLinha("Meses Restantes:", txtMeses));
        res.add(criarLinha("Base Mensal:", txtBase));
        res.add(criarLinha("Total Multa:", txtTotal));
        
        txtTotal.setForeground(new Color(204, 0, 0));
        txtTotal.setFont(new Font("Arial", Font.BOLD, 15));
        central.add(res);

        add(central, BorderLayout.CENTER);
        add(criarBotoes(), BorderLayout.SOUTH);

        rbMulta.addActionListener(e -> alternar(true));
        rbPlano.addActionListener(e -> alternar(false));
        
        limparTudo();
        setLocationRelativeTo(null);
    }

    private void alternar(boolean m) { 
        txtMulta.setEnabled(m); 
        txtPlano.setEnabled(!m); 
    }

    private JFormattedTextField criarMonetario() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        NumberFormatter nfr = new NumberFormatter(nf);
        nfr.setValueClass(Double.class);
        return new JFormattedTextField(new DefaultFormatterFactory(nfr));
    }

    private JTextField criarVisor() {
        JTextField tf = new JTextField(12);
        tf.setEditable(false);
        tf.setHorizontalAlignment(JTextField.RIGHT);
        tf.setBackground(Color.WHITE);
        return tf;
    }

    private JPanel criarLinha(String t, Component c) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        p.setBackground(Color.WHITE); 
        p.add(new JLabel(t)); 
        p.add(c);
        return p;
    }

    private JPanel criarLinhaRadio(JRadioButton r, Component c) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        p.setBackground(Color.WHITE); r.setBackground(Color.WHITE); 
        p.add(r); p.add(c);
        return p;
    }

    private JPanel criarBotoes() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        p.setBackground(Color.WHITE);
        Dimension dimB = new Dimension(110, 35);
        btnCalcular.setPreferredSize(dimB);
        btnLimpar.setPreferredSize(dimB);
        btnVoltar.setPreferredSize(dimB);
        p.add(btnCalcular); p.add(btnLimpar); p.add(btnVoltar);
        return p;
    }

    public void limparTudo() {
        rbPlano.setSelected(true); alternar(false);
        txtPlano.setValue(0.0); txtMulta.setValue(0.0);
        dataInicio.setDate(new Date());
        txtMeses.setText("0"); txtBase.setText("0,00"); txtTotal.setText("0,00");
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

    public JButton getBtnCalcular() { return btnCalcular; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnVoltar() { return btnVoltar; }
    public boolean isPlano() { return rbPlano.isSelected(); }
    public double getValPlano() { return ((Number)txtPlano.getValue()).doubleValue(); }
    public double getValMulta() { return ((Number)txtMulta.getValue()).doubleValue(); }
    public Date getDataIni() { return dataInicio.getDate(); }
    public void setRes(String m, String b, String t) { txtMeses.setText(m); txtBase.setText(b); txtTotal.setText(t); }
}
