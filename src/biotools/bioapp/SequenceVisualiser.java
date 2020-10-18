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

public class SequenceVisualiser extends JFrame implements ActionListener {
    private JButton browseButton, openButton, visualise;
    private JFileChooser fileChooser;
    private JTextField nameField;
    public JTextArea textArea;
    private BufferedReader inFile;

    public SequenceVisualiser() {
        this.setSize(600, 500);
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

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(600, 20));
        window.add(nameField);

        textArea = new JTextArea();
        textArea.setFont(new Font("FreeMono", Font.BOLD, 13));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(600, 400));

        window.add(textArea);
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

    public void actionPerformed(ActionEvent event) {
        File selectedFile;
        int reply;
        if (event.getSource() == visualise) {
            DNA dna = new DNA("ATCG");
            System.out.println(dna.getGcPerc());
            System.out.println(dna.getSeq());
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
    }
}
