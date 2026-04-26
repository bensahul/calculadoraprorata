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
        setSize(500, 550);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel lblHeader = new JLabel("DIRECTNET", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        lblHeader.setForeground(new Color(204, 0, 0));
        add(lblHeader, BorderLayout.NORTH);

        JPanel central = new JPanel();
        central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));
        central.setBackground(Color.WHITE);

        configurarInputs();
        central.add(criarLinha("Valor Plano (BRL):", txtValorPlano));
        central.add(criarLinha("Desconto (BRL):", txtDesconto));
        central.add(criarLinha("Data Inicial:", dataInicio));
        central.add(criarLinha("Data Final:", dataFim));
        
        JPanel pResult = new JPanel(new GridLayout(4, 1, 2, 2));
        pResult.setBackground(Color.WHITE);
        pResult.setBorder(BorderFactory.createTitledBorder(null, "Cálculo", 0, 0, null, new Color(204, 0, 0)));
        pResult.add(criarLinha("Dias:", txtDiasCorridos));
        pResult.add(criarLinha("Valor Prop:", txtValorProp));
        pResult.add(criarLinha("Desc Prop:", txtDescProp));
        pResult.add(criarLinha("Total:", txtTotal));
        central.add(pResult);

        areaReferencia.setEditable(false);
        areaReferencia.setBackground(new Color(245, 245, 245));
        JPanel pRef = new JPanel(new BorderLayout());
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
        DefaultFormatterFactory dff = new DefaultFormatterFactory(nfr);
        txtValorPlano = new JFormattedTextField(dff);
        txtDesconto = new JFormattedTextField(dff);
        txtValorPlano.setColumns(10);
        txtDesconto.setColumns(10);
    }

    public void copiarReferencia() {
        String texto = areaReferencia.getText();
        if (!texto.isEmpty()) {
            java.awt.datatransfer.StringSelection selection = new java.awt.datatransfer.StringSelection(texto);
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            JOptionPane.showMessageDialog(this, "Texto copiado para a área de transferência!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Não há texto para copiar. Realize o cálculo primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }


    private JTextField criarVisor() {
        JTextField tf = new JTextField(10);
        tf.setEditable(false);
        tf.setHorizontalAlignment(JTextField.RIGHT);
        return tf;
    }

    private JPanel criarLinha(String t, Component c) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p.setBackground(Color.WHITE);
        p.add(new JLabel(t));
        p.add(c);
        return p;
    }

    private JPanel criarBotoes() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
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

    // Getters omitidos para brevidade, devem ser incluídos conforme lógica anterior
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
