package dev.bene.gui;

import dev.bene.Converter;
import dev.bene.History;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GUIHistory {
    private JFrame frame;
    private JLabel jLabelTitle;
    private JButton jButtonExport;
    private JTable jTableHistory;
    private DefaultTableModel tableModel;
    private final History history;

    public GUIHistory() {
        history = new History();

        frame = new JFrame("History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setContentPane(new JPanel() {{setBorder(new EmptyBorder(30, 45, 30, 45));}});

        buildElements();

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(jLabelTitle, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(jTableHistory), BorderLayout.CENTER);
        frame.getContentPane().add(jButtonExport, BorderLayout.SOUTH);

        loadHistory();
    }

    private void buildElements() {
        jLabelTitle = new JLabel("Conversion History");
        jLabelTitle.setPreferredSize(new Dimension(100, 50));
        jLabelTitle.setFont(new Font("Arial", Font.BOLD, 30));

        jButtonExport = new JButton("Export");
        jButtonExport.addActionListener(e -> history.exportCSV());

        jTableHistory = new JTable();
        jTableHistory.setDefaultEditor(Object.class, null);
        tableModel = (DefaultTableModel) jTableHistory.getModel();
        tableModel.setColumnIdentifiers(new String[]{"Input", "Output", "From", "To", "Formula", "Unit"});
    }

    public void loadHistory() {
        List<Converter> historyList = history.getHistory();
        if (historyList != null) {
            for (Converter converter : historyList) {
                tableModel.addRow(new Object[]{
                        converter.getInput(),
                        converter.getOutput(),
                        converter.getFrom(),
                        converter.getTo(),
                        converter.getFormula(),
                        converter.getUnit()
                });
            }
        }
    }
}
