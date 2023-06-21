package dev.bene;

import dev.bene.model.Units;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

public class GUI {

    private JFrame frame;
    private JPanel panelTop;
    private JPanel panelCenter;
    private JPanel panelBottom;
    private JButton buttonHistory;
    private JButton buttonConvert;
    private JComboBox<String> comboBoxUnit;
    private JComboBox<String> comboBoxFrom;
    private JComboBox<String> comboBoxTo;
    private JTextField textFieldInput;
    private JTextField textFieldOutput;
    private JTextField textFieldFormula;
    private GridBagConstraints gbc;
    private CheckInput documentFilter;

    public GUI() {
        frame = new JFrame("Unit Converter");
        frame.setVisible(true);
        frame.setSize(900, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new JPanel() {{setBorder(new EmptyBorder(30, 45, 30, 45));}});
        frame.setLayout(new BorderLayout(25, 10));

        panelTop = new JPanel();
        panelCenter = new JPanel();
        panelBottom = new JPanel();

        panelTop.setLayout(new GridLayout(1, 1));
        panelBottom.setLayout(new BorderLayout(0, 10));
        panelCenter.setLayout(new GridBagLayout());

        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.SOUTH);

        buildElements();

        // Load Units into ComboBox via action listener
        for (Units unit : Units.values()) {
            comboBoxUnit.addItem(unit.toString());
            for (Enum<?> enumItem : Objects.requireNonNull(unit.getUnitsByType(Objects.requireNonNull(comboBoxUnit.getSelectedItem()).toString()))) {
                comboBoxFrom.addItem(enumItem.toString());
                comboBoxTo.addItem(enumItem.toString());
            }

            comboBoxUnit.addActionListener(e -> {
                comboBoxFrom.removeAllItems();
                comboBoxTo.removeAllItems();

                for (Enum<?> enumItem : Objects.requireNonNull(unit.getUnitsByType(Objects.requireNonNull(comboBoxUnit.getSelectedItem()).toString()))) {
                    comboBoxFrom.addItem(enumItem.toString());
                    comboBoxTo.addItem(enumItem.toString());
                }
            });
        }

        //action listener for buttonHistory
        buttonHistory.addActionListener(e -> {
            GUIHistory guiHistory = new GUIHistory();
        });

        //action listener for buttonConvert
        buttonConvert.addActionListener(e -> {
            if (textFieldInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Input is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Converter converter = new Converter();
            converter.convertValue(Double.parseDouble(textFieldInput.getText()), Objects.requireNonNull(comboBoxFrom.getSelectedItem()).toString(), Objects.requireNonNull(comboBoxTo.getSelectedItem()).toString());
            textFieldOutput.setText(String.valueOf(converter.getOutput()));
            textFieldFormula.setText(converter.getFormula());
        });
    }

    public void buildElements() {
        // Top Panel
        panelTop.add(createLabel("Unit Converter", 38));

        // Center Panel
        comboBoxUnit = new JComboBox<>();
        comboBoxFrom = new JComboBox<>();
        comboBoxTo = new JComboBox<>();
        textFieldInput = new JTextField();
        textFieldOutput = new JTextField();
        textFieldFormula = new JTextField();

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(6, 6, 6, 6);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panelCenter.add(createLabel("Choose a Unit", 16), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        panelCenter.add(comboBoxUnit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.gridwidth = 1;
        panelCenter.add(createLabel("Input", 16), gbc);

        gbc.gridx = 1;
        panelCenter.add(createLabel("From", 16), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        documentFilter = new CheckInput();
        ((AbstractDocument) textFieldInput.getDocument()).setDocumentFilter(documentFilter);
        panelCenter.add(textFieldInput, gbc);

        gbc.gridx = 1;
        panelCenter.add(comboBoxFrom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelCenter.add(createLabel("Output", 16), gbc);

        gbc.gridx = 1;
        panelCenter.add(createLabel("To", 16), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelCenter.add(textFieldOutput, gbc);
        textFieldOutput.setEditable(false);

        gbc.gridx = 1;
        panelCenter.add(comboBoxTo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelCenter.add(createLabel("Formula", 16), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelCenter.add(textFieldFormula, gbc);
        textFieldFormula.setEditable(false);

        // Bottom panel
        buttonConvert = new JButton("Convert");
        buttonHistory = new JButton("History");
        panelBottom.add(buttonConvert, BorderLayout.NORTH);
        panelBottom.add(buttonHistory, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        return label;
    }

}
