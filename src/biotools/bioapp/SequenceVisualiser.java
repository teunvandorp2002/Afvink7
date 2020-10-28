package biotools.bioapp;

import biotools.bioseq.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SequenceVisualiser extends JFrame implements ActionListener {
    private JButton browseButton, openButton, visualise, clear;
    private JFileChooser fileChooser;
    private JTextField nameField;
    public JTextArea textArea;
    public JPanel panel;
    private BufferedReader inFile;
    private DNA dna;
    private RNA rna;
    private Protein protein;

    public SequenceVisualiser() {
        this.setSize(600, 550);
        this.createGUI();
        this.setVisible(true);
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        browseButton = new JButton("Browse");
        window.add(browseButton);
        browseButton.addActionListener(this);

        openButton = new JButton("Open");
        window.add(openButton);
        openButton.addActionListener(this);

        visualise = new JButton("Visualise");
        window.add(visualise);
        visualise.addActionListener(this);

        clear = new JButton("Clear");
        window.add(clear);
        clear.addActionListener(this);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(600, 20));
        window.add(nameField);

        textArea = new JTextArea();
        textArea.setFont(new Font("FreeMono", Font.BOLD, 13));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(600, 400));
        window.add(textArea);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 20));

        window.add(panel);
    }

    public void readFile() {
        try {
            inFile = new BufferedReader(new FileReader(nameField.getText()));
            textArea.setText("");
            String line;
            while ((line = inFile.readLine()) != null) {
                if (!line.contains(">")) {
                    textArea.append(line.toUpperCase());
                }
            }
            inFile.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "File Error: " + e.toString());
        }
    }

    private void isDNA() {
        dna = new DNA(textArea.getText());
        visualiser();
    }
    private void isRNA() {
        rna = new RNA(textArea.getText());
        visualiser();
    }
    private void isProtein() {
        protein = new Protein(textArea.getText());
        visualiser();
    }


    public void visualiser() {
        Graphics graphics = panel.getGraphics();
        graphics.clearRect(0, 0, 600, 20);
        Color[] colorArray;
        float length;
        if (dna != null) {
            length = (float) dna.getLength();
            dna.setColor();
            colorArray = dna.getColor();
            System.out.println(Arrays.toString(dna.getColor()));
        } else if (rna != null) {
            length = (float) rna.getLength();
            rna.setColor();
            colorArray = rna.getColor();
            System.out.println(Arrays.toString(rna.getColor()));
        } else {
            length = (float) protein.getLength();
            protein.setColor();
            colorArray = protein.getColor();
            System.out.println(Arrays.toString(protein.getColor()));
        }
        System.out.println(Arrays.toString(colorArray));
        int position = 0;
        for (int i = 0; i < length; i++) {
            int width = Math.round((i + 1) / length * 600);
            graphics.setColor(colorArray[i]);
            graphics.fillRect(position, 0, width, 20);
            position += width;
        }
    }


    public void actionPerformed(ActionEvent event) {
        File selectedFile;
        int reply;
        if (event.getSource() == visualise) {
            if (textArea.getText().matches("^[ATCG]*$")){
                isDNA();
            } else if (textArea.getText().matches("^[AUCG]*$")){
                isRNA();
            } else if (textArea.getText().matches("^[ARNDCQGEHILKMFPSTWYV]*$")){
                isProtein();
            } else {
                System.out.println("NO!");
                try {
                    throw new NoValidSequence();
                } catch (NoValidSequence noValidSequence) {
                    JOptionPane.showMessageDialog(this, noValidSequence.toString(), "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (event.getSource() == browseButton) {
            fileChooser = new JFileChooser();
            reply = fileChooser.showOpenDialog(this);
            if (reply == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                nameField.setText(selectedFile.getAbsolutePath());
                readFile();
            }
        }
        if (event.getSource() == openButton) {
            readFile();
        }
        if (event.getSource() == clear) {
            Graphics g = panel.getGraphics();
            g.clearRect(0, 0, 600, 20);
            textArea.setText("");
        }
    }
}

class NoValidSequence extends Exception {
    public NoValidSequence() {
        super("Input isn't a DNA, RNA or poly peptide sequence");
    }
}
