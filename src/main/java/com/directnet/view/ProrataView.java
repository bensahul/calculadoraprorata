package com.directnet.view;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.text.NumberFormat;
import java.util.Date;

/**
 * @author Gleydson
 */
public class ProrataView extends JFrame {
    private JDateChooser dataInicio = new JDateChooser();
    private JDateChooser dataFim = new JDateChooser();
    private JFormattedTextField txtValorPlano, txtDesconto;
    private JTextField txtDiasCorridos = criarVisor(), txtValorProp = criarVisor(), 
                       txtDescProp = criarVisor(), txtTotal = criarVisor();
    private JTextArea areaReferencia = new JTextArea(3, 20);
    private JButton btnCalcular = new JButton("Calcular"), btnLimpar = new JButton("Limpar"), 
                    btnVoltar = new JButton("Voltar"), btnCopiar = new JButton("Copiar");

    public ProrataView() {
        setTitle("DIRECTNET - Pro rata");
        setSize(500, 600); // Aumentado levemente para acomodar margens
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Margem de respiro de 20px em todos os lados
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblHeader = new JLabel("DIRECTNET", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeader.setForeground(new Color(204, 0, 0));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(lblHeader, BorderLayout.NORTH);

        JPanel central = new JPanel();
        central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));
        central.setBackground(Color.WHITE);

        configurarInputs();
        
        // Aumentando tamanho dos campos para 30px de altura
        Dimension dimCampo = new Dimension(160, 30);
        txtValorPlano.setPreferredSize(dimCampo);
        txtDesconto.setPreferredSize(dimCampo);
        dataInicio.setPreferredSize(dimCampo);
        dataFim.setPreferredSize(dimCampo);

        central.add(criarLinha("Valor Plano (BRL):", txtValorPlano));
        central.add(criarLinha("Desconto (BRL):", txtDesconto));
        central.add(criarLinha("Data Inicial:", dataInicio));
        central.add(criarLinha("Data Final:", dataFim));
        
        central.add(Box.createVerticalStrut(15));

        JPanel pResult = new JPanel(new GridLayout(4, 1, 5, 8));
        pResult.setBackground(Color.WHITE);
        pResult.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "Cálculo", 0, 0, null, new Color(204, 0, 0)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        pResult.add(criarLinha("Dias:", txtDiasCorridos));
        pResult.add(criarLinha("Valor Prop:", txtValorProp));
        pResult.add(criarLinha("Desc Prop:", txtDescProp));
        pResult.add(criarLinha("Total a Pagar:", txtTotal));
        central.add(pResult);

        central.add(Box.createVerticalStrut(15));

        areaReferencia.setEditable(false);
        areaReferencia.setLineWrap(true);
        areaReferencia.setBackground(new Color(245, 245, 245));
        JPanel pRef = new JPanel(new BorderLayout(5, 0));
        pRef.setBackground(Color.WHITE);
        pRef.add(new JScrollPane(areaReferencia), BorderLayout.CENTER);
        pRef.add(btnCopiar, BorderLayout.EAST);
        central.add(pRef);

        add(central, BorderLayout.CENTER);
        add(criarBotoes(), BorderLayout.SOUTH);
        
        limparTudo();
        setLocationRelativeTo(null);
    }

    private void configurarInputs() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        NumberFormatter nfr = new NumberFormatter(nf);
        nfr.setValueClass(Double.class);
        txtValorPlano = new JFormattedTextField(new DefaultFormatterFactory(nfr));
        txtDesconto = new JFormattedTextField(new DefaultFormatterFactory(nfr));
    }

    private JTextField criarVisor() {
        JTextField tf = new JTextField(12);
        tf.setEditable(false);
        tf.setHorizontalAlignment(JTextField.RIGHT);
        tf.setBackground(Color.WHITE);
        tf.setPreferredSize(new Dimension(120, 25));
        return tf;
    }

    private JPanel criarLinha(String t, Component c) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        p.setBackground(Color.WHITE);
        p.add(new JLabel(t));
        p.add(c);
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
        txtValorPlano.setValue(0.0); txtDesconto.setValue(0.0);
        dataInicio.setDate(new Date()); dataFim.setDate(new Date());
        txtDiasCorridos.setText("0"); txtValorProp.setText("0,00");
        txtDescProp.setText("0,00"); txtTotal.setText("0,00");
        areaReferencia.setText("");
    }

    public void copiarReferencia() {
        String texto = areaReferencia.getText();
        if(!texto.isEmpty()){
            StringSelection sel = new StringSelection(texto);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
            JOptionPane.showMessageDialog(this, "Copiado!");
        }
    }

    public JButton getBtnCalcular() { return btnCalcular; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnVoltar() { return btnVoltar; }
    public JButton getBtnCopiar() { return btnCopiar; }
    public double getValorPlano() { return ((Number)txtValorPlano.getValue()).doubleValue(); }
    public double getValorDesconto() { return ((Number)txtDesconto.getValue()).doubleValue(); }
    public Date getDataIni() { return dataInicio.getDate(); }
    public Date getDataFim() { return dataFim.getDate(); }
    public void setResultados(String d, String v, String ds, String t, String ref) {
        txtDiasCorridos.setText(d); txtValorProp.setText(v);
        txtDescProp.setText(ds); txtTotal.setText(t);
        areaReferencia.setText(ref);
    }
}
