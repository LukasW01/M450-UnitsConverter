package dev.bene;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GUIHistory {
    private JFrame frame;
    private JLabel jlabelTitle;
    private JButton jButtonExport;
    private JTable jTableHistory;
    private DefaultTableModel tableModel;
    private History history;

    public GUIHistory() {
        history = new History();
        history.importCSV();

        frame = new JFrame("History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setContentPane(new JPanel() {{setBorder(new EmptyBorder(30, 45, 30, 45));}});

        buildElements();

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(jlabelTitle, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(jTableHistory), BorderLayout.CENTER);
        frame.getContentPane().add(jButtonExport, BorderLayout.SOUTH);

        loadHistory();
    }

    private void buildElements() {
        jlabelTitle = new JLabel("Conversion History");
        jlabelTitle.setPreferredSize(new Dimension(100, 50));
        jlabelTitle.setFont(new Font("Arial", Font.BOLD, 30));

        jButtonExport = new JButton("Export");
        jButtonExport.addActionListener(e -> history.exportCSV());

        jTableHistory = new JTable();
        jTableHistory.setDefaultEditor(Object.class, null);
        tableModel = (DefaultTableModel) jTableHistory.getModel();
        tableModel.setColumnIdentifiers(new String[]{"Input", "Output", "From", "To", "Formula"});
    }

    public void loadHistory() {
        List<Converter> historyList = history.getHistory();
        if (historyList != null) {
            for (Converter converter : historyList) {
                tableModel.addRow(new Object[]{converter.getInput(), converter.getOutput(), converter.getFrom(), converter.getTo(), converter.getFormula()});
            }
        }
    }
}
